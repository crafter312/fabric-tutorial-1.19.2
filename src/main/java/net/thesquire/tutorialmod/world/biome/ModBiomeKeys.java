package net.thesquire.tutorialmod.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.thesquire.tutorialmod.TutorialMod;

public class ModBiomeKeys {

    public static final RegistryKey<Biome> HOT_RED = register("hot_red");
    public static final RegistryKey<Biome> COLD_BLUE = register("cold_blue");
    public static final RegistryKey<Biome> LEVEL_0 = register("level_0");

    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(TutorialMod.MOD_ID, name));
    }

}
