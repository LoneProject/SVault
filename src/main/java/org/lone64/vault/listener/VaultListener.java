package org.lone64.vault.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.lone64.vault.data.util.PlayerUtil;

public class VaultListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerUtil.get(event.getPlayer()).init();
    }

}
