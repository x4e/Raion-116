package com.raionclient.raion.mixin.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.raionclient.raion.module.render.NoRenderModule;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(BackgroundRenderer.class)
public class MixinBackgroundRenderer {
	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	private static void renderInject(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci) {
		if (NoRenderModule.INSTANCE.getEnabled()) {
			RenderSystem.clearColor(1, 1, 1, 0.0F);
			ci.cancel();
		}
	}
	
	@Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
	private static void applyFogInject(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
		if (NoRenderModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
}
