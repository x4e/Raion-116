package com.raionclient.raion.module

import com.google.gson.JsonObject
import com.raionclient.raion.Raion
import com.raionclient.raion.module.render.ClickGuiModule
import com.raionclient.raion.utils.Configurable
import com.raionclient.raion.utils.Saveable
import java.io.File

/**
 * @author cookiedragon234 24/Jun/2020
 */
object ModuleManager: Saveable {
	val modules: MutableCollection<Module> = arrayListOf()
	
	init {
		modules.add(ClickGuiModule)
	}
	
	override val configFile: File = File(Raion.configurationFile, "modules.json")
	
	override fun write(jsonObject: JsonObject) {
		modules.forEach {
			it.write(jsonObject)
		}
	}
	override fun read(jsonObject: JsonObject) {
		modules.forEach {
			it.read(jsonObject)
		}
	}
}
