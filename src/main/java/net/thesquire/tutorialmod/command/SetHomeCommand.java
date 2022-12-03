package net.thesquire.tutorialmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.thesquire.tutorialmod.util.IEntityDataSaver;

public class SetHomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("set").executes(SetHomeCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();
        Vec3d playerPos = context.getSource().getPosition();
        Vec2f playerRot = context.getSource().getRotation();
        String pos = "(" + ((int) playerPos.getX()) + ", " + ((int) playerPos.getY()) + ", " + ((int) playerPos.getZ()) + ")";

        player.getPersistentData().putIntArray("homepos",
                new int[] {(int) playerPos.getX(), (int) playerPos.getY(), (int) playerPos.getZ() });
        player.getPersistentData().putFloat("homepitch", playerRot.x);
        player.getPersistentData().putFloat("homeyaw", playerRot.y);

        context.getSource().sendFeedback(Text.literal("Set home at " + pos), true);
        return 1;
    }

}
