package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Food implements Listener {

    private static boolean similar(ItemStack item, ItemStack SFitem, boolean lore) {
        return SlimefunUtils.isItemSimilar(item, SFitem, lore);
    }

    private static boolean similar(ItemStack item, ItemStack SFitem) {
        return similar(item, SFitem, true);
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        ItemStack food = e.getItem();
        Player p = e.getPlayer();

        if (similar(food, me.Freeze_Dolphin.lab.Food.MILK_BOTTLE)) {
            for (PotionEffect pe : p.getActivePotionEffects()) {
                p.removePotionEffect(pe.getType());
            }
        }
    }
}
