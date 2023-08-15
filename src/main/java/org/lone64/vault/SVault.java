package org.lone64.vault;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.vault.data.PlayerData;
import org.lone64.vault.listener.VaultListener;

import java.io.File;
import java.util.Objects;

public final class SVault extends JavaPlugin {

    private PlayerData playerData;
    private SkriptAddon addon;

    @Override
    public void onEnable() {
        this.playerData = new PlayerData();
        this.getServer().getPluginManager().registerEvents(new VaultListener(), this);
        if (this.isSkript()) {
            try {
                this.addon = Skript.registerAddon(this);
                this.addon.loadClasses("org.lone64.vault", "skript");
            } catch (Exception ignored) { }
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public SkriptAddon getAddon() {
        return addon;
    }

    public boolean isSkript() {
        for (File file : Objects.requireNonNull(new File("plugins").listFiles())) {
            if (file.getName().toLowerCase().contains("skript")) return true;
        }
        return false;
    }

}
