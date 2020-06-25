package com.raionclient.raion.module.player

import com.raionclient.raion.event.events.PacketSendEvent
import com.raionclient.raion.imixin.client.network.packet.IMixinPlayerMoveC2SPacket
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket

/**
 * @author cookiedragon234 25/Jun/2020
 */
object NoFallModule: Module("No Fall", "Dont take fall damage", Category.PLAYER) {
	init {
		register { event: PacketSendEvent ->
			val packet = event.packet
			if (packet is PlayerMoveC2SPacket) {
				packet as IMixinPlayerMoveC2SPacket
				packet.setOnGround(true)
			}
		}
	}
}
