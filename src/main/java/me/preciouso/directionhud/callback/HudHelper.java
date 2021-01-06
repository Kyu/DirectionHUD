package me.preciouso.directionhud.callback;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

import java.util.Locale;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class HudHelper implements HudRenderCallback {
    @Override
    public void onHudRender(MatrixStack matrixStack, float v) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;

        if (!MinecraftClient.getInstance().options.debugEnabled) {
            if (player != null) {
                // from: net.minecraft.client.gui.hud.DebugHud#getLeftText

                // Prepare string
                Direction playerDir = player.getHorizontalFacing();
                String cardinalString;
                switch (playerDir) {
                    case NORTH:
                        cardinalString = "Towards negative Z";
                        break;
                    case SOUTH:
                        cardinalString = "Towards positive Z";
                        break;
                    case WEST:
                        cardinalString = "Towards negative X";
                        break;
                    case EAST:
                        cardinalString = "Towards positive X";
                        break;
                    default:
                        cardinalString = "Invalid";
                }

                String facing = String.format(Locale.ROOT, "Facing: %s (%s) (%.1f / %.1f)", playerDir, cardinalString,
                        MathHelper.wrapDegrees(player.yaw), MathHelper.wrapDegrees(player.pitch));
                int stringWidth = MinecraftClient.getInstance().textRenderer.getWidth(facing);

                // Fill with grey and render text as white
                fill(matrixStack, 1, 1, 2 + stringWidth + 1, 11 - 1, -1873784752);
                renderer.draw(matrixStack, facing, 2.0F, 2.0F, 14737632);
            }
        }
    }
}
