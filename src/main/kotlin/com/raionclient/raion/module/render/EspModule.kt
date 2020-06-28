package com.raionclient.raion.module.render

import com.mojang.blaze3d.platform.GlStateManager
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import org.lwjgl.opengl.GL11

/**
 * @author cookiedragon234 26/Jun/2020
 */
object EspModule: Module("ESP", "See entities", Category.RENDER) {
	fun shouldEsp(entity: Entity?) = entity is PlayerEntity
	
	fun preRenderEntity(entity: Entity) {
		if (enabled && shouldEsp(entity)) {
			GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL)
			GlStateManager.enablePolygonOffset()
			GlStateManager.polygonOffset(1f, -10000000f)
		}
	}
	fun postRenderEntity(entity: Entity) {
		if (enabled && shouldEsp(entity)) {
			GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL)
			GlStateManager.polygonOffset(1f, 10000000f)
			GlStateManager.disablePolygonOffset()
		}
	}
}
