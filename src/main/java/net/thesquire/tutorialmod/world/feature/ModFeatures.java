package net.thesquire.tutorialmod.world.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.world.feature.custom.ModSimpleLightingFeature;
import net.thesquire.tutorialmod.world.feature.custom.ModSimpleLightingFeatureConfig;
import net.thesquire.tutorialmod.world.feature.custom.ModSimpleWallFeature;
import net.thesquire.tutorialmod.world.feature.custom.ModSimpleWallFeatureConfig;

public class ModFeatures {

    public static final Feature<ModSimpleWallFeatureConfig> WALL = register(new Identifier(TutorialMod.MOD_ID, "wall"),
            new ModSimpleWallFeature(ModSimpleWallFeatureConfig.CODEC));
    public static final Feature<ModSimpleLightingFeatureConfig> LIGHTING_FEATURE = register(new Identifier(TutorialMod.MOD_ID, "lighting_feature"),
            new ModSimpleLightingFeature(ModSimpleLightingFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(Identifier id, F feature) {
        return (F)Registry.register(Registry.FEATURE, id, feature);
    }

}
