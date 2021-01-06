package me.preciouso.directionhud;

import me.preciouso.directionhud.callback.HudHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class DirectionHUD implements ModInitializer {

    @Override
    public void onInitialize() {
        HudRenderCallback.EVENT.register(new HudHelper());
    }
}
