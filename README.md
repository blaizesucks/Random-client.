# Lunar++ Integrated GUI & HUD for EaglercraftX 1.8.8 (Desktop Only)

This pack integrates a Lunar-style GUI and HUD **inside** the Eaglercraft client so the menu
opens from **inside the game** via the keybind system (default: **Right Shift**). No HTML overlay.
Android/mobile code is intentionally removed/omitted.

**What’s included**
- In-game **Right Shift** menu (toggle) that *ungrabs* the mouse (ESC-like) but does **not pause** the world
- Tabs: Player, Visuals, HUD, Keybinds, Profiles, About
- Draggable HUD modules: **CPS**, **Keystrokes**, **FPS** (positions persist in localStorage)
- Smooth **Zoom** (true FOV-based, default key: **C**), adjustable amount and easing
- **Freecam (overlay)** toggle placeholder (stops input capture; real camera detachment requires deeper hooks)
- **Toggle Sprint / Toggle Sneak** (client-side key state latching)
- **FullBright**, **Crosshair dot**, **HUD scaling**, **Saturation**
- **Profiles**: save/load/delete full config
- **Options → Controls** entries for all keybinds
- Desktop only – Android support removed

> Note: Advanced features that need access to Minecraft internals (true detached camera, hitbox render,
> packet-accurate CPS, server ping readout, etc.) require additional hooks in Minecraft classes.
> This patch scaffolds those hooks and marks them with `// LUNAR++ HOOK` comments.

---

## Apply to Source

These files assume an **MCP-like** source layout for EaglercraftX 1.8.8 (the archive repo uses similar names).
If your repo differs, adjust package paths accordingly.

1) **Copy** `src/` into your EaglercraftX 1.8.8 source tree root (it complements existing packages):
```
your-repo/
  src/
    net/
      lax1dude/
        eaglercraft/
          v1_8/
            mods/
              lunarpp/   <-- (new)
  (existing src for Minecraft/Eaglercraft)
```

2) **Patch** vanilla files using the unified diffs in `patches/`.
Apply with `git apply` (from repo root) or manually:
```
git apply patches/0001-minecraft-hooks-and-controls.diff
git apply patches/0002-ingame-overlay-hud-hooks.diff
```

3) **Build** with the original TeaVM/Gradle script your repo uses to make the single-file HTML.
This patch **does not** change the build pipeline—only adds sources and small hooks.

---

## Keybinds (default)
- Menu: **Right Shift** (`ShiftRight`)
- Zoom (hold): **C** (`KeyC`)
- Freecam toggle: **V** (`KeyV`)
- Hat toggle: **H** (`KeyH`)

All are available and **rebindable** in `Options → Controls → Lunar++` section.

---

## Where things live

- Core mod entry: `net.lax1dude.eaglercraft.v1_8.mods.lunarpp.LunarPlusMod`
- Key handling: `...lunarpp.input.LunarPlusKeyHandler`
- Options model: `...lunarpp.config.LunarPlusOptions`
- HUD: `...lunarpp.hud.LunarPlusHud`
- GUI screen: `...lunarpp.gui.GuiLunarPlusMenu`

**Hooks added** (via patches):
- `Minecraft.java`: tick & focus hooks, pointer lock toggle, FOV zoom multiplier
- `GameSettings.java`: registers keybinds & category
- `GuiOptions.java`: adds a “Lunar++ Settings” button and routes to `GuiLunarPlusMenu`
- `GuiIngame.java`: calls HUD renderer

---

## Troubleshooting

- **GUI doesn’t open**: ensure `Minecraft#runTick` has the `LUNAR++ HOOK` block that calls `LunarPlusMod.handleGlobalKey`.
- **No mouse unlock** on menu: confirm the hook `LunarPlusMod.setMenuOpen(...)` is called and `Minecraft.mouseHelper` is released.
- **Zoom feels weird**: adjust defaults in `LunarPlusOptions.DEFAULT_ZOOM_AMOUNT` and `DEFAULT_ZOOM_EASE`.
- **Build fails**: your package names might differ; search for classes mentioned in patches and adjust imports.

Good luck! — and yes, Android is excluded by design here.
