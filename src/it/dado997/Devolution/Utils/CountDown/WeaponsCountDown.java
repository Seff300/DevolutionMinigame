package it.dado997.Devolution.Utils.CountDown;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WeaponsCountDown extends BukkitRunnable {

    private int time = 5;
    private Player player;
    private Arena arena;

    public WeaponsCountDown(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public void run() {
        if(time == 0) {
            arena.getPlayerHandler().getCanShot().add(player);
            player.sendMessage(ChatUtil.format(Messages.abilityavaible));
            cancel();
        } else {
            time -= 1;
        }
    }

}
