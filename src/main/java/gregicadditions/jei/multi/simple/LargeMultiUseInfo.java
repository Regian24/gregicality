package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeMultiUse;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeMultiUseInfo extends MultiblockInfoPage {
	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.LARGE_MULTI_USE;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
			shapeInfo.add(MultiblockShapeInfo.builder()
					.aisle("XXX", "CMP", "XXX")
					.aisle("IXX", "p#s", "XXX")
					.aisle("OEH", "eSR", "XXX")
					.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.SOUTH)
					.where('S', GATileEntities.LARGE_MULTI_USE, EnumFacing.SOUTH)
					.where('X', TileEntityLargeMultiUse.casingState)
					.where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
					.where('#', Blocks.AIR.getDefaultState())
					.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
					.where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
					.where('P', GAMetaBlocks.PISTON_CASING.getDefaultState())
					.where('p', GAMetaBlocks.PUMP_CASING.getDefaultState())
					.where('C', GAMetaBlocks.CONVEYOR_CASING.getDefaultState())
					.where('s', GAMetaBlocks.SENSOR_CASING.getDefaultState())
					.where('e', GAMetaBlocks.EMITTER_CASING.getDefaultState())
					.where('R', GAMetaBlocks.ROBOT_ARM_CASING.getDefaultState())
					.build());


		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[]{"This Multiblock will be removed in the next update. Use the dedicated multiblocks for each machine instead."};
	}
}
