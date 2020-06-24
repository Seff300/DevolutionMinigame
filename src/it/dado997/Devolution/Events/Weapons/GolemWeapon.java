package it.dado997.Devolution.Events.Weapons;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import it.dado997.Devolution.Utils.CountDown.WeaponsCountDown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class GolemWeapon implements Listener {

    Arena arena;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        arena = ArenaFile.getInstance().getPlayerArena(player.getName());

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if(arena != null) {
                if(arena.getPlayerHandler().getGolem().contains(player)) {
                    if(arena.getPlayerHandler().getCanShot().contains(player)) {
                        if(player.getItemInHand().getType() == Material.IRON_INGOT) {
                            List<Entity> entities = player.getNearbyEntities(2, 2, 2);

                            for(Entity en : entities) {
								Damageable d = (Damageable) en;
                                if(d.getHealth() <= 6) {
                                    d.setHealth(0);
                                } else {
                                    d.setHealth(d.getHealth() - 6);
                                }
                            }

                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);

                            arena.getPlayerHandler().getCanShot().remove(player);
                            new WeaponsCountDown(player, arena).runTaskTimer(Main.getInstance(), 0L, 20L);
                            player.sendMessage(ChatUtil.format(Messages.abilityused));
                        } else {
                            return;
                        }
                    } else {
                        player.sendMessage(ChatUtil.format(Messages.abilitynotavaible));
                    }
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
