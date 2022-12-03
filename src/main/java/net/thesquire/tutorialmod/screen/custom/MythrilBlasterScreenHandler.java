package net.thesquire.tutorialmod.screen.custom;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.Slot;
import net.thesquire.tutorialmod.block.entity.MythrilBlasterBlockEntity;
import net.thesquire.tutorialmod.screen.ModScreenHandlers;
import net.thesquire.tutorialmod.screen.ModSimpleScreenHandler;
import net.thesquire.tutorialmod.screen.slot.ModFuelSlot;
import net.thesquire.tutorialmod.screen.slot.ModResultSlot;

import java.util.List;

public class MythrilBlasterScreenHandler extends ModSimpleScreenHandler {

    public MythrilBlasterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(MythrilBlasterBlockEntity.INVENTORY_SIZE),
                new ArrayPropertyDelegate(MythrilBlasterBlockEntity.INVENTORY_SIZE));
    }

    public MythrilBlasterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.MYTHRIL_BLASTER_SCREEN_HANDLER, syncId, playerInventory,
                inventory, MythrilBlasterBlockEntity.INVENTORY_SIZE,
                List.of(new ModFuelSlot(inventory, 0, 18, 50),
                        new Slot(inventory, 1, 66, 16),
                        new Slot(inventory, 2, 66, 50),
                        new ModResultSlot(inventory, 3, 114, 33)),
                delegate);
    }

    public boolean isCrafting() {
        return super.isCrafting(0);
    }

    public boolean hasFuel() {
        return super.hasFuel(2);
    }

    public int getScaledProgress() {
        return super.getScaledProgress(0, 1, 26);
    }

    public int getScaledFuelProgress() {
        return super.getScaledFuelProgress(2, 3, 14);
    }

}
