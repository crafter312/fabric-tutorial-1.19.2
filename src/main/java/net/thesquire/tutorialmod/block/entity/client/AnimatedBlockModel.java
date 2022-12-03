package net.thesquire.tutorialmod.block.entity.client;

import net.minecraft.util.Identifier;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.block.entity.AnimatedBlockEntity;
import net.thesquire.tutorialmod.item.custom.AnimatedBlockItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockModel extends AnimatedGeoModel<AnimatedBlockEntity> {
    @Override
    public Identifier getModelResource(AnimatedBlockEntity object) {
        return new Identifier(TutorialMod.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public Identifier getTextureResource(AnimatedBlockEntity object) {
        return new Identifier(TutorialMod.MOD_ID, "textures/machine/animated_block.png");
    }

    @Override
    public Identifier getAnimationResource(AnimatedBlockEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/animated_block.animation.json");
    }
}
