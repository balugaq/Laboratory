package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StartupTasks {
    public StartupTasks() {
        radioactivityTask();
    }

    private static void radioactivityTask() {
        Config config = io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getCfg();
        Laboratory.debug(config.getBoolean("options.enable-armor-effects") + " , "
                + config.getInt("options.armor-update-interval"));

        if (config.getBoolean("options.enable-armor-effects"))
            Variables.plug
                    .getServer()
                    .getScheduler()
                    .runTaskTimer(
                            Laboratory.getPlugin(Laboratory.class),
                            () -> {
                                for (Player p : Variables.plug.getServer().getOnlinePlayers()) {
                                    for (ItemStack radioactive : Variables.penetratingRadioactive) {
                                        if (p.getInventory().containsAtLeast(radioactive, 1)
                                                || SlimefunUtils.isItemSimilar(
                                                p.getInventory().getItemInOffHand(), radioactive, true)) {

                                            if (SlimefunUtils.isItemSimilar(
                                                    ArmorWeapon.REINFORCED_SCUBA_HELMET,
                                                    p.getInventory().getHelmet(),
                                                    true)
                                                    && SlimefunUtils.isItemSimilar(
                                                    ArmorWeapon.REINFORCED_HAZMATSUIT_CHESTPLATE,
                                                    p.getInventory().getChestplate(),
                                                    true)
                                                    && SlimefunUtils.isItemSimilar(
                                                    ArmorWeapon.REINFORCED_HAZMATSUIT_LEGGINGS,
                                                    p.getInventory().getLeggings(),
                                                    true)
                                                    && SlimefunUtils.isItemSimilar(
                                                    SlimefunItems.HAZMAT_BOOTS,
                                                    p.getInventory().getBoots(),
                                                    true)) {
                                                break;
                                            }

                                            SlimefunItem slimefunItem = SlimefunItem.getByItem(radioactive);
                                            if (slimefunItem == null) {
                                                continue;
                                            }

                                            if (!slimefunItem.isDisabled()) {
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.WITHER, 400, 10), true);
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.BLINDNESS, 400, 3), true);
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.CONFUSION, 400, 3), true);
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.WEAKNESS, 400, 3), true);
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.SLOW, 400, 3), true);
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 3), true);

                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.HUNGER, 400, 15), true);
                                                p.addPotionEffect(
                                                        new PotionEffect(PotionEffectType.JUMP, 400, -10), true);

                                                p.setFireTicks(400);
                                                break;
                                            }
                                            continue;
                                        }
                                        byte b;
                                        int i;
                                        ItemStack[] arrayOfItemStack;
                                        for (i = (arrayOfItemStack = p.getInventory()
                                                .getContents())
                                                .length,
                                                     b = 0;
                                             b < i; ) {
                                            ItemStack itemStack = arrayOfItemStack[b];
                                            if (SlimefunUtils.isItemSimilar(itemStack, radioactive, true)) {
                                                if (SlimefunUtils.isItemSimilar(
                                                        ArmorWeapon.REINFORCED_SCUBA_HELMET,
                                                        p.getInventory().getHelmet(),
                                                        true)
                                                        && SlimefunUtils.isItemSimilar(
                                                        ArmorWeapon.REINFORCED_HAZMATSUIT_CHESTPLATE,
                                                        p.getInventory().getChestplate(),
                                                        true)
                                                        && SlimefunUtils.isItemSimilar(
                                                        ArmorWeapon.REINFORCED_HAZMATSUIT_LEGGINGS,
                                                        p.getInventory().getLeggings(),
                                                        true)
                                                        && SlimefunUtils.isItemSimilar(
                                                        SlimefunItems.HAZMAT_BOOTS,
                                                        p.getInventory().getBoots(),
                                                        true)) {
                                                    break;
                                                }

                                                SlimefunItem slimefunItem = SlimefunItem.getByItem(radioactive);
                                                if (slimefunItem == null) {
                                                    continue;
                                                }

                                                if (!slimefunItem.isDisabled()) {
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.WITHER, 400, 10), true);
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.BLINDNESS, 400, 3), true);
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.CONFUSION, 400, 3), true);
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.WEAKNESS, 400, 3), true);
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.SLOW, 400, 3), true);
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 3),
                                                            true);

                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.HUNGER, 400, 15), true);
                                                    p.addPotionEffect(
                                                            new PotionEffect(PotionEffectType.JUMP, 400, -10), true);

                                                    p.setFireTicks(400);

                                                    break;
                                                }
                                            }

                                            b++;
                                        }
                                    }
                                }
                            },
                            0L,
                            config.getInt("options.armor-update-interval") * 20L);
    }
}
