package org.lone64.vault;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.vault.data.StatsManager;
import org.lone64.vault.database.impl.SQLiteLoader;

import java.io.File;
import java.util.Objects;

public final class SVault extends JavaPlugin {

    private static SVault instance;
    private static StatsManager statsManager;

    private SkriptAddon addon;
    private SQLiteLoader loader;

    @Override
    public void onLoad() {
        instance = this;
        this.loader = new SQLiteLoader(this);
        this.loader.init();
    }

    @Override
    public void onEnable() {
        statsManager = new StatsManager().loadAll(this.loader);
        if (this.isSkript()) {
            try {
                this.addon = Skript.registerAddon(this);
                this.addon.loadClasses("org.lone64.vault", "skript");
            } catch (Exception ignored) { }
        }
    }

    @Override
    public void onDisable() {
        if (statsManager != null) statsManager.saveAll(this.loader);
    }

    public static SVault getInstance() {
        return instance;
    }

    public static StatsManager getStatsManager() {
        return statsManager;
    }

    public SkriptAddon getAddon() {
        return addon;
    }

    public SQLiteLoader getLoader() {
        return loader;
    }

    public boolean isSkript() {
        for (File file : Objects.requireNonNull(new File("plugins").listFiles())) {
            if (file.getName().toLowerCase().contains("skript")) return true;
        }
        return false;
    }

}
