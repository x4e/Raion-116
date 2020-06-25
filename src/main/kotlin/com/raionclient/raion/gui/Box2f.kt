package com.raionclient.raion.gui

import com.raionclient.raion.utils.Vec2f
import com.raionclient.raion.utils.Vec2fMutable

/**
 * @author cookiedragon234 24/Jun/2020
 */
open class Box2f(
	posX: Float = 0f,
	posY: Float = 0f,
	sizeX: Float = 0f,
	sizeY: Float = 0f
) {
	var topLeft = Vec2fMutable(posX, posY)
	var bottomRight = Vec2fMutable(posX + sizeX, posY + sizeY)
	var posX: Float
		get() = topLeft.x
		set(value) { topLeft.x = value }
	var posY: Float
		get() = topLeft.y
		set(value) { topLeft.y = value }
	val sizeX: Float
		get() = bottomRight.x - topLeft.x
	val sizeY: Float
		get() = bottomRight.y - topLeft.y
	var rightX: Float
		get() = bottomRight.x
		set(value) { bottomRight.x = value }
	var bottomY: Float
		get() = bottomRight.y
		set(value) { bottomRight.y = value }
	
	fun contains(position: Vec2f): Boolean {
		return position.x in posX..rightX && position.y in posY..bottomY
	}
	
	val corners: Array<Vec2f>
		get() = arrayOf(Vec2f(posX, bottomY), bottomRight, Vec2f(rightX, posY), topLeft)
}
