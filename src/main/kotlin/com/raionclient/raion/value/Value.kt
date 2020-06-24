package com.raionclient.raion.value

import com.raionclient.raion.utils.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author cookiedragon234 24/Jun/2020
 */
abstract class Value<T>(
	val name: String,
	value: T
): Serializable, ReadWriteProperty<Any, T> {
	var value: T = value
		set(value) {
			field = value
			callbacks.forEach {
				it(field, value)
			}
		}
	val defaultVal: T = value
	private val callbacks: MutableCollection<(oldValue: T, newValue: T) -> Unit> = arrayListOf()
	
	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		return value
	}
	
	override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
		this.value = value
	}
	
	fun addCallback(callback: (oldValue: T, newValue: T) -> Unit): Value<T> = this.apply {
		callbacks.add(callback)
	}
}
