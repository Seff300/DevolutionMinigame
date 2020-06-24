package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Locations.Selections.PlayerSelection;
import org.bukkit.entity.Player;

public class SetP1SbCmd implements CommandHandler {

    private PlayerSelection selection;

    public SetP1SbCmd(final PlayerSelection selection) {
        this.selection = selection;
    }

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        this.selection.setSelectionPoint1(player);
        player.sendMessage("p1 saved");
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 0;
    }

}
