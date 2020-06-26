package com.raionclient.raion.module.render

import com.mojang.blaze3d.platform.GlStateManager
import com.raionclient.raion.event.events.PostRenderEntityEvent
import com.raionclient.raion.event.events.PostRenderWorldEvent
import com.raionclient.raion.event.events.PreRenderEntityEvent
import com.raionclient.raion.imixin.client.render.IMixinGameRenderer
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import com.raionclient.raion.utils.createShader
import com.raionclient.raion.utils.getShaderLogInfo
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.entity.Entity
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import org.lwjgl.opengl.ARBFragmentShader.GL_FRAGMENT_SHADER_ARB
import org.lwjgl.opengl.ARBShaderObjects
import org.lwjgl.opengl.ARBVertexShader.GL_VERTEX_SHADER_ARB
import org.lwjgl.opengl.GL11

/**
 * @author cookiedragon234 26/Jun/2020
 */
object EspModule: Module("ESP", "See entities", Category.RENDER) {
	val program by lazy {
		val vertShader = createShader("/assets/raion/shaders/bunny.vert", GL_VERTEX_SHADER_ARB)
		val fragShader = createShader("/assets/raion/shaders/bunny.frag", GL_FRAGMENT_SHADER_ARB)
		
		val program = ARBShaderObjects.glCreateProgramObjectARB()
		if (program == 0) error(program)
		
		ARBShaderObjects.glAttachObjectARB(program, vertShader);
		ARBShaderObjects.glAttachObjectARB(program, fragShader);
		
		ARBShaderObjects.glLinkProgramARB(program);
		if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
			error(getShaderLogInfo(program))
		}
		
		ARBShaderObjects.glValidateProgramARB(program);
		if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
			error(getShaderLogInfo(program))
		}
		
		program
	}
	
	
	override fun onEnabled() {
		loaded = false
	}
	
	override fun onDisabled() {
		loaded = false
		if (mc.gameRenderer.shader != null) {
			mc.gameRenderer.disableShader()
		}
	}
	
	var loaded = false
	
	
	init {
		register { event: PreRenderEntityEvent ->
			//GlStateManager.disableDepthTest()
		}
		register { event: PostRenderEntityEvent ->
			//GlStateManager.enableDepthTest()
		}
		/*register { event: PostRenderWorldEvent ->
			if (!loaded) {
				if (mc.gameRenderer.shader != null) {
					mc.gameRenderer.disableShader()
				}
				
				//(mc.gameRenderer as IMixinGameRenderer).loadShader0(Identifier("shaders/post/wobble.json"))
				loaded = true
			}
			
			GlStateManager.pushMatrix()
			val camera = mc.gameRenderer.camera.pos
			ARBShaderObjects.glUseProgramObjectARB(program)
			GlStateManager.disableDepthTest()
			mc.entityRenderManager.setRenderShadows(false)
			val layer = mc.bufferBuilders.entityVertexConsumers
			mc.world?.entities?.forEach { entity ->
				renderEntity(entity, camera.x, camera.y, camera.z, mc.tickDelta, event.matrices, layer)
			}
			layer.draw()
			mc.entityRenderManager.setRenderShadows(true)
			ARBShaderObjects.glUseProgramObjectARB(0)
			GlStateManager.popMatrix()
			/*draw3d(translate = true) {
				mc.world?.entities?.forEach { entity ->
					if (entity is LivingEntity) {
						begin(GL11.GL_LINE_LOOP) {
							color(0f, 1f, 0f, 0.5f)
							box(entity.boundingBox)
						}
					}
				}
			}*/
		}*/
	}
	
	private fun renderEntity(
		entity: Entity,
		cameraX: Double,
		cameraY: Double,
		cameraZ: Double,
		tickDelta: Float,
		matrices: MatrixStack,
		vertexConsumers: VertexConsumerProvider
	) {
		val d = MathHelper.lerp(tickDelta.toDouble(), entity.lastRenderX, entity.x)
		val e = MathHelper.lerp(tickDelta.toDouble(), entity.lastRenderY, entity.y)
		val f = MathHelper.lerp(tickDelta.toDouble(), entity.lastRenderZ, entity.z)
		val g = MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw)
		mc.entityRenderManager.render(
			entity,
			d - cameraX,
			e - cameraY,
			f - cameraZ,
			g,
			tickDelta,
			matrices,
			vertexConsumers,
			50
		)
	}
	
	fun drawVertex() {
		GlStateManager.disableDepthTest()
	}
	fun drawVertexend() {
		GlStateManager.enableDepthTest()
	}
}
