package me.Freeze_Dolphin.lab;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class CustomPotion extends ItemStack {
    public CustomPotion(String name, Color color, PotionEffect effect, String... lore) {
        super(Material.POTION);

        ItemMeta meta = getItemMeta();
        if (meta == null) {
            return;
        }

        if (name != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        }

        if (lore != null) {
            meta.setLore(Arrays.stream(lore).map(s -> ChatColor.translateAlternateColorCodes('&', s)).toList());
        }

        if (meta instanceof PotionMeta potionMeta) {
            if (color != null) {
                potionMeta.setColor(color);
            }
            if (effect != null) {
                potionMeta.addCustomEffect(effect, true);
            }
        }

        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        this.setItemMeta(meta);
    }
}
