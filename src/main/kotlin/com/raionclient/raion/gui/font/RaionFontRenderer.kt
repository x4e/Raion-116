package com.raionclient.raion.gui.font

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Matrix4f

/**
 * @author cookiedragon234 24/Jun/2020
 */
interface RaionFontRenderer {
	fun drawString(matrix: MatrixStack, str: String, x: Float, y: Float, color: Int, shadow: Boolean = false)
	fun getStringHeight(str: String): Float
	fun getStringWidth(str: String): Float
	
	fun drawCentered(matrix: MatrixStack, str: String, x: Float, y: Float, color: Int, shadow: Boolean = false) {
		val width = getStringWidth(str)
		val realX = x - (width / 2)
		drawString(matrix, str, realX, y, color, shadow)
	}
	
	/**
	 * The default implementation, at the moment mojangs font renderer
	 */
	companion object: RaionFontRenderer {
		private val mc = MinecraftClient.getInstance()
		override fun drawString(matrix: MatrixStack, str: String, x: Float, y: Float, color: Int, shadow: Boolean) {
			if (shadow) {
				mc.textRenderer.drawWithShadow(matrix, str, x, y, color)
			} else {
				mc.textRenderer.draw(matrix, str, x, y, color)
			}
		}
		
		override fun getStringHeight(str: String): Float {
			return mc.textRenderer.fontHeight.toFloat()
		}
		
		override fun getStringWidth(str: String): Float {
			return mc.textRenderer.getWidth(str).toFloat()
		}
	}
}
