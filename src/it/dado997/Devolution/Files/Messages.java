package it.dado997.Devolution.Files;

import it.dado997.Devolution.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Messages {

    public static String nopermission;
    public static String teleporttolobby;
    public static String availablearenas;
    public static String arenawolrdna;
    public static String arenadisabled;
    public static String arenarunning;
    public static String arenaregenerating;
    public static String arenavehicle;
    public static String arenadisabling;
    public static String playerscountinarena;
    public static String limitreached;
    public static String playerjoinedtoplayer;
    public static String playerjoinedtoothers;
    public static String playerlefttoplayer;
    public static String playerlefttoothers;
    public static String playervotedforstart;
    public static String arenastarted;
    public static String arenacountdown;
    public static String arenafinished;
    public static String playerwontoplayer;
    public static String playerdeathtoplayer;
    public static String playerdeathtoothers;
    public static String playerwonbroadcast;
    public static String playerrewardmessage;
    public static String invalidcommand;
    public static String devoluting;
    public static String cannotmovewhiledevoluting;
    public static String alreadyingame;
    public static String abilityavaible;
    public static String abilitynotavaible;
    public static String abilityused;
    public static String respawning;
    public static String respawned;

    public static void sendMessage(final Player player, final String message) {
        if (!message.equals("")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public static void broadcastMessage(final String message) {
        if (!message.equals("")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public static void loadMessages() {
        final File messageconfig = new File(Main.getInstance().getDataFolder(), "Messages.yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(messageconfig);
        Messages.nopermission = config.getString("nopermission", Messages.nopermission);
        Messages.teleporttolobby = config.getString("teleporttolobby", Messages.teleporttolobby);
        Messages.availablearenas = config.getString("availablearenas", Messages.availablearenas);
        Messages.arenawolrdna = config.getString("arenawolrdna", Messages.arenawolrdna);
        Messages.arenadisabled = config.getString("arenadisabled", Messages.arenadisabled);
        Messages.arenarunning = config.getString("arenarunning", Messages.arenarunning);
        Messages.arenaregenerating = config.getString("arenaregenerating", Messages.arenaregenerating);
        Messages.arenavehicle = config.getString("arenavehicle", Messages.arenavehicle);
        Messages.arenadisabling = config.getString("arenadisabling", Messages.arenadisabling);
        Messages.playerscountinarena = config.getString("playerscountinarena", Messages.playerscountinarena);
        Messages.limitreached = config.getString("limitreached", Messages.limitreached);
        Messages.playerjoinedtoplayer = config.getString("playerjoinedtoplayer", Messages.playerjoinedtoplayer);
        Messages.playerjoinedtoothers = config.getString("playerjoinedtoothers", Messages.playerjoinedtoothers);
        Messages.playerlefttoplayer = config.getString("playerlefttoplayer", Messages.playerlefttoplayer);
        Messages.playerlefttoothers = config.getString("playerlefttoothers", Messages.playerlefttoothers);
        Messages.playervotedforstart = config.getString("playervotedforstart", Messages.playervotedforstart);
        Messages.arenastarted = config.getString("arenastarted", Messages.arenastarted);
        Messages.arenacountdown = config.getString("arenacountdown", Messages.arenacountdown);
        Messages.arenafinished = config.getString("arenafinished", Messages.arenafinished);
        Messages.playerwontoplayer = config.getString("playerwontoplayer", Messages.playerwontoplayer);
        Messages.playerdeathtoplayer = config.getString("playerdeathtoplayer", Messages.playerdeathtoplayer);
        Messages.playerdeathtoothers = config.getString("playerdeathtoothers", Messages.playerdeathtoothers);
        Messages.playerwonbroadcast = config.getString("playerwonbroadcast", Messages.playerwonbroadcast);
        Messages.playerrewardmessage = config.getString("playerrewardmessage", Messages.playerrewardmessage);
        Messages.invalidcommand = config.getString("invalidcommand", Messages.invalidcommand);
        Messages.devoluting = config.getString("devoluting", Messages.devoluting);
        Messages.cannotmovewhiledevoluting = config.getString("playermovewhiledevoluting", Messages.cannotmovewhiledevoluting);
        Messages.alreadyingame = config.getString("alreadyingame", Messages.alreadyingame);
        Messages.abilityavaible = config.getString("abilityavaible", Messages.abilityavaible);
        Messages.abilitynotavaible = config.getString("abilitynotavaible", Messages.abilitynotavaible);
        Messages.abilityused = config.getString("abilityused", Messages.abilityused);
        Messages.respawning = config.getString("respawning", Messages.respawning);
        Messages.respawned = config.getString("respawned", Messages.respawned);
        saveMessages(messageconfig);
    }

    private static void saveMessages(final File messageconfig) {
        final FileConfiguration config = (FileConfiguration)new YamlConfiguration();
        config.set("nopermission", (Object)Messages.nopermission);
        config.set("invalidcommand", (Object)Messages.invalidcommand);
        config.set("teleporttolobby", (Object)Messages.teleporttolobby);
        config.set("availablearenas", (Object)Messages.availablearenas);
        config.set("arenawolrdna", (Object)Messages.arenawolrdna);
        config.set("arenadisabled", (Object)Messages.arenadisabled);
        config.set("arenarunning", (Object)Messages.arenarunning);
        config.set("arenaregenerating", (Object)Messages.arenaregenerating);
        config.set("arenavehicle", (Object)Messages.arenavehicle);
        config.set("arenadisabling", (Object)Messages.arenadisabling);
        config.set("playerscountinarena", (Object)Messages.playerscountinarena);
        config.set("limitreached", (Object)Messages.limitreached);
        config.set("playerjoinedtoplayer", (Object)Messages.playerjoinedtoplayer);
        config.set("playerjoinedtoothers", (Object)Messages.playerjoinedtoothers);
        config.set("alreadyingame", (Object)Messages.alreadyingame);
        config.set("playerlefttoplayer", (Object)Messages.playerlefttoplayer);
        config.set("playerlefttoothers", (Object)Messages.playerlefttoothers);
        config.set("playervotedforstart", (Object)Messages.playervotedforstart);
        config.set("arenastarted", (Object)Messages.arenastarted);
        config.set("arenacountdown", (Object)Messages.arenacountdown);
        config.set("arenafinished", (Object)Messages.arenafinished);
        config.set("abilityavaible", (Object)Messages.abilityavaible);
        config.set("abilitynotavaible", (Object)Messages.abilitynotavaible);
        config.set("abilityused", (Object)Messages.abilityused);
        config.set("devoluting", (Object)Messages.devoluting);
        config.set("devoluted", (Object)Messages.devoluting);
        config.set("respawning", (Object)Messages.respawning);
        config.set("respawned", (Object)Messages.respawned);
        config.set("playermovewhiledevoluting", (Object)Messages.cannotmovewhiledevoluting);
        config.set("playerwontoplayer", (Object)Messages.playerwontoplayer);
        config.set("playerdeathtoplayer", (Object)Messages.playerdeathtoplayer);
        config.set("playerdeathtoothers", (Object)Messages.playerdeathtoothers);
        config.set("playerwonbroadcast", (Object)Messages.playerwonbroadcast);
        config.set("playerrewardmessage", (Object)Messages.playerrewardmessage);
        try {
            config.save(messageconfig);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        Messages.nopermission = "&4You don't have permission to do this";
        Messages.teleporttolobby = "&6Teleported to lobby";
        Messages.availablearenas = "&6Available arenas:&r ";
        Messages.arenawolrdna = "&6Arena world is not loaded";
        Messages.arenadisabled = "&6Arena is disabled";
        Messages.arenarunning = "&6Arena already running";
        Messages.arenaregenerating = "&6Arena is regenerating";
        Messages.arenavehicle = "&6You can't join the game while sitting inside vehicle";
        Messages.arenadisabling = "&6Arena is disabling";
        Messages.playerscountinarena = "&6Current number of players in arena: {COUNT}";
        Messages.limitreached = "&6Slot limit reached.";
        Messages.playerjoinedtoplayer = "&6You joined the arena";
        Messages.playerjoinedtoothers = "&6Player {PLAYER} joined the arena";
        Messages.playerlefttoplayer = "&6You left the arena";
        Messages.playerlefttoothers = "&6Player {PLAYER} left the game";
        Messages.playervotedforstart = "&6You voted for game start";
        Messages.arenastarted = "&6Arena started.";
        Messages.arenacountdown = "&6Arena starts in {COUNTDOWN} seconds";
        Messages.arenafinished = "&6Arena ended.";
        Messages.devoluting = "&a{TIME} seconds remaining for devolution";
        Messages.playerwontoplayer = "&6You won the game";
        Messages.playerdeathtoplayer = "&6You death.";
        Messages.playerdeathtoothers = "&6Player {PLAYER} death.";
        Messages.playerwonbroadcast = "&9[Devolution] &a{PLAYER}&r won the game on arena &c{ARENA}";
        Messages.playerrewardmessage = "&6You have been rewarded: {REWARD}";
        Messages.invalidcommand = "&cInvalid command!";
        Messages.cannotmovewhiledevoluting = "&cDon't move while devoluting!";
        Messages.alreadyingame = "&cYou are already in game!";
        Messages.abilityavaible = "&aYour ability is ready to use!";
        Messages.abilitynotavaible = "&cYou ability isn't ready to use!";
        Messages.abilityused = "&aYou used your ability!";
        Messages.respawning = "&a{TIME} seconds remaining for respawn.";
        Messages.respawned = "&aRespawned";
    }

}
