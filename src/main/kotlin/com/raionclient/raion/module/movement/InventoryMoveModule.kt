package com.raionclient.raion.module.movement

import com.raionclient.raion.event.events.KeyDownEvent
import com.raionclient.raion.event.events.KeyEvent
import com.raionclient.raion.event.events.KeyUpEvent
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import com.raionclient.raion.utils.Key.*
import com.raionclient.raion.utils.updateState
import net.minecraft.client.input.Input
import net.minecraft.client.input.KeyboardInput
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil

/**
 * @author cookiedragon234 25/Jun/2020
 */
object InventoryMoveModule: Module("Inventory Move", "Move while in your inventory", Category.MOVEMENT) {
	fun process(input: Input, sneakingPressed: Boolean) {
		if (input is KeyboardInput) {
			val settings = mc.options
			input.apply {
				settings.keyForward.updateState()
				settings.keyBack.updateState()
				settings.keyLeft.updateState()
				settings.keyRight.updateState()
				settings.keyJump.updateState()
				settings.keySprint.updateState()
				settings.keySneak.updateState()
				
				pressingForward = settings.keyForward.isPressed
				pressingBack = settings.keyBack.isPressed
				pressingLeft = settings.keyLeft.isPressed
				pressingRight = settings.keyRight.isPressed
				movementForward =
					if (pressingForward == pressingBack) 0.0f else if (pressingForward) 1.0f else -1.0f
				movementSideways =
					if (pressingLeft == pressingRight) 0.0f else if (pressingLeft) 1.0f else -1.0f
				jumping = settings.keyJump.isPressed
				sneaking = settings.keySneak.isPressed
				if (sneakingPressed) {
					movementSideways = (movementSideways.toDouble() * 0.3).toFloat()
					movementForward = (movementForward.toDouble() * 0.3).toFloat()
				}
			}
		}
	}
}
