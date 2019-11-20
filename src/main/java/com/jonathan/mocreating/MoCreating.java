package com.jonathan.mocreating;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jonathan.mocreating.block.Grass;
import com.jonathan.mocreating.block.MyceliumLog;
import com.jonathan.mocreating.block.NetherMycelium;
import com.jonathan.mocreating.block.WitheredMycelium;
import com.jonathan.mocreating.lists.BiomesList;
import com.jonathan.mocreating.lists.BlockList;
import com.jonathan.mocreating.lists.ItemList;
import com.jonathan.mocreating.world.biomes.MocreatingBiome;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
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
					
					ItemList.mycelium_withered_soulsand		= new BlockItem(BlockList.mycelium_withered_soulsand,		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_withered_soulsand.getRegistryName()),
					ItemList.mycelium_dirt 					= new BlockItem(BlockList.mycelium_dirt, 					new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.mycelium_dirt.getRegistryName()),
					
					ItemList.grass_acacia_planks 	= new BlockItem(BlockList.grass_acacia_planks, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_acacia_planks.getRegistryName()),
					ItemList.grass_birch_planks 	= new BlockItem(BlockList.grass_birch_planks, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_birch_planks.getRegistryName()),
					ItemList.grass_coarse_dirt 		= new BlockItem(BlockList.grass_coarse_dirt, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_coarse_dirt.getRegistryName()),
					ItemList.grass_dark_oak_planks 	= new BlockItem(BlockList.grass_dark_oak_planks, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_dark_oak_planks.getRegistryName()),
					ItemList.grass_jungle_planks 	= new BlockItem(BlockList.grass_jungle_planks, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_jungle_planks.getRegistryName()),
					ItemList.grass_oak_planks 		= new BlockItem(BlockList.grass_oak_planks, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_oak_planks.getRegistryName()),
					ItemList.grass_sponge 			= new BlockItem(BlockList.grass_sponge, 			new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_sponge.getRegistryName()),
					ItemList.grass_spruce_planks 	= new BlockItem(BlockList.grass_spruce_planks, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_spruce_planks.getRegistryName()),
					ItemList.grass_wet_sponge 		= new BlockItem(BlockList.grass_wet_sponge, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_wet_sponge.getRegistryName()),

					ItemList.grass_stone 	= new BlockItem(BlockList.grass_stone, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_stone.getRegistryName()),
					ItemList.grass_andesite = new BlockItem(BlockList.grass_andesite, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_andesite.getRegistryName()),
					ItemList.grass_diorite 	= new BlockItem(BlockList.grass_diorite, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_diorite.getRegistryName()),
					ItemList.grass_granite 	= new BlockItem(BlockList.grass_granite, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_granite.getRegistryName()),
					ItemList.grass_gravel 	= new BlockItem(BlockList.grass_gravel, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_gravel.getRegistryName()),
					ItemList.grass_red_sand = new BlockItem(BlockList.grass_red_sand, 	new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_red_sand.getRegistryName()),
					ItemList.grass_sand 	= new BlockItem(BlockList.grass_sand, 		new Item.Properties()	.group(ItemGroup.BUILDING_BLOCKS))	.setRegistryName(BlockList.grass_sand.getRegistryName()),
					
					
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
				BlockList.mycelium_acacia_planks 	= new NetherMycelium(						Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_acacia_planks")),
				BlockList.mycelium_acacia_log 		= new MyceliumLog(MaterialColor.ADOBE, 		Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_acacia_log")),
				BlockList.mycelium_birch_planks 	= new NetherMycelium(						Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_birch_planks")),
				BlockList.mycelium_birch_log 		= new MyceliumLog(MaterialColor.SAND, 		Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_birch_log")),
				BlockList.mycelium_bone_block 		= new MyceliumLog(MaterialColor.QUARTZ, 	Block.Properties.create(Material.ROCK)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.STONE)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_bone_block")),
				BlockList.mycelium_coarse_dirt 		= new NetherMycelium(						Block.Properties.create(Material.EARTH)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("mycelium_coarse_dirt")),
				BlockList.mycelium_dark_oak_planks 	= new NetherMycelium(						Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_dark_oak_planks")),
				BlockList.mycelium_dark_oak_log 	= new MyceliumLog(MaterialColor.BROWN, 		Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_dark_oak_log")),
				BlockList.mycelium_jungle_planks 	= new NetherMycelium(						Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_jungle_planks")),
				BlockList.mycelium_jungle_log 		= new MyceliumLog(MaterialColor.DIRT, 		Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_jungle_log")),
				BlockList.mycelium_nether_wart 		= new NetherMycelium(						Block.Properties.create(Material.ORGANIC)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART)	.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("mycelium_nether_wart")),
				BlockList.mycelium_nether_quartz 	= new NetherMycelium(						Block.Properties.create(Material.ROCK)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.STONE)			.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("mycelium_nether_quartz")),
				BlockList.mycelium_netherrack 		= new NetherMycelium(						Block.Properties.create(Material.ROCK)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.STONE)			.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("mycelium_netherrack")),
				BlockList.mycelium_oak_planks 		= new NetherMycelium(						Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_oak_planks")),
				BlockList.mycelium_oak_log 			= new MyceliumLog(MaterialColor.WOOD, 		Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_oak_log")),
				BlockList.mycelium_sponge 			= new NetherMycelium(						Block.Properties.create(Material.SPONGE)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART))									.setRegistryName(location("mycelium_sponge")),
				BlockList.mycelium_spruce_planks 	= new NetherMycelium(						Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_spruce_planks")),
				BlockList.mycelium_spruce_log 		= new MyceliumLog(MaterialColor.OBSIDIAN, 	Block.Properties.create(Material.WOOD)		.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("mycelium_spruce_log")),
				BlockList.mycelium_wet_sponge 		= new NetherMycelium(						Block.Properties.create(Material.SPONGE)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART))									.setRegistryName(location("mycelium_wet_sponge")),
				
				BlockList.mycelium_stripped_oak_log 		= new MyceliumLog(MaterialColor.WOOD, Block.Properties.create(Material.WOOD)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_oak_log")),
				BlockList.mycelium_stripped_spruce_log 		= new MyceliumLog(MaterialColor.OBSIDIAN, Block.Properties.create(Material.WOOD).tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_spruce_log")),
				BlockList.mycelium_stripped_dark_oak_log 	= new MyceliumLog(MaterialColor.BROWN, Block.Properties.create(Material.WOOD)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_dark_oak_log")),
				BlockList.mycelium_stripped_jungle_log 		= new MyceliumLog(MaterialColor.DIRT, Block.Properties.create(Material.WOOD)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_jungle_log")),
				BlockList.mycelium_stripped_acacia_log 		= new MyceliumLog(MaterialColor.ADOBE, Block.Properties.create(Material.WOOD)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_acacia_log")),
				BlockList.mycelium_stripped_birch_log 		= new MyceliumLog(MaterialColor.SAND, Block.Properties.create(Material.WOOD)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)	.harvestTool(ToolType.AXE))	.setRegistryName(location("mycelium_stripped_birch_log")),
				
				BlockList.mycelium_withered_soulsand 		= new WitheredMycelium(Block.Properties.create(Material.SAND)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.SAND)			.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("mycelium_withered_soulsand")),
				BlockList.mycelium_dirt 					= new NetherMycelium(Block.Properties.create(Material.EARTH)	.tickRandomly()	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL)).setRegistryName(location("mycelium_dirt")),
				
				BlockList.grass_acacia_planks 	= new Grass(Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("grass_acacia_planks")),
				BlockList.grass_birch_planks 	= new Grass(Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("grass_birch_planks")),
				BlockList.grass_dark_oak_planks = new Grass(Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("grass_dark_oak_planks")),
				BlockList.grass_jungle_planks 	= new Grass(Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("grass_jungle_planks")),
				BlockList.grass_oak_planks 		= new Grass(Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("grass_oak_planks")),
				BlockList.grass_spruce_planks 	= new Grass(Block.Properties.create(Material.WOOD)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WOOD)			.harvestTool(ToolType.AXE))		.setRegistryName(location("grass_spruce_planks")),
				
				BlockList.grass_wet_sponge 		= new Grass(Block.Properties.create(Material.SPONGE).hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART))									.setRegistryName(location("grass_wet_sponge")),
				BlockList.grass_sponge 			= new Grass(Block.Properties.create(Material.SPONGE).hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.NETHER_WART))									.setRegistryName(location("grass_sponge")),
				BlockList.grass_coarse_dirt 	= new Grass(Block.Properties.create(Material.EARTH)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("grass_coarse_dirt")),
				BlockList.grass_stone 			= new Grass(Block.Properties.create(Material.ROCK)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("grass_stone")),
				BlockList.grass_andesite 		= new Grass(Block.Properties.create(Material.ROCK)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("grass_andesite")),
				BlockList.grass_diorite 		= new Grass(Block.Properties.create(Material.ROCK)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("grass_diorite")),
				BlockList.grass_granite 		= new Grass(Block.Properties.create(Material.ROCK)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.PICKAXE))	.setRegistryName(location("grass_granite")),
				BlockList.grass_gravel 			= new Grass(Block.Properties.create(Material.SAND)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("grass_gravel")),
				BlockList.grass_red_sand 		= new Grass(Block.Properties.create(Material.SAND)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("grass_red_sand")),
				BlockList.grass_sand 			= new Grass(Block.Properties.create(Material.EARTH)	.hardnessAndResistance(2.0f, 15.0f)	.sound(SoundType.WET_GRASS)		.harvestTool(ToolType.SHOVEL))	.setRegistryName(location("grass_sand"))
			);
			
			LOGGER.info("Blocks registered.");
		}
		
		@SubscribeEvent
		public static void registerBiomes (final RegistryEvent.Register<Biome> event)
		{
			event.getRegistry().registerAll
			(
				BiomesList.nether_mycelium = new MocreatingBiome()
			);
			
			BiomesList.registerBiomes();
		}
		
		public static ResourceLocation location(String name)
		{
			return new ResourceLocation(MODID, name);
		}
	}
}