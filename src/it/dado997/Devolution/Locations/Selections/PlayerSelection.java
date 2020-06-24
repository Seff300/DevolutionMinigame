package it.dado997.Devolution.Locations.Selections;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

public class PlayerSelection {

    private WEIntegration weintegration;
    private OwnLocations ownlocations;

    public PlayerSelection() {
        this.weintegration = null;
        this.ownlocations = new OwnLocations();
        if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
            this.weintegration = new WEIntegration();
        }
    }

    public PlayerCuboidSelection getPlayerSelection(final Player player) {
        Location[] locs = this.ownlocations.getLocations(player);
        if (locs != null) {
            return new PlayerCuboidSelection(locs[0], locs[1]);
        }
        if (this.weintegration != null) {
            locs = this.weintegration.getLocations(player);
            if (locs != null) {
                return new PlayerCuboidSelection(locs[0], locs[1]);
            }
        }
        return null;
    }

    public void setSelectionPoint1(final Player player) {
        this.ownlocations.putPlayerLoc1(player.getName(), player.getTargetBlock((Set)null, 30).getLocation());
    }

    public void setSelectionPoint2(final Player player) {
        this.ownlocations.putPlayerLoc2(player.getName(), player.getTargetBlock((Set)null, 30).getLocation());
    }

    public void clearSelectionPoints(final Player player) {
        this.ownlocations.clearPoints(player.getName());
    }

}
