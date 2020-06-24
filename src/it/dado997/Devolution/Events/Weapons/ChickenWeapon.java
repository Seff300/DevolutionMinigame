package it.dado997.Devolution.Events.Weapons;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import it.dado997.Devolution.Utils.CountDown.WeaponsCountDown;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChickenWeapon implements Listener {

    Arena arena;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        Arena arena = ArenaFile.getInstance().getPlayerArena(player.getName());

        if(e.getAction() == Action.RIGHT_CLICK_AIR) {
            if(arena != null) {
                if(arena.getPlayerHandler().getChicken().contains(player)) {
                    if(arena.getPlayerHandler().getCanShot().contains(player)) {
                        if(player.getItemInHand().getType() == Material.FEATHER) {
                            player.launchProjectile(Egg.class);
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
