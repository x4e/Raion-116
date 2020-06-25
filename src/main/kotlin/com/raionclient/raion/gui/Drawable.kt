package com.raionclient.raion.gui

import com.raionclient.raion.utils.Mouse
import com.raionclient.raion.utils.Vec2f
import net.minecraft.client.util.math.MatrixStack

/**
 * @author cookiedragon234 24/Jun/2020
 */
interface Drawable {
	var isBeingRenderered: Boolean
	fun onUpdate() {}
	fun render(matrices: MatrixStack, mousePos: Vec2f)
	fun mouseDown(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean
	fun mouseUp(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean
	fun mouseDragged(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean
}
