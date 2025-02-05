package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.AdvancedAContainer;
import me.Freeze_Dolphin.lab.BlockMenuUtil;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.MachineHelper;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ChemicalReactor extends AdvancedAContainer {
    public static final Map<Block, MachineRecipe> processing = new HashMap<>();
    public static final Map<Block, Integer> progress = new HashMap<>();
    private static final int[] border = {4, 5, 6, 7, 8, 13, 31, 40, 41, 42, 43, 44};
    private static final int[] border_in = {0, 1, 2, 3, 9, 12, 18, 21, 27, 30, 36, 37, 38, 39};
    private static final int[] border_out = {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};

    public ChemicalReactor(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow.equals(ItemTransportFlow.WITHDRAW)) return getOutputSlots();
                List<Integer> slots = new ArrayList<>();
                for (int slot : getInputSlots()) {
                    if (SlimefunUtils.isItemSimilar(menu.getItemInSlot(slot), item, true)) {
                        slots.add(slot);
                    }
                }
                if (slots.isEmpty()) {
                    return getInputSlots();
                } else {
                    slots.sort((a, b) -> a < b ? -1 : 1);
                    return ArrayUtils.toPrimitive(slots.toArray(new Integer[0]));
                }
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
                }
                progress.remove(b.getLocation());
                processing.remove(b.getLocation());
            }
        });
        this.registerDefaultRecipes();
    }

    public void registerDefaultRecipes() {
        int time = Variables.cfg.getInt("items.chemical-reator.time");
        registerRecipe(
                6 * time,
                new ItemStack[]{
                        SlimefunItems.URANIUM,
                        SlimefunItems.SMALL_URANIUM,
                        new SlimefunItemStack(SlimefunItems.SULFATE, 3)
                },
                new ItemStack[]{Tech.RADIUM});
    }

    @SuppressWarnings("deprecation")
    protected void constructMenu(BlockMenuPreset preset) {
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
                22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), (arg0, arg1, arg2, arg3) -> false);
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

    @Override
    public String getInventoryTitle() {
        return getItemName();
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SPLASH_POTION);
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{10, 11, 19, 20, 28, 29};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{24, 25};
    }

    @Override
    public String getMachineIdentifier() {
        return getId();
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, SlimefunBlockData data) {
                ChemicalReactor.this.tick(b);
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

    protected void tick(Block b) {
        BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
        CraftingOperation currentOperation = this.getMachineProcessor().getOperation(b);
        if (currentOperation != null) {
            if (this.takeCharge(b.getLocation())) {
                try {
                    b.getLocation()
                            .getWorld()
                            .spawnParticle(Particle.SMOKE_NORMAL, b.getLocation(), 0, 1, 0.5, 0.5, U.random(15, 3));
                } catch (Exception ex) {
                    Laboratory.debugException(ex);
                }
                if (!currentOperation.isFinished()) {
                    this.getMachineProcessor().updateProgressBar(inv, 22, currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", new String[0]));
                    ItemStack[] var4 = currentOperation.getResults();
                    int var5 = var4.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        ItemStack output = var4[var6];
                        inv.pushItem(output.clone(), this.getOutputSlots());
                    }

                    this.getMachineProcessor().endOperation(b);
                }
            }
        } else {
            MachineRecipe next = this.findNextRecipe(inv);
            if (next != null) {
                currentOperation = new CraftingOperation(next);
                this.getMachineProcessor().startOperation(b, currentOperation);
                this.getMachineProcessor().updateProgressBar(inv, 22, currentOperation);
            }
        }
    }
}
