package me.Freeze_Dolphin.lab.bugrepair;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ScrollOfDimensionalTelepositionConsumption implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        if (e.hasItem()
                && SlimefunUtils.isItemSimilar(e.getItem(), SlimefunItems.SCROLL_OF_DIMENSIONAL_TELEPOSITION, true))
            e.getItem().setAmount(e.getItem().getAmount() - 1);
    }
}
