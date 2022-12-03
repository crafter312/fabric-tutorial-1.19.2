package net.thesquire.tutorialmod.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

@Mixin(AbstractBlock.class)
public abstract class ModCropBlock {

    /**
     * This method adds right click to harvest for all crops.
     * Since it's added to the {@code AbstractBlock} class and
     * since the crop items dropped are derived from loot tables,
     * it should work on crops from other mods too. See
     * <a href="https://www.reddit.com/r/fabricmc/comments/p4ab7i/overwriting_inherited_method_with_mixin/">this reddit page</a>
     * for information about how to modify an inherited method
     * (why I modify {@code AbstractBlock} here instead of
     * {@code CropBlock}). This method is also a good example of
     * how to return early from your injected code (see
     * <a href="https://fabricmc.net/wiki/tutorial:mixin_injects">this fabric wiki page</a>
     * for more information).
     */
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    public void injectOnUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit,
                                          CallbackInfoReturnable<ActionResult> info) {
        if(!world.isClient()) {
            if(state.getBlock() instanceof CropBlock) {
                CropBlock block = (CropBlock) state.getBlock();
                if (state.get(block.getAgeProperty()).intValue() == block.getMaxAge()) {

                    // reset crop age to 0
                    world.setBlockState(pos, state.with(block.getAgeProperty(), 0), Block.NOTIFY_ALL);

                    // build loot from crop
                    ServerWorld serverWorld = Objects.requireNonNull(world.getServer()).getWorld(world.getRegistryKey());
                    LootContext.Builder builder = new LootContext.Builder(serverWorld).parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                            .parameter(LootContextParameters.BLOCK_STATE, state).optionalParameter(LootContextParameters.BLOCK_ENTITY, world.getBlockEntity(pos))
                            .optionalParameter(LootContextParameters.THIS_ENTITY, player).parameter(LootContextParameters.TOOL, player.getStackInHand(hand));
                    List<ItemStack> list = state.getDroppedStacks(builder);

                    // construct ItemStack to drop
                    decreaseItemInList(list, block.asItem());
                    dropItemsInList(list, world, pos);
                    info.setReturnValue(ActionResult.SUCCESS);
                    info.cancel();
                }
            }
        }
    }

    /***************************************************************************************************************/

    private void decreaseItemInList(List<ItemStack> list, Item item) {
        for(int i = 0; i < list.size(); i++) {
            ItemStack stack = list.get(i);
            if(stack.getItem().equals(item)) {
                stack.decrement(1);
                list.set(i, stack);
            }
        }
    }

    private void dropItemsInList(List<ItemStack> list, World world, BlockPos pos) {
        for(ItemStack stack : list) {
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, stack);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
    }

}
