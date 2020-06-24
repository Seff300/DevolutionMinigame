package it.dado997.Devolution.Utils.CountDown;

import it.dado997.Devolution.Game.Arena;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpiderWeaponCountDown extends BukkitRunnable {

    private int time = 2;
    private Player player;
    private Arena arena;

    public SpiderWeaponCountDown(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public void run() {
        if(time == 0) {
            arena.getPlayerHandler().getSpiderWeapon().remove(player);
            cancel();
        } else {
            time -= 1;
        }
    }

}
