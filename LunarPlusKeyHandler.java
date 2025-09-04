package net.lax1dude.eaglercraft.v1_8.mods.lunarpp.input;

import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.LunarPlusMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

public class LunarPlusKeyHandler {

    public final KeyBinding keyMenu = new KeyBinding("Lunar++ Menu", Keyboard.KEY_RSHIFT, "Lunar++");
    public final KeyBinding keyZoom = new KeyBinding("Zoom (Hold)", Keyboard.KEY_C, "Lunar++");
    public final KeyBinding keyFreecam = new KeyBinding("Freecam Toggle", Keyboard.KEY_V, "Lunar++");
    public final KeyBinding keyHat = new KeyBinding("Hat Toggle", Keyboard.KEY_H, "Lunar++");

    private boolean wantOpenMenu = false;
    private boolean zoomHeld = false;

    public LunarPlusKeyHandler() {
        // Register in GameSettings via patch (see diff)
    }

    public void tick() {
        // Menu toggle
        if (keyMenu.isPressed()) {
            wantOpenMenu = true;
        }

        // Zoom (hold)
        zoomHeld = keyZoom.getIsKeyPressed();

        // Toggle sprint/sneak latching (simple client-side emulation)
        if (LunarPlusMod.OPTIONS.toggleSprint) {
            GameSettings gs = Minecraft.getMinecraft().gameSettings;
            if (gs != null) {
                gs.keyBindSprint.setPressed(true);
            }
        }
        if (LunarPlusMod.OPTIONS.toggleSneak) {
            GameSettings gs = Minecraft.getMinecraft().gameSettings;
            if (gs != null) {
                gs.keyBindSneak.setPressed(true);
            }
        }
    }

    public boolean consumeOpenMenu() {
        if (wantOpenMenu) {
            wantOpenMenu = false;
            return true;
        }
        return false;
    }

    public boolean isZoomHeld() {
        return zoomHeld;
    }
}
