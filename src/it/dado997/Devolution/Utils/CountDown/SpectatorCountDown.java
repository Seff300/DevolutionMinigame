package it.dado997.Devolution.Utils.CountDown;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import it.dado997.Devolution.Utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpectatorCountDown extends BukkitRunnable {

    private int time = 10;
    private Player player;
    private Arena arena;

    public SpectatorCountDown(Player player, Arena arena) {
        this.player = player;
        this.arena = arena;
    }

    @Override
    public void run() {
        if(time == 0) {
            if(arena.getPlayersManager().getSpectators().contains(player)) {
                new SpectatorEvent(player, arena).runTaskTimer(Main.getInstance(), 0, 2);
            }
            cancel();
        } else {
            time -= 1;
            if(time == 10 || time == 9 || time == 8 || time == 7 || time == 6 || time == 5 || time == 4 || time == 3 || time == 3 || time == 2 || time == 1) {
                if(arena.getPlayersManager().getSpectators().contains(player)) {
                    player.sendMessage(ChatUtil.format(Messages.respawning).replace("{TIME}", String.valueOf(time)));
                }
            }
        }
    }

}
