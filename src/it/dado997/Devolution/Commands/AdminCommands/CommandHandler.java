package it.dado997.Devolution.Commands.AdminCommands;

import org.bukkit.entity.Player;

public interface CommandHandler {

    int getMinArgsLength();

    boolean handleCommand(final Player p0, final String[] p1);

}
