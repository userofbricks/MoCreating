package com.jonathan.mocreating;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jonathan.mocreating.config.Config;
import com.jonathan.mocreating.lists.ItemList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("mocreating")
public class MoCreating
{
	public static MoCreating instance;
	public static final String MODID = "mocreating";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public MoCreating()
	{
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegisteries);
		
		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mocreating-client.toml").toString());
		Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mocreating-server.toml").toString());
		
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
					ItemList.nether_dust = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("nether_dust")),
					ItemList.nether_shard = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("nether_shard")),
			
					ItemList.skin_scraps = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("skin_scraps")),
					ItemList.skin_fold = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("skin_fold")),
					ItemList.wing_fold = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("wing_fold")),
					ItemList.wing = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("wing")),
					ItemList.bone_hardend = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("bone_hardend")),
			
					ItemList.trident_handle = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("trident_handle")),
					ItemList.prismarine_stick = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("prismarine_stick")),
					ItemList.prismarine_sharpened = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("prismarine_sharpened")),
					ItemList.trident_prong_holder = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("trident_prong_holder")),
					ItemList.trident_prong = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("trident_prong"))
			);
			
			LOGGER.info("items registered.");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(MODID, name);
		}
	}
}
