package com.raionclient.raion.mixin.client.render;

import com.raionclient.raion.event.events.PostRenderWorldEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import com.raionclient.raion.module.render.EspModule;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
	@Inject(method = "drawEntityOutlinesFramebuffer", at = @At("HEAD"))
	private void drawEntityOutlinesFramebufferInject(CallbackInfo ci) {
		EspModule.INSTANCE.drawVertex();
	}
	@Inject(method = "drawEntityOutlinesFramebuffer", at = @At("RETURN"))
	private void drawEntityOutlinesFramebufferInjectRet(CallbackInfo ci) {
		EspModule.INSTANCE.drawVertexend();
	}
}
