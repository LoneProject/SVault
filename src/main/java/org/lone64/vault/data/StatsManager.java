package org.lone64.vault.data;

import org.bukkit.OfflinePlayer;
import org.lone64.vault.database.impl.SQLiteLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsManager {

    private final Map<UUID, Double> statsMap = new HashMap<>();

    public StatsManager loadAll(SQLiteLoader loader) {
        this.statsMap.clear();
        for (UUID uuid : loader.getPlayers()) {
            double amount = loader.getAmount(uuid);
            this.statsMap.put(uuid, amount);
        }
        return this;
    }

    public void saveAll(SQLiteLoader loader) {
        for (Map.Entry<UUID, Double> entry : this.statsMap.entrySet()) {
            UUID uuid = entry.getKey();
            double amount = entry.getValue();
            loader.setStats(uuid, amount);
        }
    }

    public void setStats(OfflinePlayer player, double amount) {
        setStats(player.getUniqueId(), amount);
    }

    public void setStats(UUID uuid, double amount) {
        this.statsMap.put(uuid, amount);
    }

    public double getStats(OfflinePlayer player) {
        return getStats(player.getUniqueId());
    }

    public double getStats(UUID uuid) {
        return this.statsMap.get(uuid);
    }

    public boolean hasStats(OfflinePlayer player) {
        return hasStats(player.getUniqueId());
    }

    public boolean hasStats(UUID uuid) {
        return this.statsMap.get(uuid) != null;
    }

}
