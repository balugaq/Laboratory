package me.Freeze_Dolphin.lab.commands;

import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.U;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Charge implements CommandExecutor {
    private final Laboratory plugin;

    public Charge(Laboratory plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            ItemStack it = p.getInventory().getItemInMainHand();

            if (!p.hasPermission("laboratory.commands.charge")) return false;

            if (ItemEnergy.getMaxEnergy(it) == 0.0F) {
                return false;
            }
            ItemEnergy.chargeItem(it, ItemEnergy.getMaxEnergy(it) - ItemEnergy.getStoredEnergy(it));
            return true;
        }

        U.scolor("&c此指令必须由玩家执行!");
        return true;
    }
}
