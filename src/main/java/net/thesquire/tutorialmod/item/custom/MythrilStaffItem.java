package net.thesquire.tutorialmod.item.custom;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.thesquire.tutorialmod.world.dimension.custom.VoidDimension;

public class MythrilStaffItem extends Item {

    public MythrilStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient() && hand.equals(Hand.MAIN_HAND)) {
            if(world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                MinecraftServer minecraftServer = serverWorld.getServer();
                RegistryKey<World> registryKey = world.getRegistryKey() == World.OVERWORLD ? VoidDimension.WORLD_KEY : World.OVERWORLD;
                if(registryKey.equals(World.OVERWORLD)) {
                    return TypedActionResult.pass(user.getStackInHand(Hand.MAIN_HAND));
                }
                ServerWorld destServerWorld = minecraftServer.getWorld(registryKey);
                TeleportTarget targ = new TeleportTarget(VoidDimension.VOID_SPAWN_POS, Vec3d.ZERO,
                        user.getYaw(), user.getPitch());
                FabricDimensions.teleport(user, destServerWorld, targ);
                VoidDimension.createVoidSpawnPlatform(destServerWorld);
            }
        }
        return TypedActionResult.success(user.getStackInHand(Hand.MAIN_HAND), true);
    }

}
