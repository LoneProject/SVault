package org.lone64.vault.economy;

import org.bukkit.OfflinePlayer;

public interface Economy {

    EconomyResponse setAmount(OfflinePlayer player, double amount);

    EconomyResponse addAmount(OfflinePlayer player, double amount);

    EconomyResponse subAmount(OfflinePlayer player, double amount);

    double getAmount(OfflinePlayer player);

}