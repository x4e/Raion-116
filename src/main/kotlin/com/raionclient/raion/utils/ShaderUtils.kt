package com.raionclient.raion.utils

import com.raionclient.raion.Raion
import org.lwjgl.opengl.ARBShaderObjects

import org.lwjgl.opengl.GL11
import java.nio.charset.Charset


/**
 * @author cookiedragon234 26/Jun/2020
 */
fun createShader(filename: String, shaderType: Int): Int {
	var shader = 0
	return try {
		shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType)
		if (shader == 0) return error("OpenGL gave shader index 0 $shader $shaderType $filename")
		ARBShaderObjects.glShaderSourceARB(shader, Raion::class.java.getResourceAsStream(filename).readBytes().toString(Charset.defaultCharset()))
		ARBShaderObjects.glCompileShaderARB(shader)
		if (ARBShaderObjects.glGetObjectParameteriARB(
				shader,
				ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB
			) == GL11.GL_FALSE
		) throw RuntimeException("Error creating shader: " + getShaderLogInfo(shader))
		shader
	} catch (exc: Exception) {
		ARBShaderObjects.glDeleteObjectARB(shader)
		throw exc
	}
}

fun getShaderLogInfo(obj: Int): String {
	return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB))
}
