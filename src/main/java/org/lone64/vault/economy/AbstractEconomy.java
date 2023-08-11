package org.lone64.vault.economy;

import org.bukkit.OfflinePlayer;
import org.lone64.vault.data.util.PlayerUtil;

public class AbstractEconomy implements Economy {

    @Override
    public EconomyResponse setAmount(OfflinePlayer player, double amount) {
        PlayerUtil.get(player).setValue(amount);
        return new EconomyResponse(player, amount, EconomyResponse.ResponseType.SUCCESS);
    }

    @Override
    public EconomyResponse addAmount(OfflinePlayer player, double amount) {
        PlayerUtil.get(player).setValue(this.getAmount(player) + amount);
        return new EconomyResponse(player, amount, EconomyResponse.ResponseType.SUCCESS);
    }

    @Override
    public EconomyResponse subAmount(OfflinePlayer player, double amount) {
        PlayerUtil.get(player).setValue(this.getAmount(player) - amount);
        return new EconomyResponse(player, amount, EconomyResponse.ResponseType.SUCCESS);
    }

    @Override
    public double getAmount(OfflinePlayer player) {
        return PlayerUtil.get(player).getValue();
    }

}
