package net.thesquire.tutorialmod.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.jetbrains.annotations.Nullable;

public class ModOverworldBiomes {

    @Nullable
    private static final MusicSound NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color)
    {
        float $$1 = color / 3.0F;
        $$1 = MathHelper.clamp($$1, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.Category category, float temperature, 
                               float downfall, SpawnSettings.Builder spawnBuilder, GenerationSettings.Builder biomeBuilder, 
                               @Nullable MusicSound music)
    {
        return biome(precipitation, category, temperature, downfall, 4159204, 329011, 
                spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.Category category, float temperature, float downfall,
                               int waterColor, int waterFogColor, SpawnSettings.Builder spawnBuilder, 
                               GenerationSettings.Builder biomeBuilder, @Nullable MusicSound music)
    {
        return (new Biome.Builder()).precipitation(precipitation).category(category).temperature(temperature)
                .downfall(downfall).effects((new BiomeEffects.Builder()).waterColor(waterColor)
                        .waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature))
                        .moodSound(BiomeMoodSound.CAVE).music(music).build())
                .spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    /**
     * There is a specific order the feature calls have to be made in. It's not
     * very clear what that order is, but the order they are defined in the
     * {@link DefaultBiomeFeatures} class is a good place to start. The vegetation
     * (step 9) seems to be in a different order though, which can be experimentally
     * determined using Cyanide and determination.
     */

    // comments in form A (B) indicate generation step A and code line B in DefaultBiomeFeatures.java
    private static void globalOverworldGeneration(GenerationSettings.Builder biomeBuilder) {
        DefaultBiomeFeatures.addLandCarvers(biomeBuilder);      // 1
        DefaultBiomeFeatures.addAmethystGeodes(biomeBuilder);   // 2
        DefaultBiomeFeatures.addDungeons(biomeBuilder);         // 3
        DefaultBiomeFeatures.addMineables(biomeBuilder);        // 6, 9
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);      // 6
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);     // 6
    }

    public static Biome hotRed()
    {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addDesertMobs(spawnBuilder);

        GenerationSettings.Builder biomeBuilder = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addFossils(biomeBuilder);          // 3
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addSprings(biomeBuilder);          // 8
        DefaultBiomeFeatures.addDefaultFlowers(biomeBuilder);   // 9 (253)
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);     // 9 (267)
        DefaultBiomeFeatures.addDesertDeadBushes(biomeBuilder); // 9 (242)
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder); // 9 (281)
        DefaultBiomeFeatures.addDesertVegetation(biomeBuilder); // 9 (309)
        DefaultBiomeFeatures.addFrozenTopLayer(biomeBuilder);   // 10
        return biome(Biome.Precipitation.NONE, Biome.Category.DESERT, 2.0F, 0.0F, spawnBuilder,
                biomeBuilder, NORMAL_MUSIC);
    }

    public static Biome coldBlue()
    {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.GOAT, 5, 1, 3));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.Builder biomeBuilder = new GenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addEmeraldOre(biomeBuilder);       // 6
        DefaultBiomeFeatures.addInfestedStone(biomeBuilder);    // 7
        DefaultBiomeFeatures.addSprings(biomeBuilder);          // 8
        DefaultBiomeFeatures.addFrozenLavaSpring(biomeBuilder); // 8
        DefaultBiomeFeatures.addFrozenTopLayer(biomeBuilder);   // 10
        return biome(Biome.Precipitation.SNOW, Biome.Category.MOUNTAIN, -0.7F, 0.9F, spawnBuilder,
                biomeBuilder, NORMAL_MUSIC);
    }

}
