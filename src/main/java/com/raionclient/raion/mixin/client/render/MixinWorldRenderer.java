package com.raionclient.raion.mixin.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.raionclient.raion.event.events.PostRenderWorldEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import com.raionclient.raion.module.render.EspModule;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;
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
	@Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
	private void injectRenderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
		EspModule.INSTANCE.preRenderEntity(entity);
	}
	@Inject(method = "renderEntity", at = @At("RETURN"), cancellable = true)
	private void injectRenderEntityReturn(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
		EspModule.INSTANCE.postRenderEntity(entity);
	}
}
