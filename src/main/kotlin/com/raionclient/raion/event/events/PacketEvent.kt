package com.raionclient.raion.event.events

import net.minecraft.network.Packet

/**
 * @author cookiedragon234 25/Jun/2020
 */
abstract class PacketEvent(open val packet: Packet<*>, open var cancelled: Boolean = false)
data class PacketReceiveEvent(override val packet: Packet<*>, override var cancelled: Boolean = false): PacketEvent(packet, cancelled) {
	constructor(packet: Packet<*>): this(packet, false)
}
data class PacketSendEvent(override val packet: Packet<*>, override var cancelled: Boolean = false): PacketEvent(packet, cancelled) {
	constructor(packet: Packet<*>): this(packet, false)
}
