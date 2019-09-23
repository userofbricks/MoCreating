package com.jonathan.mocreating.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.jonathan.mocreating.MoCreating;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config 
{
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SERVER_CONFIG;
	public static final ForgeConfigSpec CLIENT_CONFIG;
	
	static
	{
		SERVER_CONFIG = BUILDER.build();
		CLIENT_CONFIG = BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path)
	{
		MoCreating.LOGGER.info("Loading Config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		MoCreating.LOGGER.info("Built Config: " + path);
		file.load();
		MoCreating.LOGGER.info("Loaded Config: " + path);
		config.setConfig(file);
	};
}
