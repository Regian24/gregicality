package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeBrewery;
import gregicadditions.machines.multi.simple.TileEntityLargeElectromagnet;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeElectromagnetInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_ELECTROMAGNET;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "XAAAX", "XGXGX")
                .aisle("XXXXX", "XAFAX", "XGXGX")
                .aisle("XXXXX", "XAAAX", "XGXGX")
                .aisle("#XHX#", "#ISO#", "#XEX#")
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                .where('S', GATileEntities.LARGE_ELECTROMAGNET, EnumFacing.SOUTH)
                .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('X', TileEntityLargeElectromagnet.casingState)
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.SOUTH)
                .where('F', GAMetaBlocks.FIELD_GEN_CASING.getDefaultState())
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.NICKEL_GLASS));
        shapeInfo.add(builder.build());
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{};
    }
}
