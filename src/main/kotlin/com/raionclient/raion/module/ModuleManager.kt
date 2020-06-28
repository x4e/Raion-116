package com.raionclient.raion.module

import com.google.common.reflect.ClassPath
import com.google.gson.JsonObject
import com.raionclient.raion.Raion
import com.raionclient.raion.Raion.logger
import com.raionclient.raion.module.movement.*
import com.raionclient.raion.module.player.FastPlaceModule
import com.raionclient.raion.module.player.NoFallModule
import com.raionclient.raion.module.player.NoRotateModule
import com.raionclient.raion.module.player.ReachModule
import com.raionclient.raion.module.render.*
import com.raionclient.raion.utils.Configurable
import com.raionclient.raion.utils.Saveable
import net.fabricmc.loader.FabricLoader
import net.fabricmc.loader.launch.common.FabricLauncherBase
import net.fabricmc.loader.launch.knot.Knot
import java.io.File
import java.lang.reflect.Modifier

/**
 * @author cookiedragon234 24/Jun/2020
 */
@Suppress("UnstableApiUsage")
object ModuleManager: Saveable {
	val modules: MutableCollection<Module> = hashSetOf()
	
	init {
		try {
			this += FlightModule
			this += InventoryMoveModule
			this += JesusModule
			this += NoSlowModule
			this += VelocityModule
			
			this += FastPlaceModule
			this += NoFallModule
			this += NoRotateModule
			this += ReachModule
			
			this += ClickGuiModule
			this += EspModule
			this += ExtraTabModule
			this += NoRenderModule
			this += StorageEspModule
		} catch (t: Throwable) {
			throw IllegalStateException("Initialising modules", t)
		}
	}
	
	inline operator fun plusAssign(module: Module) {
		modules.add(module)
	}
	
	override val configFile: File = File(Raion.configurationFile, "modules.json")
	
	override fun write(jsonObject: JsonObject) {
		modules.forEach {
			try {
				it.write(jsonObject)
			} catch (t: Throwable) { t.printStackTrace() }
		}
	}
	override fun read(jsonObject: JsonObject) {
		modules.forEach {
			try {
				it.read(jsonObject)
			} catch (t: Throwable) { t.printStackTrace() }
		}
	}
}
