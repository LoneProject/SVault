package org.lone64.vault.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.lone64.vault.SVault;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    private final OfflinePlayer player;
    private final File file = new File(SVault.getPlugin(SVault.class).getDataFolder(), "data/conf.yml");
    private final YamlConfiguration config = YamlConfiguration.loadConfiguration(this.file);

    public PlayerData() {
        this.player = null;

        File main = new File(SVault.getPlugin(SVault.class).getDataFolder(), "");
        if (!main.exists()) main.mkdir();

        File data = new File(SVault.getPlugin(SVault.class).getDataFolder(), "data");
        if (!data.exists()) data.mkdir();

        if (!this.file.exists())
            try {this.file.createNewFile();} catch (IOException ignore) {}
    }

    public PlayerData(OfflinePlayer player) {
        this.player = player;
    }

    public void init() {
        if (!this.isValue()) {
            this.setValue(0.0);
        }
    }

    public void setValue(double amount) {
        this.config.set(this.player.getUniqueId().toString(), amount);
        try {this.config.save(this.file);} catch (IOException ignore) {}
    }

    public double getValue() {
        return this.config.getDouble(this.player.getUniqueId().toString());
    }

    public boolean isValue() {
        return this.config.contains(this.player.getUniqueId().toString());
    }

}
