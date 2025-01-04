package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.Freeze_Dolphin.lab.MachineHelper;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class BlastFurnace extends SlimefunItem {
    public static final Map<Block, MachineRecipe> processing = new HashMap<>();
    public static final Map<Block, Integer> progress = new HashMap<>();
    public static final Map<Block, Boolean> heat_processing = new HashMap<>();
    public static final Map<Block, Integer> heat_progress = new HashMap<>();
    private static final int[] border = {4, 22};
    private static final int[] border_in = {0, 1, 2, 3, 9, 12, 18, 19, 20, 21};
    private static final int[] border_out = {5, 6, 7, 8, 14, 17, 23, 24, 25, 26};
    private static final int[] border_heat = {
        27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 38, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53
    };
    private static final int[] heat = {39, 40, 41, 42, 43};
    private static final int process = 13, heat_source = 37, heat_process = 38;
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public BlastFurnace(ItemGroup category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);
        new BlockMenuPreset(id, getInventoryTitle()) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(BlockMenu menu, Block b) {}

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || canUse(p, true);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) return getInputSlots();
                else return getOutputSlots();
            }
        };

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    for (int slot : getInputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                    }
                    for (int slot : getOutputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                    }
                    if (inv.getItemInSlot(37) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(37));
                        inv.replaceExistingItem(37, null);
                    }
                }
                progress.remove(b);
                processing.remove(b);
            }
        });
        this.registerDefaultRecipes();
    }

    @SuppressWarnings("deprecation")
    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : border_heat) {
            preset.addItem(
                    i,
                    new CustomItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " "),
                    (arg0, arg1, arg2, arg3) -> false);
        }
        for (int i : border) {
            preset.addItem(
                    i, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
        }
        for (int i : border_in) {
            preset.addItem(
                    i, new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
        }
        for (int i : border_out) {
            preset.addItem(
                    i, new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
        }
        preset.addItem(
                process,
                new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "),
                (arg0, arg1, arg2, arg3) -> false);
        preset.addItem(
                heat_process,
                new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, " "),
                (arg0, arg1, arg2, arg3) -> false);
        for (int i : heat) {
            preset.addItem(
                    i,
                    new CustomItemStack(Material.WHITE_STAINED_GLASS_PANE, "&f热量填充: &f&l0%"),
                    (arg0, arg1, arg2, arg3) -> false);
        }
        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(
                        InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    if (cursor == null) return true;
                    cursor.getType();
                    return cursor.getType() == Material.AIR;
                }
            });
        }
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public ItemStack getProgressBar() {
        return U.mat(Material.FIRE);
    }

    public ItemStack getHeatProgressBar() {
        return U.mat(Material.FLINT_AND_STEEL);
    }

    public abstract void registerDefaultRecipes();

    public int getEnergyConsumption() {
        return 0;
    }

    public abstract int getSpeed();

    public String getMachineIdentifier() {
        return getId();
    }

    public int[] getInputSlots() {
        return new int[] {10, 11};
    }

    public int[] getOutputSlots() {
        return new int[] {15, 16};
    }

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public Boolean getHeatProcessing(Block b) {
        return heat_processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return getProcessing(b) != null;
    }

    public boolean isHeatProcessing(Block b) {
        if (getHeatProcessing(b) != null) {
            return getHeatProcessing(b);
        } else {
            return false;
        }
    }

    public void registerRecipe(MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks() / getSpeed());
        this.recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        this.registerRecipe(new MachineRecipe(seconds, input, output));
    }

    private Inventory inject(Block b) {
        BlockMenu menu = StorageCacheUtils.getMenu(b.getLocation());
        int size = menu.toInventory().getSize();
        Inventory inv = Bukkit.createInventory(null, size);
        for (int i = 0; i < size; i++) {
            inv.setItem(i, new CustomItemStack(Material.COMMAND_BLOCK, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US"));
        }
        for (int slot : getOutputSlots()) {
            inv.setItem(slot, menu.getItemInSlot(slot));
        }
        return inv;
    }

    protected boolean fits(Block b, ItemStack[] items) {
        return inject(b).addItem(items).isEmpty();
    }

    protected void pushItems(Block b, ItemStack[] items) {
        BlockMenu menu = StorageCacheUtils.getMenu(b.getLocation());
        Inventory inv = inject(b);
        inv.addItem(items);
        for (int slot : getOutputSlots()) {
            menu.replaceExistingItem(slot, inv.getItem(slot));
        }
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, SlimefunBlockData data) {
                BlastFurnace.this.tick(b);
            }

            @Override
            public void uniqueTick() {}

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
        super.preRegister();
    }

    @SuppressWarnings("deprecation")
    protected void tick(Block b) {
        BlockMenu bm = StorageCacheUtils.getMenu(b.getLocation());
        if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "heat")) <= 1) {
            bm.replaceExistingItem(heat[0], new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&f热量填充: &a&l25%"));
        }
        if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "heat")) <= 2) {
            bm.replaceExistingItem(heat[1], new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&f热量填充: &e&l50%"));
        }
        if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "heat")) <= 3) {
            bm.replaceExistingItem(heat[2], new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&f热量填充: &c&l75%"));
        }
        if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "heat")) <= 4) {
            bm.replaceExistingItem(heat[3], new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&f热量填充: &4&l100%"));
        }
        if (isProcessing(b)) {
            int timeleft = progress.get(b);
            if (timeleft > 0) {
                ItemStack item = getHeatProgressBar().clone();
                item.setDurability(MachineHelper.getDurability(
                        item, timeleft, processing.get(b).getTicks()));
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(" ");
                List<String> lore = new ArrayList<>();
                lore.add(MachineHelper.getProgress(timeleft, processing.get(b).getTicks()));
                lore.add("");
                lore.add(MachineHelper.getTimeLeft(timeleft / 2));
                im.setLore(lore);
                item.setItemMeta(im);
                bm.replaceExistingItem(process, item);
                progress.put(b, timeleft - 1);
            } else {
                bm
                        .replaceExistingItem(process, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                pushItems(b, processing.get(b).getOutput().clone());
                progress.remove(b);
                processing.remove(b);
            }
        } else {
            BlockMenu hbm = StorageCacheUtils.getMenu(b.getLocation());
            for (ItemStack fuel : Variables.BlastFurnaceFuel) {
                if (SlimefunUtils.isItemSimilar(hbm.getItemInSlot(heat_source), fuel, true)) {
                    int fuel_ticks = Variables.cfg.getInt("items.blast-furnace.fuel-ticks");
                    // fuel burning [start]
                    if (isHeatProcessing(b)) {
                        int timeleft = heat_progress.get(b);
                        if (timeleft > 0) {
                            ItemStack item = getProgressBar().clone();
                            item.setDurability(MachineHelper.getDurability(item, timeleft, fuel_ticks));
                            ItemMeta im = item.getItemMeta();
                            im.setDisplayName(" ");
                            List<String> lore = new ArrayList<>();
                            lore.add(MachineHelper.getProgress(timeleft, fuel_ticks));
                            lore.add("");
                            lore.add(MachineHelper.getTimeLeft(timeleft / 2));
                            im.setLore(lore);
                            item.setItemMeta(im);
                            hbm.replaceExistingItem(heat_process, item);
                            heat_progress.put(b, timeleft - 1);
                        } else {
                            hbm
                                    .replaceExistingItem(
                                            heat_process, new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, " "));
                            heat_progress.remove(b);
                            heat_processing.remove(b);

                            BlockStorage.addBlockInfo(
                                    b,
                                    "heat",
                                    Integer.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "heat")) + 1 + "");
                        }
                    } else {
                        if (hbm.getItemInSlot(heat_source).getAmount()
                                >= Variables.cfg.getInt("items.blast-furnace.fuel-number")) {
                            hbm
                                    .consumeItem(heat_source, Variables.cfg.getInt("items.blast-furnace.fuel-number"));
                            heat_processing.put(b, true);
                            heat_progress.put(b, fuel_ticks);
                        }
                    }
                    // fuel burning [end]
                }
            }
            if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "heat")) == 4) {
                MachineRecipe r = null;
                Map<Integer, Integer> found = new HashMap<>();
                for (MachineRecipe recipe : recipes) {
                    for (ItemStack input : recipe.getInput()) {
                        for (int slot : getInputSlots()) {
                            if (SlimefunUtils.isItemSimilar(
                                    hbm.getItemInSlot(slot), input, true)) {
                                found.put(slot, input.getAmount());
                                break;
                            }
                        }
                    }
                    if (found.size() == recipe.getInput().length) {
                        r = recipe;
                        break;
                    } else found.clear();
                }
                if (r != null) {
                    if (!fits(b, r.getOutput())) return;
                    for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                        hbm.consumeItem(entry.getKey(), entry.getValue());
                    }
                    processing.put(b, r);
                    progress.put(b, r.getTicks());
                }
            }
        }
    }
}
