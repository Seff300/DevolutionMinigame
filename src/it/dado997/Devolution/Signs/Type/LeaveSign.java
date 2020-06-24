package it.dado997.Devolution.Signs.Type;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.ChatColor;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeaveSign implements SignType {

    @Override
    public void handleCreation(final SignChangeEvent e) {
        e.setLine(0, ChatColor.BLUE + "[Devolution]");
        e.getPlayer().sendMessage("Sign succesfully created");
    }

    @Override
    public void handleClick(final PlayerInteractEvent e) {
        final Arena arena = ArenaFile.getInstance().getPlayerArena(e.getPlayer().getName());
        if (arena != null) {
            arena.getPlayerHandler().leavePlayer(e.getPlayer(), Messages.playerlefttoplayer, Messages.playerlefttoothers);
            e.setCancelled(true);
        }
        else {
            e.getPlayer().sendMessage("You are not in arena");
        }
    }

    @Override
    public void handleDestroy(final BlockBreakEvent e) {
    }

}
