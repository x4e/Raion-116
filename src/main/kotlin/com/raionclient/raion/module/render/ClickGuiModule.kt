package com.raionclient.raion.module.render

import com.google.gson.JsonObject
import com.raionclient.raion.gui.Drawable
import com.raionclient.raion.gui.Panel
import com.raionclient.raion.gui.clickgui.CategoryPanel
import com.raionclient.raion.gui.clickgui.ModuleButton
import com.raionclient.raion.module.Category
import com.raionclient.raion.module.Module
import com.raionclient.raion.module.ModuleManager
import com.raionclient.raion.utils.*
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText


/**
 * @author cookiedragon234 24/Jun/2020
 */
object ClickGuiModule: Module("Click Gui", "A clickable gui", Category.RENDER, Key.KEY_O) {
	override fun onEnabled() {
		mc.openScreen(ClickGuiScreen)
		println("Opened")
	}
	override fun onDisabled() {
		if (mc.currentScreen == ClickGuiScreen) {
			mc.openScreen(null)
		}
	}
	
	// Dont serialize the enabled state
	override fun write(jsonObject: JsonObject) {
		super.write(jsonObject)
	}
	override fun read(jsonObject: JsonObject) {
		if (jsonObject.has(name)) {
			val moduleObj = jsonObject.get(name).asJsonObject
			for (value in values) {
				if (value == this::enabled) continue
				try {
					value.read(moduleObj)
				} catch (t: Throwable) { t.printStackTrace() }
			}
		}
	}
	
	object ClickGuiScreen: Screen(LiteralText("Raion ClickGui")) {
		val panels by lazy {
			val arr = arrayListOf<Drawable>()
			var x = 10f
			for (category in Category.values()) {
				arr.add(CategoryPanel(
					category
				).also {
					it.posX = x
					it.posY = 10f
				})
				x += 150f
			}
			arr
		}
		
		override fun onClose() {
			enabled = false
		}
		
		override fun tick() {
			panels.forEach { it.onUpdate() }
		}
		
		override fun isPauseScreen(): Boolean = false
		override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
			val mousePos = Vec2f(mouseX.toFloat(), mouseY.toFloat())
			panels.forEach { it.render(matrices, mousePos) }
		}
		override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
			val mousePos = Vec2f(mouseX.toFloat(), mouseY.toFloat())
			val button = Mouse[button]
			var out = false
			panels.forEach { if (it.mouseDown(mousePos, button, out)) { out = true } }
			return out
		}
		override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
			val mousePos = Vec2f(mouseX.toFloat(), mouseY.toFloat())
			val button = Mouse[button]
			var out = false
			panels.forEach { if (it.mouseUp(mousePos, button, out)) { out = true } }
			return out
		}
		override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
			val mousePos = Vec2f(mouseX.toFloat(), mouseY.toFloat())
			val button = Mouse[button]
			var out = false
			panels.forEach { if (it.mouseDragged(mousePos, button, out)) { out = true } }
			return out
		}
		//override fun charTyped(chr: Char, keyCode: Int): Boolean {
		//}
		//override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
		//}
		//override fun keyReleased(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
		//}
	}
}
