package it.dado997.Devolution.Signs;

import it.dado997.Devolution.Files.ArenaFile;
import it.dado997.Devolution.Game.Arena;
import it.dado997.Devolution.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class SignEditor {

    private static final SignEditor instance;
    private final HashMap<String, HashSet<SignInfo>> signs;
    private final File configfile;

    private SignEditor() {
        this.signs = new HashMap<String, HashSet<SignInfo>>();
        this.configfile = new File(Main.getInstance().getDataFolder().getAbsolutePath(), "signs.yml");
        saveConfiguration();
    }

    public static SignEditor getInstance() {
        return SignEditor.instance;
    }

    public void addArena(final String arena) {
        if (!this.signs.containsKey(arena)) {
            this.signs.put(arena, new HashSet<SignInfo>());
        }
    }

    public void removeArena(final String arena) {
        for (final Block block : this.getSignsBlocks(arena)) {
            this.removeSign(block, arena);
        }
        this.signs.remove(arena);
    }

    public void addSign(final Block block, final String arena) {
        final SignInfo signinfo = new SignInfo(block);
        this.addArena(arena);
        this.getSigns(arena).add(signinfo);
    }

    public void removeSign(final Block block, final String arena) {
        if (block.getState() instanceof Sign) {
            final Sign sign = (Sign)block.getState();
            sign.setLine(0, "");
            sign.setLine(1, "");
            sign.setLine(2, "");
            sign.setLine(3, "");
            sign.update();
        }
        this.addArena(arena);
        this.getSigns(arena).remove(new SignInfo(block));
    }

    public HashSet<Block> getSignsBlocks(final String arena) {
        final HashSet<Block> signs = new HashSet<Block>();
        for (final SignInfo signinfo : this.getSigns(arena)) {
            final Block block = signinfo.getBlock();
            if (block != null) {
                signs.add(block);
            }
        }
        return signs;
    }

    private void addSignInfo(final SignInfo si, final String arena) {
        this.addArena(arena);
        this.getSigns(arena).add(si);
    }

    private HashSet<SignInfo> getSigns(final String arena) {
        this.addArena(arena);
        return this.signs.get(arena);
    }

    public void modifySigns(final String arenaname) {
        try {
            final Arena arena = ArenaFile.getInstance().getArenaByName(arenaname);
            if (arena == null) {
                return;
            }
            String text = null;
            final int players = arena.getPlayersManager().getCount();
            final int maxPlayers = arena.getStructureManager().getMaxPlayers();
            if (!arena.getStatusManager().isArenaEnabled()) {
                text = ChatColor.RED.toString() + ChatColor.BOLD.toString() + "Disabled";
            }
            else if (arena.getStatusManager().isArenaRunning()) {
                text = ChatColor.RED.toString() + ChatColor.BOLD.toString() + "In Game";
            }
            else if (arena.getStatusManager().isArenaRegenerating()) {
                text = ChatColor.RED.toString() + ChatColor.BOLD.toString() + "Regenerating";
            }
            else if (players == maxPlayers) {
                text = ChatColor.RED.toString() + ChatColor.BOLD.toString() + Integer.toString(players) + "/" + Integer.toString(maxPlayers);
            }
            else {
                text = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + Integer.toString(players) + "/" + Integer.toString(maxPlayers);
            }
            for (final Block block : this.getSignsBlocks(arenaname)) {
                if (block.getState() instanceof Sign) {
                    final Sign sign = (Sign)block.getState();
                    sign.setLine(3, text);
                    sign.update();
                }
                else {
                    this.removeSign(block, arenaname);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfiguration() {
        final FileConfiguration file = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configfile);
        for (final String arena : file.getKeys(false)) {
            final ConfigurationSection section = file.getConfigurationSection(arena);
            for (final String block : section.getKeys(false)) {
                final ConfigurationSection blockSection = section.getConfigurationSection(block);
                final SignInfo si = new SignInfo(blockSection.getString("world"), blockSection.getInt("x"), blockSection.getInt("y"), blockSection.getInt("z"));
                this.addSignInfo(si, arena);
            }
            this.modifySigns(arena);
        }
    }

    public void saveConfiguration() {
        final FileConfiguration file = (FileConfiguration)new YamlConfiguration();
        for (final String arena : this.signs.keySet()) {
            final ConfigurationSection section = file.createSection(arena);
            int i = 0;
            for (final SignInfo si : this.getSigns(arena)) {
                final ConfigurationSection blockSection = section.createSection(Integer.toString(i++));
                blockSection.set("x", (Object)si.getX());
                blockSection.set("y", (Object)si.getY());
                blockSection.set("z", (Object)si.getZ());
                blockSection.set("world", (Object)si.getWorldName());
            }
        }
        try {
            file.save(this.configfile);
        }
        catch (IOException ex) {}
    }

    static {
        instance = new SignEditor();
    }

}
