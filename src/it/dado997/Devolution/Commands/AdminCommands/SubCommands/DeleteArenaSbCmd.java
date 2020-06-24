package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Signs.SignEditor;
import org.bukkit.entity.Player;

import java.io.File;

public class DeleteArenaSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(args[0]);
        if (arena == null) {
            player.sendMessage("Arena does not exist");
            return true;
        }
        if (arena.getStatusManager().isArenaEnabled()) {
            player.sendMessage("Disable arena first");
            return true;
        }
        new File(Main.getInstance().getDataFolder() + File.separator + "arenas" + File.separator + arena.getArenaName() + ".yml").delete();
        SignEditor.getInstance().removeArena(arena.getArenaName());
        ArenaFile.getInstance().unregisterArena(arena);
        player.sendMessage("Arena deleted");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 1;
    }

}
