package it.dado997.Devolution.Game;

import it.dado997.Devolution.Game.Managers.*;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.Rewards;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.io.File;

public class Arena {

    private String arenaname;
    private File arenafile;
    private GameHandler arenagh;
    private PlayerHandler arenaph;
    private StatusManager statusManager;
    private StructureManager structureManager;
    private PlayersManager playersManager;

    public Arena(final String name) {
        this.statusManager = new StatusManager(this);
        this.structureManager = new StructureManager(this);
        this.playersManager = new PlayersManager();
        this.arenaname = name;
        this.arenagh = new GameHandler(this);
        this.arenaph = new PlayerHandler(this);
        this.arenafile = new File(Main.getInstance().getDataFolder() + File.separator + "arenas" + File.separator + this.arenaname + ".yml");
    }

    public String getArenaName() {
        return this.arenaname;
    }

    public File getArenaFile() {
        return this.arenafile;
    }

    public GameHandler getGameHandler() {
        return this.arenagh;
    }

    public PlayerHandler getPlayerHandler() {
        return this.arenaph;
    }

    public StatusManager getStatusManager() {
        return this.statusManager;
    }

    public StructureManager getStructureManager() {
        return this.structureManager;
    }

    public PlayersManager getPlayersManager() {
        return this.playersManager;
    }

}
