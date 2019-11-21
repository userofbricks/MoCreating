package com.jonathan.mocreating.block;

import java.util.Random;

import com.jonathan.mocreating.lists.BlockList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class Mushroom extends MushroomBlock 
{

	public Mushroom(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      BlockPos blockpos = pos.down();
	      BlockState blockstate = worldIn.getBlockState(blockpos);
	      Block block = blockstate.getBlock();
	      if (block != Blocks.MYCELIUM 
	    		  && block != Blocks.PODZOL 
	    		  && block != BlockList.mycelium_acacia_log 
	    		  && block != BlockList.mycelium_acacia_planks 
	    		  && block != BlockList.mycelium_birch_log 				
	    		  && block != BlockList.mycelium_birch_planks 		
	    		  && block != BlockList.mycelium_dark_oak_log 
	    		  && block != BlockList.mycelium_dark_oak_planks 		
	    		  && block != BlockList.mycelium_jungle_log 			
	    		  && block != BlockList.mycelium_jungle_planks 
	    		  && block != BlockList.mycelium_oak_log 				
	    		  && block != BlockList.mycelium_oak_planks 			
	    		  && block != BlockList.mycelium_spruce_log 
	    		  && block != BlockList.mycelium_spruce_planks 			
	    		  && block != BlockList.mycelium_stripped_acacia_log 	
	    		  && block != BlockList.mycelium_stripped_birch_log 
	    		  && block != BlockList.mycelium_stripped_dark_oak_log 	
	    		  && block != BlockList.mycelium_stripped_jungle_log 	
	    		  && block != BlockList.mycelium_stripped_oak_log 
	    		  && block != BlockList.mycelium_stripped_spruce_log 	
	    		  && block != BlockList.mycelium_sponge  				
	    		  && block != BlockList.mycelium_wet_sponge
	    		  && block != BlockList.mycelium_nether_quartz 			
	    		  && block != BlockList.mycelium_nether_wart 			
	    		  && block != BlockList.mycelium_netherrack 
	    		  && block != BlockList.mycelium_dirt 					
	    		  && block != BlockList.mycelium_coarse_dirt 			
	    		  && block != BlockList.mycelium_bone_block 
	    		  && block != BlockList.mycelium_withered_soulsand) {
	         return worldIn.getLightSubtracted(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
	      } else {
	         return true;
	      }
	   }
	
	@Override
	public boolean generateBigMushroom(IWorld worldIn, BlockPos pos, BlockState state, Random rand) {
	      worldIn.removeBlock(pos, false);
	      Feature<BigMushroomFeatureConfig> feature = null;
	      if (this == BlockList.mushroom_brown) {
	         feature = Feature.HUGE_BROWN_MUSHROOM;
	      } else if (this == BlockList.mushroom_red) {
	         feature = Feature.HUGE_RED_MUSHROOM;
	      }

	      if (feature != null && feature.place(worldIn, worldIn.getChunkProvider().getChunkGenerator(), rand, pos, new BigMushroomFeatureConfig(true))) {
	         return true;
	      } else {
	         worldIn.setBlockState(pos, state, 3);
	         return false;
	      }
	   }
}
