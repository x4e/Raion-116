package com.raionclient.raion.mixin.client.network.packet;

import com.raionclient.raion.imixin.client.network.packet.IMixinPlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(PlayerMoveC2SPacket.class)
public abstract class MixinPlayerMoveC2SPacket implements IMixinPlayerMoveC2SPacket {
	@Override
	@Accessor
	public abstract void setOnGround(boolean onGround);
}
