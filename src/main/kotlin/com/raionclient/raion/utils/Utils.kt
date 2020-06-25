package com.raionclient.raion.utils

import com.raionclient.raion.imixin.client.options.IMixinKeybinding
import com.raionclient.raion.mixin.client.options.MixinKeybinding
import net.minecraft.client.MinecraftClient
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil

/**
 * @author cookiedragon234 25/Jun/2020
 */
fun KeyBinding.updateState() {
	this as IMixinKeybinding
	if (boundKey.category == InputUtil.Type.KEYSYM && boundKey.code != InputUtil.UNKNOWN_KEY.code) {
		isPressed = InputUtil.isKeyPressed(MinecraftClient.getInstance().window.handle, boundKey.code)
	}
}
