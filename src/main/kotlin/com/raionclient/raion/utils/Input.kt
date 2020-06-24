@file:Suppress("unused")

package com.raionclient.raion.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.InputUtil

/**
 * @author cookiedragon234 24/Jun/2020
 */
enum class Mouse(val code: Int) {
	UNKNOWN(-1),
	MOUSE_LEFT(0),
	MOUSE_RIGHT(1),
	MOUSE_MIDDLE(2),
	MOUSE_4(3),
	MOUSE_5(4),
	MOUSE_6(5),
	MOUSE_7(6),
	MOUSE_8(7);
	
	private val niceName = this.name.removePrefix("MOUSE_")
	override fun toString(): String = niceName
	
	val isKeyDown: Boolean
		get() = this != UNKNOWN && InputUtil.isKeyPressed(MinecraftClient.getInstance().window.handle, this.code)
	
	companion object {
		private val codeMap: MutableMap<Int, Mouse> = hashMapOf()
		private val nameMap: MutableMap<String, Mouse> = hashMapOf()
		
		operator fun get(code: Int): Mouse = codeMap[code] ?: UNKNOWN
		operator fun get(name: String): Mouse = nameMap[name] ?: UNKNOWN
		
		init {
			for (value in values()) {
				codeMap[value.code] = value
				nameMap[value.toString()] = value
			}
		}
	}
}
enum class Key(val code: Int) {
	UNKNOWN(-1),
	KEY_0(48),
	KEY_1(49),
	KEY_2(50),
	KEY_3(51),
	KEY_4(52),
	KEY_5(53),
	KEY_6(54),
	KEY_7(55),
	KEY_8(56),
	KEY_9(57),
	KEY_A(65),
	KEY_B(66),
	KEY_C(67),
	KEY_D(68),
	KEY_E(69),
	KEY_F(70),
	KEY_G(71),
	KEY_H(72),
	KEY_I(73),
	KEY_J(74),
	KEY_K(75),
	KEY_L(76),
	KEY_M(77),
	KEY_N(78),
	KEY_O(79),
	KEY_P(80),
	KEY_Q(81),
	KEY_R(82),
	KEY_S(83),
	KEY_T(84),
	KEY_U(85),
	KEY_V(86),
	KEY_W(87),
	KEY_X(88),
	KEY_Y(89),
	KEY_Z(90),
	KEY_F1(290),
	KEY_F2(291),
	KEY_F3(292),
	KEY_F4(293),
	KEY_F5(294),
	KEY_F6(295),
	KEY_F7(296),
	KEY_F8(297),
	KEY_F9(298),
	KEY_F10(299),
	KEY_F11(300),
	KEY_F12(301),
	KEY_F13(302),
	KEY_F14(303),
	KEY_F15(304),
	KEY_F16(305),
	KEY_F17(306),
	KEY_F18(307),
	KEY_F19(308),
	KEY_F20(309),
	KEY_F21(310),
	KEY_F22(311),
	KEY_F23(312),
	KEY_F24(313),
	KEY_F25(314),
	KEY_NUM_LOCK(282),
	KEY_KEYPAD_0(320),
	KEY_KEYPAD_1(321),
	KEY_KEYPAD_2(322),
	KEY_KEYPAD_3(323),
	KEY_KEYPAD_4(324),
	KEY_KEYPAD_5(325),
	KEY_KEYPAD_6(326),
	KEY_KEYPAD_7(327),
	KEY_KEYPAD_8(328),
	KEY_KEYPAD_9(329),
	KEY_KEYPAD_ADD(334),
	KEY_KEYPAD_DECIMAL(330),
	KEY_KEYPAD_ENTER(335),
	KEY_KEYPAD_EQUAL(336),
	KEY_KEYPAD_MULTIPLY(332),
	KEY_KEYPAD_DIVIDE(331),
	KEY_KEYPAD_SUBTRACT(333),
	KEY_DOWN(264),
	KEY_LEFT(263),
	KEY_RIGHT(262),
	KEY_UP(265),
	KEY_APOSTROPHE(39),
	KEY_BACKSLASH(92),
	KEY_COMMA(44),
	KEY_EQUAL(61),
	KEY_GRAVE(96),
	KEY_LEFT_BRACKET(91),
	KEY_MINUS(45),
	KEY_PERIOD(46),
	KEY_RIGHT_BRACKET(93),
	KEY_SEMICOLON(59),
	KEY_SLASH(47),
	KEY_SPACE(32),
	KEY_TAB(258),
	KEY_LEFT_ALT(342),
	KEY_LEFT_CONTROL(341),
	KEY_LEFT_SHIFT(340),
	KEY_LEFT_WIN(343),
	KEY_RIGHT_ALT(346),
	KEY_RIGHT_CONTROL(345),
	KEY_RIGHT_SHIFT(344),
	KEY_RIGHT_WIN(347),
	KEY_ENTER(257),
	KEY_ESCAPE(256),
	KEY_BACKSPACE(259),
	KEY_DELETE(261),
	KEY_END(269),
	KEY_HOME(268),
	KEY_INSERT(260),
	KEY_PAGE_DOWN(267),
	KEY_PAGE_UP(266),
	KEY_CAPS_LOCK(280),
	KEY_PAUSE(284),
	KEY_SCROLL_LOCK(281),
	KEY_MENU(348),
	KEY_PRINT_SCREEN(283),
	KEY_WORLD_1(161),
	KEY_WORLD_2(162);
	
	private val niceName = this.name.removePrefix("KEY_")
	override fun toString(): String = niceName
	
	val isKeyDown: Boolean
		get() = this != UNKNOWN && InputUtil.isKeyPressed(MinecraftClient.getInstance().window.handle, this.code)
	
	companion object {
		private val codeMap: MutableMap<Int, Key> = hashMapOf()
		private val nameMap: MutableMap<String, Key> = hashMapOf()
		
		operator fun get(code: Int): Key = codeMap[code] ?: UNKNOWN
		operator fun get(name: String): Key = nameMap[name] ?: UNKNOWN
		
		init {
			for (value in values()) {
				codeMap[value.code] = value
				nameMap[value.toString()] = value
			}
		}
	}
}
