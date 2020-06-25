package com.raionclient.raion.module.render

import com.mojang.blaze3d.systems.RenderSystem
import com.raionclient.raion.event.events.PostRenderWorldEvent
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import net.minecraft.block.entity.*
import net.minecraft.client.render.debug.DebugRenderer

/**
 * @author cookiedragon234 25/Jun/2020
 */
object StorageEspModule: Module("Storage ESP", "View storage items", Category.RENDER) {
	init {
		register { event: PostRenderWorldEvent ->
			RenderSystem.disableDepthTest()
			RenderSystem.enableBlend()
			RenderSystem.defaultBlendFunc()
			mc.world?.blockEntities?.forEach { entity ->
				if (isStorage(entity)) {
					DebugRenderer.drawBox(entity.pos, 0f, 1f, 0f, 0f, 1f)
					//event.matrices.draw(translate = event.camera, glMode = GL_LINES, format = VertexFormats.POSITION_COLOR) {
					//	bb(Box(entity.pos), 255, 0, 0, 255)
					//}//.offset(event.camera.pos)
				}
			}
		}
	}
	
	fun isStorage(entity: BlockEntity): Boolean
		= entity is BarrelBlockEntity || entity is ChestBlockEntity || entity is DispenserBlockEntity || entity is DropperBlockEntity || entity is FurnaceBlockEntity || entity is HopperBlockEntity || entity is ShulkerBoxBlockEntity || entity is TrappedChestBlockEntity
}
