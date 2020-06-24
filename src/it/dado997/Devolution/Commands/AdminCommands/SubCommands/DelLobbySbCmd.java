package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Locations.GlobalLobby;
import org.bukkit.entity.Player;

public class DelLobbySbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        GlobalLobby.getInstance().setLobbyLocation(null);
        player.sendMessage("Lobby deleted");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 0;
    }

}
