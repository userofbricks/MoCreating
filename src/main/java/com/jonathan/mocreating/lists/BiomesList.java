package com.jonathan.mocreating.lists;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BiomesList 
{
	public static Biome nether_mycelium;
	
	
	public static void registerBiomes()
	{
		registerBiome(nether_mycelium, Type.NETHER);
	}
	
	public static void registerBiome(Biome biome, Type... types) 
	{
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
}
