package com.raionclient.raion.mixin.client.render;

import com.raionclient.raion.module.render.NoRenderModule;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(GameRenderer.class)
public class MixinGameRenderer {
	@Inject(method = "bobViewWhenHurt", at = @At("HEAD"), cancellable = true)
	private void injectHurtCam(MatrixStack matrixStack, float f, CallbackInfo ci) {
		if (NoRenderModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
}
