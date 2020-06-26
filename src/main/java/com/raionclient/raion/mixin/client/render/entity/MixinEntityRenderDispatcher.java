package com.raionclient.raion.mixin.client.render.entity;

import com.raionclient.raion.event.events.PostRenderEntityEvent;
import com.raionclient.raion.event.events.PreRenderEntityEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 26/Jun/2020
 */
@Mixin(EntityRenderDispatcher.class)
public class MixinEntityRenderDispatcher {
	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	private void injectRender(Entity entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
		PreRenderEntityEvent event = new PreRenderEntityEvent(entity);
		EventDispatcher.dispatch(event);
		if (event.getCancelled()) {
			ci.cancel();
		}
	}
	@Inject(method = "render", at = @At("RETURN"), cancellable = true)
	private void injectRenderReturn(Entity entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
		PostRenderEntityEvent event = new PostRenderEntityEvent(entity);
		EventDispatcher.dispatch(event);
	}
}
