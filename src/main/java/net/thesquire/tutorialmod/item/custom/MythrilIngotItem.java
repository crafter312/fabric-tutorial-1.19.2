package net.thesquire.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.thesquire.tutorialmod.item.ModItems;

public class MythrilIngotItem extends Item {

    public MythrilIngotItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if(!world.isClient()) {
            BlockPos pos = context.getBlockPos();
            Block block = context.getWorld().getBlockState(pos).getBlock();
            PlayerEntity player = context.getPlayer();
            if(block == Blocks.GRINDSTONE) {
                context.getStack().decrement(1);

                Vec3i facing = context.getPlayerFacing().getVector();
                Double posX = pos.getX() + 0.5 - facing.getX()*0.5;
                Double posZ = pos.getZ() + 0.5 - facing.getZ()*0.5;
                ItemEntity itemEntity = new ItemEntity(world, posX, pos.getY(), posZ, new ItemStack(ModItems.MYTHRIL_DUST, 1));
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);

                player.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1, 1);
                return ActionResult.SUCCESS;
            }
        }

        return super.useOnBlock(context);
    }
}
