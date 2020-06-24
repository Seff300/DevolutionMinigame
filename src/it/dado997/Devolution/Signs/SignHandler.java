package it.dado997.Devolution.Signs;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Signs.Type.JoinSign;
import it.dado997.Devolution.Signs.Type.LeaveSign;
import it.dado997.Devolution.Signs.Type.SignType;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class SignHandler implements Listener {

    private HashMap<String, SignType> signs;

    public SignHandler() {
        this.signs = new HashMap<String, SignType>();
        signs.put("join", new JoinSign());
        signs.put("leave", new LeaveSign());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTNTRunSignCreate(final SignChangeEvent e) {
        final Player player = e.getPlayer();
        if (e.getLine(0).equalsIgnoreCase("[Devolution]") || e.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[Devolution]")) {
            if (!player.hasPermission("devolution.setup")) {
                Messages.sendMessage(player, Messages.nopermission);
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                return;
            }

            final String line = e.getLine(1).toLowerCase();
            if (this.signs.containsKey(line)) {
                this.signs.get(line).handleCreation(e);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSignClick(final PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!(e.getClickedBlock().getState() instanceof Sign)) {
            return;
        }
        final Sign sign = (Sign)e.getClickedBlock().getState();
        if (sign.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[Devolution]")) {
            final String line = sign.getLine(1).toLowerCase();
            if (this.signs.containsKey(line)) {
                this.signs.get(line).handleClick(e);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSignDestroy(final BlockBreakEvent e) {
        if (!(e.getBlock().getState() instanceof Sign)) {
            return;
        }
        final Player player = e.getPlayer();
        final Sign sign = (Sign)e.getBlock().getState();
        if (sign.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[Devolution]")) {
            if (!player.hasPermission("devolution.setup")) {
                Messages.sendMessage(player, Messages.nopermission);
                e.setCancelled(true);
                return;
            }
            final String line = sign.getLine(1).toLowerCase();
            if (this.signs.containsKey(line)) {
                this.signs.get(line).handleDestroy(e);
            }
        }
    }

}
