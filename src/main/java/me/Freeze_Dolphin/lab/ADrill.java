package me.Freeze_Dolphin.lab;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class ADrill extends AContainer {
    public static final Map<Block, MachineRecipe> processing = new HashMap<>();
    public static final Map<Block, Integer> progress = new HashMap<>();
    private static final int[] border = new int[] {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44, 9, 10, 11, 12, 18, 21, 27, 28, 29, 30,
        19, 20
    };
    private static final int[] border_out = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};

    public ADrill(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getId(), getInventoryTitle()) {
            public void init() {
                constructMenu(this);
            }

            private void constructMenu(BlockMenuPreset preset) {
                for (int i : ADrill.border) {
                    preset.addItem(
                            i,
                            new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "),
                            (arg0, arg1, arg2, arg3) -> false);
                }

                for (int i : ADrill.border_out) {
                    preset.addItem(
                            i,
                            new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, " "),
                            (arg0, arg1, arg2, arg3) -> false);
                }

                preset.addItem(
                        22,
                        new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "),
                        (arg0, arg1, arg2, arg3) -> false);

                for (int i : ADrill.this.getOutputSlots()) {
                    preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {
                        public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                            return false;
                        }

                        public boolean onClick(
                                InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                            if (cursor == null) return true;
                            cursor.getType();
                            return cursor.getType() == Material.AIR;
                        }
                    });
                }
            }

            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {}

            public boolean canOpen(@NotNull Block b, @NotNull Player p) {
                if (!p.hasPermission("slimefun.inventory.bypass") && !canUse(p, true)) {
                    Main.debug("Player " + p.getName() + " does not have permission to use " + ADrill.this.getId() + " at " + b.getLocation());
                    return false;
                }

                Main.debug("Player " + p.getName() + " can use " + ADrill.this.getId() + " at " + b.getLocation());
                if (hasGeneratedResources(ADrill.this.getOreGenResource(), b)) {
                    return true;
                } else {
                    p.sendMessage("§c你还没有扫描区块资源!");
                    return false;
                }
            }

            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) return ADrill.this.getInputSlots();
                return ADrill.this.getOutputSlots();
            }
        };
    }

    public int[] getInputSlots() {
        return new int[0];
    }

    public void registerDefaultRecipes() {}

    protected void tick(Block b) {
        if (isProcessing(b)) {
            int timeleft = progress.get(b);
            if (timeleft > 0) {
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

                if (ChargeableBlock.getCharge(b) < getEnergyConsumption()) return;
                ChargeableBlock.addCharge(b, -getEnergyConsumption());

                progress.put(b, timeleft - 1);
            } else {

                StorageCacheUtils.getMenu(b.getLocation())
                        .replaceExistingItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                BlockMenuUtil.pushItem(b, processing.get(b).getOutput());

                progress.remove(b);
                processing.remove(b);
            }

        } else if (hasGeneratedResources(getOreGenResource(), b)) {
            int current = getResource(getOreGenResource(), b).orElse(0);
            if (current <= 0) {
                return;
            }
            Main.debug("Generating resources for " + this.getId() + " at " + b.getLocation());
            MachineRecipe r = new MachineRecipe(getProcessingTime() / getSpeed(), new ItemStack[0], getOutputItems());
            if (!BlockMenuUtil.fits(b, r.getOutput(), getOutputSlots())) return;
            Main.debug("Generated resources for " + this.getId() + " at " + b.getLocation());
            processing.put(b, r);
            progress.put(b, r.getTicks());
            setResource(getOreGenResource(), b, current - 1);
        }
    }

    public abstract GEOResource getOreGenResource();

    public abstract ItemStack[] getOutputItems();

    public abstract int getProcessingTime();

    public abstract int getSpeed();

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return getProcessing(b) != null;
    }

    public OptionalInt getResource(GEOResource resource, Block b) {
        return Slimefun.getGPSNetwork().getResourceManager().getSupplies(resource, b.getChunk().getWorld(), b.getChunk().getX(), b.getChunk().getZ());
    }

    public boolean hasGeneratedResources(GEOResource resource, Block b) {
        return getResource(resource, b).isPresent();
    }

    public void setResource(GEOResource resource, Block b, int amount) {
        Slimefun.getGPSNetwork().getResourceManager().setSupplies(resource, b.getChunk().getWorld(), b.getChunk().getX(), b.getChunk().getZ(), amount);
    }
}
