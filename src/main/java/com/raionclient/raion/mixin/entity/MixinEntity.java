package com.raionclient.raion.mixin.entity;

import com.raionclient.raion.module.movement.VelocityModule;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(Entity.class)
public class MixinEntity {
	@Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
	private void pushAwayInject(Entity entity, CallbackInfo ci) {
		if (VelocityModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
}
