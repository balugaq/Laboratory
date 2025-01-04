package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class UnplaceableItems implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        for (ItemStack it : Variables.UnplaceableItems) {
            if (SlimefunUtils.isItemSimilar(e.getItemInHand(), it, true)) e.setCancelled(true);
        }
    }
}
