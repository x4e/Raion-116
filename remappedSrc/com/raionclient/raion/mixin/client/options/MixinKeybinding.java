package com.raionclient.raion.mixin.client.options;

import com.raionclient.raion.imixin.client.options.IMixinKeybinding;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(KeyBinding.class)
public class MixinKeybinding implements IMixinKeybinding {
	@Shadow
	private InputUtil.Key boundKey;
	
	@Override
	public InputUtil.Key getBoundKey() {
		return boundKey;
	}
}
