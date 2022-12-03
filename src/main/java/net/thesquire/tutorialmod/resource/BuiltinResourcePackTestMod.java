package net.thesquire.tutorialmod.resource;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.thesquire.tutorialmod.TutorialMod;

public class BuiltinResourcePackTestMod implements ModInitializer {

    @Override
    public void onInitialize() {
        // Should always be present as it's **this** mod.
        FabricLoader.getInstance().getModContainer(TutorialMod.MOD_ID)
                .map(container -> ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(TutorialMod.MOD_ID, "test"),
                        container, "Fabric Resource Loader Test Pack", ResourcePackActivationType.NORMAL))
                .filter(success -> !success).ifPresent(success -> TutorialMod.LOGGER.warn("Could not register built-in resource pack with custom name."));

        FabricLoader.getInstance().getModContainer(TutorialMod.MOD_ID)
                .map(container -> ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(TutorialMod.MOD_ID, "test2"),
                        container, ResourcePackActivationType.ALWAYS_ENABLED))
                .filter(success -> !success).ifPresent(success -> TutorialMod.LOGGER.warn("Could not register built-in resource pack."));
    }

}
