package com.raionclient.raion.event.events

import net.minecraft.client.render.Camera
import net.minecraft.client.util.math.MatrixStack

/**
 * @author cookiedragon234 25/Jun/2020
 */
data class PostRenderWorldEvent(
	val matrices: MatrixStack,
	val partialTicks: Float,
	val camera: Camera
)
