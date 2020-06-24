package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;

public class CreateArenaSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        final Arena arenac = ArenaFile.getInstance().getArenaByName(args[0]);
        if (arenac != null) {
            player.sendMessage("Arena already exists");
            return true;
        }
        final Arena arena = new Arena(args[0]);
        ArenaFile.getInstance().registerArena(arena);
        player.sendMessage("Arena created");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 1;
    }

}
