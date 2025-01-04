package me.Freeze_Dolphin.lab.machines;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import java.util.List;
import me.Freeze_Dolphin.lab.ChargeableBlock;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Lantern extends SlimefunItem {
    private static final String light =
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NjMjE3YTliOWUzY2UzY2QwNDg0YzdlOGNlNDlkMWNmNzQxMjgxYmRkYTVhNGQ2Y2I4MjFmMzc4NzUyNzE4In19fQ==";
    private static final String dark =
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJjODFiNDM1ZGMyMmQyOWQ0Nzc4ZmZkMjJmZWI4NDZhNjhiNjQ4ZGQxYWY1ZGU4MThiNTE3ZjA1NzRkIn19fQ==";

    public Lantern(ItemGroup category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Block b = blockBreakEvent.getBlock();
                BlockStorage.clearBlockInfo(b);
            }
        });
    }

    public abstract int getEnergyConsumption();

    public abstract String getMachineIdentifier();

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, SlimefunBlockData data) {
                Lantern.this.tick(b);
            }

            @Override
            public void uniqueTick() {}

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    @SuppressWarnings("deprecation")
    protected void tick(Block b) {
        if (ChargeableBlock.getCharge(b) >= getEnergyConsumption()) {
            try {
                PlayerHead.setSkin(b, PlayerSkin.fromBase64(light), false);
            } catch (Exception ignored) {
            }
        }
        if (Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "tick"))
                == Variables.cfg.getInt("items.lantern.tick-value")) {
            BlockStorage.addBlockInfo(b, "tick", "0");
            if (ChargeableBlock.getCharge(b) >= getEnergyConsumption()) {
                try {
                    PlayerHead.setSkin(b, PlayerSkin.fromBase64(light), false);
                } catch (Exception ignored) {
                }
            } else {
                try {
                    PlayerHead.setSkin(b, PlayerSkin.fromBase64(dark), false);
                } catch (Exception ignored) {
                }
            }
        } else {
            BlockStorage.addBlockInfo(
                    b, "tick", Integer.valueOf(BlockStorage.getLocationInfo(b.getLocation(), "tick")) + 1 + "");
        }
        //		} else {
        //			try { CustomSkull.setSkull(b, dark); } catch (Exception e) {}
        //		}
    }
}
