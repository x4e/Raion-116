package com.raionclient.raion.mixin.entity;

import com.raionclient.raion.module.movement.JesusModule;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(LivingEntity.class)
public class MixinLivingEntity {
	@Inject(method = "canWalkOnFluid", at = @At(value = "HEAD"), cancellable = true)
	private void injectWalkOnFluid(Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
		//noinspection ConstantConditions
		if (((Object)this) instanceof ClientPlayerEntity && JesusModule.INSTANCE.getEnabled()) {
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
