package it.dado997.Devolution.Locations.Selections;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WEIntegration {

    private WorldEditPlugin we;

    public WEIntegration() {
        this.we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
    }

    protected Location[] getLocations(final Player player) {
        try {
            return this.getPlayerSelection(player);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public Location[] getPlayerSelection(final Player player) {
        final Location[] locs = new Location[2];
        final Selection psel = this.we.getSelection(player);
        locs[0] = psel.getMinimumPoint();
        locs[1] = psel.getMaximumPoint();
        return locs;
    }

}
