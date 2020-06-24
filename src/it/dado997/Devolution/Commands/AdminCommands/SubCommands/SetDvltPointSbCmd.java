package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;

public class SetDvltPointSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(args[0]);
        if (arena != null) {
            if (arena.getStatusManager().isArenaEnabled()) {
                player.sendMessage("Disable arena first");
                return true;
            }
            if (arena.getStructureManager().setDevolutePoint(player, player.getLocation())) {
                player.sendMessage("Devolutepoint set");
            }
            else {
                player.sendMessage("Devolutepoint should be in arena bounds");
            }
        }
        else {
            player.sendMessage("Arena does not exist");
        }
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 1;
    }

}
