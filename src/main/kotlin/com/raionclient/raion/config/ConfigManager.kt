package com.raionclient.raion.config

import com.raionclient.raion.module.ModuleManager
import com.raionclient.raion.utils.Configurable

/**
 * @author cookiedragon234 24/Jun/2020
 */
object ConfigManager: Configurable {
	val configurables: MutableCollection<Configurable> = arrayListOf()
	
	init {
		configurables.add(ModuleManager)
		
		load()
		Runtime.getRuntime().addShutdownHook(Thread(this::save))
	}
	
	override fun load() {
		configurables.forEach {
			try {
				it.load()
			} catch (t: Throwable) { t.printStackTrace() }
		}
	}
	
	override fun save() {
		configurables.forEach {
			try {
				it.save()
			} catch (t: Throwable) { t.printStackTrace() }
		}
	}
}
