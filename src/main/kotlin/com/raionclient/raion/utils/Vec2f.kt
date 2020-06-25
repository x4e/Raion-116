package com.raionclient.raion.utils

/**
 * @author cookiedragon234 24/Jun/2020
 */
open class Vec2f(
	x: Float,
	y: Float
) {
	open var x: Float = x
		protected set
	open var y: Float = y
		protected set
	
	operator fun plus(other: Vec2f): Vec2f {
		return Vec2f(this.x + other.x, this.y + other.y)
	}
	operator fun minus(other: Vec2f): Vec2f {
		return Vec2f(this.x - other.x, this.y - other.y)
	}
}
/*data class Vec2fImpl(
	override val x: Float,
	override val y: Float
): Vec2f(x, y)*/
class Vec2fMutable(
	x: Float,
	y: Float
): Vec2f(x, y) {
	override var x: Float = x
		public set
	override var y: Float = y
		public set
}

