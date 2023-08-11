package org.lone64.vault;

import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.vault.data.PlayerData;
import org.lone64.vault.listener.VaultListener;

public final class SVault extends JavaPlugin {

    private PlayerData playerData;

    @Override
    public void onEnable() {
        this.playerData = new PlayerData();
        this.getServer().getPluginManager().registerEvents(new VaultListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public PlayerData getPlayerData() {
        return playerData;
    }

}
