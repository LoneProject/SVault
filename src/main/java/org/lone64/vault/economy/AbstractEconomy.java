package org.lone64.vault.economy;

import org.bukkit.OfflinePlayer;
import org.lone64.vault.SVault;

public class AbstractEconomy implements Economy {

    @Override
    public EconomyResponse setAmount(OfflinePlayer player, double amount) {
        SVault.getStatsManager().setStats(player, amount);
        return new EconomyResponse(player, amount, EconomyResponse.ResponseType.SUCCESS);
    }

    @Override
    public EconomyResponse addAmount(OfflinePlayer player, double amount) {
        SVault.getStatsManager().setStats(player, this.getAmount(player) + amount);
        return new EconomyResponse(player, amount, EconomyResponse.ResponseType.SUCCESS);
    }

    @Override
    public EconomyResponse subAmount(OfflinePlayer player, double amount) {
        SVault.getStatsManager().setStats(player, this.getAmount(player) - amount);
        return new EconomyResponse(player, amount, EconomyResponse.ResponseType.SUCCESS);
    }

    @Override
    public double getAmount(OfflinePlayer player) {
        return SVault.getStatsManager().getStats(player);
    }

}
