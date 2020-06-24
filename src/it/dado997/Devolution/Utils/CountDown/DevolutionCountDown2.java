package it.dado997.Devolution.Utils.CountDown;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DevolutionCountDown2 extends BukkitRunnable {

    private int time = 5;
    private Player player;
    private Arena arena;
    public static ItemStack blazeweapon = new ItemStack(Material.BLAZE_ROD);
    public static ItemStack spiderweapon = new ItemStack(Material.SPIDER_EYE);
    public static ItemStack slimeweapon = new ItemStack(Material.SLIME_BALL);
    public static ItemStack creeperweapon = new ItemStack(Material.SULPHUR);
    public static ItemStack chickenweapon = new ItemStack(Material.FEATHER);


    public DevolutionCountDown2(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public void run() {
        if(time == 0) {
            if(arena.getPlayerHandler().getGolem().contains(player)) {
                arena.getPlayerHandler().getGolem().remove(player);
                arena.getPlayerHandler().getBlaze().add(player);
                player.getInventory().clear();
                blazeweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString("blaze-weapon-name"));
                blazeweapon.setAmount(1);
                blazeweapon.getItemMeta().setUnbreakable(true);
                player.setMaxHealth(38);
                player.setHealth(38);
                player.getInventory().addItem(new ItemStack(blazeweapon));
                DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.getType(EntityType.BLAZE)).setViewSelfDisguise(false));
                player.teleport(arena.getStructureManager().getSpawnPoint());

                cancel();

            }else if(arena.getPlayerHandler().getBlaze().contains(player)) {
                arena.getPlayerHandler().getBlaze().remove(player);
                arena.getPlayerHandler().getSpider().add(player);
                player.getInventory().clear();
                spiderweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString("spider-weapon-name"));
                spiderweapon.setAmount(1);
                spiderweapon.getItemMeta().setUnbreakable(true);
                player.setMaxHealth(35);
                player.setHealth(35);
                player.getInventory().addItem(new ItemStack(spiderweapon));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.getType(EntityType.SPIDER)).setViewSelfDisguise(false));
                player.teleport(arena.getStructureManager().getSpawnPoint());

                cancel();

            } else if(arena.getPlayerHandler().getSpider().contains(player)) {
                arena.getPlayerHandler().getSpider().remove(player);
                arena.getPlayerHandler().getCreeper().add(player);
                player.getInventory().clear();
                player.setMaxHealth(30);
                player.setHealth(30);
                player.getActivePotionEffects().clear();
                creeperweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString("creeper-weapon-name"));
                creeperweapon.hasItemMeta();
                for(PotionEffect pe : player.getActivePotionEffects()) {
                    player.removePotionEffect(pe.getType());
                }
                player.getInventory().addItem(new ItemStack(creeperweapon));
                DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.getType(EntityType.CREEPER)).setViewSelfDisguise(false));
                player.teleport(arena.getStructureManager().getSpawnPoint());

                cancel();

            } else if(arena.getPlayerHandler().getCreeper().contains(player)) {
                arena.getPlayerHandler().getCreeper().remove(player);
                arena.getPlayerHandler().getSlime().add(player);
                player.getInventory().clear();
                slimeweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString("slime-weapon-name"));
                slimeweapon.setAmount(1);
                slimeweapon.getItemMeta().setUnbreakable(true);
                player.setMaxHealth(22);
                player.setHealth(22);
                player.getInventory().addItem(new ItemStack(slimeweapon));
                DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.getType(EntityType.SLIME)).setViewSelfDisguise(false));
                player.teleport(arena.getStructureManager().getSpawnPoint());

                cancel();

            } else if(arena.getPlayerHandler().getSlime().contains(player)) {
                arena.getPlayerHandler().getSlime().remove(player);
                arena.getPlayerHandler().getChicken().add(player);
                player.getInventory().clear();
                chickenweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString("chicken-weapon-name"));
                chickenweapon.setAmount(1);
                chickenweapon.getItemMeta().setUnbreakable(true);
                player.setMaxHealth(16);
                player.setHealth(16);
                player.getInventory().addItem(new ItemStack(chickenweapon));
                for(PotionEffect pe : player.getActivePotionEffects()) {
                    player.removePotionEffect(pe.getType());
                }
                DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.getType(EntityType.CHICKEN)).setViewSelfDisguise(false));
                player.teleport(arena.getStructureManager().getSpawnPoint());

                cancel();
            } else if(arena.getPlayerHandler().getChicken().contains(player)) {
                for (final Player player : arena.getPlayersManager().getPlayersCopy()) {
                    arena.getPlayerHandler().leavePlayer(player, ChatUtil.format(Messages.playerwonbroadcast.replace("{PLAYER}", player.getName()).replace("{ARENA}", arena.getArenaName())), "");
                    player.getInventory().clear();
                }
                player.sendMessage(ChatUtil.format(Messages.playerwontoplayer));
                arena.getGameHandler().handlePlayer(player);
                arena.getGameHandler().stopArena();
                cancel();
            }

        } else {
            time -= 1;
            if(time == 5 || time == 4 || time == 3 || time == 3 || time == 2 || time == 1) {
                player.sendMessage(ChatUtil.format(Messages.devoluting).replace("{TIME}", String.valueOf(time)));
            }
        }
    }
}
