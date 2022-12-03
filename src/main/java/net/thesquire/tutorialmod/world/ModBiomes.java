package net.thesquire.tutorialmod.world;


import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.world.biome.ModBackroomsBiomes;
import net.thesquire.tutorialmod.world.biome.ModBiomeKeys;
import net.thesquire.tutorialmod.world.biome.ModOverworldBiomes;

public class ModBiomes
{
    public static void registerBiomes() {
        TutorialMod.LOGGER.info("Registering mod biomes for " + TutorialMod.MOD_ID);

        register(ModBiomeKeys.HOT_RED, ModOverworldBiomes.hotRed());
        register(ModBiomeKeys.COLD_BLUE, ModOverworldBiomes.coldBlue());
        register(ModBiomeKeys.LEVEL_0, ModBackroomsBiomes.level0());
    }

    private static void register(RegistryKey<Biome> key, Biome biome) {
        BuiltinRegistries.add(BuiltinRegistries.BIOME, key, biome);
    }
}
