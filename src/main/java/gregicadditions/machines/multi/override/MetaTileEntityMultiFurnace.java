package gregicadditions.machines.multi.override;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAHeatingCoil;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.InventoryUtils;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

import static gregtech.api.render.Textures.HEAT_PROOF_CASING;

public class MetaTileEntityMultiFurnace extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    protected int heatingCoilLevel;
    protected int heatingCoilDiscount;

    public MetaTileEntityMultiFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.FURNACE_RECIPES);
        this.recipeMapWorkable = new GAMultiFurnaceWorkable(this);
    }

    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityMultiFurnace(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "XXX")
                .aisle("XXX", "C#C", "XXX")
                .aisle("XSX", "CCC", "XXX")
                .setAmountAtLeast('L', 8)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', heatingCoilPredicate().or(heatingCoilPredicate2()))
                .where('#', isAirPredicate())
                .build();
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof BlockWireCoil))
                return false;
            BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
            BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
            if (Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName()))
                return false;
            int heatingCoilDiscount = coilType.getEnergyDiscount();
            int currentCoilDiscount = blockWorldState.getMatchContext().getOrPut("heatingCoilDiscount", heatingCoilDiscount);
            int heatingCoilLevel = coilType.getLevel();
            int currentCoilLevel = blockWorldState.getMatchContext().getOrPut("heatingCoilLevel", heatingCoilLevel);
            return currentCoilDiscount == heatingCoilDiscount && heatingCoilLevel == currentCoilLevel;
        };
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate2() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAHeatingCoil))
                return false;
            GAHeatingCoil blockWireCoil = (GAHeatingCoil) blockState.getBlock();
            GAHeatingCoil.CoilType coilType = blockWireCoil.getState(blockState);
            if (Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName()))
                return false;
            int heatingCoilDiscount = coilType.getEnergyDiscount();
            int currentCoilDiscount = blockWorldState.getMatchContext().getOrPut("heatingCoilDiscount", heatingCoilDiscount);
            int heatingCoilLevel = coilType.getLevel();
            int currentCoilLevel = blockWorldState.getMatchContext().getOrPut("heatingCoilLevel", heatingCoilLevel);
            return currentCoilDiscount == heatingCoilDiscount && heatingCoilLevel == currentCoilLevel;
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        BlockWireCoil.CoilType coilType = context.getOrDefault("CoilType", BlockWireCoil.CoilType.CUPRONICKEL);
        this.heatingCoilLevel = coilType.getLevel();
        this.heatingCoilDiscount = coilType.getEnergyDiscount();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.heatingCoilLevel = 0;
        this.heatingCoilDiscount = 0;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return HEAT_PROOF_CASING;
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed() && !hasProblems()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.multi_furnace.heating_coil_level", heatingCoilLevel));
            textList.add(new TextComponentTranslation("gregtech.multiblock.multi_furnace.heating_coil_discount", heatingCoilDiscount));
        }
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.MULTI_FURNACE_OVERLAY;
    }

    protected class GAMultiFurnaceWorkable extends GAMultiblockRecipeLogic {

        public GAMultiFurnaceWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected void trySearchNewRecipe() {
            long maxVoltage = getMaxVoltage();
            Recipe currentRecipe = null;
            IItemHandlerModifiable importInventory = getInputInventory();
            IMultipleTankHandler importFluids = getInputTank();
            boolean dirty = checkRecipeInputsDirty(importInventory, importFluids);
            //inverse of logic in normal AbstractRecipeLogic
            //for MultiSmelter, we can reuse previous recipe if inputs didn't change
            //otherwise, we need to recompute it for new ingredients
            //but technically, it means we can cache multi smelter recipe, but changing inputs have more priority
            if(dirty || forceRecipeRecheck) {
                this.forceRecipeRecheck = false;
                //else, try searching new recipe for given inputs
                currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
                if (currentRecipe != null) {
                    this.previousRecipe = currentRecipe;
                }
            } else if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
                //if previous recipe still matches inputs, try to use it
                currentRecipe = previousRecipe;
            }
            if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
                setupRecipe(currentRecipe);
            }
        }

        @Override
        protected Recipe findRecipe(long maxVoltage,
                                    IItemHandlerModifiable inputs,
                                    IMultipleTankHandler fluidInputs)
        {
            int currentItemsEngaged = 0;
            final int maxItemsLimit = 32 * heatingCoilLevel;
            final ArrayList<CountableIngredient> recipeInputs = new ArrayList<>();
            final ArrayList<ItemStack> recipeOutputs = new ArrayList<>();

            /* Iterate over the input items looking for more things to add until we run either out of input items
             * or we have exceeded the number of items permissible from the smelting bonus
             */
            for(int index = 0; index < inputs.getSlots() && currentItemsEngaged < maxItemsLimit; index++) {

                // Skip this slot if it is empty.
                final ItemStack currentInputItem = inputs.getStackInSlot(index);
                if(currentInputItem.isEmpty())
                    continue;

                // Determine if there is a valid recipe for this item. If not, skip it.
                Recipe matchingRecipe = recipeMap.findRecipe(maxVoltage,
                        Collections.singletonList(currentInputItem),
                        Collections.emptyList(), 0);
                CountableIngredient inputIngredient;
                if(matchingRecipe != null)
                    inputIngredient = matchingRecipe.getInputs().get(0);
                else
                    continue;

                // There's something not right with this recipe if the ingredient is null.
                if(inputIngredient == null)
                    throw new IllegalStateException(
                            String.format("Got recipe with null ingredient %s", matchingRecipe));

                // If there are enough slots left to smelt this item stack
                int itemsLeftUntilMax = (maxItemsLimit - currentItemsEngaged);
                if(itemsLeftUntilMax >= inputIngredient.getCount()) {

                    /* Choose the lesser of the number of possible crafts in this ingredient's stack, or the number of
                     * items remaining to reach the coil bonus's max smelted items.
                     */
                    int craftsPossible = currentInputItem.getCount() / inputIngredient.getCount();
                    int craftsUntilMax = itemsLeftUntilMax / inputIngredient.getCount();
                    int recipeMultiplier = Math.min(craftsPossible, craftsUntilMax);

                    // copy the outputs list so we don't mutate it yet
                    ArrayList<ItemStack> temp = new ArrayList<>(recipeOutputs);

                    // Process the stacks to see how many items this makes
                    computeOutputItemStacks(temp, matchingRecipe.getOutputs().get(0), recipeMultiplier);

                    // determine if there is enough room in the output to fit all of this
                    boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(temp, this.getOutputInventory());

                    // if there isn't, we can't process this recipe.
                    if(!canFitOutputs)
                        break;

                    // otherwise, let's add the new output items and keep going
                    temp.removeAll(recipeOutputs);
                    recipeOutputs.addAll(temp);

                    // Add the ingredients to the list of things to smelt.
                    recipeInputs.add(new CountableIngredient(inputIngredient.getIngredient(),
                            inputIngredient.getCount() * recipeMultiplier));

                    currentItemsEngaged += inputIngredient.getCount() * recipeMultiplier;
                }
            }

            // If there were no accepted ingredients, then there is no recipe to process.
            if(recipeInputs.isEmpty()) {
                //Set here to prevent recipe deadlock on world load with full output bus
                forceRecipeRecheck = true;
                return null;
            }

            return recipeMap.recipeBuilder()
                    .inputsIngredients(recipeInputs)
                    .outputs(recipeOutputs)
                    .EUt(Math.max(1, 16 / heatingCoilDiscount))
                    .duration((int) Math.max(1.0, 256 * (currentItemsEngaged / (maxItemsLimit * 1.0))))
                    .build().getResult();
        }

        /**
         * Computes the minimal number of ItemStacks necessary to store a multiplied recipe output, then
         * generates the stacks. The result is then stored in {@code recipeOutputs}.
         *
         * @param recipeOutputs   a collection of outputs to store the resulting output ItemStacks
         * @param outputStack     an ItemStack representing the output item of a recipe
         * @param overclockAmount the number of times that {@code outputStack}'s quantity should
         *                        be multiplied by for the desired total
         */
        private void computeOutputItemStacks(Collection<ItemStack> recipeOutputs,
                                             ItemStack outputStack,
                                             int overclockAmount)
        {
            if(!outputStack.isEmpty()) {
                // number of output items we're generating
                int finalAmount = outputStack.getCount() * overclockAmount;

                // max items allowed in a stack
                int maxCount = outputStack.getMaxStackSize();

                // number of whole stacks of output this will make
                int numStacks = finalAmount / maxCount;

                // number of items left (partial stack)
                int remainder = finalAmount % maxCount;

                // Add full stacks of the output item
                for(int fullStacks = numStacks; fullStacks > 0; fullStacks--) {
                    ItemStack full = outputStack.copy();
                    full.setCount(maxCount);
                    recipeOutputs.add(full);
                }

                // if there is a partial stack, add it too
                if(remainder > 0) {
                    ItemStack partial = outputStack.copy();
                    partial.setCount(remainder);
                    recipeOutputs.add(partial);
                }
            }
        }

    }
}
