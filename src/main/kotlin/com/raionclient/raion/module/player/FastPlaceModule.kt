package com.raionclient.raion.module.player

import com.raionclient.raion.event.events.TickEvent
import com.raionclient.raion.imixin.client.IMixinMinecraftClient
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module

/**
 * @author cookiedragon234 25/Jun/2020
 */
object FastPlaceModule: Module("Fast Place", "Place items faster", Category.PLAYER) {
	init {
		register { event: TickEvent ->
			val mc = mc as IMixinMinecraftClient
			mc.itemUseCooldown = 0
		}
	}
}
