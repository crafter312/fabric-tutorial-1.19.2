package net.thesquire.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thesquire.tutorialmod.item.ModItems;
import net.thesquire.tutorialmod.particle.ModParticles;
import net.thesquire.tutorialmod.sound.ModSounds;
import net.thesquire.tutorialmod.util.InventoryUtil;
import net.thesquire.tutorialmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tutorialmod.dowsing_rod.tooltip.shift"));
            tooltip.add(Text.translatable("item.tutorialmod.dowsing_rod.tooltip.shift_2"));
        } else {
            tooltip.add(Text.translatable("item.tutorialmod.dowsing_rod.tooltip"));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockBelow = context.getWorld().getBlockState(positionClicked.down(i));

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.down(i), player, blockBelow.getBlock());
                    foundBlock = true;

                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addNbtToDataTablet(player, positionClicked.down(i), blockBelow.getBlock());
                    }
                    spawnFoundParticles(context, positionClicked);
                    context.getWorld().playSound(player, positionClicked, ModSounds.DOWSING_ROD_FOUND_ORE,
                            SoundCategory.BLOCKS, 1f, 1f);

                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(Text.translatable("item.tutorialmod.dowsing_rod.no_valuables"), false);
            }
        }

        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }

    /*****************************************************************************************************************/

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow) {
        player.sendMessage(Text.literal("Found " + blockBelow.asItem().getName().getString() + " at (" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isIn(ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS);
    }

    private void addNbtToDataTablet(PlayerEntity player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("tutorialmod.last_ore", "Found " + blockBelow.asItem().getName().getString() + " at (" +
                pos.getX() + ", "+ pos.getY() + ", "+ pos.getZ() + ")");

        dataTablet.setNbt(nbtData);
    }

    private void spawnFoundParticles(ItemUsageContext pContext, BlockPos positionClicked) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                pContext.getWorld().addParticle(ModParticles.CITRINE_PARTICLE,
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }

}
