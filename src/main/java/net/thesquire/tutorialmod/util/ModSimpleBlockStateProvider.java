package net.thesquire.tutorialmod.util;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class ModSimpleBlockStateProvider extends SimpleBlockStateProvider {
    public ModSimpleBlockStateProvider(BlockState state) {
        super(state);
    }
}
