package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Freeze_Dolphin.lab.AdvancedAContainer;
import me.Freeze_Dolphin.lab.BlockMenuUtil;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.ItemEnergy;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class AdvancedChargingBench extends AdvancedAContainer {
    public AdvancedChargingBench(
            ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_PICKAXE);
    }

    public int getEnergyConsumption() {
        return 40;
    }

    public void registerDefaultRecipes() {
    }

    protected void tick(Block b) {
        if (ChargeableBlock.getCharge(b) < getEnergyConsumption()) return;
        byte b1;
        int i;
        int[] arrayOfInt;
        for (i = (arrayOfInt = getInputSlots()).length, b1 = 0; b1 < i; ) {
            int slot = arrayOfInt[b1];
            ItemStack stack = StorageCacheUtils.getMenu(b.getLocation()).getItemInSlot(slot);
            if (ItemEnergy.getMaxEnergy(stack) > 0.0F) {
                if (ItemEnergy.getStoredEnergy(stack) < ItemEnergy.getMaxEnergy(stack)) {

                    ChargeableBlock.addCharge(b, -getEnergyConsumption());
                    float rest = ItemEnergy.addStoredEnergy(stack, (float) (getEnergyConsumption() * 0.75D));
                    if (rest > 0.0F) {
                        if (BlockMenuUtil.fits(b, new ItemStack[]{stack})) {
                            BlockMenuUtil.pushItem(b, new ItemStack[]{stack});
                            StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, null);
                        } else {

                            StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, stack);
                        }
                    } else {

                        StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, stack);
                    }

                } else if (BlockMenuUtil.fits(b, new ItemStack[]{stack})) {
                    BlockMenuUtil.pushItem(b, new ItemStack[]{stack});
                    StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, null);
                } else {

                    StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, stack);
                }
                return;
            }
            b1++;
        }
    }

    public int getSpeed() {
        return 1;
    }

    public String getMachineIdentifier() {
        return getId();
    }
}
