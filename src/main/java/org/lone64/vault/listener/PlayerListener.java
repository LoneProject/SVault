package org.lone64.vault.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.lone64.vault.SVault;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!SVault.getStatsManager().hasStats(player.getUniqueId())) {
            SVault.getStatsManager().setStats(player.getUniqueId(), 0);
        }
    }

}