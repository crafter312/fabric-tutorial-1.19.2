package net.thesquire.tutorialmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.thesquire.tutorialmod.TutorialMod;
import net.thesquire.tutorialmod.entity.custom.RaccoonEntity;

public class ModEntities {

    public static EntityType<RaccoonEntity> RACCOON;

    public static void registerEntities() {
        TutorialMod.LOGGER.info("Registering mod entities for " + TutorialMod.MOD_ID);

        RACCOON = registerFixedSizeEntity("raccoon", SpawnGroup.CREATURE, RaccoonEntity::new, 0.4f, 0.3f);
    }

    private static <T extends AnimalEntity> EntityType<T> registerFixedSizeEntity(String name, SpawnGroup group,
                                                                                  EntityType.EntityFactory<T> factory,
                                                                                  float width, float height) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, name),
                FabricEntityTypeBuilder.create(group, factory).dimensions(EntityDimensions.fixed(width, height)).build());
    }

}
