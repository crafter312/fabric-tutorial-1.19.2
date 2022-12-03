package net.thesquire.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.block.ModBlocks;
import net.thesquire.tutorialmod.entity.ModEntities;
import net.thesquire.tutorialmod.fluid.ModFluids;
import net.thesquire.tutorialmod.item.custom.*;
import net.thesquire.tutorialmod.sound.ModSounds;

public class ModItems {

    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new MythrilIngotItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item RAW_MYTHRIL = registerItem("raw_mythril",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxDamage(16)));

    public static final Item MYTHRIL_ROD = registerItem("mythril_rod",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item LILAC_FLOWER_BULB = registerItem("lilac_flower_bulb",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(16)));

    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL).food(ModFoodComponents.GRAPE)));

    public static final Item MYTHRIL_DUST = registerItem("mythril_dust",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item DATA_TABLET = registerItem("data_tablet",
            new DataTabletItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(1)));

    public static final Item GRAPE_SEEDS = registerItem("grape_seeds",
            new AliasedBlockItem(ModBlocks.GRAPE_VINE, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_BOW = registerItem("mythril_bow",
            new BowItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxDamage(640)));

    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new ModMusicDiscItem(7, ModSounds.BAR_BRAWL,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(1), 122));

    public static final Item MYTHRIL_STAFF = registerItem("mythril_staff",
            new MythrilStaffItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(1)));

    public static final Item JACARANDA_SIGN = registerItem("jacaranda_sign",
            new SignItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(16),
                    ModBlocks.JACARANDA_SIGN, ModBlocks.JACARANDA_WALL_SIGN));

    public static final Item HONEY_BUCKET = registerItem("honey_bucket",
            new BucketItem(ModFluids.HONEY_STILL, new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(1)));

    public static final Item ANIMATED_ITEM = registerItem("animated_item",
            new AnimatedItem(new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(1)));

    public static final Item ANIMATED_BLOCK_ITEM = registerItem("animated_block",
            new AnimatedBlockItem(ModBlocks.ANIMATED_BLOCK,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL).maxCount(1)));

    public static Item RACCOON_SPAWN_EGG;

    /******** Tools and Armor ********/

    public static final Item MYTHRIL_SWORD = registerItem("mythril_sword",
            new ModSlownessSwordItem(ModToolMaterials.MYTHRIL, 3, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_SHOVEL = registerItem("mythril_shovel",
            new ShovelItem(ModToolMaterials.MYTHRIL, 1.5f, -3f,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_PICKAXE = registerItem("mythril_pickaxe",
            new ModPickaxeItem(ModToolMaterials.MYTHRIL, 1, -2.8f,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_AXE = registerItem("mythril_axe",
            new ModAxeItem(ModToolMaterials.MYTHRIL, 5.5f, -3.0f,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_HOE = registerItem("mythril_hoe",
            new ModHoeItem(ModToolMaterials.MYTHRIL, -2, 0.0f,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_HELMET = registerItem("mythril_helmet",
            new MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_CHESTPLATE = registerItem("mythril_chestplate",
            new MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_LEGGINGS = registerItem("mythril_leggings",
            new MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_BOOTS = registerItem("mythril_boots",
            new MythrilArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

/******************************************************************************************/

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering mod items for " + TutorialMod.MOD_ID);

        RACCOON_SPAWN_EGG = registerItem("raccoon_spawn_egg",
                new SpawnEggItem(ModEntities.RACCOON, 0x948e8d, 0x3b3635,
                        new FabricItemSettings().group(ModItemGroup.MYTHRIL)));
    }

}
