package com.raionclient.raion

import com.raionclient.raion.event.events.KeyDownEvent
import com.raionclient.raion.event.events.KeyEvent
import com.raionclient.raion.event.events.KeyUpEvent
import com.raionclient.raion.event.system.EventDispatcher
import com.raionclient.raion.utils.Key
import org.apache.logging.log4j.LogManager
import java.util.logging.Logger

object Raion {
	val logger = LogManager
		.getContext(this::class.java.classLoader, false, this::class.java.getResource("/log4j2.raion.xml").toURI())
		.getLogger("Raion")
	
	fun init() {
		logger.info("Raion Init")
		
		EventDispatcher.register { event: KeyDownEvent ->
			logger.info("Key Down Event $event")
		}
		
		EventDispatcher.register { event: KeyUpEvent ->
			logger.info("Key Up Event $event")
		}
		
		EventDispatcher.register { event: KeyEvent ->
			logger.info("Key Event $event")
		}
	}
	
	fun onKeyPress(key: Key) {
		logger.info("Key pressed $key")
		EventDispatcher.dispatch(KeyDownEvent(key))
	}
	
	fun onKeyUp(key: Key) {
		logger.info("Key released $key")
		EventDispatcher.dispatch(KeyUpEvent(key))
	}
}

