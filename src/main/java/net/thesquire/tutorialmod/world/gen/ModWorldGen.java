package net.thesquire.tutorialmod.world.gen;

public class ModWorldGen {

    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlowers();

        ModEntitySpawn.addEntitySpawn();
    }

}
