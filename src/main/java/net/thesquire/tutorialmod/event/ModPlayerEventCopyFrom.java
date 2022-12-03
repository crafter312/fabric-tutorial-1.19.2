package net.thesquire.tutorialmod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.thesquire.tutorialmod.util.IEntityDataSaver;

public class ModPlayerEventCopyFrom implements ServerPlayerEvents.CopyFrom {

    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IEntityDataSaver oldPl = (IEntityDataSaver) oldPlayer;
        IEntityDataSaver newPl = (IEntityDataSaver) newPlayer;

        newPl.getPersistentData().putIntArray("homepos", oldPl.getPersistentData().getIntArray("homepos"));
    }

}
