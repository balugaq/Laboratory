package me.Freeze_Dolphin.lab.listeners;

import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Reloader implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        if (e.getItem().isSimilar(Lab.CONFIG_RELOADER) && e.getPlayer().isOp()) {
            try {
                Variables.plug.reloadConfig();
            } catch (Exception ex) {

                e.getPlayer().sendMessage(U.getCfgMessage("messages.reloader.wrong"));
            }

            e.getPlayer().sendMessage(U.getCfgMessage("messages.reloader.successfully-reloaded"));
            e.getPlayer().sendMessage(U.getCfgMessage("messages.reloader.warn"));
        }
    }
}
