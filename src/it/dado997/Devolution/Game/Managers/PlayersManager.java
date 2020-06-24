package it.dado997.Devolution.Game.Managers;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class PlayersManager {

    private HashMap<String, Player> players;
    private HashMap<String, Player> spectators;

    public PlayersManager() {
        this.players = new HashMap<String, Player>();
        this.spectators = new HashMap<String, Player>();
    }

    public boolean isInArena(final String name) {
        return this.players.containsKey(name);
    }

    public int getCount() {
        return this.players.size();
    }

    public HashSet<Player> getAllParticipantsCopy() {
        final HashSet<Player> p = new HashSet<Player>();
        p.addAll(this.players.values());
        p.addAll(this.spectators.values());
        return p;
    }

    public Collection<Player> getPlayers() {
        return Collections.unmodifiableCollection((Collection<? extends Player>)this.players.values());
    }

    public HashSet<Player> getPlayersCopy() {
        return new HashSet<Player>(this.players.values());
    }

    public void add(final Player player) {
        this.players.put(player.getName(), player);
    }

    public void remove(final Player player) {
        this.players.remove(player.getName());
    }

    public boolean isSpectator(final String name) {
        return this.spectators.containsKey(name);
    }

    public void addSpectator(final Player player) {
        this.spectators.put(player.getName(), player);
    }

    public void removeSpecator(final String name) {
        this.spectators.remove(name);
    }

    public Collection<Player> getSpectators() {
        return Collections.unmodifiableCollection((Collection<? extends Player>)this.spectators.values());
    }

    public HashSet<Player> getSpectatorsCopy() {
        return new HashSet<Player>(this.spectators.values());
    }

}
