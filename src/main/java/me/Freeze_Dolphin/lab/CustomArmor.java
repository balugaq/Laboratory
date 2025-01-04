package me.Freeze_Dolphin.lab;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CustomArmor extends ItemStack {
    public CustomArmor(ItemStack itemStack, Color color) {
        super(itemStack);
        ItemMeta meta = getItemMeta();
        if (meta instanceof LeatherArmorMeta lam) {
            lam.setColor(color);
        }
        setItemMeta(meta);
    }
}
