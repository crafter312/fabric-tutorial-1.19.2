package net.thesquire.tutorialmod.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class ModSimpleLightingFeature extends Feature<ModSimpleLightingFeatureConfig> {

    public ModSimpleLightingFeature(Codec<ModSimpleLightingFeatureConfig> configCodec) { super(configCodec); }

    @Override
    public boolean generate(FeatureContext<ModSimpleLightingFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        ModSimpleLightingFeatureConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        int width = config.width().get(new Random());
        int depth = config.depth().get(new Random());
        BlockPos startPos = new BlockPos(
                pos.getX() - width,
                pos.getY(),
                pos.getZ() - depth);

        int total = width * depth;
        for(int t = 0; t < total; t++) {
            int i = t % width;
            int j = t / width;
            BlockPos blockPos = startPos.add(i*2, 0, j*2);
            for(OreFeatureConfig.Target target : config.targets()) {
                if (!shouldPlace(world.getBlockState(blockPos), target, context.getRandom())) continue;
                world.setBlockState(blockPos, target.state, Block.NOTIFY_LISTENERS);
            }
        }

        return true;
    }

    private boolean shouldPlace(BlockState state, OreFeatureConfig.Target target, Random random) {
        return target.target.test(state, random);
    }

}
