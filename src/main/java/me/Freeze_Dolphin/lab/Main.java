package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import me.Freeze_Dolphin.lab.bugrepair.ElevatorBook;
import me.Freeze_Dolphin.lab.bugrepair.Me;
import me.Freeze_Dolphin.lab.bugrepair.ScrollOfDimensionalTelepositionConsumption;
import me.Freeze_Dolphin.lab.bugrepair.SpeedIngotBUG;
import me.Freeze_Dolphin.lab.commands.Charge;
import me.Freeze_Dolphin.lab.commands.TechItems;
import me.Freeze_Dolphin.lab.listeners.ArcSword;
import me.Freeze_Dolphin.lab.listeners.DragonFireBall;
import me.Freeze_Dolphin.lab.listeners.EggGuarder;
import me.Freeze_Dolphin.lab.listeners.ElectricAxe;
import me.Freeze_Dolphin.lab.listeners.ElectricDigger;
import me.Freeze_Dolphin.lab.listeners.EmeraldCapacity;
import me.Freeze_Dolphin.lab.listeners.JukeboxDisc;
import me.Freeze_Dolphin.lab.listeners.NoDyeableSheep;
import me.Freeze_Dolphin.lab.listeners.PlasmaCell;
import me.Freeze_Dolphin.lab.listeners.RadioactivityWeapons;
import me.Freeze_Dolphin.lab.listeners.Reloader;
import me.Freeze_Dolphin.lab.listeners.Rubber;
import me.Freeze_Dolphin.lab.listeners.RubberBoots;
import me.Freeze_Dolphin.lab.listeners.Stomper;
import me.Freeze_Dolphin.lab.listeners.UnplaceableItems;
import me.Freeze_Dolphin.lab.listeners.Vanisher;
import me.Freeze_Dolphin.lab.listeners.WitherProofArmors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Main extends JavaPlugin implements SlimefunAddon {
    public static final Map<Double, ItemStack> lo = new HashMap<>();
    public static Main instance;

    static {
        lo.put(10d, new ItemStack(Material.WOODEN_PICKAXE));
        lo.put(30d, new ItemStack(Material.STONE_PICKAXE));
        lo.put(50d, new ItemStack(Material.IRON_PICKAXE));
        lo.put(60d, new ItemStack(Material.GOLDEN_PICKAXE));
        lo.put(70d, new ItemStack(Material.DIAMOND_PICKAXE));
        lo.put(90d, new ItemStack(Material.NETHERITE_PICKAXE));
    }

    public static void debug(String msg) {
        if (Variables.plug.getConfig().getBoolean("debug.normal")) {
            Variables.plug
                    .getServer()
                    .getConsoleSender()
                    .sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[&4&lDEBUG&c&l]&c: &r" + msg));
        }
    }

    public static void debugException(Exception ex) {
        if (Variables.plug.getConfig().getBoolean("debug.exception")) {
            Variables.plug
                    .getServer()
                    .getConsoleSender()
                    .sendMessage("\n" + ChatColor.translateAlternateColorCodes('&', "&c&l[&4&lDEBUG&c&l]&c: &r"));
            ex.printStackTrace();
        }
    }

    public static void debugBreakpoint(int breakpointOrder, Class<?> c) {
        if (Variables.plug.getConfig().getBoolean("debug.breakpoint"))
            Variables.plug
                    .getServer()
                    .getConsoleSender()
                    .sendMessage("\n"
                            + ChatColor.translateAlternateColorCodes(
                                    '&',
                                    "&c&l[&4&lDEBUG&c&l] &7" + c.getName() + ": &cBreakpoint &f[" + breakpointOrder
                                            + "]"));
    }

    public void onEnable() {
        getLogger().info("Enabling Laboratory...");
        instance = this;
        Variables.plug = this;
        Variables.cfg = Variables.plug.getConfig();

        File datad = getDataFolder();
        String datadp = datad.getPath();

        if (!(new File(datadp + "/config.yml")).exists()) {
            U.scolor("&c&l配置文件未找到, 正在加载默认配置文件...");
            saveDefaultConfig();
        }

        if (!(new File(datadp + "/mt.yml")).exists()) {
            U.scolor("&c&l分子重组仪合成表文件未找到, 正在加载默认合成表文件...");
            saveResource("mt.yml", true);
        }

        Variables.ResearchIdentify = Variables.cfg.getInt("research-identify-start");

        Variables.Nar = true;
        try {
            Class.forName("me.mrCookieSlime.Slimefun.Lists.NarItems");
            Class.forName("me.mrCookieSlime.Slimefun.Setup.NarItemSetup");
        } catch (ClassNotFoundException ex) {
            Variables.Nar = false;
        }

        if (Variables.Nar) U.scolor("&e&l检测到你所安装的粘液科技为Nar重置版");

        Variables.Non = new RecipeType(
                new NamespacedKey(Variables.plug, "non-craftable"),
                new CustomItemStack(Material.BARRIER, "&c&l不可合成", "", "&f此物品无法通过合成方式获得"));
        Variables.Mystery = new RecipeType(
                new NamespacedKey(Variables.plug, "mystery"),
                new CustomItemStack(Material.PAPER, "&d未知", "", "&f此物品的来源未知", "&f请自行探索"));

        getCommand("charge").setExecutor(new Charge(this));
        getCommand("techitems").setExecutor(new TechItems(this));

        getServer().getPluginManager().registerEvents(new Reloader(), this);

        getServer().getPluginManager().registerEvents(new Stomper(), this);
        getServer().getPluginManager().registerEvents(new ElectricAxe(), this);

        getServer().getPluginManager().registerEvents(new Vanisher(), this);
        getServer().getPluginManager().registerEvents(new EmeraldCapacity(), this);

        getServer().getPluginManager().registerEvents(new ElectricDigger(), this);
        getServer().getPluginManager().registerEvents(new EggGuarder(), this);
        getServer().getPluginManager().registerEvents(new RubberBoots(), this);
        getServer().getPluginManager().registerEvents(new ArcSword(), this);

        getServer().getPluginManager().registerEvents(new UnplaceableItems(), this);
        getServer().getPluginManager().registerEvents(new NoDyeableSheep(), this);

        getServer().getPluginManager().registerEvents(new Rubber(), this);
        getServer().getPluginManager().registerEvents(new PlasmaCell(), this);
        getServer().getPluginManager().registerEvents(new DragonFireBall(), this);
        getServer().getPluginManager().registerEvents(new JukeboxDisc(), this);
        getServer().getPluginManager().registerEvents(new RadioactivityWeapons(), this);
        getServer().getPluginManager().registerEvents(new WitherProofArmors(), this);

        if (Variables.cfg.getBoolean("bugfixers.speed-ingot")) {
            getServer().getPluginManager().registerEvents(new SpeedIngotBUG(), this);
        }
        if (Variables.cfg.getBoolean("bugfixers.me")) {
            getServer().getPluginManager().registerEvents(new Me(), this);
        }
        if (Variables.cfg.getBoolean("bugfixers.scroll-of-dimensional-teleposition.consume")) {
            getServer().getPluginManager().registerEvents(new ScrollOfDimensionalTelepositionConsumption(), this);
        }
        if (Variables.cfg.getBoolean("bugfixers.elevator-book")) {
            getServer().getPluginManager().registerEvents(new ElevatorBook(), this);
        }

        getLogger().info("Loading Items...");
        new Tech();
        new Food();
        new Lab();
        new Nuclear();
        new Resource();
        new ArmorWeapon();

        getLogger().info("Laboratory enabled.");
    }

    public void onDisable() {
        DragonFireBall.clean();
    }

    @NotNull @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable @Override
    public String getBugTrackerURL() {
        return null;
    }
}
