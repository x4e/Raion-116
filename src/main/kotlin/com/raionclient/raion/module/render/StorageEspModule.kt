package com.raionclient.raion.module.render

import com.raionclient.raion.event.events.PostRenderWorldEvent
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import com.raionclient.raion.utils.box
import com.raionclient.raion.utils.color
import com.raionclient.raion.utils.draw3d
import net.minecraft.block.entity.*
import net.minecraft.util.math.Box
import org.lwjgl.opengl.GL11.*

/**
 * @author cookiedragon234 25/Jun/2020
 */
object StorageEspModule: Module("Storage ESP", "View storage items", Category.RENDER) {
	init {
		register { event: PostRenderWorldEvent ->
			draw3d(translate = true) {
				mc.world?.blockEntities?.forEach { entity ->
					if (isStorage(entity)) {
						begin(GL_LINE_LOOP) {
							color(0f, 1f, 0f, 0.5f)
							box(Box(entity.pos))
						}
					}
				}
			}
		}
	}
	
	fun isStorage(entity: BlockEntity): Boolean
		= entity is BarrelBlockEntity || entity is ChestBlockEntity || entity is DispenserBlockEntity || entity is DropperBlockEntity || entity is FurnaceBlockEntity || entity is HopperBlockEntity || entity is ShulkerBoxBlockEntity || entity is TrappedChestBlockEntity
}
