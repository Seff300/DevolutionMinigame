package it.dado997.Devolution.Utils.CountDown;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DevolutionCountDown extends BukkitRunnable {

    private int time = 5;
    private Player player;
    private Arena arena;

    public DevolutionCountDown(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public void run() {
        if(time == 0) {
            if(arena.getPlayerHandler().getPlayersDevoluting().contains(player)) {
                new DevolutionEvent(player, arena).runTaskTimer(Main.getInstance(), 0, 2);
            }
            cancel();
        } else {
            time -= 1;
            if(time == 5 || time == 4 || time == 3 || time == 3 || time == 2 || time == 1) {
                if(arena.getPlayerHandler().getPlayersDevoluting().contains(player)) {
                   player.sendMessage(ChatUtil.format(Messages.devoluting).replace("{TIME}", String.valueOf(time)));
                }
            }
        }
    }

}
