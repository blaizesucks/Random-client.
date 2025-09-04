package net.lax1dude.eaglercraft.v1_8.mods.lunarpp.config;

import java.util.prefs.Preferences;

public class LunarPlusOptions {

    private static final String NS = "lunarpp_";

    public boolean zoomEnabled = true;
    public float zoomAmount = 1.75f;
    public float zoomEase = 0.18f;

    public boolean hudKeystrokes = true;
    public boolean hudCPS = true;
    public boolean hudFPS = true;
    public boolean hudFullbright = false;
    public boolean hudDot = false;
    public float hudScale = 1.0f;
    public float saturation = 1.0f;
    public boolean thirdPersonOffset = false;

    public boolean toggleSprint = false;
    public boolean toggleSneak = false;

    public LunarPlusOptions() {
        load();
    }

    public void load() {
        try {
            Preferences p = Preferences.userRoot().node(NS);
            zoomEnabled = p.getBoolean("zoomEnabled", zoomEnabled);
            zoomAmount = p.getFloat("zoomAmount", zoomAmount);
            zoomEase = p.getFloat("zoomEase", zoomEase);
            hudKeystrokes = p.getBoolean("hudKeystrokes", hudKeystrokes);
            hudCPS = p.getBoolean("hudCPS", hudCPS);
            hudFPS = p.getBoolean("hudFPS", hudFPS);
            hudFullbright = p.getBoolean("hudFullbright", hudFullbright);
            hudDot = p.getBoolean("hudDot", hudDot);
            hudScale = p.getFloat("hudScale", hudScale);
            saturation = p.getFloat("saturation", saturation);
            thirdPersonOffset = p.getBoolean("thirdPersonOffset", thirdPersonOffset);
            toggleSprint = p.getBoolean("toggleSprint", toggleSprint);
            toggleSneak = p.getBoolean("toggleSneak", toggleSneak);
        } catch (Throwable t) {}
    }

    public void save() {
        try {
            Preferences p = Preferences.userRoot().node(NS);
            p.putBoolean("zoomEnabled", zoomEnabled);
            p.putFloat("zoomAmount", zoomAmount);
            p.putFloat("zoomEase", zoomEase);
            p.putBoolean("hudKeystrokes", hudKeystrokes);
            p.putBoolean("hudCPS", hudCPS);
            p.putBoolean("hudFPS", hudFPS);
            p.putBoolean("hudFullbright", hudFullbright);
            p.putBoolean("hudDot", hudDot);
            p.putFloat("hudScale", hudScale);
            p.putFloat("saturation", saturation);
            p.putBoolean("thirdPersonOffset", thirdPersonOffset);
            p.putBoolean("toggleSprint", toggleSprint);
            p.putBoolean("toggleSneak", toggleSneak);
        } catch (Throwable t) {}
    }
}
