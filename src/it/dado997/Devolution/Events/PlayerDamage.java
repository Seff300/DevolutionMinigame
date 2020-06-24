package it.dado997.Devolution.Events;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        Entity damager  = e.getDamager();
        Player player = (Player) e.getEntity();
        Arena arena = ArenaFile.getInstance().getPlayerArena(damager.getName());
        if(e.getEntity() instanceof Player) {
        if(arena != null) {
        if(arena.getPlayerHandler().getGolem().contains(damager)) {
            e.setDamage(5);
        } else if(arena.getPlayerHandler().getBlaze().contains(damager)) {
            e.setDamage(5);
        } else if(arena.getPlayerHandler().getSpider().contains(damager)) {
            if(arena.getPlayerHandler().getSpiderWeapon().contains(damager)) {
                if(player instanceof Player) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 45, 0));
                    e.setDamage(4);
                } else {
                    return;
                }
            } else {
                e.setDamage(4);
            }
        } else if(arena.getPlayerHandler().getCreeper().contains(damager)) {
            e.setDamage(4);
        } else if(arena.getPlayerHandler().getSlime().contains(damager)) {
            e.setDamage(3);
        } else if(arena.getPlayerHandler().getChicken().contains(damager)) {
            e.setDamage(2);
        } else {
            return;
        }
        } else {
            return;
        }
      } else {
            return;
        }
    }

}
