package it.dado997.Devolution.Game;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Signs.SignEditor;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class GameHandler {

    private final Arena arena;
    private int leavetaskid;
    protected int runtaskid;
    protected int count;
    private int arenahandler;
    Random rnd;
    private boolean hasDevolutionPoint;
    private ItemStack GolemWeapon = new ItemStack(Material.IRON_INGOT);

    public GameHandler(final Arena arena) {
        this.rnd = new Random();
        this.arena = arena;
        this.count = arena.getStructureManager().getCountdown();
    }

    public void startArenaAntiLeaveHandler() {
        this.leavetaskid = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Player player : GameHandler.this.arena.getPlayersManager().getPlayersCopy()) {
                    if (!GameHandler.this.arena.getStructureManager().isInArenaBounds(player.getLocation())) {
                        GameHandler.this.arena.getPlayerHandler().leavePlayer(player, Messages.playerlefttoplayer, Messages.playerlefttoothers);
                    }
                }
                for (final Player player : GameHandler.this.arena.getPlayersManager().getSpectatorsCopy()) {
                    if (!GameHandler.this.arena.getStructureManager().isInArenaBounds(player.getLocation())) {
                        GameHandler.this.arena.getPlayerHandler().leavePlayer(player, "", "");
                    }
                }
            }
        }, 0L, 1L);
    }

    public void stopArenaAntiLeaveHandler() {
        Bukkit.getScheduler().cancelTask(this.leavetaskid);
    }

    public void runArenaCountdown() {
        this.arena.getStatusManager().setStarting(true);
        this.arena.getStatusManager().setIsinlobby(true);
        this.runtaskid = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                if (GameHandler.this.arena.getPlayersManager().getCount() < GameHandler.this.arena.getStructureManager().getMinPlayers()) {
                    GameHandler.this.stopArenaCountdown();
                }
                else if (GameHandler.this.count == 0) {
                    GameHandler.this.stopArenaCountdown();
                    GameHandler.this.startArena();
                }
                else if (GameHandler.this.count < 11) {
                    String message = Messages.arenacountdown;
                    message = message.replace("{COUNTDOWN}", String.valueOf(GameHandler.this.count));
                    for (final Player player2 : GameHandler.this.arena.getPlayersManager().getPlayers()) {
                        Messages.sendMessage(player2, message);
                    }
                    final GameHandler this$0 = GameHandler.this;
                    --this$0.count;
                }
                else {
                    for (final Player player : GameHandler.this.arena.getPlayersManager().getPlayers()) {
                        player.setLevel(GameHandler.this.count);
                    }

                    if(GameHandler.this.arena.getPlayersManager().getPlayers().size() <= 1) {
                        GameHandler.this.stopArena();
                    }

                    final GameHandler this$2 = GameHandler.this;
                    --this$2.count;
                }
            }
        }, 0L, 20L);
    }

    public void stopArenaCountdown() {
        this.arena.getStatusManager().setStarting(false);
        this.count = this.arena.getStructureManager().getCountdown();
        Bukkit.getScheduler().cancelTask(this.runtaskid);
    }

    public boolean isHasDevolutionPoint() {
        return hasDevolutionPoint;
    }

    public void setHasDevolutionPoint(boolean hasDevolutionPoint) {
        this.hasDevolutionPoint = hasDevolutionPoint;
    }

    public void startArena() {
        this.arena.getStatusManager().setRunning(true);
        this.arena.getStatusManager().setIsinlobby(false);
        for (final Player p : this.arena.getPlayersManager().getPlayers()) {
            Messages.sendMessage(p, Messages.arenastarted);
            p.teleport(arena.getStructureManager().getSpawnPoint());
            p.setCanPickupItems(false);
        }
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
        for (Player player : GameHandler.this.arena.getPlayersManager().getPlayers()) {
            player.getInventory().clear();
            GolemWeapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString("golem-weapon-name"));
            GolemWeapon.getItemMeta().setUnbreakable(true);
            player.getInventory().addItem(GolemWeapon);
            player.setMaxHealth(40);
            player.setHealth(40);
            DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.getType(EntityType.IRON_GOLEM)).setViewSelfDisguise(false));
            arena.getPlayerHandler().getGolem().add(player);
            arena.getPlayerHandler().getCanShot().add(player);
        }
        this.arenahandler = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
               if (GameHandler.this.arena.getPlayersManager().getCount() == 0) {
               GameHandler.this.stopArena();
               return;
            }
            }
        }, 0L, 1L);
    }

    public void stopArena() {
        for (final Player player : this.arena.getPlayersManager().getAllParticipantsCopy()) {
            this.arena.getPlayerHandler().leavePlayer(player, "", "");
            player.setMaxHealth(20);
            player.getInventory().clear();
            if(arena.getPlayerHandler().getGolem().contains(player)) {
                arena.getPlayerHandler().getGolem().remove(player);
            } else if(arena.getPlayerHandler().getBlaze().contains(player)) {
                arena.getPlayerHandler().getBlaze().remove(player);
            } else if(arena.getPlayerHandler().getSpider().contains(player)) {
                arena.getPlayerHandler().getSpider().remove(player);
            } else if(arena.getPlayerHandler().getCreeper().contains(player)) {
                arena.getPlayerHandler().getCreeper().remove(player);
            } else if(arena.getPlayerHandler().getSlime().contains(player)) {
                arena.getPlayerHandler().getSlime().remove(player);
            } else if(arena.getPlayerHandler().getChicken().contains(player)) {
                arena.getPlayerHandler().getChicken().remove(player);
            } else if(arena.getPlayerHandler().getCanShot().contains(player)) {
                arena.getPlayerHandler().getCanShot().remove(player);
            } else if(arena.getPlayerHandler().getSpiderWeapon().contains(player)) {
                arena.getPlayerHandler().getSpiderWeapon().remove(player);
            }
        }
        this.arena.getStatusManager().setRunning(false);
        Bukkit.getScheduler().cancelTask(this.arenahandler);
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());

        if (this.arena.getStatusManager().isArenaEnabled()) {
            this.startArenaRegen();
        }
    }

    public void handlePlayer(final Player player) {
        final Location plloc = player.getLocation();
        final Location plufloc = plloc.clone().add(0.0, -1.0, 0.0);
        if (this.arena.getPlayersManager().getCount() == 1) {
            this.arena.getPlayerHandler().leaveWinner(player, Messages.playerwontoplayer);
            this.broadcastWin(player);
            return;
        }
    }

    private void broadcastWin(final Player player) {
        String message = Messages.playerwonbroadcast;
        message = message.replace("{PLAYER}", player.getName());
        message = message.replace("{ARENA}", this.arena.getArenaName());
        Messages.broadcastMessage(message);
    }

    private void startArenaRegen() {
        this.arena.getStatusManager().disableArena();
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
        this.arena.getStatusManager().enableArena();
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
    }

}
