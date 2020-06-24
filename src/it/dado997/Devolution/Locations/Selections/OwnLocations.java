package it.dado997.Devolution.Locations.Selections;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class OwnLocations {

    private HashMap<String, Location> loc1;
    private HashMap<String, Location> loc2;

    public OwnLocations() {
        this.loc1 = new HashMap<String, Location>();
        this.loc2 = new HashMap<String, Location>();
    }

    protected void putPlayerLoc1(final String playername, Location loc) {
        loc = loc.getBlock().getLocation();
        this.loc1.put(playername, loc);
    }

    protected void putPlayerLoc2(final String playername, Location loc) {
        loc = loc.getBlock().getLocation();
        this.loc2.put(playername, loc);
    }

    protected void clearPoints(final String playername) {
        this.loc1.remove(playername);
        this.loc2.remove(playername);
    }

    protected Location[] getLocations(final Player player) {
        try {
            return this.sortLoc(player);
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Location[] sortLoc(final Player player) {
        final double x1 = this.loc1.get(player.getName()).getX();
        final double x2 = this.loc2.get(player.getName()).getX();
        final double y1 = this.loc1.get(player.getName()).getY();
        final double y2 = this.loc2.get(player.getName()).getY();
        final double z1 = this.loc1.get(player.getName()).getZ();
        final double z2 = this.loc2.get(player.getName()).getZ();
        final Location[] locs = { new Location(this.loc1.get(player.getName()).getWorld(), Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2)), new Location(this.loc1.get(player.getName()).getWorld(), Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2)) };
        locs[0].distanceSquared(locs[1]);
        return locs;
    }

}
