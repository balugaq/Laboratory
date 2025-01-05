package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class SapphireCapacity implements Listener {
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.SAPPHIRE_CAPACITY, false)) {
            e.setCancelled(true);
        }
    }
}
