package com.raionclient.raion.mixin.client.gui.hud;

import com.raionclient.raion.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(InGameOverlayRenderer.class)
public class MixinInGameOverlayRenderer {
	@Inject(method = "renderOverlays", at = @At(value = "HEAD"), cancellable = true)
	private static void renderOverlaysInject(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo ci) {
		if (NoRenderModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
}
