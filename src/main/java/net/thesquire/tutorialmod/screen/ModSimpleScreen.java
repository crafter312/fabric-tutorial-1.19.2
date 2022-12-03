package net.thesquire.tutorialmod.screen;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thesquire.tutorialmod.TutorialMod;

public abstract class ModSimpleScreen<T extends ScreenHandler> extends HandledScreen<T> {
    protected final Identifier TEXTURE;

    public ModSimpleScreen(T handler, PlayerInventory inventory, Text title, String path) {
        super(handler, inventory, title);
        TEXTURE = new Identifier(TutorialMod.MOD_ID, path);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected abstract void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY);

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

}
