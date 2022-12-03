package net.thesquire.tutorialmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.thesquire.tutorialmod.TutorialMod;

public class ModEnchantments {

    public static Enchantment LIGHTNING_STRIKER;

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(TutorialMod.MOD_ID, name), enchantment);
    }

    public static void registerEnchantments(){
        TutorialMod.LOGGER.info("Registering mod enchantments for " + TutorialMod.MOD_ID);

        LIGHTNING_STRIKER = register("lightning_striker", new LightningStrikerEnchantment(
                Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));
    }

}
