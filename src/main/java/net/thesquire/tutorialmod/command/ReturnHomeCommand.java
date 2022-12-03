package net.thesquire.tutorialmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.thesquire.tutorialmod.util.IEntityDataSaver;

public class ReturnHomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("return").executes(ReturnHomeCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();

        // not 0 means it contains SOMETHING
        int[] homepos = player.getPersistentData().getIntArray("homepos");
        float pitch = player.getPersistentData().getFloat("homepitch");
        float yaw = player.getPersistentData().getFloat("homeyaw");

        if(homepos.length != 0) {
            int[] playerPos = player.getPersistentData().getIntArray("homepos");
            context.getSource().getPlayer().networkHandler.requestTeleport(playerPos[0], playerPos[1], playerPos[2], yaw, pitch);

            context.getSource().sendFeedback(Text.literal("Player returned Home!"), true);
            return 1;
        }
        else {
            context.getSource().sendFeedback(Text.literal("No Home position has been set!"), true);
            return -1;
        }
    }

}
