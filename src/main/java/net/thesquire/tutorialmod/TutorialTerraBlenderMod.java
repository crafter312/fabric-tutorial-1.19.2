package net.thesquire.tutorialmod;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.thesquire.tutorialmod.world.biome.ModRegion;
import net.thesquire.tutorialmod.world.biome.ModSurfaceRuleData;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class TutorialTerraBlenderMod implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        // Given we only add two biomes, we should keep our weight relatively low.
        Regions.register(new ModRegion(new Identifier(TutorialMod.MOD_ID, "overworld"), 2));

        // Register our surface rules
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, TutorialMod.MOD_ID,
                ModSurfaceRuleData.makeOverworldRules());

        Regions.register(new ModRegion(new Identifier(TutorialMod.MOD_ID, "backrooms"), 0));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, TutorialMod.MOD_ID,
                ModSurfaceRuleData.makeBackroomsRules());
    }

}
