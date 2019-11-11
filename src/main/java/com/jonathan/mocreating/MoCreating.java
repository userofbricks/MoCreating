package com.jonathan.mocreating;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jonathan.mocreating.block.Mycelium;
import com.jonathan.mocreating.block.MyceliumLog;
import com.jonathan.mocreating.lists.BlockList;
import com.jonathan.mocreating.lists.ItemList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mocreating")
public class MoCreating
{
	public static MoCreating instance;
	public static final String MODID = "mocreating";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public MoCreating()
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegisteries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) 
	{
		LOGGER.info("setup method registered.");
	}
	
	private void clientRegisteries(final FMLClientSetupEvent event) 
	{
		LOGGER.info("clientRegisteries method registered.");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
					//blocks
					ItemList.mycelium_acacia_planks 	= new BlockItem(BlockList.mycelium_acacia_planks, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_acacia_planks.getRegistryName()),
					ItemList.mycelium_acacia_log 		= new BlockItem(BlockList.mycelium_acacia_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_acacia_log.getRegistryName()),
					ItemList.mycelium_birch_planks 		= new BlockItem(BlockList.mycelium_birch_planks, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_birch_planks.getRegistryName()),
					ItemList.mycelium_birch_log 		= new BlockItem(BlockList.mycelium_birch_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_birch_log.getRegistryName()),
					ItemList.mycelium_bone_block 		= new BlockItem(BlockList.mycelium_bone_block, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_bone_block.getRegistryName()),
					ItemList.mycelium_coarse_dirt 		= new BlockItem(BlockList.mycelium_coarse_dirt, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_coarse_dirt.getRegistryName()),
					ItemList.mycelium_dark_oak_planks 	= new BlockItem(BlockList.mycelium_dark_oak_planks, new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_dark_oak_planks.getRegistryName()),
					ItemList.mycelium_dark_oak_log 		= new BlockItem(BlockList.mycelium_dark_oak_log, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_dark_oak_log.getRegistryName()),
					ItemList.mycelium_jungle_planks 	= new BlockItem(BlockList.mycelium_jungle_planks, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_jungle_planks.getRegistryName()),
					ItemList.mycelium_jungle_log 		= new BlockItem(BlockList.mycelium_jungle_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_jungle_log.getRegistryName()),
					ItemList.mycelium_nether_wart 		= new BlockItem(BlockList.mycelium_nether_wart, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_nether_wart.getRegistryName()),
					ItemList.mycelium_nether_quartz 	= new BlockItem(BlockList.mycelium_nether_quartz, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_nether_quartz.getRegistryName()),
					ItemList.mycelium_netherrack 		= new BlockItem(BlockList.mycelium_netherrack, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_netherrack.getRegistryName()),
					ItemList.mycelium_oak_planks 		= new BlockItem(BlockList.mycelium_oak_planks, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_oak_planks.getRegistryName()),
					ItemList.mycelium_oak_log 			= new BlockItem(BlockList.mycelium_oak_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_oak_log.getRegistryName()),
					ItemList.mycelium_sponge 			= new BlockItem(BlockList.mycelium_sponge, 			new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_sponge.getRegistryName()),
					ItemList.mycelium_spruce_planks 	= new BlockItem(BlockList.mycelium_spruce_planks, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_spruce_planks.getRegistryName()),
					ItemList.mycelium_spruce_log 		= new BlockItem(BlockList.mycelium_spruce_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_spruce_log.getRegistryName()),
					ItemList.mycelium_wet_sponge 		= new BlockItem(BlockList.mycelium_wet_sponge, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_wet_sponge.getRegistryName()),
						
					ItemList.mycelium_stripped_oak_log 		= new BlockItem(BlockList.mycelium_stripped_oak_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_stripped_oak_log.getRegistryName()),
					ItemList.mycelium_stripped_spruce_log 	= new BlockItem(BlockList.mycelium_stripped_spruce_log, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_stripped_spruce_log.getRegistryName()),
					ItemList.mycelium_stripped_dark_oak_log = new BlockItem(BlockList.mycelium_stripped_dark_oak_log, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_stripped_dark_oak_log.getRegistryName()),
					ItemList.mycelium_stripped_jungle_log 	= new BlockItem(BlockList.mycelium_stripped_jungle_log, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_stripped_jungle_log.getRegistryName()),
					ItemList.mycelium_stripped_acacia_log 	= new BlockItem(BlockList.mycelium_stripped_acacia_log, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_stripped_acacia_log.getRegistryName()),
					ItemList.mycelium_stripped_birch_log 	= new BlockItem(BlockList.mycelium_stripped_birch_log, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_stripped_birch_log.getRegistryName()),
					
					//items
					ItemList.nether_dust 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("nether_dust")),
					ItemList.nether_shard 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("nether_shard")),
					
					ItemList.skin_scraps 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("skin_scraps")),
					ItemList.skin_fold 		= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("skin_fold")),
					ItemList.wing_fold 		= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("wing_fold")),
					ItemList.wing 			= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("wing")),
					ItemList.bone_hardend 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("bone_hardend")),
					
					ItemList.spawn_egg_base = new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("spawn_egg_base")),
					
					ItemList.trident_handle 		= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("trident_handle")),
					ItemList.prismarine_stick 		= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("prismarine_stick")),
					ItemList.prismarine_sharpened 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("prismarine_sharpened")),
					ItemList.trident_prong_holder 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("trident_prong_holder")),
					ItemList.trident_prong 			= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("trident_prong")),
					
					ItemList.mycelium 				= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("mycelium")),
					
					ItemList.spawn_frame_fragment 	= new Item(new Item.Properties()	.group(ItemGroup.MISC))	.setRegistryName(location("spawn_frame_fragment"))
			);
			LOGGER.info("items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.mycelium_acacia_planks 	= new Mycelium(								Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_acacia_planks")),
				BlockList.mycelium_acacia_log 		= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_acacia_log")),
				BlockList.mycelium_birch_planks 	= new Mycelium(								Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_birch_planks")),
				BlockList.mycelium_birch_log 		= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_birch_log")),
				BlockList.mycelium_bone_block 		= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.ROCK)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.STONE)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_bone_block")),
				BlockList.mycelium_coarse_dirt 		= new Mycelium(								Block.Properties.create(Material.EARTH)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("mycelium_coarse_dirt")),
				BlockList.mycelium_dark_oak_planks 	= new Mycelium(								Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_dark_oak_planks")),
				BlockList.mycelium_dark_oak_log 	= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_dark_oak_log")),
				BlockList.mycelium_jungle_planks 	= new Mycelium(								Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_jungle_planks")),
				BlockList.mycelium_jungle_log 		= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_jungle_log")),
				BlockList.mycelium_nether_wart 		= new Mycelium(								Block.Properties.create(Material.ORGANIC)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("mycelium_nether_wart")),
				BlockList.mycelium_nether_quartz 	= new Mycelium(								Block.Properties.create(Material.ROCK)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.STONE)			.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("mycelium_nether_quartz")),
				BlockList.mycelium_netherrack 		= new Mycelium(								Block.Properties.create(Material.ROCK)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.STONE)			.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("mycelium_netherrack")),
				BlockList.mycelium_oak_planks 		= new Mycelium(								Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_oak_planks")),
				BlockList.mycelium_oak_log 			= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_oak_log")),
				BlockList.mycelium_sponge 			= new Mycelium(								Block.Properties.create(Material.SPONGE)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART))									.setRegistryName(location("mycelium_sponge")),
				BlockList.mycelium_spruce_planks 	= new Mycelium(								Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_spruce_planks")),
				BlockList.mycelium_spruce_log 		= new MyceliumLog(MaterialColor.PURPLE, 	Block.Properties.create(Material.WOOD)		.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_spruce_log")),
				BlockList.mycelium_wet_sponge 		= new Mycelium(								Block.Properties.create(Material.SPONGE)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART))									.setRegistryName(location("mycelium_wet_sponge")),
				
				BlockList.mycelium_stripped_oak_log 		= new MyceliumLog(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_oak_log")),
				BlockList.mycelium_stripped_spruce_log 		= new MyceliumLog(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_spruce_log")),
				BlockList.mycelium_stripped_dark_oak_log 	= new MyceliumLog(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_dark_oak_log")),
				BlockList.mycelium_stripped_jungle_log 		= new MyceliumLog(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_jungle_log")),
				BlockList.mycelium_stripped_acacia_log 		= new MyceliumLog(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_acacia_log")),
				BlockList.mycelium_stripped_birch_log 		= new MyceliumLog(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_birch_log"))
			);
			
			LOGGER.info("Blocks registered.");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(MODID, name);
		}
	}
}
































