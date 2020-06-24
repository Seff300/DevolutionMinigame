package it.dado997.Devolution.Locations;

import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

public class Lobby {

    private String worldname;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    protected String getWorldName() {
        return this.worldname;
    }

    protected Vector getVector() {
        return new Vector(this.x, this.y, this.z);
    }

    protected float getYaw() {
        return this.yaw;
    }

    protected float getPitch() {
        return this.pitch;
    }

    public Lobby(final String worldname, final Vector vector, final float yaw, final float pitch) {
        this.worldname = worldname;
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
        this.yaw = yaw;
        this.pitch = pitch;
    }

    protected boolean isWorldAvailable() {
        return Bukkit.getWorld(this.worldname) != null;
    }

    protected Location getLocation() {
        if (this.isWorldAvailable()) {
            return new Location(Bukkit.getWorld(this.worldname), this.x, this.y, this.z, this.yaw, this.pitch);
        }
        return null;
    }

}
