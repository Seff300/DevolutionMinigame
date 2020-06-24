package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Locations.Selections.PlayerCuboidSelection;
import it.dado997.Devolution.Locations.Selections.PlayerSelection;
import org.bukkit.entity.Player;

public class SetArenaSbCmd implements CommandHandler {

    private PlayerSelection selection;

    public SetArenaSbCmd(final PlayerSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        final Arena arena = ArenaFile.getInstance().getArenaByName(args[0]);
        if (arena != null) {
            if (arena.getStatusManager().isArenaEnabled()) {
                player.sendMessage("Disable arena first");
                return true;
            }
            final PlayerCuboidSelection sel = this.selection.getPlayerSelection(player);
            if (sel != null) {
                arena.getStructureManager().setArenaPoints(sel.getMinimumLocation(), sel.getMaximumLocation());
                player.sendMessage("Arena bounds set");
            }
            else {
                player.sendMessage("Locations are wrong or not defined");
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
