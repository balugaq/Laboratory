package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.items.misc.AlloyIngot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RadioactiveAlloyIngot extends AlloyIngot implements Radioactive {
    private final Radioactivity radioactivity;

    public RadioactiveAlloyIngot(
            ItemGroup itemGroup, Radioactivity radioactivity, SlimefunItemStack item, ItemStack[] recipe) {
        super(itemGroup, item, recipe);
        this.radioactivity = radioactivity;
    }

    @NotNull
    @Override
    public Radioactivity getRadioactivity() {
        return this.radioactivity;
    }
}
