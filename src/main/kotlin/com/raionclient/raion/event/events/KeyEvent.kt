package com.raionclient.raion.event.events

import com.raionclient.raion.utils.Key

/**
 * @author cookiedragon234 24/Jun/2020
 */
abstract class KeyEvent(val key: Key)
class KeyDownEvent(key: Key): KeyEvent(key)
class KeyUpEvent(key: Key): KeyEvent(key)
