package it.dado997.Devolution.Locations.Selections;

import org.bukkit.Location;

public class PlayerCuboidSelection {

    private Location minpoint;
    private Location maxpoint;

    public PlayerCuboidSelection(final Location minpoint, final Location maxpoint) {
        this.minpoint = minpoint;
        this.maxpoint = maxpoint;
    }

    public Location getMinimumLocation() {
        return this.minpoint;
    }

    public Location getMaximumLocation() {
        return this.maxpoint;
    }

}
