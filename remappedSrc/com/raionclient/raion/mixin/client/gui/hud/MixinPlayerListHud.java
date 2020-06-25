package com.raionclient.raion.mixin.client.gui.hud;

import com.raionclient.raion.module.render.ExtraTabModule;
import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

/**
 * @author cookiedragon234 25/Jun/2020
 */
@Mixin(PlayerListHud.class)
public class MixinPlayerListHud {
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;"))
	private List redirSublist(List list, int fromIndex, int toIndex) {
		if (ExtraTabModule.INSTANCE.getEnabled()) {
			return list;
		}
		return list.subList(fromIndex, toIndex);
	}
}
