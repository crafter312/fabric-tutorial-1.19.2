package net.thesquire.tutorialmod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.placementmodifier.*;
import net.thesquire.tutorialmod.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    /**
     * NOTE: There seems to be a required order of some kind for the placement modifiers! It seems to go roughly
     * as follows: rarity or count filter, square placement modifier, height range, block or other kinds of
     * environment filters, and MOST IMPORTANTLY biome placement modifier comes last.
     */

    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED = PlacedFeatures.register("jacaranda_placed",
            ModConfiguredFeatures.JACARANDA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(0, 0.1f, 2)));

    public static final RegistryEntry<PlacedFeature> LILAC_PLACED = PlacedFeatures.register("lilac_placed",
            ModConfiguredFeatures.LILAC_FLOWER, RarityFilterPlacementModifier.of(12), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MYTHRIL_ORE_PLACED = PlacedFeatures.register("mythril_ore_placed",
            ModConfiguredFeatures.MYTHRIL_ORE,
            modifiersWithCount(3, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-20), YOffset.fixed(48))));

    public static final RegistryEntry<PlacedFeature> NETHER_MYTHRIL_ORE_PLACED = PlacedFeatures.register("nether_mythril_ore_placed",
            ModConfiguredFeatures.NETHER_MYTHRIL_ORE,
            modifiersWithCount(6, HeightRangePlacementModifier.trapezoid(YOffset.fixed(100), YOffset.fixed(128))));

    public static final RegistryEntry<PlacedFeature> END_MYTHRIL_ORE_PLACED = PlacedFeatures.register("end_mythril_ore_placed",
            ModConfiguredFeatures.END_MYTHRIL_ORE,
            modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));

    public static final RegistryEntry<PlacedFeature> TEST_BLOCK_PLACED = PlacedFeatures.register("test_block_placed",
            ModConfiguredFeatures.TEST_BLOCK,
            CountPlacementModifier.of(12),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.fixed(73), YOffset.fixed(75)),
            BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(List.of(Blocks.AIR), Direction.DOWN.getVector())),
            BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> TEST_WALL_PLACED = PlacedFeatures.register("test_wall_placed",
            ModConfiguredFeatures.TEST_WALL,
            CountPlacementModifier.of(8),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.fixed(68), YOffset.fixed(72)),
            BlockFilterPlacementModifier.of(BlockPredicate.bothOf(
                    BlockPredicate.matchingBlocks(List.of(Blocks.STONE), Direction.DOWN.getVector()),
                    BlockPredicate.not(BlockPredicate.matchingBlocks(List.of(Blocks.STONE), BlockPos.ORIGIN)))),
            BiomePlacementModifier.of());

    /***********************************************************************************************************/

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return ModPlacedFeatures.modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return ModPlacedFeatures.modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

}
