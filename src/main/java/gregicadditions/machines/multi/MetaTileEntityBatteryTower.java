package gregicadditions.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.item.CellCasing;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

import static gregicadditions.GAMaterials.IncoloyMA956;
import static gregicadditions.GAMaterials.Talonite;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MetaTileEntityBatteryTower extends MultiblockWithDisplayBase implements IEnergyContainer {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.INPUT_ENERGY, MultiblockAbility.OUTPUT_ENERGY};


    private long energyStored;
    private long maxCapacity;
    private EnergyContainerList input;
    private EnergyContainerList output;
    private CellCasing.CellType cell;

    public MetaTileEntityBatteryTower(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setLong("EnergyStored", this.energyStored);
        return data;
    }

    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.energyStored = data.getLong("EnergyStored");
    }

    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.cell = context.getOrDefault("CellType", CellCasing.CellType.CELL_HV);
        int size = context.getOrDefault("nbCell", 0);

        long inputAboveTier = getAbilities(MultiblockAbility.INPUT_ENERGY).stream().map(iEnergyContainer -> GTUtility.getTierByVoltage(iEnergyContainer.getInputVoltage())).filter(aByte -> aByte > cell.getTier()).count();
        long outputAboveTier = getAbilities(MultiblockAbility.OUTPUT_ENERGY).stream().map(iEnergyContainer -> GTUtility.getTierByVoltage(iEnergyContainer.getOutputVoltage())).filter(aByte -> aByte > cell.getTier()).count();

        if (inputAboveTier > 0 || outputAboveTier > 0) {
            this.invalidateStructure();
            return;
        }

        input = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
        output = new EnergyContainerList(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
        maxCapacity = this.cell.getStorage() * size;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.battery_tower.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.battery_tower.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.battery_tower.tooltip.3"));

    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            long inputEnergyStore = input.getEnergyStored();
            long energyAddedFromInput = this.addEnergy(inputEnergyStore);
            input.changeEnergy(-energyAddedFromInput);
            this.changeEnergy(GTValues.V[cell.getTier()] * 10 / 100);
            long bankEnergyStore = this.getEnergyStored();
            long energyAddedFromBank = output.addEnergy(bankEnergyStore);
            this.changeEnergy(-energyAddedFromBank);
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("CCSCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                .aisle("XXXXX", "XRRRX", "XRRRX", "XRRRX", "XXXXX").setRepeatable(4, 16)
                .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('X', statePredicate(getGlassState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('R', cellPredicate())
                .build();
    }

    public static Predicate<BlockWorldState> cellPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof CellCasing)) {
                return false;
            } else {
                CellCasing blockWireCoil = (CellCasing) blockState.getBlock();
                CellCasing.CellType cellType = blockWireCoil.getState(blockState);
                CellCasing.CellType currentCoilType = blockWorldState.getMatchContext().getOrPut("CellType", cellType);
                if (currentCoilType.getName().equals(cellType.getName())) {
                    blockWorldState.getMatchContext().increment("nbCell", 1);
                } else {
                    blockWorldState.getMatchContext().set("nbCell", 0);
                }
                return currentCoilType.getName().equals(cellType.getName());
            }
        };
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.energy_store", getEnergyStored(), getEnergyCapacity()));
        }

        super.addDisplayText(textList);
    }

    public IBlockState getGlassState() {
        return GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS);
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Talonite);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Talonite);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityBatteryTower(metaTileEntityId);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(),
                isStructureFormed() && energyStored > 0);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
            return (T) this;
        }
        return null;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing enumFacing, long l, long l1) {
        return 0;
    }

    @Override
    public boolean inputsEnergy(EnumFacing enumFacing) {
        return false;
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeLong(energyStored);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.energyStored = buf.readLong();
    }

    public void setEnergyStored(long energyStored) {
        this.energyStored = energyStored;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(1, buf -> buf.writeLong(energyStored));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 1) {
            this.energyStored = buf.readLong();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    public long changeEnergy(long energyToAdd) {
        long oldEnergyStored = this.getEnergyStored();
        long newEnergyStored = this.maxCapacity - oldEnergyStored < energyToAdd ? this.maxCapacity : oldEnergyStored + energyToAdd;
        if (newEnergyStored < 0L) {
            newEnergyStored = 0L;
        }
        this.setEnergyStored(newEnergyStored);
        return newEnergyStored - oldEnergyStored;
    }

    @Override
    public long getEnergyStored() {
        return this.energyStored;
    }

    @Override
    public long getEnergyCapacity() {
        return this.maxCapacity;
    }

    @Override
    public long getInputAmperage() {
        return 0;
    }

    @Override
    public long getInputVoltage() {
        return 0;
    }
}
