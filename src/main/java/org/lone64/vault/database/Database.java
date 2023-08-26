package org.lone64.vault.database;

import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.UUID;

public interface Database {

    void init();

    void setStats(UUID uuid, double amount);

    void setStats(OfflinePlayer player, double amount);

    boolean hasStats(UUID uuid);

    double getAmount(UUID uuid);

    List<UUID> getPlayers();

}
