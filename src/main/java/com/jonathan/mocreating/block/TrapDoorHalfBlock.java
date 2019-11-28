package com.jonathan.mocreating.block;

import javax.annotation.Nullable;

import com.jonathan.mocreating.state.properties.MoBlockstateProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TrapDoorHalfBlock extends Block
{
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
	public static final BooleanProperty POSITIVE = MoBlockstateProperties.POSITIVE;
	public static final BooleanProperty NEGATIVE = MoBlockstateProperties.NEGATIVE;
	
	protected static final VoxelShape EAST_WEST_OPEN_UP_P = Block.makeCuboidShape(0, 8, 0, 3, 16, 16);
	protected static final VoxelShape EAST_WEST_OPEN_UP_N = Block.makeCuboidShape(13, 8, 0, 16, 16, 16);
	protected static final VoxelShape EAST_WEST_OPEN_DOWN_P = Block.makeCuboidShape(0, 0, 0, 3, 8, 16);
	protected static final VoxelShape EAST_WEST_OPEN_DOWN_N = Block.makeCuboidShape(13, 0, 0, 16, 8, 16);
	protected static final VoxelShape EAST_WEST_OPEN_UP = VoxelShapes.or(EAST_WEST_OPEN_UP_P, EAST_WEST_OPEN_UP_N);
	protected static final VoxelShape EAST_WEST_OPEN_DOWN = VoxelShapes.or(EAST_WEST_OPEN_DOWN_P, EAST_WEST_OPEN_DOWN_N);
	
	protected static final VoxelShape NORTH_SOUTH_OPEN_UP_P = Block.makeCuboidShape(0, 8, 0, 16, 16, 3);
	protected static final VoxelShape NORTH_SOUTH_OPEN_UP_N = Block.makeCuboidShape(0, 8, 13, 16, 16, 16);
	protected static final VoxelShape NORTH_SOUTH_OPEN_DOWN_P = Block.makeCuboidShape(0, 0, 0, 16, 8, 3);
	protected static final VoxelShape NORTH_SOUTH_OPEN_DOWN_N = Block.makeCuboidShape(0, 0, 13, 16, 8, 16);
	protected static final VoxelShape NORTH_SOUTH_OPEN_UP = VoxelShapes.or(NORTH_SOUTH_OPEN_UP_P, NORTH_SOUTH_OPEN_UP_N);
	protected static final VoxelShape NORTH_SOUTH_OPEN_DOWN = VoxelShapes.or(NORTH_SOUTH_OPEN_DOWN_P, NORTH_SOUTH_OPEN_DOWN_N);
	
	protected static final VoxelShape EAST_WEST_CLOSED_UP_P = Block.makeCuboidShape(0, 13, 0, 8, 16, 16);
	protected static final VoxelShape EAST_WEST_CLOSED_UP_N = Block.makeCuboidShape(8, 13, 0, 16, 16, 16);
	protected static final VoxelShape EAST_WEST_CLOSED_DOWN_P = Block.makeCuboidShape(0, 0, 0, 8, 3, 16);
	protected static final VoxelShape EAST_WEST_CLOSED_DOWN_N = Block.makeCuboidShape(8, 0, 0, 16, 3, 16);
	
	protected static final VoxelShape NORTH_SOUTH_CLOSED_UP_P = Block.makeCuboidShape(0, 13, 0, 16, 16, 8);
	protected static final VoxelShape NORTH_SOUTH_CLOSED_UP_N = Block.makeCuboidShape(0, 13, 8, 16, 16, 16);
	protected static final VoxelShape NORTH_SOUTH_CLOSED_DOWN_P = Block.makeCuboidShape(0, 0, 0, 16, 3, 8);
	protected static final VoxelShape NORTH_SOUTH_CLOSED_DOWN_N = Block.makeCuboidShape(0, 0, 8, 16, 3, 16);
	
	protected static final VoxelShape CLOSED_UP = Block.makeCuboidShape(0, 13, 0, 16, 16, 16);
	protected static final VoxelShape CLOSED_DOWN = Block.makeCuboidShape(0, 0, 0, 16, 3, 16);
	
	public TrapDoorHalfBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)).with(HALF, Half.BOTTOM).with(POSITIVE, Boolean.valueOf(true)).with(NEGATIVE, Boolean.valueOf(false)));
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		if (!state.get(OPEN))
		{
			if (!state.get(POSITIVE) || !state.get(NEGATIVE))
			{
				switch(state.get(HALF))
				{
				case BOTTOM:
				default:
					switch((Direction)state.get(HORIZONTAL_FACING))
					{
					case NORTH:
					case SOUTH:
					default:
						if (!state.get(POSITIVE)) return NORTH_SOUTH_CLOSED_DOWN_N;
						else return NORTH_SOUTH_CLOSED_DOWN_P;
					case WEST:
					case EAST:
						if (!state.get(POSITIVE)) return EAST_WEST_CLOSED_DOWN_N;
						else return EAST_WEST_CLOSED_DOWN_P;
					
					}
				case TOP:
					switch((Direction)state.get(HORIZONTAL_FACING))
					{
					case NORTH:
					case SOUTH:
					default:
						if (!state.get(POSITIVE)) return NORTH_SOUTH_CLOSED_UP_N;
						else return NORTH_SOUTH_CLOSED_UP_P;
					case WEST:
					case EAST:
						if (!state.get(POSITIVE)) return EAST_WEST_CLOSED_UP_N;
						else return EAST_WEST_CLOSED_UP_P;
					}
				
				}
			} else 
			{
				switch(state.get(HALF))
				{
				case BOTTOM:
				default:
					return CLOSED_DOWN;
				case TOP:
					return CLOSED_UP;
				}
			}
		} else 
		{
			if (!state.get(POSITIVE) || !state.get(NEGATIVE))
			{
				switch(state.get(HALF))
				{
				case BOTTOM:
				default:
					switch((Direction)state.get(HORIZONTAL_FACING))
					{
					case NORTH:
					case SOUTH:
					default:
						if (!state.get(POSITIVE)) return NORTH_SOUTH_OPEN_DOWN_N;
						else return NORTH_SOUTH_OPEN_DOWN_P;
					case WEST:
					case EAST:
						if (!state.get(POSITIVE)) return EAST_WEST_OPEN_DOWN_N;
						else return EAST_WEST_OPEN_DOWN_P;
					}
				case TOP:
					switch((Direction)state.get(HORIZONTAL_FACING))
					{
					case NORTH:
					case SOUTH:
					default:
						if (!state.get(POSITIVE)) return NORTH_SOUTH_OPEN_UP_N;
						else return NORTH_SOUTH_OPEN_UP_P;
					case WEST:
					case EAST:
						if (!state.get(POSITIVE)) return EAST_WEST_OPEN_UP_N;
						else return EAST_WEST_OPEN_UP_P;
					}
				
				}
			} else
			{
				switch(state.get(HALF))
				{
				case BOTTOM:
				default:
					switch((Direction)state.get(HORIZONTAL_FACING))
					{
					case NORTH:
					case SOUTH:
					default:
						return NORTH_SOUTH_OPEN_DOWN;
					case WEST:
					case EAST:
						return EAST_WEST_OPEN_DOWN;
					}
				case TOP:
					switch((Direction)state.get(HORIZONTAL_FACING))
					{
					case NORTH:
					case SOUTH:
					default:
						return NORTH_SOUTH_OPEN_UP;
					case WEST:
					case EAST:
						return EAST_WEST_OPEN_UP;
					}
				}
			}
		}
	}
	
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		switch(type) {
	      case LAND:
	         return state.get(OPEN);
	      case WATER:
	         return state.get(OPEN);
	      case AIR:
	         return state.get(OPEN);
	      default:
	         return false;
	      }
	}
	
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		state = state.cycle(OPEN);
		worldIn.setBlockState(pos, state, 2);
		this.playSound(player, worldIn, pos, state.get(OPEN));
        return true;
	}
	
	protected void playSound(@Nullable PlayerEntity player, World worldIn, BlockPos pos, boolean p_185731_4_) {
	      if (p_185731_4_) {
	         int i = this.material == Material.IRON ? 1037 : 1007;
	         worldIn.playEvent(player, i, pos, 0);
	      } else {
	         int j = this.material == Material.IRON ? 1036 : 1013;
	         worldIn.playEvent(player, j, pos, 0);
	      }

	   }
	
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockPos blockpos = context.getPos();
		BlockState blockstate = context.getWorld().getBlockState(blockpos);
		BlockState blockstate1 = this.getDefaultState();
		if (blockstate.getBlock() == this)
		{
			return blockstate.with(HORIZONTAL_FACING, blockstate.get(HORIZONTAL_FACING)).with(OPEN, blockstate.get(OPEN)).with(HALF, blockstate.get(HALF)).with(POSITIVE, true).with(NEGATIVE, true);
		} else
		{
			Direction direction = context.getFace();
			if (!context.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) 
			{
				if (direction == Direction.NORTH) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, direction).with(HALF, context.getHitVec().y - (double)context.getPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(true)).with(NEGATIVE, Boolean.valueOf(false));
				} else if (direction == Direction.SOUTH) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, direction).with(HALF, context.getHitVec().y - (double)context.getPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(false)).with(NEGATIVE, Boolean.valueOf(true));
				} else if (direction == Direction.WEST) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, direction).with(HALF, context.getHitVec().y - (double)context.getPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(true)).with(NEGATIVE, Boolean.valueOf(false));
				} else if (direction == Direction.EAST) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, direction).with(HALF, context.getHitVec().y - (double)context.getPos().getY() > 0.5D ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(false)).with(NEGATIVE, Boolean.valueOf(true));
				}
			} else
			{
				if (direction == Direction.NORTH) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(HALF, direction == Direction.UP ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(true)).with(NEGATIVE, Boolean.valueOf(false));
				} else if (direction == Direction.SOUTH) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(HALF, direction == Direction.UP ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(false)).with(NEGATIVE, Boolean.valueOf(true));
				} else if (direction == Direction.WEST) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(HALF, direction == Direction.UP ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(true)).with(NEGATIVE, Boolean.valueOf(false));
				} else if (direction == Direction.EAST) {
					blockstate = blockstate1.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(HALF, direction == Direction.UP ? Half.TOP : Half.BOTTOM).with(POSITIVE, Boolean.valueOf(false)).with(NEGATIVE, Boolean.valueOf(true));
				}
			}
		}
		return blockstate1;
	}
	
	public boolean isReplaceble(BlockState state, BlockItemUseContext useContext)
	{
		ItemStack itemstack = useContext.getItem();
		Half half = state.get(HALF);
		if ((!state.get(POSITIVE) || !state.get(NEGATIVE)) && itemstack.getItem() == this.asItem())
		{
			if (useContext.replacingClickedOnBlock())
			{
				boolean flag = useContext.getHitVec().y - (double)useContext.getPos().getY() > 0.5D;
				Direction direction = useContext.getFace();
				if (half == Half.BOTTOM)
				{
					return direction == Direction.UP || flag && direction.getAxis().isHorizontal();
				} else
				{
					return direction == Direction.DOWN || !flag && direction.getAxis().isHorizontal();
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public BlockRenderLayer getRenderLayer() {
	      return BlockRenderLayer.CUTOUT;
	   }
	
	@Override
	   public boolean isLadder(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos, net.minecraft.entity.LivingEntity entity) {
	      if (state.get(OPEN)) {
	         BlockState down = world.getBlockState(pos.down());
	         if (down.getBlock() == net.minecraft.block.Blocks.LADDER)
	            return down.get(LadderBlock.FACING) == state.get(HORIZONTAL_FACING) || down.get(LadderBlock.FACING) == state.get(HORIZONTAL_FACING).getOpposite();
	      }
	      return false;
	   }
	
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
	      return false;
	   }
}







