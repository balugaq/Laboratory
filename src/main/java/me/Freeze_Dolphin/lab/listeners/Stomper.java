package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.Freeze_Dolphin.lab.Lab;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public final class Stomper implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDrop(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p && !e.isCancelled()) {
            byte b;
            int i;
            ItemStack[] arrayOfItemStack;
            for (i = (arrayOfItemStack = p.getInventory().getArmorContents()).length, b = 0; b < i; ) {
                ItemStack armor = arrayOfItemStack[b];
                if (armor != null && SlimefunItem.getByItem(armor) != null) {
                    if (SlimefunItem.getByItem(armor).isItem(Lab.REINFORCED_STOMPER)
                            && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setCancelled(true);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 2.0F, 2.0F);
                        p.setVelocity(new Vector(0.0D, 0.7D, 0.0D));
                        for (Entity n : p.getNearbyEntities(4.0D, 4.0D, 4.0D)) {
                            if (n instanceof LivingEntity
                                    && !n.getUniqueId()
                                    .toString()
                                    .equalsIgnoreCase(p.getUniqueId().toString())) {
                                n.setVelocity(n.getLocation()
                                        .toVector()
                                        .subtract(p.getLocation().toVector())
                                        .normalize()
                                        .multiply(1.4D));
                                if (p.getWorld().getPVP()) {
                                    EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(
                                            p, n, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e.getDamage() * 2.0D);
                                    Bukkit.getPluginManager().callEvent(event);
                                    if (!event.isCancelled()) {
                                        ((LivingEntity) n).damage(e.getDamage() / 1.5D);
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < 2; j++) {
                            byte b1;
                            int k;
                            BlockFace[] arrayOfBlockFace;
                            for (k = (arrayOfBlockFace = BlockFace.values()).length, b1 = 0; b1 < k; ) {
                                BlockFace face = arrayOfBlockFace[b1];
                                p.getWorld()
                                        .playEffect(
                                                p.getLocation()
                                                        .getBlock()
                                                        .getRelative(BlockFace.DOWN)
                                                        .getRelative(face)
                                                        .getLocation(),
                                                Effect.STEP_SOUND,
                                                p.getLocation()
                                                        .getBlock()
                                                        .getRelative(BlockFace.DOWN)
                                                        .getRelative(face)
                                                        .getType());
                                b1++;
                            }
                        }
                    }
                }
                b++;
            }
        }
    }
}
