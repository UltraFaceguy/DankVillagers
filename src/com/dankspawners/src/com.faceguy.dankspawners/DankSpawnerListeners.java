package com.faceguy.dankspawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class DankSpawnerListeners implements Listener {
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerMineSpawner(BlockBreakEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (!evt.getBlock().getType().equals(Material.MOB_SPAWNER)) {
            return;
        }
        if (!evt.getPlayer().hasPermission("dankspawners.mine")) {
            return;
        }
        if (!evt.getPlayer().getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            return;
        }
        evt.setExpToDrop(0);
        evt.getBlock().getDrops().clear();
        CreatureSpawner s = (CreatureSpawner)evt.getBlock().getState();
        ItemStack item = DankSpawnerCommands.getSpawnerItem(1, s.getSpawnedType());
        evt.getBlock().getDrops().add(item);
    }

    @EventHandler
    public static void onSpawnerPlaced(BlockPlaceEvent evt) {
        ItemStack item = evt.getItemInHand();
        if (item == null) {
            return;
        }
        if (item.getType().equals(Material.MOB_SPAWNER)) {
            String sType = item.getItemMeta().getLore() == null ? "Pig Spawner" : item.getItemMeta().getLore().get(0);
            Block setBlock = evt.getBlock();
            setBlock.setType(Material.MOB_SPAWNER);
            CreatureSpawner s = (CreatureSpawner)setBlock.getState();
            s.setSpawnedType(EntityType.valueOf(sType.split(" ")[0].toUpperCase()));
        }
    }
}

