package me.Freeze_Dolphin.lab.geo;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;

import java.util.Random;

import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.Tech;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Iridium implements GEOResource {
    public static Iridium instance = new Iridium();
    private static final Random random = new Random();

    @Override
    public int getDefaultSupply(World.Environment environment, Biome bio) {
        if (bio == Biome.MUSHROOM_FIELDS) {
            return random.nextInt(2);
        }
        return 0;
    }

    @Override
    public int getMaxDeviation() {
        return 0;
    }

    @Override
    public String getName() {
        return "铱";
    }

    @NotNull @Override
    public ItemStack getItem() {
        return Tech.IRIDIUM.clone();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return false;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey(Laboratory.instance, "iridium");
    }
}
