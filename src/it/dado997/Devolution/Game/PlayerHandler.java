package it.dado997.Devolution.Game;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Files.PlayerDataStore;
import it.dado997.Devolution.Game.Managers.TeleportDestination;
import it.dado997.Devolution.Locations.GlobalLobby;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Signs.SignEditor;
import it.dado997.Devolution.Utils.ChatUtil;
import it.dado997.Devolution.Utils.CountDown.SpectatorCountDown;
import it.dado997.Devolution.Utils.CountDown.SpectatorEvent;
import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class PlayerHandler {

    private final Arena arena;
    private int spectator;
    private int time;
    private List<Player> playersCanDevolute = new ArrayList<>();
    private List<Player> playersDevoluting = new ArrayList<>();

    //Entities types
    private List<Player> Golem = new ArrayList<>();
    private List<Player> Blaze = new ArrayList<>();
    private List<Player> Spider = new ArrayList<>();
    private List<Player> SpiderWeapon = new ArrayList<>();
    private List<Player> Creeper = new ArrayList<>();
    private List<Player> Slime = new ArrayList<>();
    private List<Player> Chicken = new ArrayList<>();
    private List<Player> canShot = new ArrayList<>();

    public PlayerHandler(final Arena arena) {
        this.arena = arena;
    }

    public boolean checkJoin(final Player player) {
        if(this.arena.getPlayersManager().getPlayers().contains(player)) {
            Messages.sendMessage(player, Messages.alreadyingame);
        }
        if (this.arena.getStructureManager().getWorld() == null) {
            Messages.sendMessage(player, Messages.arenawolrdna);
            return false;
        }
        if (!this.arena.getStatusManager().isArenaEnabled()) {
            Messages.sendMessage(player, Messages.arenadisabled);
            return false;
        }
        if (this.arena.getStatusManager().isArenaRunning()) {
            Messages.sendMessage(player, Messages.arenarunning);
            return false;
        }
        if (player.isInsideVehicle()) {
            Messages.sendMessage(player, Messages.arenavehicle);
            return false;
        }
        if (this.arena.getPlayersManager().getCount() == this.arena.getStructureManager().getMaxPlayers()) {
            Messages.sendMessage(player, Messages.limitreached);
            return false;
        }
        return true;
    }

    public void spawnPlayer(final Player player, final String msgtoplayer, String msgtoarenaplayers) {
        final PlayerDataStore storage = PlayerDataStore.getInstance();
        storage.storePlayerLocation(player);
        player.teleport(GlobalLobby.getInstance().getLobbyLocation());
        for (final Player aplayer : Bukkit.getOnlinePlayers()) {
            aplayer.showPlayer(player);
        }
        storage.storePlayerGameMode(player);
        player.setFlying(false);
        player.setAllowFlight(false);
        storage.storePlayerInventory(player);
        storage.storePlayerPotionEffects(player);
        storage.storePlayerHunger(player);
        player.updateInventory();
        Messages.sendMessage(player, msgtoplayer);
        for (final Player oplayer : this.arena.getPlayersManager().getPlayers()) {
            msgtoarenaplayers = msgtoarenaplayers.replace("{PLAYER}", player.getName());
            Messages.sendMessage(oplayer, msgtoarenaplayers);
        }
        this.arena.getPlayersManager().add(player);
        String message = Messages.playerscountinarena;
        message = message.replace("{COUNT}", String.valueOf(this.arena.getPlayersManager().getCount()));
        Messages.sendMessage(player, message);
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
        if (!this.arena.getStatusManager().isArenaStarting() && this.arena.getPlayersManager().getCount() == this.arena.getStructureManager().getMinPlayers()) {
            this.arena.getGameHandler().runArenaCountdown();
        }
    }

    public void leavePlayer(final Player player, final String msgtoplayer, String msgtoarenaplayers) {
        player.setLevel(0);

        final boolean spectator = this.arena.getPlayersManager().isSpectator(player.getName());
        if (spectator) {
            this.arena.getPlayersManager().removeSpecator(player.getName());
            for (final Player oplayer : Bukkit.getOnlinePlayers()) {
                oplayer.showPlayer(player);
            }
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setCanPickupItems(true);
        }
        this.removePlayerFromArenaAndRestoreState(player, false);

        if (spectator) {
            return;
        }
        Messages.sendMessage(player, msgtoplayer);
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
        for (final Player oplayer : this.arena.getPlayersManager().getAllParticipantsCopy()) {
            msgtoarenaplayers = msgtoarenaplayers.replace("{PLAYER}", player.getName());
            Messages.sendMessage(oplayer, msgtoarenaplayers);
        }
    }

    protected void leaveWinner(final Player player, final String msgtoplayer) {
        this.removePlayerFromArenaAndRestoreState(player, true);
        if(DisguiseAPI.isDisguised(player)) {
            DisguiseAPI.undisguiseToAll(player);
            player.setMaxHealth(20);
            player.setHealth(20);
            player.setCanPickupItems(true);
            if(getGolem().contains(player)) {
                getGolem().remove(player);
            } else if(getBlaze().contains(player)) {
                getBlaze().remove(player);
            } else if(getSpider().contains(player)) {
                getSpider().remove(player);
            } else if(getCreeper().contains(player)) {
                getCreeper().remove(player);
            } else if(getSlime().contains(player)) {
                getSlime().remove(player);
            } else if(getChicken().contains(player)) {
                getChicken().remove(player);
            }  else if(getCanShot().contains(player)) {
                getCanShot().remove(player);
            }
        }
        Messages.sendMessage(player, msgtoplayer);
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
    }

    public void spectatePlayer(Player player) {
        arena.getPlayersManager().addSpectator(player);
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.teleport(arena.getStructureManager().getSpectatorSpawn());
        for(Player oplayer : Bukkit.getOnlinePlayers()) {
            oplayer.hidePlayer(player);
        }
        new SpectatorCountDown(player, arena).runTaskTimer(Main.getInstance(), 0, 20);
    }

    private void removePlayerFromArenaAndRestoreState(final Player player, final boolean winner) {
        final PlayerDataStore storage = PlayerDataStore.getInstance();
        this.arena.getPlayersManager().remove(player);
        for (final PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        storage.restorePlayerHunger(player);
        storage.restorePlayerPotionEffects(player);
        storage.restorePlayerInventory(player);
        if (winner) {
            this.arena.getStructureManager().getRewards().rewardPlayer(player);
        }
        if(DisguiseAPI.isDisguised(player)) {
            DisguiseAPI.undisguiseToAll(player);
        }

            player.setMaxHealth(20);
            player.setHealth(20);
            player.setCanPickupItems(true);
            if(getGolem().contains(player)) {
                getGolem().remove(player);
            } else if(getBlaze().contains(player)) {
                getBlaze().remove(player);
            } else if(getSpider().contains(player)) {
                getSpider().remove(player);
            } else if(getCreeper().contains(player)) {
                getCreeper().remove(player);
            } else if(getSlime().contains(player)) {
                getSlime().remove(player);
            } else if(getChicken().contains(player)) {
                getChicken().remove(player);
            } else if(getCanShot().contains(player)) {
                getCanShot().remove(player);
        }

        storage.restorePlayerGameMode(player);
        if (this.arena.getStructureManager().getTeleportDestination() == TeleportDestination.LOBBY && GlobalLobby.getInstance().isLobbyLocationWorldAvailable()) {
            player.teleport(GlobalLobby.getInstance().getLobbyLocation());
            storage.clearPlayerLocation(player);
        }
        else {
            storage.restorePlayerLocation(player);
        }
        if(DisguiseAPI.isDisguised(player)) {
            DisguiseAPI.undisguiseToAll(player);
        }
        player.updateInventory();
    }

    public List<Player> getPlayersDevoluting() {
        return playersDevoluting;
    }

    public List<Player> getPlayersCanDevolute() {
        return playersCanDevolute;
    }

    public List<Player> getGolem() {
        return Golem;
    }

    public List<Player> getBlaze() {
        return Blaze;
    }

    public List<Player> getSpider() {
        return Spider;
    }

    public List<Player> getSpiderWeapon() {
        return SpiderWeapon;
    }

    public List<Player> getCreeper() {
        return Creeper;
    }

    public List<Player> getSlime() {
        return Slime;
    }

    public List<Player> getChicken() {
        return Chicken;
    }

    public List<Player> getCanShot() {
        return canShot;
    }

}
