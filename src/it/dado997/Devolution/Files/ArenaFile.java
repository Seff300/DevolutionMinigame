package it.dado997.Devolution.Files;

import it.dado997.Devolution.Game.Arena;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class ArenaFile {

    private static final ArenaFile instance;
    private HashMap<String, Arena> arenanames;

    private ArenaFile() {
        this.arenanames = new HashMap<String, Arena>();
    }

    public static ArenaFile getInstance() {
        return ArenaFile.instance;
    }

    public void registerArena(final Arena arena) {
        this.arenanames.put(arena.getArenaName(), arena);
    }

    public void unregisterArena(final Arena arena) {
        this.arenanames.remove(arena.getArenaName());
    }

    public Collection<Arena> getArenas() {
        return this.arenanames.values();
    }

    public Set<String> getArenasNames() {
        return this.arenanames.keySet();
    }

    public Arena getArenaByName(final String name) {
        return this.arenanames.get(name);
    }

    public Arena getPlayerArena(final String name) {
        for (final Arena arena : this.arenanames.values()) {
            if (arena.getPlayersManager().isInArena(name)) {
                return arena;
            }
        }
        return null;
    }

    static {
        instance = new ArenaFile();
    }

}
