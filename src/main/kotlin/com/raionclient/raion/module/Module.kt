package com.raionclient.raion.module

import com.google.gson.JsonObject
import com.raionclient.raion.event.events.KeyDownEvent
import com.raionclient.raion.event.system.EventDispatcher
import com.raionclient.raion.utils.Key
import com.raionclient.raion.utils.Serializable
import com.raionclient.raion.value.BooleanValue
import com.raionclient.raion.value.KeyValue
import com.raionclient.raion.value.Value
import net.minecraft.client.MinecraftClient
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

/**
 * @author cookiedragon234 24/Jun/2020
 */
abstract class Module(
	val name: String,
	val description: String,
	val category: Category,
	val defaultKeyBind: Key = Key.UNKNOWN
): Serializable {
	val mc = MinecraftClient.getInstance()
	var enabled by BooleanValue("Enabled", false)
		.addCallback { oldValue, newValue ->
			println("Toggled $name ($newValue)")
			if (newValue) {
				onEnabled()
			} else {
				onDisabled()
			}
		}
	var keyBind by KeyValue("Key", defaultKeyBind)
	val metadata: String?
		get() = null
	
	val values: MutableCollection<Value<*>>
	init {
		val valueSet = hashSetOf<Value<*>>()
		
		var thisClass: Class<*>? = this::class.java
		while (thisClass != null) {
			for (field in thisClass.declaredFields) {
				if (Value::class.java.isAssignableFrom(field.type)) {
					valueSet.add(field.get(this) as Value<*>)
				}
			}
			thisClass = thisClass.superclass
		}
		
		this.values = ArrayList(valueSet)
		
		EventDispatcher.register { event: KeyDownEvent ->
			if (event.key == keyBind) {
				enabled = enabled.not()
			}
		}
	}
	
	open fun onEnabled() {}
	open fun onDisabled() {}
	
	override fun read(jsonObject: JsonObject) {
		if (jsonObject.has(name)) {
			val moduleObj = jsonObject.get(name).asJsonObject
			for (value in values) {
				try {
					value.read(moduleObj)
				} catch (t: Throwable) { t.printStackTrace() }
			}
		}
	}
	override fun write(jsonObject: JsonObject) {
		val moduleObj = JsonObject()
		jsonObject.add(name, moduleObj)
		for (value in values) {
			value.write(moduleObj)
		}
	}
	
	inline fun <reified T> register(noinline lambda: (T) -> Unit) = register(T::class.java, lambda)
	fun <T> register(type: Class<T>, lambda: (T) -> Unit) {
		val listener = ModuleEventListener(lambda, this)
		EventDispatcher.register(type, listener)
	}
}

enum class Category {
	COMBAT,
	MOVEMENT,
	RENDER
}

class ModuleEventListener<T>(lambda: (T) -> Unit, val module: Module) : EventDispatcher.Listener<T>(lambda) {
	override var active: Boolean
		get() = module.enabled
		set(value) { throw UnsupportedOperationException() }
}
