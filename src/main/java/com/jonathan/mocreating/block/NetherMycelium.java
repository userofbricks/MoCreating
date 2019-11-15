package com.jonathan.mocreating.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

public class NetherMycelium extends SpreadableNetherMyceliumBlock
{

	public NetherMycelium(Properties properties) {
		super(properties);
	}
	
	/**
	* Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
	* this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
	* of whether the block can receive random update ticks
	*/
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
	    if (rand.nextInt(10) == 0) {
	    	worldIn.addParticle(ParticleTypes.MYCELIUM, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
	    }

	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
