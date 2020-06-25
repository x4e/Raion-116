package com.raionclient.raion.mixin.client.network;

import com.raionclient.raion.module.movement.InventoryMoveModule;
import com.raionclient.raion.module.movement.NoSlowModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
	@Shadow public Input input;
	
	@Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;tick(Z)V"))
	private void tickMovementRedir(Input input, boolean bl) {
		if (NoSlowModule.INSTANCE.getEnabled()) {
			bl = false;
		}
		MinecraftClient mc = MinecraftClient.getInstance();
		boolean shouldProcess = (mc.currentScreen == null || mc.currentScreen.passEvents);
		if (!shouldProcess && InventoryMoveModule.INSTANCE.getEnabled()) {
			InventoryMoveModule.INSTANCE.process(input, bl);
		} else {
			input.tick(bl);
		}
	}
	
	@Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
	private boolean isUsingItemRedir(ClientPlayerEntity clientPlayerEntity) {
		if (NoSlowModule.INSTANCE.getEnabled()) {
			return false;
		}
		return clientPlayerEntity.isUsingItem();
	}
}
