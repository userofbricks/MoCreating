package com.jonathan.mocreating.block;


import java.util.Random;

import com.jonathan.mocreating.lists.BlockList;

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

public class SpreadableGrassBlock extends SnowyDirtBlock
{
	protected SpreadableGrassBlock(Properties properties)
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
	        	worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
	        } else {
	            if (worldIn.getLight(pos.up()) >= 6) {
	            	BlockState acacia_planks = BlockList.grass_acacia_planks.getDefaultState();
	            	BlockState birch_planks = BlockList.grass_birch_planks.getDefaultState();
	            	BlockState dark_oak_planks = BlockList.grass_dark_oak_planks.getDefaultState();
	            	BlockState jungle_planks = BlockList.grass_jungle_planks.getDefaultState();
	            	BlockState oak_planks = BlockList.grass_oak_planks.getDefaultState();
	            	BlockState spruce_planks = BlockList.grass_spruce_planks.getDefaultState();

	            	BlockState sponge = BlockList.grass_sponge.getDefaultState();
	            	BlockState wet_sponge = BlockList.grass_wet_sponge.getDefaultState();
	            	BlockState coarse_dirt = BlockList.grass_coarse_dirt.getDefaultState();
	            	BlockState stone = BlockList.grass_stone.getDefaultState();
	            	BlockState andesite = BlockList.grass_andesite.getDefaultState();
	            	BlockState diorite = BlockList.grass_diorite.getDefaultState();
	            	BlockState granite = BlockList.grass_granite.getDefaultState();
	            	BlockState gravel = BlockList.grass_gravel.getDefaultState();
	            	BlockState red_sand = BlockList.grass_red_sand.getDefaultState();
	            	BlockState sand = BlockList.grass_sand.getDefaultState();
	            	
	            	BlockState dirt = Blocks.GRASS_BLOCK.getDefaultState();

	            	for(int i = 0; i < 4; ++i) {
	            		BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
	            		
	            		if (worldIn.getBlockState(blockpos).getBlock() == Blocks.ACACIA_PLANKS && func_220256_c(acacia_planks, worldIn, blockpos)) 
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
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.SPONGE && func_220256_c(sponge, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, sponge);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WET_SPONGE && func_220256_c(wet_sponge, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, wet_sponge);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.COARSE_DIRT && func_220256_c(coarse_dirt, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, coarse_dirt);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.STONE && func_220256_c(stone, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, stone);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.ANDESITE && func_220256_c(andesite, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, andesite);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIORITE && func_220256_c(diorite, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, diorite);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.GRANITE && func_220256_c(granite, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, granite);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.GRAVEL && func_220256_c(gravel, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, gravel);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.RED_SAND && func_220256_c(red_sand, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, red_sand);
	            		}else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.SAND && func_220256_c(sand, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, sand);
	            		}
	            		
	            		else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_220256_c(dirt, worldIn, blockpos)) 
	            		{
	            			worldIn.setBlockState(blockpos, dirt);
	            		}
	            	}
	            }

	        }
		}
	}
}
