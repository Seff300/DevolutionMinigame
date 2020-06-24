package it.dado997.Devolution.Events.Weapons;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import it.dado997.Devolution.Utils.CountDown.WeaponsCountDown;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
public class BlazeWeapon implements Listener {

    Arena arena;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());

        Vector vector;

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(arena != null) {
                if(arena.getPlayerHandler().getBlaze().contains(player)) {
                    if(arena.getPlayerHandler().getCanShot().contains(player)) {
                        if(player.getItemInHand().getType() == Material.BLAZE_ROD) {
                        vector = player.getEyeLocation().getDirection();
                        player.launchProjectile(SmallFireball.class, vector);
                        player.launchProjectile(SmallFireball.class, vector.setX(-3));
                        player.launchProjectile(SmallFireball.class, vector.setX(+3));
                        player.launchProjectile(SmallFireball.class, vector.setZ(-3));
                        player.launchProjectile(SmallFireball.class, vector.setZ(+3));
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
