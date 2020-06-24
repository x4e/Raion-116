package com.raionclient.raion.value

import com.google.gson.JsonObject

/**
 * @author cookiedragon234 24/Jun/2020
 */
class BooleanValue(name: String, value: Boolean) : Value<Boolean>(name, value) {
	override fun write(jsonObject: JsonObject) {
		jsonObject.addProperty(name, value)
	}
	override fun read(jsonObject: JsonObject) {
		if (jsonObject.has(name)) {
			value = jsonObject.get(name).asBoolean
		}
	}
}
