package com.raionclient.raion.value

import com.raionclient.raion.utils.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author cookiedragon234 24/Jun/2020
 */
abstract class Value<T>(
	val name: String,
	var value: T
): Serializable, ReadWriteProperty<Any, T> {
	val defaultVal: T = value
	
	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		return value
	}
	
	override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
		this.value = value
	}
}
