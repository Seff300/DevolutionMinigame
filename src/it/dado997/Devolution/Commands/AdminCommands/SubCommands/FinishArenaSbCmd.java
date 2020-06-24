package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;

public class FinishArenaSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(args[0]);
        if (arena != null) {
            if (!arena.getStatusManager().isArenaEnabled()) {
                if (arena.getStructureManager().isArenaConfigured()) {
                    arena.getStructureManager().saveToConfig();
                    ArenaFile.getInstance().registerArena(arena);
                    arena.getStatusManager().enableArena();
                    player.sendMessage("Arena saved and enabled");
                }
                else {
                    player.sendMessage("Arena is not configured. Reason: " + arena.getStructureManager().isArenaConfiguredString());
                }
            }
            else {
                player.sendMessage("Disable arena first");
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
