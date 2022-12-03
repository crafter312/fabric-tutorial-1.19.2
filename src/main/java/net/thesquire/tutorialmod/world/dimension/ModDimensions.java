package net.thesquire.tutorialmod.world.dimension;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.item.ModItems;
import net.thesquire.tutorialmod.world.dimension.custom.Level0Dimension;
import net.thesquire.tutorialmod.world.dimension.custom.VoidDimension;

public class ModDimensions {

    public static void registerDimensions() {
        TutorialMod.LOGGER.info("Registering mod dimensions for " + TutorialMod.MOD_ID);

        VoidDimension.registerVoidDimension();
        Level0Dimension.registerLevel0Dimension();

        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.GLOWSTONE)
                .destDimID(Level0Dimension.WORLD_KEY.getValue())
                .tintColor(45, 79, 195)
                .lightWithItem(ModItems.MYTHRIL_STAFF)
                .onlyLightInOverworld()
                .registerPortal();
    }

}
