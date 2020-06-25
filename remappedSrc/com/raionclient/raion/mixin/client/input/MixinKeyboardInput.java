package com.raionclient.raion.mixin.client.input;

import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(KeyboardInput.class)
public class MixinKeyboardInput {
	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void tickInject(boolean bl, CallbackInfo ci) {
	
	}
}
