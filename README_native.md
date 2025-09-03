# Native Mod (TeaVM) — Overview

> This path integrates features **inside** the Minecraft 1.8.8 client compiled by TeaVM.

## Goals
- Right Shift menu rendered in the game's GUI layer
- Draggable HUD (CPS/FPS/Coords/Keystrokes) using in-game render hooks
- Smooth FOV Zoom (toggle/hold)
- Freecam (camera detachment) with server-safe behavior
- Keybinds in the Controls menu

## Suggested Hook Points (Guidance)
- **Keybinds**: extend the game's keybinding registry; add `KEY_LUNAR_PLUS_MENU (Right Shift)`, `KEY_ZOOM (C)`, `KEY_FREECAM (F6)`
- **GUI Screen**: create `LunarPlusScreen extends GuiScreen` (or equivalent class name in the ECX codebase)
- **HUD**: render in the layer where debug text or hotbar is drawn (post-world render)
- **Zoom**: adjust FOV value in the camera setup method; smooth with lerp
- **Freecam**: detach camera position from player, freeze server inputs while allowing local movement

> Class names differ in ECX — search for where Options/Controls are defined, in-game HUD is drawn, and FOV is calculated.

## Build
1. Clone source:
```
git clone https://github.com/Eaglercraft-Archive/Eaglercraftx-1.8.8-src.git
```
2. Implement features following the above hooks.
3. Run the project's TeaVM/Gradle build to produce the offline HTML.
