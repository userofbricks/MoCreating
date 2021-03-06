package com.jonathan.mocreating.block;


import java.util.Random;

import com.jonathan.mocreating.lists.BlockList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

public class SpreadableNetherMyceliumBlock extends SnowyDirtBlock
{
	protected SpreadableNetherMyceliumBlock(Properties properties)
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
			return i < world.getMaxLightLevel();
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
	        	if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_netherrack) 			{ worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState()); 			}
	        	else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_nether_quartz) 	{ worldIn.setBlockState(pos, Blocks.NETHER_QUARTZ_ORE.getDefaultState()); 	}
	        	else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_nether_wart) 		{ worldIn.setBlockState(pos, Blocks.NETHER_WART_BLOCK.getDefaultState()); 	}
	        	
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_acacia_planks) 	{ worldIn.setBlockState(pos, Blocks.ACACIA_PLANKS.getDefaultState()); 		}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_birch_planks)		{ worldIn.setBlockState(pos, Blocks.BIRCH_PLANKS.getDefaultState()); 		}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_dark_oak_planks)	{ worldIn.setBlockState(pos, Blocks.DARK_OAK_PLANKS.getDefaultState()); 	}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_jungle_planks) 	{ worldIn.setBlockState(pos, Blocks.JUNGLE_PLANKS.getDefaultState()); 		}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_oak_planks)		{ worldIn.setBlockState(pos, Blocks.OAK_PLANKS.getDefaultState()); 			}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_spruce_planks) 	{ worldIn.setBlockState(pos, Blocks.SPRUCE_PLANKS.getDefaultState()); 		}
        		
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_acacia_log) 		{ worldIn.setBlockState(pos, Blocks.ACACIA_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_birch_log) 		{ worldIn.setBlockState(pos, Blocks.BIRCH_LOG.getDefaultState()		.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_dark_oak_log) 		{ worldIn.setBlockState(pos, Blocks.DARK_OAK_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_jungle_log) 		{ worldIn.setBlockState(pos, Blocks.JUNGLE_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_oak_log) 			{ worldIn.setBlockState(pos, Blocks.OAK_LOG.getDefaultState()		.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_spruce_log) 		{ worldIn.setBlockState(pos, Blocks.SPRUCE_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_stripped_acacia_log) 	{ worldIn.setBlockState(pos, Blocks.STRIPPED_ACACIA_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_stripped_birch_log) 	{ worldIn.setBlockState(pos, Blocks.STRIPPED_BIRCH_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_stripped_dark_oak_log) { worldIn.setBlockState(pos, Blocks.STRIPPED_DARK_OAK_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_stripped_jungle_log) 	{ worldIn.setBlockState(pos, Blocks.STRIPPED_JUNGLE_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_stripped_oak_log) 		{ worldIn.setBlockState(pos, Blocks.STRIPPED_OAK_LOG.getDefaultState()		.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_stripped_spruce_log) 	{ worldIn.setBlockState(pos, Blocks.STRIPPED_SPRUCE_LOG.getDefaultState()	.with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
        		
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_sponge) 				{ worldIn.setBlockState(pos, Blocks.SPONGE.getDefaultState()); 		}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_wet_sponge) 			{ worldIn.setBlockState(pos, Blocks.WET_SPONGE.getDefaultState()); 	}
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_coarse_dirt) 			{ worldIn.setBlockState(pos, Blocks.COARSE_DIRT.getDefaultState()); }
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_dirt) 					{ worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState()); 		}
        		
        		else if (worldIn.getBlockState(pos).getBlock() == BlockList.mycelium_bone_block) 			{ worldIn.setBlockState(pos, Blocks.BONE_BLOCK.getDefaultState().with(RotatedPillarBlock.AXIS, worldIn.getBlockState(pos).get(RotatedPillarBlock.AXIS))); }
	        	
	        } else {
	            if (worldIn.getLight(pos.up()) >= 6) {
	            	BlockState netherrack = 	BlockList.mycelium_netherrack.getDefaultState();
	            	BlockState nether_quartz = 	BlockList.mycelium_nether_quartz.getDefaultState();
	            	BlockState nether_wart = 	BlockList.mycelium_nether_wart.getDefaultState();
	            	BlockState soulsand = 		BlockList.mycelium_withered_soulsand.getDefaultState();
	            	
	            	BlockState acacia_planks = 		BlockList.mycelium_acacia_planks.getDefaultState();
	            	BlockState birch_planks = 		BlockList.mycelium_birch_planks.getDefaultState();
	            	BlockState dark_oak_planks = 	BlockList.mycelium_dark_oak_planks.getDefaultState();
	            	BlockState jungle_planks = 		BlockList.mycelium_jungle_planks.getDefaultState();
	            	BlockState oak_planks = 		BlockList.mycelium_oak_planks.getDefaultState();
	            	BlockState spruce_planks = 		BlockList.mycelium_spruce_planks.getDefaultState();
	            	
	            	BlockState acacia_log_base = 	BlockList.mycelium_acacia_log.getDefaultState();
	            	BlockState birch_log_base = 	BlockList.mycelium_birch_log.getDefaultState();
	            	BlockState dark_oak_log_base = 	BlockList.mycelium_dark_oak_log.getDefaultState();
	            	BlockState jungle_log_base = 	BlockList.mycelium_jungle_log.getDefaultState();
	            	BlockState oak_log_base = 		BlockList.mycelium_oak_log.getDefaultState();
	            	BlockState spruce_log_base = 	BlockList.mycelium_spruce_log.getDefaultState();
	            	
	            	BlockState stripped_acacia_log_base = 	BlockList.mycelium_stripped_acacia_log.getDefaultState();
	            	BlockState stripped_birch_log_base = 	BlockList.mycelium_stripped_birch_log.getDefaultState();
	            	BlockState stripped_dark_oak_log_base = BlockList.mycelium_stripped_dark_oak_log.getDefaultState();
	            	BlockState stripped_jungle_log_base = 	BlockList.mycelium_stripped_jungle_log.getDefaultState();
	            	BlockState stripped_oak_log_base = 		BlockList.mycelium_stripped_oak_log.getDefaultState();
	            	BlockState stripped_spruce_log_base = 	BlockList.mycelium_stripped_spruce_log.getDefaultState();

	            	BlockState sponge = 			BlockList.mycelium_sponge.getDefaultState();
	            	BlockState wet_sponge = 		BlockList.mycelium_wet_sponge.getDefaultState();
	            	BlockState bone_block_base = 	BlockList.mycelium_bone_block.getDefaultState();
	            	BlockState coarse_dirt = 		BlockList.mycelium_coarse_dirt.getDefaultState();
	            	
	            	BlockState dirt = BlockList.mycelium_dirt.getDefaultState();

	            	for(int i = 0; i < 4; ++i) {
	            		BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
	            		BlockState blockstate = worldIn.getBlockState(blockpos);
	            		
	            		if (worldIn.getBlockState(blockpos).getBlock() == Blocks.NETHERRACK && func_220256_c(netherrack, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, netherrack);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.NETHER_QUARTZ_ORE && func_220256_c(nether_quartz, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, nether_quartz);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.NETHER_WART_BLOCK && func_220256_c(nether_wart, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, nether_wart);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.SOUL_SAND && func_220256_c(soulsand, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, soulsand);
	            		}
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.ACACIA_PLANKS && func_220256_c(acacia_planks, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, acacia_planks);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.BIRCH_PLANKS && func_220256_c(birch_planks, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, birch_planks);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DARK_OAK_PLANKS && func_220256_c(dark_oak_planks, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, dark_oak_planks);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.JUNGLE_PLANKS && func_220256_c(jungle_planks, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, jungle_planks);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.OAK_PLANKS && func_220256_c(oak_planks, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, oak_planks);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.SPRUCE_PLANKS && func_220256_c(spruce_planks, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, spruce_planks);
	            		}
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.ACACIA_LOG && func_220256_c(acacia_log_base, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, acacia_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.BIRCH_LOG && func_220256_c(birch_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, birch_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DARK_OAK_LOG && func_220256_c(dark_oak_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, dark_oak_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.JUNGLE_LOG && func_220256_c(jungle_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, jungle_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.OAK_LOG && func_220256_c(oak_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, oak_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.SPRUCE_LOG && func_220256_c(spruce_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, spruce_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STRIPPED_ACACIA_LOG && func_220256_c(stripped_acacia_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, stripped_acacia_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STRIPPED_BIRCH_LOG && func_220256_c(stripped_birch_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, stripped_birch_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STRIPPED_DARK_OAK_LOG && func_220256_c(stripped_dark_oak_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, stripped_dark_oak_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STRIPPED_JUNGLE_LOG && func_220256_c(stripped_jungle_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, stripped_jungle_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STRIPPED_OAK_LOG && func_220256_c(stripped_oak_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, stripped_oak_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STRIPPED_SPRUCE_LOG && func_220256_c(stripped_spruce_log_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, stripped_spruce_log_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.SPONGE && func_220256_c(sponge, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, sponge);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WET_SPONGE && func_220256_c(wet_sponge, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, wet_sponge);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.COARSE_DIRT && func_220256_c(coarse_dirt, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, coarse_dirt);
	            		}else if ((worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT || worldIn.getBlockState(blockpos).getBlock() == Blocks.MYCELIUM) && func_220256_c(dirt, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, dirt);
	            		}
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.BONE_BLOCK && func_220256_c(bone_block_base, worldIn, blockpos)) 
	            		{
		            			worldIn.setBlockState(blockpos, bone_block_base.with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)));
	            		}
	            	}
	            }

	        }
		}
	}
}
