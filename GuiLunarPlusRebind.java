package net.lax1dude.eaglercraft.v1_8.mods.lunarpp.gui;

import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.LunarPlusMod;
import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.input.LunarPlusKeyHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiLunarPlusRebind extends GuiScreen {

    private final GuiScreen parent;
    private KeyBinding waiting = null;
    private GuiButton menuBtn, zoomBtn, freeBtn, hatBtn;

    public GuiLunarPlusRebind(GuiScreen parent){
        this.parent = parent;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int y = this.height / 2 - 50;
        int x = this.width / 2 - 100;
        LunarPlusKeyHandler k = LunarPlusMod.KEYS;
        menuBtn = new GuiButton(1, x, y, 200, 20, "Menu: " + Keyboard.getKeyName(k.keyMenu.getKeyCode())); y+=24;
        zoomBtn = new GuiButton(2, x, y, 200, 20, "Zoom: " + Keyboard.getKeyName(k.keyZoom.getKeyCode())); y+=24;
        freeBtn = new GuiButton(3, x, y, 200, 20, "Freecam: " + Keyboard.getKeyName(k.keyFreecam.getKeyCode())); y+=24;
        hatBtn  = new GuiButton(4, x, y, 200, 20, "Hat: " + Keyboard.getKeyName(k.keyHat.getKeyCode())); y+=24;
        this.buttonList.add(menuBtn);
        this.buttonList.add(zoomBtn);
        this.buttonList.add(freeBtn);
        this.buttonList.add(hatBtn);
        this.buttonList.add(new GuiButton(0, x, y, 200, 20, "Done"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(parent);
            return;
        }
        waiting = (button == menuBtn) ? LunarPlusMod.KEYS.keyMenu :
                  (button == zoomBtn) ? LunarPlusMod.KEYS.keyZoom :
                  (button == freeBtn) ? LunarPlusMod.KEYS.keyFreecam :
                  (button == hatBtn)  ? LunarPlusMod.KEYS.keyHat : null;
        button.displayString = "Press any key...";
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (waiting != null) {
            waiting.setKeyCode(keyCode);
            waiting = null;
            initGui(); // refresh labels
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() { return false; }
}
