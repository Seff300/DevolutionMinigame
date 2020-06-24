package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Main;
import org.bukkit.entity.Player;

public class ReloadSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        Main.getInstance().reloadConfig();
        Messages.loadMessages();
        player.sendMessage("Config and messages reloaded!");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 0;
    }

}
