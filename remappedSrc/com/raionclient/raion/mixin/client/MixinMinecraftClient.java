package com.raionclient.raion.mixin.client;

import com.raionclient.raion.event.events.TickEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import com.raionclient.raion.imixin.client.IMixinMinecraftClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient implements IMixinMinecraftClient {
	@Inject(method = "getWindowTitle", at = @At("RETURN"), cancellable = true)
	private void injectWindowTitle(CallbackInfoReturnable<String> cir) {
		cir.setReturnValue(cir.getReturnValue() + " [Raion]");
	}
	
	@Inject(method = "tick", at = @At("HEAD"))
	private void tickInject(CallbackInfo ci) {
		EventDispatcher.dispatch(new TickEvent());
	}
	
	@Override
	@Accessor
	public abstract void setItemUseCooldown(int itemUseCooldown);
	
	@Override
	@Accessor
	public abstract int getItemUseCooldown();
}
