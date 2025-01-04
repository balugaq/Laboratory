package me.Freeze_Dolphin.lab;

import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class CustomPotion extends ItemStack {
    public CustomPotion(String name, Color color, PotionEffect effect, String... lore) {
        super(Material.POTION);

        ItemMeta meta = this.getItemMeta();
        if (meta == null) {
            return;
        }

        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(Arrays.stream(lore).toList());
        }
        if (meta instanceof PotionMeta potionMeta) {
            if (effect != null) {
                potionMeta.addCustomEffect(effect, true);
            }

            if (color != null) {
                potionMeta.setColor(color);
            }
        }

        this.setItemMeta(meta);
    }
}
