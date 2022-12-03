package net.thesquire.tutorialmod.world.dimension.custom;

import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.thesquire.tutorialmod.block.ModBlocks;

public class VoidDimension {

    // The dimension options refer to the JSON-file in the dimension subfolder of the datapack,
    // which will always share it's ID with the world that is created from it
    public static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY,
            new Identifier("tutorialmod", "void"));
    public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, DIMENSION_KEY.getValue());
    public static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            new Identifier("tutorialmod", "void_type"));

    public static final Vec3d VOID_SPAWN_POS = new Vec3d(0, 70, 0);

    public static void registerVoidDimension() {
        Registry.register(Registry.CHUNK_GENERATOR, DIMENSION_KEY.getValue(), VoidChunkGenerator.CODEC);
    }

    public static void createVoidSpawnPlatform(ServerWorld world) {
        int i = (int) VOID_SPAWN_POS.getX();
        int j = (int) VOID_SPAWN_POS.getY() - 2;
        int k = (int) VOID_SPAWN_POS.getZ();
        BlockPos.iterate(i - 2, j + 1, k - 2, i + 2, j + 3, k + 2)
                .forEach(pos -> world.setBlockState(pos, Blocks.AIR.getDefaultState()));
        BlockPos.iterate(i - 2, j, k - 2, i + 2, j, k + 2)
                .forEach(pos -> world.setBlockState(pos, ModBlocks.MYTHRIL_BLOCK.getDefaultState()));
    }

}
