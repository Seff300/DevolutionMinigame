package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Arrays;
import java.util.HashSet;

public class RestrictionHandler implements Listener {

    private final HashSet<String> allowedcommands;

    public RestrictionHandler() {
        this.allowedcommands = new HashSet<String>(Arrays.asList("/devolution leave", "/devolution leave", "/devolution:devolution leave", "/devolution:devolution vote", "/devolution:dv leave"));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerCommand(final PlayerCommandPreprocessEvent e) {
        final Player player = e.getPlayer();
        final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
        if (arena == null) {
            return;
        }
        if (player.hasPermission("devolution.cmdblockbypass")) {
            return;
        }
        if (!this.allowedcommands.contains(e.getMessage().toLowerCase())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerBlockBreak(final BlockBreakEvent e) {
        final Player player = e.getPlayer();
        final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
        if (arena == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerBlockPlace(final BlockPlaceEvent e) {
        final Player player = e.getPlayer();
        final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
        if (arena == null) {
            return;
        }

        if (e.getBlock().getType() == Material.WEB) {
            e.setCancelled(false);
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerItemDrop(final PlayerDropItemEvent e) {
        final Player player = e.getPlayer();
        final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
        if (arena == null) {
            return;
        }
        e.setCancelled(true);
    }

}
