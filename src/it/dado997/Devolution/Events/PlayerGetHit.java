package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerGetHit implements Listener {

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());
        if(arena != null) {
            if(arena.getPlayerHandler().getPlayersDevoluting().contains(player)) {
                arena.getPlayerHandler().getPlayersDevoluting().remove(player);
                player.sendMessage(ChatUtil.format(Messages.cannotmovewhiledevoluting));
            }
        }
    }

}
