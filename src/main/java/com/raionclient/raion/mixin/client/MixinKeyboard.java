package com.raionclient.raion.mixin.client;

import com.raionclient.raion.Raion;
import com.raionclient.raion.event.events.KeyDownEvent;
import com.raionclient.raion.event.events.KeyUpEvent;
import com.raionclient.raion.event.system.EventDispatcher;
import com.raionclient.raion.utils.Key;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cookiedragon234 24/Jun/2020
 */
@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKeyInject(long window, int code, int scancode, int i, int j, CallbackInfo ci) {
        if (window == MinecraftClient.getInstance().getWindow().getHandle()) {
            Key key = Key.Companion.get(code);
            if (key != null && key != Key.UNKNOWN) {
                if (key.isKeyDown()) {
                    EventDispatcher.dispatch(new KeyDownEvent(key));
                } else {
                    EventDispatcher.dispatch(new KeyUpEvent(key));
                }
            }
        }
    }
}
