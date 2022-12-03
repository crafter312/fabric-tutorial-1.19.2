package net.thesquire.tutorialmod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.effect.ModEffects;
import net.thesquire.tutorialmod.item.ModItems;
import net.thesquire.tutorialmod.mixin.BrewingRecipeRegistryMixin;

public class ModPotions {

    public static Potion FREEZE_POTION;

    public static Potion registerPotion(String name) {
        return Registry.register(Registry.POTION, new Identifier(TutorialMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffects.FREEZE, 200, 0)));
    }

    public static void registerPotions() {
        FREEZE_POTION = registerPotion("freeze_potion");

        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.involkRegisterPotionRecipe(Potions.AWKWARD, ModItems.MYTHRIL_INGOT, FREEZE_POTION);
    }

}
