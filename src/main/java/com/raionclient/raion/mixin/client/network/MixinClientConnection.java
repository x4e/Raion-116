package com.raionclient.raion.mixin.client.network;

import com.raionclient.raion.event.events.PacketEvent;
import com.raionclient.raion.event.events.PacketReceiveEvent;
import com.raionclient.raion.event.events.PacketSendEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(ClientConnection.class)
public class MixinClientConnection {
	@Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
	private void injectSendPacket(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo ci) {
		PacketEvent event = new PacketSendEvent(packet);
		EventDispatcher.dispatch(event);
		if (event.getCancelled()) {
			ci.cancel();
		}
	}
	@Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
	private void injectReadPacket(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
		PacketEvent event = new PacketReceiveEvent(packet);
		EventDispatcher.dispatch(event);
		if (event.getCancelled()) {
			ci.cancel();
		}
	}
}
