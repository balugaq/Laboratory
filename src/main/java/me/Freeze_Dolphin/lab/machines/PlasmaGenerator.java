package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.MachineHelper;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class PlasmaGenerator extends AContainer {
    public static final Map<Block, MachineRecipe> processing = new HashMap<>();
    public static final Map<Block, Integer> progress = new HashMap<>();
    private static final int[] border =
            new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] border_in = new int[] {9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    private static final int[] border_out = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public PlasmaGenerator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    byte b1;
                    int i;
                    int[] arrayOfInt;
                    for (i = (arrayOfInt = PlasmaGenerator.this.getInputSlots()).length, b1 = 0; b1 < i; ) {
                        int slot = arrayOfInt[b1];
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                        b1++;
                    }

                    for (i = (arrayOfInt = PlasmaGenerator.this.getOutputSlots()).length, b1 = 0; b1 < i; ) {
                        int slot = arrayOfInt[b1];
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                        b1++;
                    }
                }
                PlasmaGenerator.progress.remove(b);
                PlasmaGenerator.processing.remove(b);
            }
        });

        registerDefaultRecipes();
    }

    protected void constructMenu(BlockMenuPreset preset) {
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

        for (i = (arrayOfInt = border_out).length, b = 0; b < i; ) {
            int j = arrayOfInt[b];
            preset.addItem(
                    j, new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);

            b++;
        }

        preset.addItem(
                22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);

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
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE);
    }

    public void registerDefaultRecipes() {
        registerRecipe(
                Variables.cfg.getInt("items.plasma-generator.time"),
                new ItemStack[] {new ItemStack(Material.GLASS_BOTTLE)},
                new ItemStack[] {Tech.PLASMA_CELL});
    }

    public abstract int getEnergyConsumption();

    public abstract int getSpeed();

    public String getMachineIdentifier() {
        return getId();
    }

    public int[] getInputSlots() {
        return new int[] {19, 20};
    }

    public int[] getOutputSlots() {
        return new int[] {24, 25};
    }

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return (getProcessing(b) != null);
    }

    public void registerRecipe(MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks() / getSpeed());
        this.recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    private Inventory inject(Block b) {
        int size = StorageCacheUtils.getMenu(b.getLocation()).toInventory().getSize();
        Inventory inv = Bukkit.createInventory(null, size);
        for (int i = 0; i < size; i++)
            inv.setItem(i, new CustomItemStack(Material.COMMAND_BLOCK, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US"));
        byte b1;
        int j;
        int[] arrayOfInt;
        for (j = (arrayOfInt = getOutputSlots()).length, b1 = 0; b1 < j; ) {
            int slot = arrayOfInt[b1];
            inv.setItem(slot, StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot));
            b1++;
        }

        return inv;
    }

    protected boolean fits(Block b, ItemStack[] items) {
        return inject(b).addItem(items).isEmpty();
    }

    protected void pushItems(Block b, ItemStack[] items) {
        Inventory inv = inject(b);
        inv.addItem(items);
        byte b1;
        int i;
        int[] arrayOfInt;
        for (i = (arrayOfInt = getOutputSlots()).length, b1 = 0; b1 < i; ) {
            int slot = arrayOfInt[b1];
            StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, inv.getItem(slot));
            b1++;
        }
    }

    public void preRegister() {
        addItemHandler(new BlockTicker() {
            public void tick(Block b, SlimefunItem sf, SlimefunBlockData data) {
                PlasmaGenerator.this.tick(b);
            }

            public void uniqueTick() {}

            public boolean isSynchronized() {
                return false;
            }
        });

        super.preRegister();
    }

    protected void tick(Block b) {
        if (isProcessing(b)) {
            int timeleft = progress.get(b);
            if (timeleft > 0) {
                Location bl = b.getLocation();
                Location loc =
                        new Location(b.getWorld(), bl.getBlockX() + 0.5D, bl.getBlockY() + 0.5D, bl.getBlockZ() + 0.5D);
                try {
                    if (U.random(300, 0) >= 150) {
                        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, Color.pink);
                    }
                    if (U.random(300, 0) >= 50) {
                        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, Color.magenta);
                    }
                    if (U.random(300, 0) >= 280) {
                        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, Color.red);
                    }
                } catch (Exception ignored) {
                }

                ItemStack item = getProgressBar().clone();
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

                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(22, item);

                if (ChargeableBlock.isChargeable(b)) {
                    if (ChargeableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargeableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else {
                    progress.put(b, timeleft - 1);
                }

            } else {

                StorageCacheUtils.getMenu(b.getLocation())
                        .replaceExistingItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                pushItems(b, processing.get(b).getOutput().clone());

                progress.remove(b);
                processing.remove(b);
            }
        } else {

            MachineRecipe r = null;
            Map<Integer, Integer> found = new HashMap<>();

            for (MachineRecipe recipe : this.recipes) {
                byte b1;
                int i;
                ItemStack[] arrayOfItemStack;
                for (i = (arrayOfItemStack = recipe.getInput()).length, b1 = 0; b1 < i; ) {
                    ItemStack input = arrayOfItemStack[b1];
                    byte b2;
                    int j;
                    int[] arrayOfInt;
                    for (j = (arrayOfInt = getInputSlots()).length, b2 = 0; b2 < j; ) {
                        int slot = arrayOfInt[b2];
                        if (SlimefunUtils.isItemSimilar(
                                StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot), input, true)) {
                            found.put(slot, input.getAmount());
                            break;
                        }
                        b2++;
                    }

                    b1++;
                }

                if (found.size() == (recipe.getInput()).length) {
                    r = recipe;
                    break;
                }
                found.clear();
            }

            if (r != null) {
                if (!fits(b, r.getOutput())) return;
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    StorageCacheUtils.getMenu(b.getLocation()).consumeItem(entry.getKey(), entry.getValue());
                }
                processing.put(b, r);
                progress.put(b, r.getTicks());
            }
        }
    }
}
