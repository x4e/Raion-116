package com.raionclient.raion.event.events

import net.minecraft.entity.Entity

/**
 * @author cookiedragon234 26/Jun/2020
 */
abstract class EntityRenderEvent(open val entity: Entity)
data class PreRenderEntityEvent(override val entity: Entity, var cancelled: Boolean = false): EntityRenderEvent(entity) {
	constructor(entity: Entity): this(entity, false)
}
data class PostRenderEntityEvent(override val entity: Entity): EntityRenderEvent(entity)
