package me.Freeze_Dolphin.lab.commands;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.U;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TechItems implements CommandExecutor {
    private final Laboratory plugin;

    public TechItems(Laboratory plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (sender instanceof Player p) {

            if (!p.hasPermission("laboratory.commands.debugfish")) return false;

            if (args[0] != null) {
                if (args[0].equalsIgnoreCase("debugfish")) {
                    p.getInventory().addItem(SlimefunItems.DEBUG_FISH);
                    return true;
                }
            }

            return false;
        }

        U.scolor("&c此指令必须由玩家执行!");
        return true;
    }
}
