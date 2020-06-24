package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Game.GameHandler;
import it.dado997.Devolution.Game.PlayerHandler;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.CountDown.DevolutionCountDown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;

public class PlayerDevolution implements Listener {

    @EventHandler
    public void devolutionEvent(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        final Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());

        if (arena != null) {
            if (player.isSneaking() && !arena.getPlayerHandler().getPlayersDevoluting().contains(player) && arena.getPlayerHandler().getPlayersCanDevolute().contains(player)) {
                arena.getPlayerHandler().getPlayersDevoluting().add(player);
                new DevolutionCountDown(player.getPlayer(), arena).runTaskTimer(Main.getInstance(), 0L, 20L);
            }
        }
    }


}
