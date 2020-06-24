package it.dado997.Devolution.Signs.Type;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Signs.SignEditor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

public class JoinSign implements SignType {

    @Override
    public void handleCreation(final SignChangeEvent e) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(e.getLine(2));
        if (arena != null) {
            e.setLine(0, ChatColor.BLUE + "[Devolution]");
            e.getPlayer().sendMessage("Sign succesfully created");
            SignEditor.getInstance().addSign(e.getBlock(), arena.getArenaName());
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    SignEditor.getInstance().modifySigns(arena.getArenaName());
                }
            });
        }
        else {
            e.getPlayer().sendMessage("Arena does not exist");
            e.setCancelled(true);
            e.getBlock().breakNaturally();
        }
    }

    @Override
    public void handleClick(final PlayerInteractEvent e) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(((Sign)e.getClickedBlock().getState()).getLine(2));
        if (arena != null) {
            final boolean canJoin = arena.getPlayerHandler().checkJoin(e.getPlayer());
            if (canJoin) {
                arena.getPlayerHandler().spawnPlayer(e.getPlayer(), Messages.playerjoinedtoplayer, Messages.playerjoinedtoothers);
            }
            e.setCancelled(true);
        }
        else {
            e.getPlayer().sendMessage("Arena does not exist");
        }
    }

    @Override
    public void handleDestroy(final BlockBreakEvent e) {
        final Block b = e.getBlock();
        SignEditor.getInstance().removeSign(b, ((Sign)b.getState()).getLine(2));
        e.getPlayer().sendMessage("Sign succesfully removed");
    }
}
