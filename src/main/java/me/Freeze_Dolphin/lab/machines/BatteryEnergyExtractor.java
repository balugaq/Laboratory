package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.BlockMenuUtil;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BatteryEnergyExtractor extends AContainer {
    public BatteryEnergyExtractor(
            ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        new BlockMenuPreset(getId(), getInventoryTitle()) {
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
    }

    @Override
    public String getInventoryTitle() {
        return getItemName();
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_PICKAXE);
    }

    @Override
    public void registerDefaultRecipes() {}

    protected void tick(Block b) {
        // getEnergy - ChargableBlock.getCharge(b)
        for (int slot : getInputSlots()) {
            ItemStack stack = StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot);
            if (ItemEnergy.getMaxEnergy(stack) > 0) {

                boolean isBattery = false;

                for (ItemStack battery : Variables.rechargableBattery) {
                    if (SlimefunUtils.isItemSimilar(stack, battery, false)) {
                        isBattery = true;
                    }
                }

                if (!isBattery) return;

                if (ItemEnergy.getStoredEnergy(stack) >= 10) {
                    ChargeableBlock.addCharge(b, 10);
                    ItemEnergy.chargeItem(stack, -10);
                    StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, stack);
                } else if (BlockMenuUtil.fits(b, new ItemStack[] {stack})) {
                    BlockMenuUtil.pushItem(b, new ItemStack[] {stack});
                    StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, null);
                } else {
                    StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, stack);
                }
                return;
            }
        }
    }

    @Override
    public String getMachineIdentifier() {
        return getId();
    }

    @Override
    public int getEnergyConsumption() {
        return 0;
    }

    @Override
    public int getSpeed() {
        return 1;
    }
}
