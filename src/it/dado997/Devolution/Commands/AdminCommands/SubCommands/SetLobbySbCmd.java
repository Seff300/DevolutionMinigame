package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Locations.GlobalLobby;
import org.bukkit.entity.Player;

public class SetLobbySbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        GlobalLobby.getInstance().setLobbyLocation(player.getLocation());
        player.sendMessage("Lobby set correctly!");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 0;
    }

}
