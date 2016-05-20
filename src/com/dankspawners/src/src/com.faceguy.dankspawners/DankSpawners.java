package com.faceguy.dankspawners;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DankSpawners extends JavaPlugin implements Listener{

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("ds").setExecutor(this);
        this.getLogger().info("DankSpawners Enabled!");
    }

    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
        this.getLogger().info("DankSpawners Disabled!");
    }
}