# Wrapper Build

Produce a **single-file** EaglercraftX client with a Lunar-style menu and HUD by embedding an existing offline HTML.

## Build

```bash
python3 wrapper/build.py /path/to/EaglercraftX_1.8_u53_Offline_Signed.html out/EaglercraftX_LunarPlus_WRAPPED.html
```

- The wrapper ensures `allow="gamepad *"` so the gamepad permissions policy doesn’t crash startup.
- Right Shift opens the menu. Zoom is **C** by default.
