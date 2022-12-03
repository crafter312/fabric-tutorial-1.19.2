package net.thesquire.tutorialmod.item.client;

import net.minecraft.util.Identifier;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.item.custom.AnimatedBlockItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockItemModel extends AnimatedGeoModel<AnimatedBlockItem> {
    @Override
    public Identifier getModelResource(AnimatedBlockItem object) {
        return new Identifier(TutorialMod.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnimatedBlockItem object) {
        return new Identifier(TutorialMod.MOD_ID, "textures/machine/animated_block.png");
    }

    @Override
    public Identifier getAnimationResource(AnimatedBlockItem animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/animated_block.animation.json");
    }
}
