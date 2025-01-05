package me.Freeze_Dolphin.lab;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class BlockMenuUtil {
    @Nullable
    public static ItemStack pushItem(@Nonnull BlockMenu blockMenu, @Nonnull ItemStack item, int... slots) {
        if (item.getType() == Material.AIR) {
            throw new IllegalArgumentException("Cannot push null or AIR");
        }

        int leftAmount = item.getAmount();

        for (int slot : slots) {
            if (leftAmount <= 0) {
                break;
            }

            ItemStack existing = blockMenu.getItemInSlot(slot);

            if (existing == null || existing.getType() == Material.AIR) {
                int received = Math.min(leftAmount, item.getMaxStackSize());
                blockMenu.replaceExistingItem(slot, StackUtils.getAsQuantity(item, received));
                leftAmount -= received;
                item.setAmount(Math.max(0, leftAmount));
            } else {
                int existingAmount = existing.getAmount();
                if (existingAmount >= item.getMaxStackSize()) {
                    continue;
                }

                if (!StackUtils.itemsMatch(item, existing)) {
                    continue;
                }

                int received = Math.max(0, Math.min(item.getMaxStackSize() - existingAmount, leftAmount));
                leftAmount -= received;
                existing.setAmount(existingAmount + received);
                item.setAmount(leftAmount);
            }
        }

        if (leftAmount > 0) {
            return new CustomItemStack(item, leftAmount);
        } else {
            return null;
        }
    }

    @Nonnull
    public static Map<ItemStack, Integer> pushItem(
            @Nonnull BlockMenu blockMenu, @Nonnull ItemStack[] items, int... slots) {
        if (items.length == 0) {
            throw new IllegalArgumentException("Cannot push null or empty array");
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item);
            }
        }

        return pushItem(blockMenu, listItems, slots);
    }

    @Nonnull
    public static Map<ItemStack, Integer> pushItem(
            @Nonnull BlockMenu blockMenu, @Nonnull List<ItemStack> items, int... slots) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Cannot push null or empty list");
        }

        Map<ItemStack, Integer> itemMap = new HashMap<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                ItemStack leftOver = pushItem(blockMenu, item, slots);
                if (leftOver != null) {
                    itemMap.put(leftOver, itemMap.getOrDefault(leftOver, 0) + leftOver.getAmount());
                }
            }
        }

        return itemMap;
    }

    public static boolean fits(@Nonnull BlockMenu blockMenu, @Nonnull ItemStack item, int... slots) {
        if (item.getType() == Material.AIR) {
            return true;
        }

        int incoming = item.getAmount();
        for (int slot : slots) {
            ItemStack stack = blockMenu.getItemInSlot(slot);

            if (stack == null || stack.getType() == Material.AIR) {
                incoming -= item.getMaxStackSize();
            } else if (stack.getMaxStackSize() > stack.getAmount() && StackUtils.itemsMatch(item, stack)) {
                incoming -= stack.getMaxStackSize() - stack.getAmount();
            }

            if (incoming <= 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean fits(@Nonnull BlockMenu blockMenu, @Nonnull ItemStack[] items, int... slots) {
        if (items.length == 0) {
            return false;
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item.clone());
            }
        }

        return fits(blockMenu, listItems, slots);
    }

    public static boolean fits(@Nonnull BlockMenu blockMenu, @Nonnull List<ItemStack> items, int... slots) {
        if (items.isEmpty()) {
            return false;
        }

        List<ItemStack> cloneMenu = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            cloneMenu.add(null);
        }

        for (int slot : slots) {
            ItemStack stack = blockMenu.getItemInSlot(slot);
            if (stack != null && stack.getType() != Material.AIR) {
                cloneMenu.set(slot, stack.clone());
            } else {
                cloneMenu.set(slot, null);
            }
        }

        for (ItemStack rawItem : items) {
            ItemStack item = rawItem.clone();
            int leftAmount = item.getAmount();
            for (int slot : slots) {
                if (leftAmount <= 0) {
                    break;
                }

                ItemStack existing = cloneMenu.get(slot);

                if (existing == null || existing.getType() == Material.AIR) {
                    int received = Math.min(leftAmount, item.getMaxStackSize());
                    cloneMenu.set(slot, StackUtils.getAsQuantity(item, leftAmount));
                    leftAmount -= received;
                    item.setAmount(Math.max(0, leftAmount));
                } else {
                    int existingAmount = existing.getAmount();
                    if (existingAmount >= item.getMaxStackSize()) {
                        continue;
                    }

                    if (!StackUtils.itemsMatch(item, existing)) {
                        continue;
                    }

                    int received = Math.max(0, Math.min(item.getMaxStackSize() - existingAmount, leftAmount));
                    leftAmount -= received;
                    existing.setAmount(existingAmount + received);
                    item.setAmount(leftAmount);
                }
            }

            if (leftAmount > 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean fits(Block block, ItemStack[] items, int... slots) {
        return fits(block.getLocation(), items, slots);
    }

    public static boolean fits(Location location, ItemStack[] items, int... slots) {
        BlockMenu menu = StorageCacheUtils.getMenu(location);
        if (menu == null) {
            return false;
        }
        return BlockMenuUtil.fits(menu, items, slots);
    }

    public static boolean fits(Block block, ItemStack item, int... slots) {
        return fits(block.getLocation(), item, slots);
    }

    public static boolean fits(Location location, ItemStack item, int... slots) {
        BlockMenu menu = StorageCacheUtils.getMenu(location);
        if (menu == null) {
            return false;
        }
        return BlockMenuUtil.fits(menu, item, slots);
    }

    public static ItemStack pushItem(Block block, ItemStack item, int... slots) {
        return pushItem(block.getLocation(), item, slots);
    }

    public static ItemStack pushItem(Location location, ItemStack item, int... slots) {
        BlockMenu menu = StorageCacheUtils.getMenu(location);
        if (menu == null) {
            return null;
        }
        return BlockMenuUtil.pushItem(menu, item, slots);
    }

    public static Map<ItemStack, Integer> pushItem(Block block, ItemStack[] items, int... slots) {
        return pushItem(block.getLocation(), items, slots);
    }

    public static Map<ItemStack, Integer> pushItem(Location location, ItemStack[] items, int... slots) {
        BlockMenu menu = StorageCacheUtils.getMenu(location);
        if (menu == null) {
            return null;
        }
        return BlockMenuUtil.pushItem(menu, items, slots);
    }

    public static Map<ItemStack, Integer> pushItem(Block block, List<ItemStack> items, int... slots) {
        return pushItem(block.getLocation(), items, slots);
    }

    public static Map<ItemStack, Integer> pushItem(Location location, List<ItemStack> items, int... slots) {
        BlockMenu menu = StorageCacheUtils.getMenu(location);
        if (menu == null) {
            return null;
        }
        return BlockMenuUtil.pushItem(menu, items, slots);
    }
}
