# EaglercraftX 1.8.8 — LunarPlus

A mod suite for **EaglercraftX 1.8.8** with a Lunar-style menu and HUD.

This repo contains two tracks:

1. **Wrapper Build (Quick & Works Now)**
   - Produces a **single-file HTML** client by embedding your existing `EaglercraftX_1.8_Signed_Offline.html` inside a wrapper that adds:
     - Right-Shift mod menu (Render / Player / HUD / Settings)
     - Draggable HUD (CPS, FPS, Coords, Keystrokes) + **Move HUD** button
     - Smooth Zoom (hold **C** by default)
     - Custom crosshair
     - Fixes the **gamepad permissions** crash by using an iframe with `allow="gamepad *"`
   - No source patching required.

2. **Native TeaVM Mod (Advanced)**
   - Source-level patching of the EaglercraftX 1.8.8 client (TeaVM).
   - Integrates Right-Shift GUI, keybinds, freecam, zoom, HUD, and settings **inside the game**.
   - Recommended path if you want *true* freecam and Controls-menu integration.

---

## Quick Start (Wrapper Build)

> This is the fastest way to get a working client with the mod menu and HUD.

**Requirements:** Python 3.9+

1. Download the official offline build HTML from your source (e.g., IPFS zip) and extract it, or use your existing file. You want a file named **`EaglercraftX_1.8_u53_Offline_Signed.html`** (the exact name can vary; any valid offline single-file HTML works).

2. Run:
```bash
python3 wrapper/build.py /path/to/EaglercraftX_1.8_u53_Offline_Signed.html out/EaglercraftX_LunarPlus_WRAPPED.html
```

3. Open the generated `out/EaglercraftX_LunarPlus_WRAPPED.html` in Chrome/Edge.

### Default Controls

- **Right Shift** → Open/close mod menu
- **C** → Zoom (hold to zoom; toggle in Render tab)
- Bottom-right **Move HUD** button to reposition widgets (auto-saves)

> The wrapper intentionally **does not block typing** in chat when the menu is closed and removes the top lip.

---

## Native Mod (TeaVM) — *Advanced Outline*

If you want **true freecam**, internal **Controls** integration, and FOV-based zoom that matches 1.8 exactly, use the TeaVM source path.

### Steps (high level)

1. Clone the archived source:
```
git clone https://github.com/Eaglercraft-Archive/Eaglercraftx-1.8.8-src.git
```

2. Review `native-mod/README_native.md` for the suggested patch points:
   - Add a **Right Shift** keybind
   - Inject a **LunarPlusScreen** GUI (in-game GUI layer)
   - Implement **HUD overlays** via the in-game renderer
   - Implement **Zoom** via FOV manipulation
   - Implement **Freecam** by detaching the camera from the player

3. Build using the repo’s TeaVM build scripts. Replace the produced HTML’s body with the wrapper **overlay** if you still want the HUD move helper (optional).

---

## Repo Layout

```
.
├─ wrapper/
│  ├─ build.py                 # CLI: embed upstream HTML into the LunarPlus wrapper
│  ├─ template_wrapper.html    # Wrapper template with mod menu + HUD
│  └─ README_wrapper.md
├─ native-mod/
│  ├─ README_native.md         # Where to patch TeaVM sources (classes & hooks)
│  └─ examples/                # Illustrative pseudo-code
├─ .github/workflows/
│  └─ build.yml                # Optional CI to assemble the wrapper from an uploaded artifact path
├─ out/                        # Build outputs (gitignored)
├─ .gitignore
├─ LICENSE
└─ README.md
```

---

## FAQ

**Q: Do I need a GitHub repo?**  
A: Not to *use* the single HTML. But a repo makes it easier to track changes and collaborate.

**Q: Why not just inject JavaScript into the HTML directly?**  
A: The game is TeaVM-compiled. Overlays work, but **native** features (freecam, keybind settings) need **source-level** hooks.

**Q: It says “file not found.”**  
A: This wrapper is one single file when built. If you see that error, make sure you opened the generated `out/EaglercraftX_LunarPlus_WRAPPED.html` and not the template.

**Q: Gamepad crash?**  
A: The wrapper gives the game an iframe with `allow="gamepad *"`, which avoids the permissions error environments sometimes trigger.
