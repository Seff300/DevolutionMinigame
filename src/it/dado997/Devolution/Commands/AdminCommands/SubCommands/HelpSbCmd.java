package it.dado997.Devolution.Commands.AdminCommands.SubCommands;

import it.dado997.Devolution.Commands.AdminCommands.CommandHandler;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.entity.Player;

public class HelpSbCmd implements CommandHandler {

    @Override
    public boolean handleCommand(final Player player, final String[] args) {
        player.sendMessage(ChatUtil.format("&7/devadmin create <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin delete <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin deletespec <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin dellobby <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin disable <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin enable <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin finish <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin help"));
        player.sendMessage(ChatUtil.format("&7/devadmin maxplayers <ArenaName> <number>"));
        player.sendMessage(ChatUtil.format("&7/devadmin minplayers <ArenaName> <number>"));
        player.sendMessage(ChatUtil.format("&7/devadmin reload"));
        player.sendMessage(ChatUtil.format("&7/devadmin setp1"));
        player.sendMessage(ChatUtil.format("&7/devadmin setp2"));
        player.sendMessage(ChatUtil.format("&7/devadmin clear"));
        player.sendMessage(ChatUtil.format("&7/devadmin setarena <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin setlobby <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin setspawn <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin setdevolutespawn <ArenaName>"));
        player.sendMessage(ChatUtil.format("&7/devadmin setspecspawn <ArenaName>"));
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 0;
    }

}
