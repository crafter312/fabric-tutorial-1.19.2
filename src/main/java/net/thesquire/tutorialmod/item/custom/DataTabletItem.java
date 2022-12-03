package net.thesquire.tutorialmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DataTabletItem extends Item {

    public DataTabletItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.getStackInHand(hand).hasNbt()) {
            if(Screen.hasShiftDown()) {
                user.getStackInHand(hand).setNbt(new NbtCompound());
            }
            else if(world.isClient()) {
                user.sendMessage(Text.literal(user.getStackInHand(hand).getNbt().getString("tutorialmod.last_ore")),
                        false);
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt()) {
            String currentOre = stack.getNbt().getString("tutorialmod.last_ore");
            tooltip.add(Text.literal(currentOre));
        }
    }
}
