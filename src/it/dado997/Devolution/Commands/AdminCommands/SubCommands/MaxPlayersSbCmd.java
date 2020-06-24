package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;

public class MaxPlayersSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(args[0]);
        if (arena != null) {
            if (arena.getStatusManager().isArenaEnabled()) {
                player.sendMessage("Disable arena first");
                return true;
            }
            arena.getStructureManager().setMaxPlayers(Integer.parseInt(args[1]));
            player.sendMessage("Max Players set");
        }
        else {
            player.sendMessage("Arena does not exist");
        }
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 2;
    }

}
