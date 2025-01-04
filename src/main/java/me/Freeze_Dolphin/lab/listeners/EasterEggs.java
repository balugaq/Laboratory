package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.U;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EasterEggs implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.hasBlock()) {
            if (BlockStorage.checkID(e.getClickedBlock()).equals("BLAST_FURNACE")) {
                if (e.hasItem()) {
                    if (SlimefunUtils.isItemSimilar(e.getItem(), U.mat(Material.PAPER), true)) {
                        e.getPlayer().sendMessage(U.color("&f<Freeze_Dolphin> 高炉这个东西我从2019年10月开始写"));
                        e.getPlayer().sendMessage(U.color("&f<Freeze_Dolphin> 写到2020年1月16日成品出来差不多有3个月"));
                        e.getPlayer().sendMessage(U.color("&f<Freeze_Dolphin> 中间弃坑至少3次"));
                        e.getPlayer().sendMessage(U.color("&f<Freeze_Dolphin> 希望你们不要对它——我肝了3个月的高炉，有什么不满"));
                        e.getPlayer().sendMessage(U.color("&f<Freeze_Dolphin> ......"));
                    }
                }
            }
        }
    }
}
