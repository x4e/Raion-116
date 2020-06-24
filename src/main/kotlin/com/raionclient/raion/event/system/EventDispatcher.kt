package com.raionclient.raion.event.system

import com.raionclient.raion.Raion.logger
import java.util.*

/**
 * @author cookiedragon234 24/Jun/2020
 */
object EventDispatcher {
	private val eventListeners: MutableMap<Class<*>, MutableCollection<Listener<*>>> = hashMapOf()
	
	inline fun <reified T> register(active: Boolean = true, noinline lambda: (T) -> Unit) = register(active, T::class.java, lambda)
	fun <T> register(active: Boolean = true, type: Class<T>, lambda: (T) -> Unit) {
		val listener = Listener(lambda, active)
		eventListeners.getOrPut(type, {LinkedList()}).add(listener)
	}
	
	fun dispatch(event: Any) {
		logger.info("Dispatching $event")
		var type: Class<*>? = event::class.java
		while (type != null) {
			logger.info("type $type")
			eventListeners[type]?.forEach {
				if (it.active) {
					it(event)
				}
			}
			
			type = type.superclass
		}
		logger.info("Final type $type")
	}
	
	private class Listener<T>(
		val lambda: (T) -> Unit,
		val active :Boolean = false
	) {
		operator fun invoke(event: Any) {
			lambda(event as T)
		}
	}
}
