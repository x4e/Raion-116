package com.raionclient.raion.mixin.client.network;

import com.raionclient.raion.module.player.ReachModule;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager {
	@Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
	private void injectReachDistance(CallbackInfoReturnable<Float> cir) {
		if (ReachModule.INSTANCE.getEnabled()) {
			cir.setReturnValue(7f);
		}
	}
}
