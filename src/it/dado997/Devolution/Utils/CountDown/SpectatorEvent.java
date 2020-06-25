package it.dado997.Devolution.Utils.CountDown;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SpectatorEvent extends BukkitRunnable {

    Player player = null;
    Arena arena = null;

    public static ItemStack golemweapon = new ItemStack(Material.IRON_INGOT);
    public static ItemStack blazeweapon = new ItemStack(Material.BLAZE_ROD);
    public static ItemStack spiderweapon = new ItemStack(Material.SPIDER_EYE);
    public static ItemStack slimeweapon = new ItemStack(Material.SLIME_BALL);
    public static ItemStack creeperweapon = new ItemStack(Material.LEGACY_SULPHUR);
    public static ItemStack chickenweapon = new ItemStack(Material.FEATHER);

    public SpectatorEvent(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public void run() {
        if(arena.getPlayersManager().getSpectators().contains(player)) {
            arena.getPlayersManager().removeSpecator(player.getName());
            for(Player aplayer : Bukkit.getOnlinePlayers()) {
                aplayer.showPlayer(player);
            }
            player.teleport(arena.getStructureManager().getSpawnPoint());
            player.setAllowFlight(false);
            player.setFlying(false);
            player.getInventory().clear();
            if(arena.getPlayerHandler().getGolem().contains(player)) {
                golemweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString(ChatUtil.format("golem-weapon-name")));
                player.getInventory().addItem(golemweapon);
            } else if(arena.getPlayerHandler().getBlaze().contains(player)) {
                blazeweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString(ChatUtil.format("blaze-weapon-name")));
                player.getInventory().addItem(blazeweapon);
            } else if(arena.getPlayerHandler().getSpider().contains(player)) {
                spiderweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString(ChatUtil.format("spider-weapon-name")));
                player.getInventory().addItem(spiderweapon);
            } else if(arena.getPlayerHandler().getCreeper().contains(player)) {
                creeperweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString(ChatUtil.format("creeper-weapon-name")));
                player.getInventory().addItem(creeperweapon);
            } else if(arena.getPlayerHandler().getSlime().contains(player)) {
                slimeweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString(ChatUtil.format("slime-weapon-name")));
                player.getInventory().addItem(slimeweapon);
            } else if(arena.getPlayerHandler().getChicken().contains(player)) {
                chickenweapon.getItemMeta().setDisplayName(Main.getInstance().getConfig().getString(ChatUtil.format("chicken-weapon-name")));
                player.getInventory().addItem(chickenweapon);
            }
            player.sendMessage(ChatUtil.format(Messages.respawned));
            cancel();
         } else {
             return;
         }
    }

}
