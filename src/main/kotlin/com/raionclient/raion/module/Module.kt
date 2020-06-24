package com.raionclient.raion.module

import com.raionclient.raion.utils.Key
import com.raionclient.raion.value.BooleanValue
import com.raionclient.raion.value.KeyValue
import kotlin.properties.Delegates

/**
 * @author cookiedragon234 24/Jun/2020
 */
abstract class Module(
	val name: String,
	val description: String,
	val category: Category
) {
	var enabled by BooleanValue("Enabled", false)
	var keyBind by KeyValue("Key", Key.UNKNOWN)
	
	init {
	
	}
	
	open fun onEnabled() {}
	open fun onDisabled() {}
	
	val metadata: String?
		get() = null
}

enum class Category {
	COMBAT,
	MOVEMENT,
	RENDER
}
