package it.dado997.Devolution.Commands.AdminCommands;

import it.dado997.Devolution.Commands.AdminCommands.SubCommands.*;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Locations.Selections.PlayerSelection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public class AdminCommandManager implements CommandExecutor {

    private final PlayerSelection ps;
    private final HashMap<String, CommandHandler> commandHandlers;

    public AdminCommandManager() {
        this.ps = new PlayerSelection();
        this.commandHandlers = new HashMap<String, CommandHandler>();
        this.commandHandlers.put("maxplayers", new MaxPlayersSbCmd());
        this.commandHandlers.put("minplayers", new MinPlayersSbCmd());
        this.commandHandlers.put("reload", new ReloadSbCmd());
        this.commandHandlers.put("setlobby", new SetLobbySbCmd());
        this.commandHandlers.put("dellobby", new DelLobbySbCmd());
        this.commandHandlers.put("create", new CreateArenaSbCmd());
        this.commandHandlers.put("finish", new FinishArenaSbCmd());
        this.commandHandlers.put("disable", new DisableArenaSbCmd());
        this.commandHandlers.put("enable", new EnableArenaSbCmd());
        this.commandHandlers.put("delete", new DeleteArenaSbCmd());
        this.commandHandlers.put("deletespec", new DeleteSpecSpwnSbCmd());
        this.commandHandlers.put("setarena", new SetArenaSbCmd(this.ps));
        this.commandHandlers.put("setspawn", new SetSpawnSbCmd());
        this.commandHandlers.put("setspecspawn", new SetSpecSpawnSbCmd());
        this.commandHandlers.put("help", new HelpSbCmd());
        this.commandHandlers.put("setdevolutespawn", new SetDvltPointSbCmd());
        this.commandHandlers.put("clear", new ClearSelectionsSbCmd(this.ps));
        this.commandHandlers.put("setp1", new SetP1SbCmd(this.ps));
        this.commandHandlers.put("setp2", new SetP2SbCmd(this.ps));
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot use this command!");
            return true;
        }

        if(args.length == 0) {
            HelpSbCmd helpSbCmd = new HelpSbCmd();
            helpSbCmd.handleCommand(((Player) sender).getPlayer(), args);
        }

        final Player player = (Player)sender;
        if (!player.hasPermission("devolution.setup")) {
            Messages.sendMessage(player, Messages.nopermission);
            return true;
        }
        if (args.length <= 0 || !this.commandHandlers.containsKey(args[0])) {
            this.commandHandlers.get("help");
            return false;
        }
        final CommandHandler commandh = this.commandHandlers.get(args[0]);
        if (args.length - 1 < commandh.getMinArgsLength()) {
            Messages.sendMessage(player, ChatColor.RED + "Not enough args");
            return false;
        }
        final boolean result = commandh.handleCommand(player, Arrays.copyOfRange(args, 1, args.length));
        return result;
    }

}
