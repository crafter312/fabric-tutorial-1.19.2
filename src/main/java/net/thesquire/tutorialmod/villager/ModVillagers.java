package net.thesquire.tutorialmod.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.block.ModBlocks;

public class ModVillagers {

    public static PointOfInterestType BLASTER_POI = registerPOI("blasterpoi", ModBlocks.MYTHRIL_BLASTER);
    public static VillagerProfession BLAST_MASTER = registerProfession("blastmaster",
            RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(TutorialMod.MOD_ID, "blasterpoi")));

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(TutorialMod.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(TutorialMod.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        Identifier id = new Identifier(TutorialMod.MOD_ID, name);
        return PointOfInterestHelper.register(id, 1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

}
