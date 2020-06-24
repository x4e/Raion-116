package com.raionclient.raion.value

import com.google.gson.JsonObject
import com.raionclient.raion.utils.Key
import net.minecraft.client.util.InputUtil

/**
 * @author cookiedragon234 24/Jun/2020
 */
class KeyValue(name: String, value: Key) : Value<Key>(name, value) {
	override fun write(jsonObject: JsonObject) {
		jsonObject.addProperty(name, value.toString())
	}
	override fun read(jsonObject: JsonObject) {
		if (jsonObject.has(name)) {
			value = Key[jsonObject.get(name).asString] ?: value
		}
	}
}
