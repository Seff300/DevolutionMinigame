package it.dado997.Devolution.Signs.Type;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface SignType {

    void handleCreation(final SignChangeEvent p0);

    void handleClick(final PlayerInteractEvent p0);

    void handleDestroy(final BlockBreakEvent p0);

}
