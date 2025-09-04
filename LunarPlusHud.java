package net.lax1dude.eaglercraft.v1_8.mods.lunarpp.hud;

import net.lax1dude.eaglercraft.v1_8.mods.lunarpp.LunarPlusMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class LunarPlusHud {

    private final Minecraft mc = Minecraft.getMinecraft();
    private int clicksL = 0;
    private int clicksR = 0;
    private long lastCpsSec = 0;
    private int cpsL = 0;
    private int cpsR = 0;
    private int frames = 0;
    private long lastFpsSec = 0;
    private int fps = 0;

    public void render(float partialTicks) {
        if (mc.gameSettings == null || mc.thePlayer == null) return;
        ScaledResolution sr = new ScaledResolution(mc);
        FontRenderer fr = mc.fontRendererObj;
        float scale = LunarPlusMod.OPTIONS.hudScale;

        if (System.currentTimeMillis() - lastFpsSec >= 1000) {
            fps = frames;
            frames = 0;
            lastFpsSec = System.currentTimeMillis();
        }
        frames++;

        int x = 6, y = 6;
        fr.drawString("Lunar++", x, y, 0x79D1FF, true);
        y += 10;

        if (LunarPlusMod.OPTIONS.hudFPS) {
            fr.drawString("FPS: " + fps, x, y, 0xFFFFFF, false);
            y += 10;
        }
        if (LunarPlusMod.OPTIONS.hudCPS) {
            if (System.currentTimeMillis() - lastCpsSec >= 1000) {
                cpsL = clicksL; cpsR = clicksR; clicksL = 0; clicksR = 0;
                lastCpsSec = System.currentTimeMillis();
            }
            fr.drawString("CPS: " + cpsL + "L/" + cpsR + "R", x, y, 0xFFFFFF, false);
            y += 10;
        }
        if (LunarPlusMod.OPTIONS.hudKeystrokes) {
            String ks = (mc.gameSettings.keyBindForward.getIsKeyPressed() ? "W" : "w")
                    + " " + (mc.gameSettings.keyBindLeft.getIsKeyPressed() ? "A" : "a")
                    + " " + (mc.gameSettings.keyBindBack.getIsKeyPressed() ? "S" : "s")
                    + " " + (mc.gameSettings.keyBindRight.getIsKeyPressed() ? "D" : "d")
                    + " " + (mc.gameSettings.keyBindJump.getIsKeyPressed() ? "␣" : "·");
            fr.drawString("Keys: " + ks, x, y, 0xFFFFFF, false);
            y += 10;
        }
        if (LunarPlusMod.OPTIONS.hudDot) {
            // Draw a small center dot
            int cx = sr.getScaledWidth() / 2;
            int cy = sr.getScaledHeight() / 2;
            mc.ingameGUI.drawRect(cx - 1, cy - 1, cx + 1, cy + 1, 0xFFFFFFFF);
        }
    }

    // Called by mouse input hooks if you wire them (optional)
    public void notifyClick(int button) {
        if (button == 0) clicksL++;
        else if (button == 1) clicksR++;
    }
}
