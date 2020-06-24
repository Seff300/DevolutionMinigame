package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();
        Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());

        if(arena != null) {
            e.setDeathMessage(ChatUtil.format(Messages.playerdeathtoothers));
            player.spigot().respawn();
            arena.getPlayerHandler().spectatePlayer(player);
            if(arena.getPlayerHandler().getPlayersCanDevolute().contains(player)) {
                arena.getPlayerHandler().getPlayersCanDevolute().remove(player);
            }

            if(arena.getPlayerHandler().getPlayersDevoluting().contains(player)) {
                arena.getPlayerHandler().getPlayersDevoluting().remove(player);
            }

            if(!arena.getPlayerHandler().getPlayersCanDevolute().contains(killer)) {
                arena.getPlayerHandler().getPlayersCanDevolute().add(killer);
            }
        }

    }

}
