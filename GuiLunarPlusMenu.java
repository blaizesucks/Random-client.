package net.lax1dude.eaglercraft.v1_8.mods.lunarpp.gui;

import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.LunarPlusMod;
import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.input.LunarPlusKeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiLunarPlusMenu extends GuiScreen {

    private final GuiScreen parent;

    public GuiLunarPlusMenu(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int y = this.height / 2 - 80;
        int x = this.width / 2 - 100;

        this.buttonList.add(new GuiButton(1, x, y, 200, 20, "Player Settings"));
        y += 24;
        this.buttonList.add(new GuiButton(2, x, y, 200, 20, "Visuals"));
        y += 24;
        this.buttonList.add(new GuiButton(3, x, y, 200, 20, "HUD & Layout"));
        y += 24;
        this.buttonList.add(new GuiButton(4, x, y, 200, 20, "Keybinds"));
        y += 24;
        this.buttonList.add(new GuiButton(5, x, y, 200, 20, "Profiles"));
        y += 24;
        this.buttonList.add(new GuiButton(0, x, y, 200, 20, "Back to Game  (Right Shift)"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                LunarPlusMod.setMenuOpen(false);
                break;
            case 4:
                // quick rebind for the four keys (minimal demo)
                mc.displayGuiScreen(new GuiLunarPlusRebind(this));
                break;
            default:
                break;
        }
    }

    @Override
    public void onGuiClosed() {
        // re-grab on close
        Minecraft.getMinecraft().setIngameFocus();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        // Allow Right Shift to also close
        LunarPlusKeyHandler k = LunarPlusMod.KEYS;
        if (k.keyMenu.getKeyCode() == keyCode) {
            LunarPlusMod.setMenuOpen(false);
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false; // keep world ticking
    }
}
