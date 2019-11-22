package com.jonathan.mocreating.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class DragonEggBlock extends FallingBlock
{
	protected static final VoxelShape SHAPE = VoxelShapes.or(
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D), 
			Block.makeCuboidShape(2.0D, 1.0D, 2.0D, 14.0D, 3.0D, 14.0D), 
			Block.makeCuboidShape(1.0D, 3.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
			Block.makeCuboidShape(2.0D, 8.0D, 2.0D, 14.0D, 11.0D, 14.0D), 
			Block.makeCuboidShape(3.0D, 11.0D, 3.0D, 13.0D, 13.0D, 13.0D), 
			Block.makeCuboidShape(5.0D, 13.0D, 5.0D, 11.0D, 15.0D, 11.0D), 
			Block.makeCuboidShape(6.0D, 15.0D, 6.0D, 10.0D, 16.0D, 10.0D)
			);
	
	public DragonEggBlock(Properties properties) {
		super(properties);
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
}
