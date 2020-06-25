package com.raionclient.raion.module.movement

import com.raionclient.raion.event.events.TickEvent
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module

/**
 * @author cookiedragon234 25/Jun/2020
 */
object FlightModule: Module("Flight", "Fly like youre in creative", Category.MOVEMENT) {
	var original: Boolean? = null
	
	override fun onEnabled() {
	}
	
	override fun onDisabled() {
		mc.player?.abilities?.flying = original ?: false
	}
	
	init {
		register { event: TickEvent ->
			val flying = mc.player?.abilities?.flying
			if (flying != null && original == null) {
				original = flying
			}
			mc.player?.abilities?.flying = true
		}
	}
}
