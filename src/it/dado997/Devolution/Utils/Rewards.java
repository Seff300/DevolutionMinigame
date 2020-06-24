package it.dado997.Devolution.Utils;

import it.dado997.Devolution.Files.Messages;
import it.dado997.Devolution.Main;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class Rewards {

    private Object economy;
    private List<ItemStack> itemrewards;
    private List<String> commands;
    private int moneyreward;

    public Rewards() {
        this.economy = null;
        this.itemrewards = new ArrayList<ItemStack>();
        this.commands = new ArrayList<String>();
        this.moneyreward = 0;
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)Bukkit.getServicesManager().getRegistration((Class)Economy.class);
            if (economyProvider != null) {
                this.economy = economyProvider.getProvider();
            }
        } else {
            Main.getInstance().getLogger().warning("Vault not detected, money rewards disabled!");
        }
    }

    public List<String> getCommandsToExecute() {
        return this.commands;
    }

    public int getMoneyReward() {
        return this.moneyreward;
    }

    public void setMoneyReward(final int money) {
        this.moneyreward = money;
    }

    public void addCommandToExecute(final String command) {
        this.commands.add(command);
    }

    public void clearCommandsToExceute() {
        this.commands.clear();
    }

    public void rewardPlayer(final Player player) {
        String rewardmessage = "";
        for (final ItemStack reward : this.itemrewards) {
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(new ItemStack[] { reward });
            }
            else {
                player.getWorld().dropItemNaturally(player.getLocation(), reward);
            }
            rewardmessage = rewardmessage + reward.getAmount() + " x " + reward.getType().toString().replace("_", "").toLowerCase() + ", ";
        }
        if (this.moneyreward != 0) {
            this.rewardMoney(player, this.moneyreward);
            rewardmessage = rewardmessage + ChatColor.GOLD.toString() + this.moneyreward;
        }
        if (rewardmessage.endsWith(", ")) {
            rewardmessage = rewardmessage.substring(0, rewardmessage.length() - 2);
        }
        rewardmessage = Messages.playerrewardmessage.replace("{REWARD}", rewardmessage);
        if (!rewardmessage.isEmpty()) {
            Messages.sendMessage(player, rewardmessage);
        }
        for (String command : this.commands) {
            command = command.replace("{playerName}", player.getName());
            command = command.replace("{playerUUID}", player.getUniqueId().toString());
            Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), command);
        }
    }

    private void rewardMoney(final Player player, final int money) {
        if (this.economy != null) {
            final Economy econ = (Economy)this.economy;
            econ.depositPlayer((OfflinePlayer)player, (double)money);
        }
    }

    public void saveToConfig(final FileConfiguration config) {
        config.set("reward.money", (Object)this.moneyreward);
        config.set("reward.items", (Object)this.itemrewards);
    }

    public void loadFromConfig(final FileConfiguration config) {
        this.moneyreward = config.getInt("reward.money", this.moneyreward);
        final Object obj = config.get("reward.items");
        if (obj != null) {
            this.itemrewards = (List<ItemStack>)obj;
        }
    }

}
