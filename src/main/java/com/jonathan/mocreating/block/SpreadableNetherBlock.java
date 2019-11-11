package com.jonathan.mocreating.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

public class SpreadableNetherBlock extends SnowyDirtBlock
{
	protected SpreadableNetherBlock(Properties properties)
	{
		super(properties);
	}
	
	private static boolean func_220257_b(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		BlockState blockstate = world.getBlockState(blockpos);
		if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else {
			int i = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
			return i > world.getMaxLightLevel();
		}
	}

	private static boolean func_220256_c(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		return func_220257_b(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
	        if (!func_220257_b(state, worldIn, pos)) {
	        	worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
	        } else {
	            if (worldIn.getLight(pos.up()) >= 9) {
	            	BlockState blockstate = this.getDefaultState();

	            	for(int i = 0; i < 4; ++i) {
	            		BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
	            		if (worldIn.getBlockState(blockpos).getBlock() == Blocks.NETHERRACK && func_220256_c(blockstate, worldIn, blockpos)) {
	            			worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
	            		}
	            	}
	            }

	        }
		}
	}
}
