package com.raionclient.raion.mixin.client.render;

import com.raionclient.raion.module.render.NoRenderModule;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(BackgroundRenderer.class)
public class MixinBackgroundRenderer {
	@Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
	private static void applyFogInject(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
		if (NoRenderModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
}
