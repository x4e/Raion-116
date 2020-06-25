package com.raionclient.raion.gui.clickgui

import com.raionclient.raion.gui.DrawableBox
import com.raionclient.raion.gui.Panel
import com.raionclient.raion.gui.font.RaionFontRenderer
import com.raionclient.raion.module.Module
import com.raionclient.raion.module.ModuleManager
import com.raionclient.raion.utils.Mouse
import com.raionclient.raion.utils.Vec2f
import com.raionclient.raion.utils.box
import com.raionclient.raion.utils.draw
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.util.math.MatrixStack
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 * @author cookiedragon234 25/Jun/2020
 */
class ModuleButton(
	val module: Module
): DrawableBox() {
	var open = true
	var titleHeight = 0f
	var dragOffset: Vec2f? = null // If this is not null the panel is being dragged
	val valueButtons: Collection<ModuleButton> = arrayListOf()
	
	override fun render(matrices: MatrixStack, mousePos: Vec2f) {
		val colour = if (module.enabled) Color(255, 255, 255, 70) else Color(0, 0, 0, 50)
		matrices.draw (glMode = GL11.GL_QUADS, format = VertexFormats.POSITION_COLOR) {
			box(posX + 1f, posY + 1f, rightX - 1f, posY + titleHeight - 1f, colour)
		}
		val strHeight = RaionFontRenderer.getStringHeight(module.name)
		titleHeight = strHeight + 4f
		RaionFontRenderer.drawCentered(matrices, module.name, posX + (sizeX / 2), posY + 2f, Color.WHITE.rgb)
		
		var currentY = titleHeight
		for (child in valueButtons) {
			child.posX = this.posX
			child.posY = currentY
			
			child.render(matrices, mousePos)
			
			currentY += child.sizeY
		}
		bottomRight.x = topLeft.x + 125f
		bottomRight.y = topLeft.y + currentY
	}
	
	override fun mouseDown(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		var thisConsumed = false
		if (isMouseInTitle(mousePos) && !consumed && button == Mouse.MOUSE_LEFT) {
			module.enabled = module.enabled.not()
			thisConsumed = true
		}
		valueButtons.forEach {
			if (it.mouseDown(mousePos, button, thisConsumed || consumed)) {
				thisConsumed = true
			}
		}
		return thisConsumed
	}
	
	override fun mouseUp(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		var thisConsumed = false
		valueButtons.forEach {
			if (it.mouseUp(mousePos, button, thisConsumed || consumed)) {
				thisConsumed = true
			}
		}
		return thisConsumed
	}
	
	override fun mouseDragged(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		var thisConsumed = false
		valueButtons.forEach {
			if (it.mouseDragged(mousePos, button, thisConsumed || consumed)) {
				thisConsumed = true
			}
		}
		return thisConsumed
	}
	
	fun isMouseInTitle(mousePos: Vec2f): Boolean
		= mousePos.x in posX..rightX && mousePos.y in posY..(posY + titleHeight)
}
