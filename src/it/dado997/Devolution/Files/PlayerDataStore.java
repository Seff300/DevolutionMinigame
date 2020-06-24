package it.dado997.Devolution.Files;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.HashMap;

public class PlayerDataStore {

    private static final PlayerDataStore instance;
    private final HashMap<String, ItemStack[]> plinv;
    private final HashMap<String, Collection<PotionEffect>> pleffects;
    private final HashMap<String, Location> plloc;
    private final HashMap<String, Integer> plhunger;
    private final HashMap<String, GameMode> plgamemode;

    private PlayerDataStore() {
        this.plinv = new HashMap<String, ItemStack[]>();
        this.pleffects = new HashMap<String, Collection<PotionEffect>>();
        this.plloc = new HashMap<String, Location>();
        this.plhunger = new HashMap<String, Integer>();
        this.plgamemode = new HashMap<String, GameMode>();
    }

    public static PlayerDataStore getInstance() {
        return PlayerDataStore.instance;
    }

    public void storePlayerInventory(final Player player) {
        final PlayerInventory pinv = player.getInventory();
        this.plinv.put(player.getName(), pinv.getContents());
        pinv.clear();
    }

    public void storePlayerPotionEffects(final Player player) {
        final Collection<PotionEffect> peff = (Collection<PotionEffect>)player.getActivePotionEffects();
        this.pleffects.put(player.getName(), peff);
        for (final PotionEffect peffect : peff) {
            player.removePotionEffect(peffect.getType());
        }
    }

    public void storePlayerLocation(final Player player) {
        this.plloc.put(player.getName(), player.getLocation());
    }

    public void storePlayerHunger(final Player player) {
        this.plhunger.put(player.getName(), player.getFoodLevel());
        player.setFoodLevel(20);
    }

    public void storePlayerGameMode(final Player player) {
        this.plgamemode.put(player.getName(), player.getGameMode());
        player.setGameMode(GameMode.SURVIVAL);
    }

    public void restorePlayerInventory(final Player player) {
        player.getInventory().setContents((ItemStack[])this.plinv.get(player.getName()));
        this.plinv.remove(player.getName());
    }

    public void restorePlayerPotionEffects(final Player player) {
        player.addPotionEffects((Collection)this.pleffects.get(player.getName()));
        this.pleffects.remove(player.getName());
    }

    public void restorePlayerLocation(final Player player) {
        player.teleport((Location)this.plloc.get(player.getName()));
        this.plloc.remove(player.getName());
    }

    public void clearPlayerLocation(final Player player) {
        this.plloc.remove(player.getName());
    }

    public void restorePlayerHunger(final Player player) {
        player.setFoodLevel((int)this.plhunger.get(player.getName()));
        this.plhunger.remove(player.getName());
    }

    public void restorePlayerGameMode(final Player player) {
        player.setGameMode((GameMode)this.plgamemode.get(player.getName()));
        this.plgamemode.remove(player.getName());
    }

    static {
        instance = new PlayerDataStore();
    }

}
