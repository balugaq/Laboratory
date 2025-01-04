package me.Freeze_Dolphin.lab.api;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Str2Item {
    public static ItemStack str2item(String configString) {
        ItemStack rt = null;

        try {
            String[] split = configString.split("{").clone();

            if (split[0].equalsIgnoreCase("MC")) {

                rt = new ItemStack(Material.getMaterial(split[1].split("}")[0]));
            } else if (split[0].equalsIgnoreCase("SF")) {

                SlimefunItem item = SlimefunItem.getById(split[1].split("}")[0]);
                if (item != null) {
                    rt = item.getItem();
                }
            } else {

                rt = getError();
            }

        } catch (Exception ex) {
            rt = getError();
        }

        return rt;
    }

    private static ItemStack getError() {
        return new CustomItemStack(Material.REDSTONE, "&c&l错误!", "", "&f出现了一个错误, 请联系管理员检查服务器配置文件!");
    }
}
