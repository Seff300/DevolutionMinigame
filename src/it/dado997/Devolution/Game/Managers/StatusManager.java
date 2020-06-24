package it.dado997.Devolution.Game.Managers;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Signs.SignEditor;
import org.bukkit.entity.Player;

public class StatusManager {

    private Arena arena;
    private boolean enabled;
    private boolean starting;
    private boolean isinlobby;
    private boolean running;
    private boolean regenerating;
    private boolean isDevoluting;

    public StatusManager(final Arena arena) {
        this.enabled = false;
        this.starting = false;
        this.running = false;
        this.regenerating = false;
        this.arena = arena;
    }

    public boolean isArenaEnabled() {
        return this.enabled;
    }

    public boolean enableArena() {
        if (this.arena.getStructureManager().isArenaConfigured()) {
            this.enabled = true;
            this.arena.getGameHandler().startArenaAntiLeaveHandler();
            SignEditor.getInstance().modifySigns(this.arena.getArenaName());
            return true;
        }
        return false;
    }

    public void disableArena() {
        this.enabled = false;
        for (final Player player : this.arena.getPlayersManager().getPlayersCopy()) {
            this.arena.getPlayerHandler().leavePlayer(player, Messages.arenadisabling, "");
        }
        if (this.arena.getStatusManager().isArenaRunning()) {
            this.arena.getGameHandler().stopArena();
        }
        if (this.arena.getStatusManager().isArenaStarting()) {
            this.arena.getGameHandler().stopArenaCountdown();
        }
        this.arena.getGameHandler().stopArenaAntiLeaveHandler();
        SignEditor.getInstance().modifySigns(this.arena.getArenaName());
    }

    public boolean isDevoluting() {
        return this.isDevoluting;
    }

    public void setDevoluting(final boolean isDevoluting) {
        this.isDevoluting = isDevoluting;
    }

    public boolean isArenaStarting() {
        return this.starting;
    }

    public void setStarting(final boolean starting) {
        this.starting = starting;
    }

    public boolean getIsInLobby() {
        return this.isinlobby;
    }

    public void setIsinlobby(final boolean isinlobby) {
        this.isinlobby = isinlobby;
    }

    public boolean isArenaRunning() {
        return this.running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public boolean isArenaRegenerating() {
        return this.regenerating;
    }

    public void setRegenerating(final boolean regenerating) {
        this.regenerating = regenerating;
    }

}
