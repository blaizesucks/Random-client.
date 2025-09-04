package net.lax1dude.eaglercraft.v1_8.mods.lunarpp;

import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.config.LunarPlusOptions;
import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.gui.GuiLunarPlusMenu;
import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.hud.LunarPlusHud;
import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.input.LunarPlusKeyHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class LunarPlusMod {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static final LunarPlusOptions OPTIONS = new LunarPlusOptions();
    private static final LunarPlusHud HUD = new LunarPlusHud();
    public static final LunarPlusKeyHandler KEYS = new LunarPlusKeyHandler();

    private static boolean menuOpen = false;

    public static void init() {
        // no-op placeholder, called from Minecraft start if needed
    }

    /** Called each tick from Minecraft.runTick (LUNAR++ HOOK) */
    public static void clientTick() {
        KEYS.tick();
        if (KEYS.consumeOpenMenu()) {
            setMenuOpen(!menuOpen);
        }
    }

    public static void setMenuOpen(boolean open) {
        if (menuOpen == open) return;
        menuOpen = open;
        if (open) {
            // Show GUI and release mouse (do not pause world)
            mc.displayGuiScreen(new GuiLunarPlusMenu(getCurrentScreen()));
        } else {
            // Close GUI and re-grab
            if (mc.currentScreen instanceof GuiLunarPlusMenu) {
                mc.displayGuiScreen(null);
            }
            mc.setIngameFocus(); // re-grab mouse
        }
    }

    public static boolean isMenuOpen() {
        return menuOpen;
    }

    private static GuiScreen getCurrentScreen() {
        return mc.currentScreen;
    }

    /** Called from GuiIngame to draw HUD */
    public static void renderHud(float partialTicks) {
        HUD.render(partialTicks);
    }

    /** Called from EntityRenderer FOV hook */
    public static float applyZoom(float fov) {
        if (!OPTIONS.zoomEnabled) return fov;
        if (!KEYS.isZoomHeld()) return fov;
        float amt = OPTIONS.zoomAmount; // >1.0 = zoom in
        // Convert: lower FOV looks zoomed; we scale fov down
        float scaled = fov / amt;
        return scaled;
    }
}
