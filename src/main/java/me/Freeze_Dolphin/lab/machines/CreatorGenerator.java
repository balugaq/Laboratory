package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Freeze_Dolphin.lab.SkullUtil;
import me.Freeze_Dolphin.lab.U;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreatorGenerator extends AGenerator {
    private static final int[] border = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static final int[] remove = {10, 11, 12};
    private static final int[] add = {14, 15, 16};
    private static final int remain = 13;
    private static final String key = "energy-generating";
    private static Block block;

    public CreatorGenerator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        new BlockMenuPreset(getId(), getInventoryTitle()) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {
                block = b;
            }

            @Override
            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || canUse(p, true);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return null;
            }
        };

        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent blockPlaceEvent) {
                Block b = blockPlaceEvent.getBlockPlaced();
                BlockStorage.addBlockInfo(b.getLocation(), key, "16");
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(
                    i, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
        }

        preset.addItem(
                remain,
                new CustomItemStack(Material.YELLOW_STAINED_GLASS_PANE, "&e当前发电效率: &f16 &7J/s"),
                (arg0, arg1, arg2, arg3) -> false);
        preset.addItem(
                add[0], new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a+1 W"), (arg0, arg1, arg2, arg3) -> {
                    BlockStorage.addBlockInfo(
                            block,
                            key,
                            String.valueOf(
                                    (Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), key)) + 1)));
                    return false;
                });
        preset.addItem(
                add[1], new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a+10 W"), (arg0, arg1, arg2, arg3) -> {
                    BlockStorage.addBlockInfo(
                            block,
                            key,
                            String.valueOf(
                                    (Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), key)) + 10)));
                    return false;
                });
        preset.addItem(
                add[2],
                new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a+100 W"),
                (arg0, arg1, arg2, arg3) -> {
                    BlockStorage.addBlockInfo(
                            block,
                            key,
                            String.valueOf(
                                    (Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), key)) + 100)));
                    return false;
                });
        preset.addItem(
                remove[2], new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&c-1 W"), (arg0, arg1, arg2, arg3) -> {
                    BlockStorage.addBlockInfo(
                            block,
                            key,
                            String.valueOf(
                                    (Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), key)) - 1)));
                    return false;
                });
        preset.addItem(
                remove[1],
                new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&c-10 W"),
                (arg0, arg1, arg2, arg3) -> {
                    BlockStorage.addBlockInfo(
                            block,
                            key,
                            String.valueOf(
                                    (Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), key)) - 10)));
                    return false;
                });
        preset.addItem(
                remove[0],
                new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&c-100 W"),
                (arg0, arg1, arg2, arg3) -> {
                    BlockStorage.addBlockInfo(
                            block,
                            key,
                            String.valueOf(
                                    (Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), key)) - 100)));
                    return false;
                });
    }

    public @NotNull String getInventoryTitle() {
        return getItemName();
    }

    public @NotNull ItemStack getProgressBar() {
        try {
            return SkullUtil.getByBase64(
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5ZjM1NmY1ZmU3ZDFiYzkyY2RkZmFlYmEzZWU3NzNhYzlkZjFjYzRkMWMyZjhmZTVmNDcwMTMwMzJjNTUxZCJ9fX0=");
        } catch (Exception e) {
            return U.mat(Material.NETHER_STAR);
        }
    }

    @Override
    protected void registerDefaultFuelTypes() {}

    public int getGeneratedOutput(Location l, SlimefunBlockData data) {
        if (l.getBlock().getBlockPower() < 0) {
            return 0;
        }
        int energy;
        energy = Integer.parseInt(BlockStorage.getLocationInfo(l, key));
        StorageCacheUtils.getMenu(l)
                .replaceExistingItem(
                        remain,
                        new CustomItemStack(Material.YELLOW_STAINED_GLASS_PANE, "&e当前发电效率: &f" + energy + " &7J/s"));
        return energy;
    }

    @Override
    public int getCapacity() {
        return 1073741824;
    }
}
