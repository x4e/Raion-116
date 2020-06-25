package com.raionclient.raion.gui.clickgui

import com.raionclient.raion.gui.DrawableBox
import com.raionclient.raion.gui.font.RaionFontRenderer
import com.raionclient.raion.module.Category
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
class CategoryPanel(
	val category: Category
): DrawableBox() {
	var open = true
	var titleHeight = 0f
	var dragOffset: Vec2f? = null // If this is not null the panel is being dragged
	val moduleButtons: Collection<ModuleButton> = ModuleManager.modules.filter { it.category == category }.map { ModuleButton(it) }
	
	override fun render(matrices: MatrixStack, mousePos: Vec2f) {
		matrices.draw (glMode = GL11.GL_QUADS, format = VertexFormats.POSITION_COLOR) {
			box(this@CategoryPanel, Color(0, 0, 0, 20))
		}
		matrices.draw (glMode = GL11.GL_QUADS, format = VertexFormats.POSITION_COLOR) {
			box(posX + 1f, posY + 1f, rightX - 1f, posY + titleHeight - 1f, Color(0, 0, 0, 80))
		}
		val strHeight = RaionFontRenderer.getStringHeight(category.toString())
		titleHeight = strHeight + 8f
		RaionFontRenderer.drawCentered(matrices, category.toString(), posX + (sizeX / 2), posY + 4f, Color.WHITE.rgb)
		
		var currentY = posY + titleHeight
		if (open) {
			moduleButtons.forEach { child ->
				child.posX = this.posX
				child.posY = currentY
				
				child.render(matrices, mousePos)
				
				currentY += child.sizeY
			}
		}
		bottomRight.x = topLeft.x + 125f
		bottomRight.y = currentY
	}
	
	override fun mouseDown(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		var thisConsumed = false
		if (isBeingRenderered && isMouseInTitle(mousePos) && !consumed) {
			if (button == Mouse.MOUSE_LEFT) {
				dragOffset = mousePos - topLeft
				thisConsumed = true
			} else if (button == Mouse.MOUSE_RIGHT) {
				open = open.not()
				thisConsumed = true
			}
		} else {
			dragOffset = null
		}
		moduleButtons.forEach {
			it.isBeingRenderered = this.isBeingRenderered && open
			if (it.mouseDown(mousePos, button, thisConsumed || consumed)) {
				thisConsumed = true
			}
		}
		return thisConsumed
	}
	
	override fun mouseUp(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		var thisConsumed = false
		dragOffset = null
		moduleButtons.forEach {
			it.isBeingRenderered = this.isBeingRenderered && open
			if (it.mouseUp(mousePos, button, thisConsumed || consumed)) {
				thisConsumed = true
			}
		}
		return thisConsumed
	}
	
	override fun mouseDragged(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		var thisConsumed = false
		val dragOffset = dragOffset
		if (isBeingRenderered && !consumed && dragOffset != null) {
			this.topLeft.x = mousePos.x - dragOffset.x
			this.topLeft.y = mousePos.y - dragOffset.y
			thisConsumed = true
		}
		moduleButtons.forEach {
			it.isBeingRenderered = this.isBeingRenderered && open
			if (it.mouseDragged(mousePos, button, thisConsumed || consumed)) {
				thisConsumed = true
			}
		}
		return thisConsumed
	}
	
	fun isMouseInTitle(mousePos: Vec2f): Boolean
		= mousePos.x in posX..rightX && mousePos.y in posY..(posY + titleHeight)
}
