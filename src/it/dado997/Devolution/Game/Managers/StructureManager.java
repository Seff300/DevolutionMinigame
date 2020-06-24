package it.dado997.Devolution.Game.Managers;

import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Utils.Rewards;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.IOException;


public class StructureManager {
    private Arena arena;
    private String world;
    private Vector p1;
    private Vector p2;
    private int gameleveldestroydelay;
    private Vector spectatorspawn;
    private Vector spawnpoint;
    private Vector devolutepoint;
    private int minPlayers;
    private int maxPlayers;
    private int timelimit;
    private int countdown;
    private Rewards rewards;
    private TeleportDestination teleportDest;

    public StructureManager(final Arena arena) {
        this.p1 = null;
        this.p2 = null;
        this.gameleveldestroydelay = 8;
        this.spectatorspawn = null;
        this.spawnpoint = null;
        this.minPlayers = 2;
        this.maxPlayers = 6;
        this.timelimit = 180;
        this.countdown = 10;
        this.rewards = new Rewards();
        this.teleportDest = TeleportDestination.PREVIOUS;
        this.arena = arena;
    }

    public String getWorldName() {
        return this.world;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public Vector getP1() {
        return this.p1;
    }

    public Vector getP2() {
        return this.p2;
    }

    public int getGameLevelDestroyDelay() {
        return this.gameleveldestroydelay;
    }

    public Vector getSpectatorSpawnVector() {
        return this.spectatorspawn;
    }

    public Location getSpectatorSpawn() {
        if (this.spectatorspawn != null) {
            return new Location(this.getWorld(), this.spectatorspawn.getX(), this.spectatorspawn.getY(), this.spectatorspawn.getZ());
        }
        return null;
    }

    public Vector getSpawnPointVector() {
        return this.spawnpoint;
    }

    public Location getSpawnPoint() {
        return new Location(this.getWorld(), this.spawnpoint.getX(), this.spawnpoint.getY(), this.spawnpoint.getZ());
    }

    public Location getDevolutePoint() {
        return new Location(this.getWorld(), this.devolutepoint.getX(), this.devolutepoint.getY(), this.devolutepoint.getZ());
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public int getMinPlayers() {
        return this.minPlayers;
    }

    public int getTimeLimit() {
        return this.timelimit;
    }

    public int getCountdown() {
        return this.countdown;
    }

    public Rewards getRewards() {
        return this.rewards;
    }

    public TeleportDestination getTeleportDestination() {
        return this.teleportDest;
    }

    public boolean isInArenaBounds(final Location loc) {
        return loc.toVector().isInAABB(this.getP1(), this.getP2());
    }

    public boolean isArenaConfigured() {
        return this.isArenaConfiguredString().equals("yes");
    }

    public String isArenaConfiguredString() {
        if (this.getP1() == null || this.getP2() == null || this.world == null) {
            return "Arena bounds not set";
        }
        if (this.spawnpoint == null) {
            return "Arena spawnpoint not set";
        }
        if (this.devolutepoint == null) {
            return "Arena devolutepoint not set";
        }
        return "yes";
    }

    public void setArenaPoints(final Location loc1, final Location loc2) {
        this.world = loc1.getWorld().getName();
        this.p1 = loc1.toVector();
        this.p2 = loc2.toVector();
        saveToConfig();
    }

    public boolean setSpawnPoint(final Player player, final Location loc) {
        if (this.isInArenaBounds(loc)) {
            this.spawnpoint = loc.toVector();
            saveToConfig();
            return true;
        } else {
            player.sendMessage("Spawnpoint should be in arena bounds");
        }
        return false;
    }

    public boolean setDevolutePoint(final Player player, final Location loc) {
        if (this.isInArenaBounds(loc)) {
            this.devolutepoint = loc.toVector();
            saveToConfig();
            return true;
        } else {
            player.sendMessage("Devolutedpoint should be in arena bounds");
        }
        return false;
    }

    public boolean setSpectatorsSpawn(final Player player, final Location loc) {
        if (this.isInArenaBounds(loc)) {
            this.spectatorspawn = loc.toVector();
            saveToConfig();
            return true;
        } else {
            player.sendMessage("Spawnpoint should be in arena bounds");
        }
        return false;
    }

    public void removeSpectatorsSpawn() {
        this.spectatorspawn = null;
    }

    public void setMaxPlayers(final int maxplayers) {
        this.maxPlayers = maxplayers;
        saveToConfig();
    }

    public void setMinPlayers(final int minplayers) {
        this.minPlayers = minplayers;
        saveToConfig();
    }

    public void setTimeLimit(final int timelimit) {
        this.timelimit = timelimit;
    }

    public void setCountdown(final int countdown) {
        this.countdown = countdown;
    }

    public void setTeleportDestination(final TeleportDestination teleportDest) {
        this.teleportDest = teleportDest;
    }

    public void saveToConfig() {
        final FileConfiguration config = (FileConfiguration)new YamlConfiguration();
        try {
            config.set("world", (Object)this.world);
            config.set("p1", (Object)this.p1);
            config.set("p2", (Object)this.p2);
        }
        catch (Exception ex) {}
        config.set("gameleveldestroydelay", (Object)this.gameleveldestroydelay);
        try {
            config.set("spawnpoint", (Object)this.spawnpoint);
        }
        catch (Exception ex3) {}
        try {
            config.set("spectatorspawn", (Object)this.spectatorspawn);
            config.set("devolutepoint", (Object)this.devolutepoint);
        }
        catch (Exception ex4) {}
        config.set("maxPlayers", (Object)this.maxPlayers);
        config.set("minPlayers", (Object)this.minPlayers);
        config.set("timelimit", (Object)this.timelimit);
        config.set("countdown", (Object)this.countdown);
        config.set("teleportto", (Object)this.teleportDest.toString());
        this.rewards.saveToConfig(config);
        try {
            config.save(this.arena.getArenaFile());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromConfig() {
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.arena.getArenaFile());
        this.world = config.getString("world", (String)null);
        this.p1 = config.getVector("p1", (Vector)null);
        this.p2 = config.getVector("p2", (Vector) null);
        this.gameleveldestroydelay = config.getInt("gameleveldestroydelay", this.gameleveldestroydelay);
        this.spawnpoint = config.getVector("spawnpoint", (Vector)null);
        this.spectatorspawn = config.getVector("spectatorspawn", (Vector)null);
        this.devolutepoint = config.getVector("devolutepoint", (Vector)null);
        this.maxPlayers = config.getInt("maxPlayers", this.maxPlayers);
        this.minPlayers = config.getInt("minPlayers", this.minPlayers);
        this.timelimit = config.getInt("timelimit", this.timelimit);
        this.countdown = config.getInt("countdown", this.countdown);
        this.teleportDest = TeleportDestination.valueOf(config.getString("teleportto", TeleportDestination.PREVIOUS.toString()));
        this.rewards.loadFromConfig(config);
    }

}
