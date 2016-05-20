package com.faceguy.dankspawners.Listeners;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DankSpawnerCommands implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ds")) {
            if (args.length == 0) {
                return false;
            }
            if (args.length < 3) {
                s.sendMessage(ChatColor.GREEN + "Usage: /ds " + ChatColor.GRAY + "give [player] [entity] (amount)");
                return false;
            }
            if (!args[0].equalsIgnoreCase("give")) {
                return false;
            }
            if (!s.hasPermission("dankspawners.give")) {
                s.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return true;
            }
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                s.sendMessage(ChatColor.RED + "Invalid player name!");
                return true;
            }
            EntityType type = EntityType.valueOf(args[2].toUpperCase());
            if (type == null) {
                s.sendMessage(ChatColor.RED + "Invalid entity name!");
                return true;
            }
            int amount = 1;
            if (args[3] != null) {
                amount = Integer.valueOf(args[3]);
            }
            giveSpawner(player, type, amount);
            s.sendMessage(ChatColor.GREEN + "Gave " + player.getName() + " " + amount + " " + type + " " + "spawner(s)!");
        }
        return true;
    }

    public void giveSpawner(Player player, EntityType type, int amount) {
        ItemStack item = getSpawnerItem(amount, type);
        player.getInventory().addItem(item);
    }

    public static ItemStack getSpawnerItem(int amount, EntityType type) {
        ItemStack item = new ItemStack(Material.MOB_SPAWNER, amount);
        ArrayList<String> lore = new ArrayList<>();
        String loreString = type.toString();
        loreString = String.valueOf(loreString.substring(0, 1).toUpperCase()) + loreString.substring(1).toLowerCase();
        loreString = String.valueOf(loreString) + " Spawner";
        lore.add(loreString);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}