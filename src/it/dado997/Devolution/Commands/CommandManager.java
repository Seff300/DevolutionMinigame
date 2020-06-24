package it.dado997.Devolution.Commands;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Locations.GlobalLobby;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.format("Console cannot use this command!"));
            return true;
        }

        if(args.length == 0) {
            sender.sendMessage("§4============§7[§6Devolution§7]§4============");
            sender.sendMessage("§7/devolution help §f- §cView all commands");
            sender.sendMessage("§7/devolution join {arena} §f- §cjoin arena");
            sender.sendMessage("§7/devolution leave §f- §cleave current arena");
            sender.sendMessage("§7/devolution list §f- §clist all arenas");
            sender.sendMessage("§7/devolution lobby §f- §cteleport to lobby");
            return true;
        }

        final Player player = (Player) sender;
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§4============§7[§6Devolution§7]§4============");
            sender.sendMessage("§7/devolution help §f- §cView all commands");
            sender.sendMessage("§7/devolution join {arena} §f- §cjoin arena");
            sender.sendMessage("§7/devolution leave §f- §cleave current arena");
            sender.sendMessage("§7/devolution list §f- §clist all arenas");
            sender.sendMessage("§7/devolution lobby §f- §cteleport to lobby");
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("lobby")) {
            if (GlobalLobby.getInstance().isLobbyLocationSet()) {
                if (GlobalLobby.getInstance().isLobbyLocationWorldAvailable()) {
                    player.teleport(GlobalLobby.getInstance().getLobbyLocation());
                    Messages.sendMessage(player, Messages.teleporttolobby);
                } else {
                    player.sendMessage("Lobby world is unloaded, can't join lobby");
                }
            } else {
                sender.sendMessage("Lobby is not set");
            }
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            final StringBuilder message = new StringBuilder(200);
            message.append(Messages.availablearenas);
            for (final Arena arena : ArenaFile.getInstance().getArenas()) {
                if (arena.getStatusManager().isArenaEnabled()) {
                    message.append("&a" + arena.getArenaName() + " ");
                } else {
                    message.append("&c" + arena.getArenaName() + " ");
                }
            }
            Messages.sendMessage(player, message.toString());
            return true;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            final Arena arena2 = ArenaFile.getInstance().getArenaByName(args[1]);
            if (arena2 != null) {
                final boolean canJoin = arena2.getPlayerHandler().checkJoin(player);
                if (canJoin) {
                    arena2.getPlayerHandler().spawnPlayer(player, Messages.playerjoinedtoplayer, Messages.playerjoinedtoothers);
                }
                return true;
            }
            sender.sendMessage("Arena does not exist");
            return true;
        } else if (args[0].equalsIgnoreCase("leave")) {
                final Arena arena2 = ArenaFile.getInstance().getPlayerArena(player.getName());
                if (arena2 != null) {
                    arena2.getPlayerHandler().leavePlayer(player, Messages.playerlefttoplayer, Messages.playerlefttoothers);
                    return true;
                }
                sender.sendMessage("You are not in arena");
                return true;
            } else {
            sender.sendMessage(ChatUtil.format("&cWrong command!"));
        }
        return false;
    }

}
