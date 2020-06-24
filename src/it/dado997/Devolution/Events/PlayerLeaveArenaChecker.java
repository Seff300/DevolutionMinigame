package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveArenaChecker implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerQuitEvent(final PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
        if (arena == null) {
            return;
        }
        arena.getPlayerHandler().leavePlayer(player, "", Messages.playerlefttoothers);
    }

}
