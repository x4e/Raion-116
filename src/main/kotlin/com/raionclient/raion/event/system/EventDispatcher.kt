package com.raionclient.raion.event.system

import com.raionclient.raion.Raion.logger
import java.lang.IllegalStateException
import java.util.*

/**
 * @author cookiedragon234 24/Jun/2020
 */
object EventDispatcher {
	private val eventListeners: MutableMap<Class<*>, MutableCollection<Listener<*>>> = hashMapOf()
	
	@JvmStatic
	inline fun <reified T> register(noinline lambda: (T) -> Unit) = register(T::class.java, lambda)
	@JvmStatic
	fun <T> register(type: Class<T>, lambda: (T) -> Unit) = register(type, Listener(lambda))
	@JvmStatic
	fun <T> register(type: Class<T>, listener: Listener<T>) {
		eventListeners.getOrPut(type, {LinkedList()}).add(listener)
	}
	
	@JvmStatic
	fun dispatch(event: Any) {
		var type: Class<*>? = event::class.java
		while (type != null) {
			eventListeners[type]?.forEach {
				if (it.active) {
					try {
						it(event)
					} catch (t: Throwable) {
						IllegalStateException("Error invoking event $event", t).printStackTrace()
					}
				}
			}
			type = type.superclass
		}
	}
	
	open class Listener<T>(
		val lambda: (T) -> Unit,
		open var active: Boolean = true
	) {
		operator fun invoke(event: Any) {
			lambda(event as T)
		}
	}
}
