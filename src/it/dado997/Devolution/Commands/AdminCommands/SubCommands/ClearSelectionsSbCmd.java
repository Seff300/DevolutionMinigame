package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Locations.Selections.PlayerSelection;
import org.bukkit.entity.Player;

public class ClearSelectionsSbCmd implements CommandHandler {

    private PlayerSelection selection;

    public ClearSelectionsSbCmd(final PlayerSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        this.selection.clearSelectionPoints(player);
        player.sendMessage("points cleared");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 0;
    }

}
