package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerStatusHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamageByPlayer(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final Player player = (Player)e.getEntity();
            final Entity damager = e.getDamager();
            final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
            if(e.getEntity() instanceof Player) {
             if(arena != null) {
                if(arena.getStatusManager().getIsInLobby() == true) {
                    e.setCancelled(true);
                } else if(arena.getPlayersManager().getSpectators().contains(damager)) {
                    e.setCancelled(true);
                } else {
                    return;
                }
            } else {
                return;
            }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDamage(final FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player player = (Player)e.getEntity();
            if (ArenaFile.getInstance().getPlayerArena(player.getName()) != null) {
                e.setCancelled(true);
            }
        }
    }

}
