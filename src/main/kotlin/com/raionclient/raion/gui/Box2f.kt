package com.raionclient.raion.gui

import com.raionclient.raion.utils.Vec2f

/**
 * @author cookiedragon234 24/Jun/2020
 */
open class Box2f(
	var posX: Float = 0f,
	var posY: Float = 0f,
	var sizeX: Float = 0f,
	var sizeY: Float = 0f
) {
	val rightX: Float
		get() = posX + sizeX
	val bottomY: Float
		get() = posY + sizeY
	
	fun contains(position: Vec2f): Boolean {
		return position.x in posX..rightX && position.y in posY..bottomY
	}
	
	val corners: Array<Vec2f>
		get() = arrayOf(Vec2f(posX, posY), Vec2f(sizeX, posY), Vec2f(sizeX, sizeY), Vec2f(posX, sizeY))
}
