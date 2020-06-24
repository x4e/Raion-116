package com.raionclient.raion.gui

import com.raionclient.raion.Raion
import com.raionclient.raion.gui.font.RaionFontRenderer
import com.raionclient.raion.utils.Mouse
import com.raionclient.raion.utils.Vec2f
import com.raionclient.raion.utils.box
import com.raionclient.raion.utils.draw
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.render.VertexFormats.POSITION
import net.minecraft.client.render.VertexFormats.POSITION_COLOR
import net.minecraft.client.util.math.MatrixStack
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_LINE_LOOP
import org.lwjgl.opengl.GL11.GL_QUADS
import java.awt.Color

/**
 * @author cookiedragon234 24/Jun/2020
 */
open class Panel(
	val title: String,
	val children: MutableCollection<Drawable>
): Box2f(), Drawable {
	override fun render(matrices: MatrixStack, mousePos: Vec2f) {
		matrices.draw (glMode = GL_LINE_LOOP, format = POSITION_COLOR) {
			box(this@Panel, Color.BLACK)
		}
		matrices.draw (glMode = GL_QUADS, format = POSITION_COLOR) {
			box(this@Panel, Color(0, 0, 0, 100))
		}
		RaionFontRenderer.drawCentered(matrices, title, posX + (sizeX / 2), posY, Color.WHITE.rgb)
	}
	
	override fun mouseDown(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun mouseUp(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun mouseDragged(mousePos: Vec2f, button: Mouse, consumed: Boolean): Boolean {
		TODO("Not yet implemented")
	}
	
}
