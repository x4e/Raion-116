package com.raionclient.raion.mixin.client.render;

import com.raionclient.raion.event.events.PostRenderWorldEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import com.raionclient.raion.imixin.client.render.IMixinGameRenderer;
import com.raionclient.raion.module.render.NoRenderModule;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer implements IMixinGameRenderer {
	@Inject(method = "bobViewWhenHurt", at = @At("HEAD"), cancellable = true)
	private void injectHurtCam(MatrixStack matrixStack, float f, CallbackInfo ci) {
		if (NoRenderModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
	
	@Final
	@Shadow
	private Camera camera;
	
	@Inject(
		method = "renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V",
		at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = Opcodes.GETFIELD, ordinal = 0)
	)
	private void postRenderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
		EventDispatcher.dispatch(new PostRenderWorldEvent(matrices, tickDelta, camera));
	}
	
	@Invoker(value = "loadShader")
	@Override
	public abstract void loadShader0(Identifier identifier);
}
