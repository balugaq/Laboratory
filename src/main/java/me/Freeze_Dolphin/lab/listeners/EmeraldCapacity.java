package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.ArrayList;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public final class EmeraldCapacity implements Listener {
    @EventHandler
    public void emeraldCapacity1(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        boolean hasEmeraldCapacity1 = false;
        ItemStack ec1 = null;
        ArrayList<ItemStack> ci1 = new ArrayList<>();

        ItemStack[] ArmorContents1 = p.getInventory().getArmorContents();
        ItemStack[] StorageContents2 = p.getInventory().getStorageContents();
        byte b;
        int i;
        ItemStack[] arrayOfItemStack1;
        for (i = (arrayOfItemStack1 = StorageContents2).length, b = 0; b < i; ) {
            ItemStack isa1 = arrayOfItemStack1[b];
            if (SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_1, false)) {
                hasEmeraldCapacity1 = true;
                if (ItemEnergy.getStoredEnergy(isa1) > 1.0F) {
                    ec1 = isa1;
                }
            }

            if (ItemEnergy.getMaxEnergy(isa1) > 0.0F
                    && !SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_1, false)
                    && !SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_2, false)
                    && ItemEnergy.getStoredEnergy(isa1) != ItemEnergy.getMaxEnergy(isa1)) {
                ci1.add(isa1);
            }

            b++;
        }

        for (i = (arrayOfItemStack1 = ArmorContents1).length, b = 0; b < i; ) {
            ItemStack isa1 = arrayOfItemStack1[b];
            if (SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_1, false)) {
                hasEmeraldCapacity1 = true;
                if (ItemEnergy.getStoredEnergy(isa1) > 1.0F) {
                    ec1 = isa1;
                }
            }

            if (ItemEnergy.getMaxEnergy(isa1) > 0.0F
                    && !SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_1, false)
                    && !SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_2, false)
                    && ItemEnergy.getStoredEnergy(isa1) != ItemEnergy.getMaxEnergy(isa1)) {
                ci1.add(isa1);
            }

            b++;
        }

        if (hasEmeraldCapacity1 && ec1 != null && ItemEnergy.getStoredEnergy(ec1) >= 0.01F * ci1.size()) {
            ItemEnergy.chargeItem(ec1, -0.01F * ci1.size());
            for (ItemStack isb : ci1) {
                ItemEnergy.chargeItem(isb, 0.01F);
            }
        }
    }

    @EventHandler
    public void emeraldCapacity2(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        boolean hasEmeraldCapacity2 = false;
        ItemStack ec2 = null;
        ArrayList<ItemStack> ci2 = new ArrayList<>();

        ItemStack[] ArmorContents2 = p.getInventory().getArmorContents();
        ItemStack[] StorageContents2 = p.getInventory().getStorageContents();
        byte b;
        int i;
        ItemStack[] arrayOfItemStack1;
        for (i = (arrayOfItemStack1 = StorageContents2).length, b = 0; b < i; ) {
            ItemStack isa2 = arrayOfItemStack1[b];
            if (SlimefunUtils.isItemSimilar(isa2, Lab.EMERALD_CAPACITY_2, false)) {
                hasEmeraldCapacity2 = true;
                if (ItemEnergy.getStoredEnergy(isa2) > 1.0F) {
                    ec2 = isa2;
                }
            }

            if (ItemEnergy.getMaxEnergy(isa2) > 0.0F
                    && !SlimefunUtils.isItemSimilar(isa2, Lab.EMERALD_CAPACITY_1, false)
                    && !SlimefunUtils.isItemSimilar(isa2, Lab.EMERALD_CAPACITY_2, false)
                    && ItemEnergy.getStoredEnergy(isa2) != ItemEnergy.getMaxEnergy(isa2)) {
                ci2.add(isa2);
            }

            b++;
        }

        for (i = (arrayOfItemStack1 = ArmorContents2).length, b = 0; b < i; ) {
            ItemStack isa2 = arrayOfItemStack1[b];
            if (SlimefunUtils.isItemSimilar(isa2, Lab.EMERALD_CAPACITY_2, false)) {
                hasEmeraldCapacity2 = true;
                if (ItemEnergy.getStoredEnergy(isa2) > 1.0F) {
                    ec2 = isa2;
                }
            }

            if (ItemEnergy.getMaxEnergy(isa2) > 0.0F
                    && !SlimefunUtils.isItemSimilar(isa2, Lab.EMERALD_CAPACITY_1, false)
                    && !SlimefunUtils.isItemSimilar(isa2, Lab.EMERALD_CAPACITY_2, false)
                    && ItemEnergy.getStoredEnergy(isa2) != ItemEnergy.getMaxEnergy(isa2)) {
                ci2.add(isa2);
            }

            b++;
        }

        if (hasEmeraldCapacity2 && ec2 != null && ItemEnergy.getStoredEnergy(ec2) >= 1.0F * ci2.size()) {
            ItemEnergy.chargeItem(ec2, -1.0F * ci2.size());
            for (ItemStack isb : ci2) {
                ItemEnergy.chargeItem(isb, 1.0F);
            }
        }
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.EMERALD_CAPACITY_1, false)) {
            e.setCancelled(true);
        }
        if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.EMERALD_CAPACITY_2, false)) e.setCancelled(true);
    }
}
