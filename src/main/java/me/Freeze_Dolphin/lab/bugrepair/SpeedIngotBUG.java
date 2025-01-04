package me.Freeze_Dolphin.lab.bugrepair;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Tech;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public final class SpeedIngotBUG implements Listener {
    private static void clearItem(ItemStack it) {
        it.setAmount(0);
        it.setType(Material.AIR);
    }

    private static boolean isBugItem(ItemStack it) {
        return SlimefunUtils.isItemSimilar(it, Tech.SPEED_INGOT_BUG, false);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        for (ItemStack it : e.getPlayer().getInventory()) {
            if (SlimefunUtils.isItemSimilar(it, Tech.SPEED_INGOT_BUG, false)) {
                clearItem(it);
                e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
            }
        }

        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getHelmet(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getHelmet());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }

        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getItemInOffHand(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getItemInOffHand());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }

        if (SlimefunUtils.isItemSimilar(
                e.getPlayer().getInventory().getItemInMainHand(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getItemInMainHand());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }
    }

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent e) {
        for (ItemStack it : e.getInventory()) {
            if (SlimefunUtils.isItemSimilar(it, Tech.SPEED_INGOT_BUG, false)) {
                clearItem(it);
                e.getPlayer().sendMessage("检测到该容器中有漏洞物品, 已自动清除");
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerItemHeldEvent e) {
        if (isBugItem(e.getPlayer().getInventory().getItem(e.getPreviousSlot()))) {
            clearItem(e.getPlayer().getInventory().getItem(e.getPreviousSlot()));
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        for (ItemStack it : e.getPlayer().getInventory()) {
            if (SlimefunUtils.isItemSimilar(it, Tech.SPEED_INGOT_BUG, false)) {
                clearItem(it);
                e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
            }
        }

        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getHelmet(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getHelmet());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }

        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getItemInOffHand(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getItemInOffHand());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }

        if (SlimefunUtils.isItemSimilar(
                e.getPlayer().getInventory().getItemInMainHand(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getItemInMainHand());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        for (ItemStack it : e.getPlayer().getInventory()) {
            if (SlimefunUtils.isItemSimilar(it, Tech.SPEED_INGOT_BUG, false)) {
                clearItem(it);
                e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
            }
        }

        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getHelmet(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getHelmet());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }

        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getItemInOffHand(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getItemInOffHand());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }

        if (SlimefunUtils.isItemSimilar(
                e.getPlayer().getInventory().getItemInMainHand(), Tech.SPEED_INGOT_BUG, false)) {
            clearItem(e.getPlayer().getInventory().getItemInMainHand());
            e.getPlayer().sendMessage("检测到你背包中有漏洞物品, 已自动清除");
        }
    }

    @EventHandler
    public void onChanged(PlayerDropItemEvent e) {
        if (isBugItem(e.getItemDrop().getItemStack())) {
            clearItem(e.getItemDrop().getItemStack());
            e.getPlayer().sendMessage("检测到你扔出了漏洞物品, 已自动清除");
        }
    }
}
