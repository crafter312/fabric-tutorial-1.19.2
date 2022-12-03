package net.thesquire.tutorialmod.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public record ModSimpleLightingFeatureConfig(List<OreFeatureConfig.Target> targets, IntProvider width, IntProvider depth) implements FeatureConfig {
    public static final Codec<ModSimpleLightingFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.list(OreFeatureConfig.Target.CODEC).fieldOf("targets").forGetter(ModSimpleLightingFeatureConfig::targets),
                    IntProvider.VALUE_CODEC.fieldOf("width").forGetter(ModSimpleLightingFeatureConfig::width),
                    IntProvider.VALUE_CODEC.fieldOf("depth").forGetter(ModSimpleLightingFeatureConfig::depth))
            .apply(instance, instance.stable(ModSimpleLightingFeatureConfig::new)));
}
