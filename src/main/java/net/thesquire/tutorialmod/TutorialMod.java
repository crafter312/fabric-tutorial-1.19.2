package net.thesquire.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.thesquire.tutorialmod.block.ModBlocks;
import net.thesquire.tutorialmod.block.ModBlockEntities;
import net.thesquire.tutorialmod.world.dimension.ModDimensions;
import net.thesquire.tutorialmod.effect.ModEffects;
import net.thesquire.tutorialmod.enchantment.ModEnchantments;
import net.thesquire.tutorialmod.entity.ModEntities;
import net.thesquire.tutorialmod.item.ModItems;
import net.thesquire.tutorialmod.painting.ModPaintings;
import net.thesquire.tutorialmod.particle.ModParticles;
import net.thesquire.tutorialmod.potion.ModPotions;
import net.thesquire.tutorialmod.recipe.ModRecipes;
import net.thesquire.tutorialmod.screen.ModScreenHandlers;
import net.thesquire.tutorialmod.util.ModLootTableModifiers;
import net.thesquire.tutorialmod.util.ModRegistries;
import net.thesquire.tutorialmod.villager.ModVillagers;
import net.thesquire.tutorialmod.world.ModBiomes;
import net.thesquire.tutorialmod.world.feature.ModConfiguredFeatures;
import net.thesquire.tutorialmod.world.gen.ModWorldGen;
import net.thesquire.tutorialmod.world.structure.ModStructures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBiomes.registerBiomes();
		ModConfiguredFeatures.registerConfiguredFeatures();
		ModDimensions.registerDimensions();
		ModEntities.registerEntities();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModPaintings.registerPaintings();
		ModRegistries.registerOtherModContents();
		ModWorldGen.generateModWorldGen();
		ModLootTableModifiers.modifyLootTables();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModBlockEntities.registerBlockEntities();
		ModRecipes.registerRecipes();
		ModParticles.registerParticles();
		ModEnchantments.registerEnchantments();
		ModScreenHandlers.registerScreenHandlers();
		ModStructures.registerStructureFeatures();

		GeckoLib.initialize();
	}
}
