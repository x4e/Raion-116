package com.raionclient.raion.mixin.client.network;

import com.raionclient.raion.module.movement.VelocityModule;
import com.raionclient.raion.module.player.NoRotateModule;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.HeldItemChangeS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
	@Redirect(method = "onExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
	private void onExplosionRedir(ClientPlayerEntity clientPlayerEntity, Vec3d velocity) {
		if (!VelocityModule.INSTANCE.getEnabled()) {
			clientPlayerEntity.setVelocity(velocity);
		}
	}
	
	@Redirect(method = "onVelocityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getEntityById(I)Lnet/minecraft/entity/Entity;"))
	private Entity getEntityForVelocityRedir(ClientWorld clientWorld, int id) {
		Entity entity = clientWorld.getEntityById(id);
		if (VelocityModule.INSTANCE.getEnabled() && entity instanceof ClientPlayerEntity) {
			return null;
		}
		return entity;
	}
	
	@Inject(method = "onHeldItemChange", at = @At("HEAD"), cancellable = true)
	private void onHeldItemChangeInject(HeldItemChangeS2CPacket packet, CallbackInfo ci) {
		if (NoRotateModule.INSTANCE.getEnabled()) {
			ci.cancel();
		}
	}
	
	@Redirect(method = "onPlayerPositionLook", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;updatePositionAndAngles(DDDFF)V"))
	private void updatePositionAndAnglesRedir(PlayerEntity playerEntity, double x, double y, double z, float yaw, float pitch) {
		if (NoRotateModule.INSTANCE.getEnabled()) {
			playerEntity.updatePositionAndAngles(x, y, z, playerEntity.yaw, playerEntity.pitch);
		} else {
			playerEntity.updatePositionAndAngles(x, y, z, yaw, pitch);
		}
	}
}
