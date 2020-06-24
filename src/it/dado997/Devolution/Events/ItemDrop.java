package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());

        if(arena != null) {
            e.setCancelled(true);
        }
    }

}
