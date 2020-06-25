package com.raionclient.raion.mixin.client;

import com.raionclient.raion.Raion;
import com.raionclient.raion.event.events.KeyDownEvent;
import com.raionclient.raion.event.events.KeyUpEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import com.raionclient.raion.module.movement.InventoryMoveModule;
import com.raionclient.raion.module.render.ClickGuiModule;
import com.raionclient.raion.utils.Key;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.objectweb.asm.Opcodes.IFNULL;

/**
 * @author cookiedragon234 24/Jun/2020
 */
@Mixin(Keyboard.class)
public class MixinKeyboard {
	@Inject(method = "onKey", at = @At("HEAD"))
	private void onKeyInject(long window, int code, int scancode, int i, int j, CallbackInfo ci) {
		MinecraftClient mc = MinecraftClient.getInstance();
		if (window == mc.getWindow().getHandle()) {
			Key key = Key.Companion.get(code);
			if (key != Key.UNKNOWN) {
				if (key.isKeyDown()) {
					EventDispatcher.dispatch(new KeyDownEvent(key));
				} else {
					EventDispatcher.dispatch(new KeyUpEvent(key));
				}
			}
		}
	}
}
