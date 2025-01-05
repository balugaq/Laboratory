package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.List;
import me.Freeze_Dolphin.lab.Main;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class RadioisotopeThermoelectricGenerator extends AGenerator {
    private static final int[] border =
            new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 50, 51, 52, 53};
    private static final int[] border_in =
            new int[] {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

    public RadioisotopeThermoelectricGenerator(
            ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getId(), getInventoryTitle()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(@NotNull Block block, @NotNull Player player) {
                return player.hasPermission("slimefun.inventory.bypass") || canUse(player, true);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow) {
                return getInputSlots();
            }
        };

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@NotNull BlockBreakEvent blockBreakEvent, @NotNull ItemStack itemStack, @NotNull List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    byte b1;
                    int i;
                    int[] arrayOfInt;
                    for (i = (arrayOfInt = RadioisotopeThermoelectricGenerator.this.getInputSlots()).length, b1 = 0;
                            b1 < i; ) {
                        int slot = arrayOfInt[b1];
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                        b1++;
                    }

                    for (i = (arrayOfInt = RadioisotopeThermoelectricGenerator.this.getOutputSlots()).length, b1 = 0;
                            b1 < i; ) {
                        int slot = arrayOfInt[b1];
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                        b1++;
                    }
                }
            }
        });
    }

    private void constructMenu(BlockMenuPreset preset) {
        byte b;
        int i;
        int[] arrayOfInt;
        for (i = (arrayOfInt = border).length, b = 0; b < i; ) {
            int j = arrayOfInt[b];
            preset.addItem(
                    j, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
            b++;
        }

        for (i = (arrayOfInt = border_in).length, b = 0; b < i; ) {
            int j = arrayOfInt[b];
            preset.addItem(
                    j, new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);

            b++;
        }

        for (i = (arrayOfInt = getOutputSlots()).length, b = 0; b < i; ) {
            int j = arrayOfInt[b];
            preset.addMenuClickHandler(j, new ChestMenu.AdvancedMenuClickHandler() {
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                public boolean onClick(
                        InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return !(cursor != null && cursor.getType() != Material.AIR);
                }
            });
            b++;
        }

        preset.addItem(
                49,
                new CustomItemStack(
                        Material.ORANGE_STAINED_GLASS_PANE,
                        "&e当前发电效率: &f0 &7J/s",
                        "",
                        "&f放入放射性同位素靶丸来发电",
                        "&f靶丸越多发电效率越高",
                        "&f堆叠的靶丸不会提供更多发电量"),
                (arg0, arg1, arg2, arg3) -> false);
    }

    public @NotNull String getInventoryTitle() {
        return getItemName();
    }

    public @NotNull ItemStack getProgressBar() {
        try {
            return new ItemStack(SlimefunItems.URANIUM);
        } catch (Exception e) {
            return U.mat(Material.NETHERRACK);
        }
    }

    public int[] getInputSlots() {
        return new int[] {19, 20, 21, 22, 23, 24, 25};
    }

    public int[] getOutputSlots() {
        return new int[0];
    }

    public int getGeneratedOutput(Location l, SlimefunBlockData data) {
        int rt;
        int rtg_counter = 0;
        BlockMenu bm = StorageCacheUtils.getMenu(l);
        if (bm == null) {
            return 0;
        }
        byte b;
        int i;
        int[] arrayOfInt;
        for (i = (arrayOfInt = RadioisotopeThermoelectricGenerator.this.getInputSlots()).length, b = 0; b < i; ) {
            int slot = arrayOfInt[b];

            if (SlimefunUtils.isItemSimilar(bm.getItemInSlot(slot), Tech.PELLETS_OF_RTG_FUEL, true)) {
                rtg_counter++;
            }

            b++;
        }

        rt = (int) Math.pow(Variables.plug.getConfig().getInt("items.rtg.energy-generation"), rtg_counter);

        if (rtg_counter == 0) {
            rt = 0;
        }

        bm.replaceExistingItem(
                49,
                new CustomItemStack(
                        Material.ORANGE_STAINED_GLASS_PANE,
                        "&e当前发电效率: &f" + rt + " &7J/s",
                        "",
                        "&f放入放射性同位素靶丸来发电",
                        "&f靶丸越多发电效率越高",
                        "&f堆叠的靶丸不会提供更多发电量"));

        try {
            if (rtg_counter == 0) {
                PlayerHead.setSkin(
                        l.getBlock(),
                        PlayerSkin.fromBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQyZTE1ZDExZWUzZWIyMDQwNDVhZDNjY2IxOWNiODk3NmFmNThjY2E2NjY1OWZiOGQ5NzQzMTYwOGM3MTBlYSJ9fX0"),
                        false);
            } else if (rtg_counter > 0) {
                PlayerHead.setSkin(
                        l.getBlock(),
                        PlayerSkin.fromBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWMzYjEzMTdjNmUwNTQxNGU2NmZkYzYzNjMzZDFhYjM4YzlhZTM4NDU5ZTU4ZDg0MWE2N2M2YTEyYTJhZDMwMSJ9fX0="),
                        false);
            }

        } catch (Exception ex) {
            Main.debugException(ex);
        }

        return rt;
    }
}
