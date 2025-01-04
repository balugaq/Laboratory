package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ItemEnergy {

    private ItemEnergy() {}

    //	"&c&o&8\u21E8 &e\u26A1 &70 / 50 J"

    public static float getStoredEnergy(@Nullable ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return 0F;
        }
        SlimefunItem sfitem = SlimefunItem.getByItem(item);
        if (sfitem instanceof Rechargeable rechargeable) {
            return rechargeable.getItemCharge(item);
        }

        return 0F;
    }

    public static float getMaxEnergy(@Nullable ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return 0F;
        }
        SlimefunItem sfitem = SlimefunItem.getByItem(item);
        if (sfitem instanceof Rechargeable rechargeable) {
            return rechargeable.getMaxItemCharge(item);
        }
        return 0F;
    }

    // Returns a non-negative float representing the amount of energy that was added or removed.
    public static float addStoredEnergy(ItemStack item, float energy) {
        float before = getStoredEnergy(item);
        chargeItem(item, energy);
        // return the added energy
        float after = getStoredEnergy(item);
        return Math.max(0, after - before);
    }

    public static ItemStack chargeItem(ItemStack item, float energy) {
        if (energy < 0) {
            return removeEnergy(item, energy * -1);
        }
        if (energy == 0) {
            return item;
        }
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        SlimefunItem sfitem = SlimefunItem.getByItem(item);
        if (sfitem instanceof Rechargeable rechargeable) {
            rechargeable.addItemCharge(item, energy);
        }
        return item;
    }

    public static ItemStack removeEnergy(ItemStack item, float energy) {
        if (energy < 0) {
            return chargeItem(item, energy * -1);
        }
        if (energy == 0) {
            return item;
        }
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        SlimefunItem sfitem = SlimefunItem.getByItem(item);
        if (sfitem instanceof Rechargeable rechargeable) {
            rechargeable.removeItemCharge(item, energy);
        }
        return item;
    }

    public static void chargeInventory(@NotNull Player p, float energy) {
        p.getInventory().setItemInMainHand(chargeItem(p.getInventory().getItemInMainHand(), energy));
        p.getInventory().setItemInOffHand(chargeItem(p.getInventory().getItemInOffHand(), energy));
        p.getInventory().setHelmet(chargeItem(p.getInventory().getHelmet(), energy));
        p.getInventory().setChestplate(chargeItem(p.getInventory().getChestplate(), energy));
        p.getInventory().setLeggings(chargeItem(p.getInventory().getLeggings(), energy));
        p.getInventory().setBoots(chargeItem(p.getInventory().getBoots(), energy));
    }
}
