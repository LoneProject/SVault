package org.lone64.vault.data.util;

import org.bukkit.OfflinePlayer;
import org.lone64.vault.SVault;
import org.lone64.vault.data.PlayerData;

public class PlayerUtil {

    public static PlayerData get(OfflinePlayer player) {
        return new PlayerData(player);
    }

}
