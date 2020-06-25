package com.raionclient.raion.event.events

import com.raionclient.raion.utils.Key

/**
 * @author cookiedragon234 24/Jun/2020
 */
abstract class KeyEvent(val key: Key, val shouldProcess: Boolean = true)
class KeyDownEvent(key: Key, shouldProcess: Boolean = true): KeyEvent(key, shouldProcess)
class KeyUpEvent(key: Key, shouldProcess: Boolean = true): KeyEvent(key, shouldProcess)
