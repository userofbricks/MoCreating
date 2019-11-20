package com.jonathan.mocreating.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;

public class Grass extends SpreadableGrassBlock 
{

	public Grass(Properties properties) {
		super(properties);
	}

	   public boolean isSolid(BlockState state) {
	      return true;
	   }

	   /**
	    * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
	    * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
	    */
	   public BlockRenderLayer getRenderLayer() {
	      return BlockRenderLayer.CUTOUT_MIPPED;
	   }
		
		@Override
		public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
			// TODO Auto-generated method stub
			return true;
		}
}
