package net.thesquire.tutorialmod.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.thesquire.tutorialmod.TutorialMod;

public class ModPaintings {

    public static final PaintingVariant MARATHON = registerPainting("marathon", new PaintingVariant(16, 16));
    public static final PaintingVariant FAMILY = registerPainting("family", new PaintingVariant(16, 32));

    /****************************************************************************/

    public static void registerPaintings() {
        TutorialMod.LOGGER.info("Registring paintings for " + TutorialMod.MOD_ID);
    }

    /****************************************************************************/

    private static PaintingVariant registerPainting(String name, PaintingVariant PaintingVariant) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(TutorialMod.MOD_ID, name), PaintingVariant);
    }

}
