package net.thesquire.tutorialmod.entity.client.armor;

import net.minecraft.util.Identifier;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.item.custom.MythrilArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MythrilArmorModel extends AnimatedGeoModel<MythrilArmorItem> {

    @Override
    public Identifier getModelResource(MythrilArmorItem object) {
        return new Identifier(TutorialMod.MOD_ID, "geo/mythril_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(MythrilArmorItem object) {
        return new Identifier(TutorialMod.MOD_ID, "textures/models/armor/mythril_armor_texture.png");
    }

    @Override
    public Identifier getAnimationResource(MythrilArmorItem animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/armor_animation.json");
    }

}
