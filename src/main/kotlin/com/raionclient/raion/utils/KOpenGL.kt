package com.raionclient.raion.utils

import com.mojang.blaze3d.systems.RenderSystem
import com.raionclient.raion.gui.Box2f
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.BufferBuilder
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Box
import net.minecraft.util.math.Matrix4f
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import java.awt.Color

/**
 * @author cookiedragon234 24/Jun/2020
 */
private val mc = MinecraftClient.getInstance()

class KOpenGLRenderer(val matrices: Matrix4f, val bufferBuilder: BufferBuilder) {
}
class KOpenGLVertex(val matrices: Matrix4f, val bufferBuilder: BufferBuilder)

inline fun <T> MatrixStack.draw(translate: Boolean = false, glMode: Int? = null, format: VertexFormat? = null, action: KOpenGLRenderer.() -> T): T {
	RenderSystem.enableBlend()
	RenderSystem.disableTexture()
	RenderSystem.defaultBlendFunc()
	push()
	val model = peek().model
	val ts = Tessellator.getInstance()
	val bb = ts.buffer
	try {
		if (glMode != null && format != null) bb.begin(glMode, format)
		if (translate) this.translateToRender()
		return action(KOpenGLRenderer(model, bb))
	} finally {
		try {
			bb.end()
			BufferRenderer.draw(bb)
			//ts.draw()
		} catch (t: Throwable) {
			t.printStackTrace()
		}
		pop()
		RenderSystem.enableTexture()
		RenderSystem.disableBlend()
	}
}

inline fun KOpenGLRenderer.vertex(action: KOpenGLVertex.() -> Unit): KOpenGLRenderer = this.apply {
	try {
		action(KOpenGLVertex(this.matrices, this.bufferBuilder))
	} finally {
		this.bufferBuilder.next()
	}
}

fun <T: MatrixStack> T.translateToRender(): T = this.apply {
	val pos = mc.gameRenderer.camera.pos
	this.translate(
		-pos.x,
		-pos.y,
		-pos.z
	)
}

fun KOpenGLRenderer.next() = this.apply {
	this.bufferBuilder.next()
}

fun KOpenGLRenderer.box(box2f: Box2f, color: Color) = this.apply {
	for (corner in box2f.corners) {
		vertex {
			vertex(corner)
			color(color)
		}
	}
}

fun KOpenGLVertex.vertex(x: Double, y: Double, z: Double): KOpenGLVertex = this.apply {
	this.bufferBuilder.vertex(x, y, z)
}

fun KOpenGLVertex.vertex(pos: Vec2f): KOpenGLVertex = this.apply {
	this.vertex(pos.x.toDouble(), pos.y.toDouble(), 0.0)
}

fun KOpenGLVertex.vertex(pos: Vec3d, xOffset: Double = 0.0, yOffset: Double = xOffset, zOffset: Double = xOffset): KOpenGLVertex = this.apply {
	this.vertex(pos.x + xOffset, pos.y + yOffset, pos.z + zOffset)
}

fun KOpenGLVertex.vertex(pos: Vec3i, xOffset: Double = 0.0, yOffset: Double = xOffset, zOffset: Double = xOffset): KOpenGLVertex = this.apply {
	this.vertex(pos.x.toDouble() + xOffset, pos.y.toDouble() + yOffset, pos.z.toDouble() + zOffset)
}

fun KOpenGLVertex.color(red: Int, green: Int, blue: Int, alpha: Int): KOpenGLVertex = this.apply {
	this.bufferBuilder.color(red, green, blue, alpha)
}

fun KOpenGLVertex.color(color: Color): KOpenGLVertex = this.apply {
	this.color(color.red, color.green, color.blue, color.alpha)
}

fun KOpenGLVertex.color(color: Int): KOpenGLVertex = this.apply {
	val alpha = (color shr 24 and 255)
	val red = (color shr 16 and 255)
	val green = (color shr 8 and 255)
	val blue = (color and 255)
	color(alpha, red, green, blue)
}

fun KOpenGLRenderer.bb(bb: Box, red: Int? = null, green: Int? = null, blue: Int? = null, alpha: Int? = null): KOpenGLRenderer = this.apply {
	vertex {
		vertex(bb.minX, bb.minY, bb.minZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.minX, bb.maxY, bb.minZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.maxX, bb.minY, bb.minZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.maxX, bb.maxY, bb.minZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.maxX, bb.minY, bb.maxZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.maxX, bb.maxY, bb.maxZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.minX, bb.minY, bb.maxZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
	vertex {
		vertex(bb.minX, bb.maxY, bb.maxZ)
		if (red != null && green != null && blue != null && alpha != null) { color(red, green, blue, alpha) }
	}
}
