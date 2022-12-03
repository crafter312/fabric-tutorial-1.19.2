package net.thesquire.tutorialmod.screen;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.screen.custom.MythrilBlasterScreenHandler;

public class ModScreenHandlers {

    public static ScreenHandlerType<MythrilBlasterScreenHandler> MYTHRIL_BLASTER_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        MYTHRIL_BLASTER_SCREEN_HANDLER = register("mythril_blaster", MythrilBlasterScreenHandler::new);
    }

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registry.SCREEN_HANDLER, new Identifier(TutorialMod.MOD_ID, id), new ScreenHandlerType<T>(factory));
    }

}
