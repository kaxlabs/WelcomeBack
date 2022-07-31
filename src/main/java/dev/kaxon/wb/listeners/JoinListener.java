package dev.kaxon.wb.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    public static Player getLastPlayer() {
        return lastPlayer;
    }

    private static Player lastPlayer;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        lastPlayer = event.getPlayer();
    }
}
