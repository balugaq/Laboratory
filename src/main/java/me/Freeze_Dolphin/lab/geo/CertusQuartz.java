package me.Freeze_Dolphin.lab.geo;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import me.Freeze_Dolphin.lab.Main;
import me.Freeze_Dolphin.lab.Tech;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CertusQuartz implements GEOResource {

    public ItemStack getIcon() {
        return Tech.CERTUS_QUARTZ.clone();
    }

    public String getMeasurementUnit() {
        return "块";
    }

    @Override
    public int getDefaultSupply(@NotNull World.Environment environment, @NotNull Biome biome) {
        if (biome == Biome.NETHER_WASTES) {
            return 1;
        }

        return 0;
    }

    @Override
    public int getMaxDeviation() {
        return 20;
    }

    public String getName() {
        return "赛特斯石英";
    }

    @NotNull @Override
    public ItemStack getItem() {
        return Tech.CERTUS_QUARTZ.clone();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Main.instance, "certus_quartz");
    }
}
