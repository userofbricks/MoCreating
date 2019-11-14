package com.jonathan.mocreating.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class WitheredMycelium extends Mycelium
{

	public WitheredMycelium(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	private static boolean air_up(IWorldReader world, BlockPos pos)
	{
		BlockPos blockpos = pos.up();
		BlockState blockstate = world.getBlockState(blockpos);
		if (blockstate.getBlock() == Blocks.AIR) {
			return true;
		} else {
		return false;
		}
	}
	
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			if (!air_up(worldIn, pos) && worldIn.getBlockState(pos.up()).getBlock() != Blocks.POPPY) {
				return;
	        } else {
	        	if (worldIn.getLight(pos.up()) <= 7) {
	        		BlockState blockstate1 = Blocks.WITHER_ROSE.getDefaultState();
	        		int chance = random.nextInt(20);
	        		BlockPos blockpos = pos.up();
	        		if (chance == 0)
	        		{
	        			worldIn.setBlockState(blockpos, blockstate1);
	        		}
	        	}
	        }
			
		}
	}

}
