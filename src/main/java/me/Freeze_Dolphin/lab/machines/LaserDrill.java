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
import me.Freeze_Dolphin.lab.ADrill;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.MachineHelper;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.Freeze_Dolphin.lab.api.Str2Item;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Warning;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
@Warning(reason = "Not used")
public abstract class LaserDrill extends ADrill {
    public static final Map<Block, MachineRecipe> processing = new HashMap<>();
    public static final Map<Block, Integer> progress = new HashMap<>();
    private static final int[] border = {27, 28, 29, 36, 38, 45, 46, 47};
    private static final int[] border_drills = {30, 31, 32, 33, 34, 35, 39, 44, 48, 49, 50, 51, 52, 53};
    private static final int[] border_out = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static final int process = 37;
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public LaserDrill(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        new BlockMenuPreset(getId(), getInventoryTitle()) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(BlockMenu menu, Block b) {
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || canUse(p, true);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) return getDrillSlots();
                else return getOutputSlots();
            }
        };

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
                if (inv != null) {
                    for (int slot : getDrillSlots()) {
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
                }
                progress.remove(b);
                processing.remove(b);
            }
        });
        this.registerDefaultRecipes();
    }

    @SuppressWarnings("deprecation")
    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(
                    i, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
        }
        for (int i : border_drills) {
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
        return U.getCfgMessage("messages.machines.laser-drill.title");
    }

    public abstract int getEnergyConsumption();

    public abstract int getSpeed();

    public String getMachineIdentifier() {
        return getId();
    }

    public int[] getOutputSlots() {
        return new int[]{10, 11, 12, 13, 14, 15, 16};
    }

    public int[] getDrillSlots() {
        return new int[]{40, 41, 42, 43};
    }

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return getProcessing(b) != null;
    }

    public void registerRecipe(MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks() / getSpeed());
        this.recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        this.registerRecipe(new MachineRecipe(seconds, input, output));
    }

    private Inventory inject(Block b) {
        int size = StorageCacheUtils.getMenu(b.getLocation()).toInventory().getSize();
        Inventory inv = Bukkit.createInventory(null, size);
        for (int i = 0; i < size; i++) {
            inv.setItem(i, new CustomItemStack(Material.COMMAND_BLOCK, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US"));
        }
        for (int slot : getOutputSlots()) {
            inv.setItem(slot, StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot));
        }
        return inv;
    }

    protected boolean fits(Block b, ItemStack[] items) {
        return inject(b).addItem(items).isEmpty();
    }

    protected void pushItems(Block b, ItemStack[] items) {
        Inventory inv = inject(b);
        inv.addItem(items);
        for (int slot : getOutputSlots()) {
            StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, inv.getItem(slot));
        }
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, SlimefunBlockData data) {
                LaserDrill.this.tick(b);
            }

            @Override
            public void uniqueTick() {
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
        super.preRegister();
    }

    @SuppressWarnings("deprecation")
    protected void tick(Block b) {
        if (isProcessing(b)) {
            int timeleft = progress.get(b);
            if (timeleft > 0) {
                List<Double> d = new ArrayList<>();
                for (double keys : Laboratory.lo.keySet()) {
                    if (U.random(100, 0) <= keys) {
                        d.add(keys);
                    }
                }
                ItemStack processBar = new ItemStack(Material.IRON_PICKAXE);
                if (d.isEmpty()) {
                    processBar = Str2Item.str2item(Variables.cfg.getString("items.laser-drill.failure"));
                } else {
                    processBar = Laboratory.lo.get(d.get(U.random(d.size(), 0)));
                }

                ItemStack item = processBar.clone();
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
                StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(process, item);
                if (ChargeableBlock.isChargeable(b)) {
                    if (ChargeableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargeableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else progress.put(b, timeleft - 1);
            } else {
                StorageCacheUtils.getMenu(b.getLocation())
                        .replaceExistingItem(process, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                pushItems(b, processing.get(b).getOutput().clone());
                progress.remove(b);
                processing.remove(b);
            }
        } else {
            MachineRecipe r = null;
            Map<Integer, Integer> found = new HashMap<>();
            for (MachineRecipe recipe : recipes) {
                for (ItemStack input : recipe.getInput()) {
                    for (int slot : getDrillSlots()) {
                        if (SlimefunUtils.isItemSimilar(
                                StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot), input, true)) {
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
                    StorageCacheUtils.getMenu(b.getLocation()).consumeItem(entry.getKey(), entry.getValue());
                }
                processing.put(b, r);
                progress.put(b, r.getTicks());
            }
        }
    }
}
