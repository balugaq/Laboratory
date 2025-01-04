package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class MachineHelper {
    public static short getDurability(@NotNull ItemStack itemStack, int timeLeft, int totalTime) {
        int durability = itemStack.getType().getMaxDurability();
        if (durability == 0) {
            return 0;
        }
        return (short) (durability * (1 - (double) timeLeft / totalTime));
    }

    public static @NotNull String getTimeLeft(int seconds) {
        String timeleft = "";

        int minutes = (int) (seconds / 60L);
        if (minutes > 0) {
            timeleft = timeleft + minutes + "m ";
        }

        seconds -= minutes * 60;
        timeleft = timeleft + seconds + "s";
        return ChatColor.translateAlternateColorCodes('&', "&7" + timeleft + " left");
    }

    public static @NotNull String getProgress(int time, int total) {
        StringBuilder progress = new StringBuilder();
        float percentage = Math.round(((((total - time) * 100.0F) / total) * 100.0F) / 100.0F);

        if (percentage < 16.0F) progress.append("&4");
        else if (percentage < 32.0F) progress.append("&c");
        else if (percentage < 48.0F) progress.append("&6");
        else if (percentage < 64.0F) progress.append("&e");
        else if (percentage < 80.0F) progress.append("&2");
        else progress = progress.append("&a");

        int rest = 20;
        for (int i = (int) percentage; i >= 5; i = i - 5) {
            progress.append(":");
            rest--;
        }

        progress.append("&7");

        progress.append(":".repeat(Math.max(0, rest)));

        progress.append(" - ").append(percentage).append("%");
        return ChatColor.translateAlternateColorCodes('&', progress.toString());
    }

    public static @NotNull String getCoolant(int time, int total) {
        int passed = ((total - time) % 25);
        StringBuilder progress = new StringBuilder();
        float percentage = Math.round(((((25 - passed) * 100.0F) / 25) * 100.0F) / 100.0F);

        if (percentage < 33.0F) progress.append("&9");
        else if (percentage < 66.0F) progress.append("&1");
        else progress = progress.append("&b");

        int rest = 20;
        for (int i = (int) percentage; i >= 5; i = i - 5) {
            progress.append(":");
            rest--;
        }

        progress.append("&7");

        progress.append(":".repeat(Math.max(0, rest)));

        progress.append(" - ").append(percentage).append("%");
        return ChatColor.translateAlternateColorCodes('&', progress.toString());
    }

    public static float getPercentage(int time, int total) {
        int passed = ((total - time) % 25);
        return Math.round(((((25 - passed) * 100.0F) / 25) * 100.0F) / 100.0F);
    }

    public static void updateProgressbar(
            @NotNull BlockMenu menu, int slot, int timeleft, int time, @NotNull ItemStack indicator) {
        ChestMenuUtils.updateProgressbar(menu, slot, timeleft, time, indicator);
    }
}
