package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.FurnaceRecipe;

public class RawRubberBurning implements Listener {
    public RawRubberBurning(Laboratory laboratory) {
        Variables.plug.getServer().addRecipe(new FurnaceRecipe(Tech.RUBBER_BALL, Tech.RAW_RUBBER.getData(), (float)
                Variables.cfg.getInt("items.rubber.exp-drop")));
        laboratory.getServer().getPluginManager().registerEvents(this, laboratory);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void smelt(FurnaceSmeltEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getSource(), Tech.RAW_RUBBER, true)) e.setResult(Tech.RUBBER_BALL.clone());
    }
}
