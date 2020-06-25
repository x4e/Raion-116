package com.raionclient.raion.imixin.client;

/**
 * @author cookiedragon234 25/Jun/2020
 */
public interface IMixinMinecraftClient {
	void setItemUseCooldown(int itemUseCooldown);
	int getItemUseCooldown();
}
