package org.lone64.vault.economy;

import org.bukkit.OfflinePlayer;

public class EconomyResponse {

    public static enum ResponseType {
        SUCCESS(0), FAILURE(1);

        private final int id;

        ResponseType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private final OfflinePlayer player;
    private final double amount;
    private final ResponseType type;

    public EconomyResponse(OfflinePlayer player, double amount, ResponseType type) {
        this.player = player;
        this.amount = amount;
        this.type = type;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public double getAmount() {
        return amount;
    }

    public ResponseType getType() {
        return type;
    }

}