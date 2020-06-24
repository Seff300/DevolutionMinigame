package it.dado997.Devolution.Locations;

import it.dado997.Devolution.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

public class GlobalLobby {

    private static final GlobalLobby instance;
    private File lobbyFile;
    private Lobby lobbyLocation;

    private GlobalLobby() {
        this.lobbyFile = new File(Main.getInstance().getDataFolder(), "lobby.yml");
    }

    public static GlobalLobby getInstance() {
        return GlobalLobby.instance;
    }

    public boolean isLobbyLocationWorldAvailable() {
        return this.isLobbyLocationSet() && this.lobbyLocation.isWorldAvailable();
    }

    public boolean isLobbyLocationSet() {
        return this.lobbyLocation != null;
    }

    public Location getLobbyLocation() {
        return this.lobbyLocation.getLocation();
    }

    public void setLobbyLocation(final Location location) {
        this.lobbyLocation = new Lobby(location.getWorld().getName(), location.toVector(), location.getYaw(), location.getPitch());
        saveToConfig();
    }

    public void saveToConfig() {
        final FileConfiguration config = (FileConfiguration)new YamlConfiguration();
        if (this.isLobbyLocationSet()) {
            config.set("lobby.world", (Object)this.lobbyLocation.getWorldName());
            config.set("lobby.vector", (Object)this.lobbyLocation.getVector());
            config.set("lobby.yaw", (Object)this.lobbyLocation.getYaw());
            config.set("lobby.pitch", (Object)this.lobbyLocation.getPitch());
            try {
                config.save(this.lobbyFile);
            }
            catch (IOException ex) {}
        }
    }

    public void loadFromConfig() {
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.lobbyFile);
        final String worldname = config.getString("lobby.world", (String)null);
        final Vector vector = config.getVector("lobby.vector", (Vector)null);
        final float yaw = (float)config.getDouble("lobby.yaw", 0.0);
        final float pitch = (float)config.getDouble("lobby.pitch", 0.0);
        if (worldname != null && vector != null) {
            this.lobbyLocation = new Lobby(worldname, vector, yaw, pitch);
        }
    }

    static {
        instance = new GlobalLobby();
    }

}
