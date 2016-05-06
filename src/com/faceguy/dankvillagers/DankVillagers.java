package com.faceguy.dankvillagers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DankVillagers extends JavaPlugin implements Listener{
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener) this, (Plugin)this);
        this.getLogger().info("DankVillagers Enabled!");
    }

    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
        this.getLogger().info("DankVillagers Disabled!");
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerHitVillager(EntityDamageByEntityEvent event) {
        Entity e = event.getDamager();
        if (!(event.getEntity() instanceof Villager)) {
            return;
        }
        if (e instanceof Projectile) {
            if (((Projectile) e).getShooter() instanceof Player) {
                e.remove();
                event.setDamage(0);
                event.setCancelled(true);
            }
        } else {
            if (e instanceof Player) {
                event.setDamage(0);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onInteractWithVillager(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        if (event.getRightClicked() instanceof Villager) {
            Villager v = (Villager) event.getRightClicked();
            if (v.getProfession() == Villager.Profession.FARMER) {
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
                if (p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 3600, 0, true, false));
            } else if (v.getProfession() == Villager.Profession.LIBRARIAN) {
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
                if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0, true, false));
            } else if (v.getProfession() == Villager.Profession.PRIEST) {
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
                if (p.hasPotionEffect(PotionEffectType.REGENERATION)) {
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3600, 0, true, false));
            } else if (v.getProfession() == Villager.Profession.BLACKSMITH) {
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
                if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
                    p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0, true, false));
            } else if (v.getProfession() == Villager.Profession.BUTCHER) {
                if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
                if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0, true, false));
            }
            event.setCancelled(true);
        }
    }
}