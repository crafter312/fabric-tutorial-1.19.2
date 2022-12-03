package net.thesquire.tutorialmod.world.biome;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModSurfaceRuleData {

    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final MaterialRules.MaterialRule BLUE_TERRACOTTA = makeStateRule(Blocks.BLUE_TERRACOTTA);

    private static final MaterialRules.MaterialCondition IS_AT_OR_ABOVE_WATER_LEVEL =
            MaterialRules.water(-1, 0);

    /**
     * See {@link net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules} for examples
     * of how to use {@link net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialRule}
     * and {@link net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialCondition}.
     */
    public static MaterialRules.MaterialRule makeOverworldRules() {
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(ModBiomeKeys.HOT_RED), makeSurfaceRule(RED_TERRACOTTA)),
                MaterialRules.condition(MaterialRules.biome(ModBiomeKeys.COLD_BLUE), makeSurfaceRule(BLUE_TERRACOTTA)),

                // Default to a grass and dirt surface
                makeSurfaceRule(GRASS_BLOCK, DIRT)
        );
    }

    public static MaterialRules.MaterialRule makeBackroomsRules() {
        return STONE;
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }

    /**
     * Method parameters:
     * @param block1 block to generate above the stone ceiling at or above the water level
     * @param block2 block to generate above the stone ceiling below the water level
     */
    private static MaterialRules.MaterialRule makeSurfaceRule(MaterialRules.MaterialRule block1,
                                                              MaterialRules.MaterialRule block2) {
        return MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                        MaterialRules.sequence(MaterialRules.condition(IS_AT_OR_ABOVE_WATER_LEVEL, block1),
                                block2)), STONE);
    }

    private static MaterialRules.MaterialRule makeSurfaceRule(MaterialRules.MaterialRule block) {
        return makeSurfaceRule(block, STONE);
    }

}
