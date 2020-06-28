package com.raionclient.raion

import com.raionclient.raion.config.ConfigManager
import com.raionclient.raion.event.events.KeyDownEvent
import com.raionclient.raion.event.events.KeyEvent
import com.raionclient.raion.event.events.KeyUpEvent
import com.raionclient.raion.event.system.EventDispatcher
import com.raionclient.raion.module.ModuleManager
import com.raionclient.raion.utils.Key
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import org.apache.logging.log4j.LogManager
import java.io.File
import java.util.logging.Logger

object Raion: ModInitializer {
	val configurationFile = File(MinecraftClient.getInstance().runDirectory, "raion/1-16").also {it.mkdirs()}
	val logger = LogManager
		.getContext(this::class.java.classLoader, false, this::class.java.getResource("/log4j2.raion.xml").toURI())
		.getLogger("Raion")
	val mc = MinecraftClient.getInstance()
	
	override fun onInitialize() {
		try {
			logger.info("Raion Init")
			ModuleManager
			ConfigManager
		} catch (t: Throwable) {
			t.printStackTrace()
			error("Couldnt start raion")
		}
	}
}

