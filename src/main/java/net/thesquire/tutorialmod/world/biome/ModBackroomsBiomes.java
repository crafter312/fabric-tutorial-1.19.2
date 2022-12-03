package net.thesquire.tutorialmod.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.thesquire.tutorialmod.world.feature.ModPlacedFeatures;
import org.jetbrains.annotations.Nullable;

public class ModBackroomsBiomes {

    @Nullable
    private static final MusicSound NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = MathHelper.clamp($$1, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature,
                               float downfall, SpawnSettings.Builder spawnBuilder, GenerationSettings.Builder biomeBuilder,
                               @Nullable MusicSound music)
    {
        return biome(precipitation, temperature, downfall, 4159204, 329011,
                spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall,
                               int waterColor, int waterFogColor, SpawnSettings.Builder spawnBuilder,
                               GenerationSettings.Builder biomeBuilder, @Nullable MusicSound music)
    {
        return (new Biome.Builder()).precipitation(precipitation).temperature(temperature)
                .downfall(downfall).effects((new BiomeEffects.Builder()).waterColor(waterColor)
                        .waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature))
                        .moodSound(BiomeMoodSound.CAVE).music(music).build())
                .spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    /**
     * Below is where all custom biomes are created,
     * including custom features for those biomes as well.
     * Note that these features should ONLY be added here,
     * and not afterwords in the {@link net.thesquire.tutorialmod.world.gen.ModWorldGen}
     * class!
     */

    public static Biome level0() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.Builder biomeBuilder = new GenerationSettings.Builder();
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.TEST_WALL_PLACED);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.TEST_BLOCK_PLACED);

        return biome(Biome.Precipitation.NONE, 0.8f, 0f, 4159204,
                0x919191, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }

}
