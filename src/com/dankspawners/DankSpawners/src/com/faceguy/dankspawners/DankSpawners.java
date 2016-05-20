package com.faceguy.dankspawners;

import com.faceguy.dankspawners.Listeners.DankSpawnerCommands;
import com.faceguy.dankspawners.Listeners.DankSpawnerListeners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DankSpawners extends JavaPlugin implements Listener{

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new DankSpawnerListeners(this), this);
        this.getCommand("ds").setExecutor(new DankSpawnerCommands());
        this.getLogger().info("DankSpawners Enabled!");
    }

    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
        this.getLogger().info("DankSpawners Disabled!");
    }
}