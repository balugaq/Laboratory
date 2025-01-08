package me.Freeze_Dolphin.lab;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.LockedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.RadioactiveItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.AndroidType;
import io.github.thebusybiscuit.slimefun4.implementation.items.androids.ProgrammableAndroid;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import io.github.thebusybiscuit.slimefun4.implementation.items.misc.AlloyIngot;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.Freeze_Dolphin.lab.geo.CertusQuartz;
import me.Freeze_Dolphin.lab.geo.Iridium;
import me.Freeze_Dolphin.lab.machines.AdvancedChargingBench;
import me.Freeze_Dolphin.lab.machines.ChemicalReactor;
import me.Freeze_Dolphin.lab.machines.Converter;
import me.Freeze_Dolphin.lab.machines.CreatorGenerator;
import me.Freeze_Dolphin.lab.machines.IridiumDrill;
import me.Freeze_Dolphin.lab.machines.ItemFreezer;
import me.Freeze_Dolphin.lab.machines.Magnetizer;
import me.Freeze_Dolphin.lab.machines.NetherStarCrusher;
import me.Freeze_Dolphin.lab.machines.PlasmaGenerator;
import me.Freeze_Dolphin.lab.machines.QuartzDrill;
import me.Freeze_Dolphin.lab.machines.RadioisotopeThermoelectricGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tech {
    public static final List<List<String>> MT_RECIPES = new ArrayList<>();
    public static final RecipeType NON = Variables.Non;
    public static final RecipeType QDRILL = new RecipeType(
            new NamespacedKey(Laboratory.instance, "qdrill"),
            new CustomItemStack(Material.WHITE_STAINED_GLASS, "&f石英钻机", "", "&a赛特斯石英需要利用石英钻机挖取"));
    public static final RecipeType MZER = new RecipeType(
            new NamespacedKey(Laboratory.instance, "mzer"),
            new CustomItemStack(Material.IRON_BLOCK, "&b磁化机", "", "&a通过磁化机磁化其他物品获得"));
    public static final RecipeType ELYZER = new RecipeType(
            new NamespacedKey(Laboratory.instance, "elyzer"),
            new CustomItemStack(Material.IRON_BLOCK, "&b电解机", "", "&a通过电解机电解其他物品获得"));
    public static final RecipeType MT = new RecipeType(
            new NamespacedKey(Laboratory.instance, "mt"),
            new CustomItemStack(Material.BEACON, "&b分子重组仪", "", "&a使用分子重组仪将某些物质转化为另一种物质"));
    public static final RecipeType DIG = new RecipeType(
            new NamespacedKey(Laboratory.instance, "dig"),
            new CustomItemStack(Material.IRON_PICKAXE, "&7采掘", "", "&a在挖掘特定方块时有几率掉落"));
    public static final RecipeType PLASMAG = new RecipeType(
            new NamespacedKey(Laboratory.instance, "plasmag"),
            new CustomItemStack(Material.PURPLE_STAINED_GLASS, "&d等离子生成机", "", "&a使用等离子生成机产生等离子体"));
    public static final ItemGroup c = new SubItemGroup(
            new NamespacedKey(Laboratory.instance, "tech_misc"),
            Laboratory.nest,
            new CustomItemStack(Material.DETECTOR_RAIL, "&7Consider 实验室科技材料"));
    public static final ItemGroup cr = new SubItemGroup(
            new NamespacedKey(Laboratory.instance, "resources"),
            Laboratory.nest,
            new CustomItemStack(Material.IRON_INGOT, "&7Consider 实验室资源"));
    public static final LockedItemGroup lockedCategory = new LockedItemGroup(
            new NamespacedKey(Laboratory.instance, "tech_machines"),
            new CustomItemStack(Material.BLACK_STAINED_GLASS, "&7Consider 实验室机器"),
            new NamespacedKey(Slimefun.instance(), "basic_machines"));
    public static final ItemGroup cm = new SubItemGroup(
            new NamespacedKey(Laboratory.instance, "misc"),
            Laboratory.nest,
            new CustomItemStack(Material.PAPER, "&7Consider 实验室杂项"));
    public static SlimefunItemStack MAGNETIC_IRON_INGOT;
    public static SlimefunItemStack RUSTY_MECHANICAL_PARTS;
    public static SlimefunItemStack CARBONADO_EGDED_COAL_GENERATOR;
    public static SlimefunItemStack REINFORCED_SILICON_ALLOY;
    public static SlimefunItemStack ALUMAG_INGOT;
    public static SlimefunItemStack ALUMAG_ALLOY;
    public static SlimefunItemStack ALUMAG_BLOCK;
    public static SlimefunItemStack A_PIECE_OF_NETHER_STAR;
    public static SlimefunItemStack NETHER_STAR_CRUSHER;
    public static SlimefunItemStack LAMA_POWER_CRYSTAL;
    public static SlimefunItemStack GRENCO_POWER_CRYSTAL;
    public static SlimefunItemStack IRIDIUM;
    public static SlimefunItemStack IRIDIUM_BLOCK;
    public static SlimefunItemStack IRIDIUM_CAPACITY;
    public static SlimefunItemStack CREATOR_GENERATOR;
    public static SlimefunItemStack EGG_GENERATOR;
    public static SlimefunItemStack ENDER_GENERATOR;
    public static SlimefunItemStack REDSTONE_GENERATOR;
    public static SlimefunItemStack RADIOISOTOPE_THERMOELECTRIC_GENERATOR;
    public static SlimefunItemStack PELLETS_OF_RTG_FUEL;
    public static SlimefunItemStack LEAD_IRON_ALLOY;
    public static SlimefunItemStack NEUTRON_REFLECTOR;
    public static SlimefunItemStack MT_CORE;
    public static SlimefunItemStack QUANTUM_CORE;
    public static SlimefunItemStack VECTOR_CORE;
    public static SlimefunItemStack CONVERTER_1;
    public static SlimefunItemStack CONVERTER_2;
    public static SlimefunItemStack CONVERTER_3;
    public static SlimefunItemStack CONVERTER_4;
    public static SlimefunItemStack SUNNARIUM_PIECE;
    public static SlimefunItemStack SUNNARIUM;
    public static SlimefunItemStack SUNNARIUM_ALLOY;
    public static SlimefunItemStack IRRADIANT_URANIUM;
    public static SlimefunItemStack ENRICHED_SUNNARIUM;
    public static SlimefunItemStack ENRICHED_SUNNARIUM_ALLOY;
    public static SlimefunItemStack IRRADIANT_GLASS;
    public static SlimefunItemStack IRIDIUM_IRON_PLATE;
    public static SlimefunItemStack REINFORCED_IRIDIUM_IRON_PLATE;
    public static SlimefunItemStack IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE;
    public static SlimefunItemStack VECTOR_THRUSTER;
    public static SlimefunItemStack REINFORCED_IRIDIUM_PLATE;
    public static SlimefunItemStack REDSTONE_ELEMENT;
    public static SlimefunItemStack SPEED_INGOT_BUG;
    public static SlimefunItemStack SPEED_INGOT;
    public static SlimefunItemStack SPECTRUM_FILTER;
    public static SlimefunItemStack OPTICAL_COMPONENT;
    public static SlimefunItemStack SUPER_MAGNET;
    public static SlimefunItemStack SUPER_ELECTRO_MAGNET;
    public static SlimefunItemStack MAGNETIZED_REINFORCED_PLATE;
    public static SlimefunItemStack SUPER_CIRCUIT_BOARD;
    public static SlimefunItemStack ULTIMATE_CIRCUIT_BOARD;
    public static SlimefunItemStack SUPER_HEATING_COIL;
    public static SlimefunItemStack SUPER_ELECTRO_MOTOR;
    public static SlimefunItemStack RAW_RUBBER;
    public static SlimefunItemStack RUBBER_BALL;
    public static SlimefunItemStack HEAT_CONDUCTOR;
    public static SlimefunItemStack MAGNETIZER_1;
    public static SlimefunItemStack MAGNETIZER_2;
    public static SlimefunItemStack ELECTROLYZER_1;
    public static SlimefunItemStack ELECTROLYZER_2;
    public static SlimefunItemStack CERTUS_QUARTZ;
    public static SlimefunItemStack CHARGED_CERTUS_QUARTZ;
    public static SlimefunItemStack QUARTZ_DRILL;
    public static SlimefunItemStack IRIDIUM_DRILL;
    public static SlimefunItemStack ADVANCED_CHARGING_BENCH;
    public static SlimefunItemStack ITEM_FREEZER;
    public static SlimefunItemStack PROGRAMMABLE_ENDER_ANDROID_FISHERMAN;
    public static SlimefunItemStack PROGRAMMABLE_ENDER_ANDROID_MINER;
    public static SlimefunItemStack PROGRAMMABLE_ENDER_ANDROID_BUTCHER;
    public static SlimefunItemStack MV_TRANSFORMER;
    public static SlimefunItemStack HV_TRANSFORMER;
    public static SlimefunItemStack EV_TRANSFORMER;
    public static SlimefunItemStack ARC_REACTOR;
    public static SlimefunItemStack PLASMA_GENERATOR;
    public static SlimefunItemStack PLASMA_CELL;
    public static SlimefunItemStack PLASMA_BALL;
    public static SlimefunItemStack RADIUM;
    public static SlimefunItemStack IRRADIANT_RADIUM;
    public static SlimefunItemStack CHEMICAL_REACTOR;
    public static SlimefunItemStack ADVANCED_BIO_REACTOR;
    public static SlimefunItemStack ALLOY_REACTOR;
    public static SlimefunItemStack ADVANCED_ELECTRIC_INGOT_PULVERIZER;
    public static RecipeType STARC;
    public static RecipeType LOGS;

    public Tech() {
        File mt_file = new File(Variables.plug.getDataFolder(), "mt.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(mt_file);

        ConfigurationSection cs = yamlConfiguration.getConfigurationSection("recipes");
        for (String key : cs.getKeys(false)) {
            MT_RECIPES.add(cs.getStringList(key));
        }

        Laboratory.debug(MT_RECIPES.toString());

        MAGNETIC_IRON_INGOT = new SlimefunItemStack("LAB_MAGNETIC_IRON_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b磁铁铁矿", "", "&a磁铁铁矿可以制造磁铁"));
        (new SlimefunItem(
                c,
                MAGNETIC_IRON_INGOT,
                new RecipeType(
                        new NamespacedKey(Laboratory.instance, "break_iron_ore"),
                        new CustomItemStack(Material.IRON_PICKAXE, "&7采掘", "", "&a在挖掘铁矿时有几率掉落")),
                U.midr(new ItemStack(Material.IRON_ORE))))
                .register(Laboratory.instance);

        STARC = new RecipeType(
                new NamespacedKey(Laboratory.instance, "starc"),
                new CustomItemStack(Material.DIAMOND_BLOCK, "&f下界之星粉碎机", "", "&a使用此粉碎机将下界之星粉碎获得"));

        LOGS = new RecipeType(
                new NamespacedKey(Laboratory.instance, "logs"),
                new CustomItemStack(Material.IRON_AXE, "&e伐木", "", "&a砍伐橡木和深色橡木时有几率掉落"));

        boolean useSelfIridium = false;
        SlimefunItem iridium = SlimefunItem.getById("IRIDIUM");
        SlimefunItem spacetechIridium = SlimefunItem.getById("SPACETECH_IRIDIUM");
        if (iridium != null) {
            IRIDIUM = new SlimefunItemStack(iridium.getId(), iridium.getItem());
        } else if (spacetechIridium != null) {
            IRIDIUM = new SlimefunItemStack(spacetechIridium.getId(), spacetechIridium.getItem());
        } else {
            IRIDIUM = new SlimefunItemStack("LAB_IRIDIUM",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJlY2Q2OTFiYWY1ZTg5NjYxOThlOWY0Zjc5NGM5OTM0NGMzZjQzYWRkMTMwZDNiOTljY2ViM2YwNGI3Nzk3ZSJ9fX0="),
                            "&d铱",
                            "",
                            "&f一种稀有的贵重金属",
                            "&f其质地硬而脆",
                            "&c在蘑菇岛通过铱钻机获取"));

            (new SlimefunItem(cr, IRIDIUM, MT, U.midr(new ItemStack(Material.IRON_BLOCK)))).register(Laboratory.instance);
            useSelfIridium = true;
        }

        CertusQuartz.instance.register();

        IRIDIUM_BLOCK = new SlimefunItemStack("LAB_IRIDIUM_BLOCK",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdkZjNmOGViYjc3NzU2ZTE3NzM0ZDE1ODQ5MTYwMDNiMWYyMWJhYmFjOGM5YjBlYTkxMGIwNjQyZTc0OTVhYSJ9fX0="),
                        "&d铱块"));
        REINFORCED_IRIDIUM_PLATE =
                new SlimefunItemStack("LAB_REINFORCED_IRIDIUM_PLATE", new CustomItemStack(Material.PAPER, "&b强化铱板"));

        A_PIECE_OF_NETHER_STAR = new SlimefunItemStack("LAB_A_PIECE_OF_NETHER_STAR", new CustomItemStack(Material.LIGHT_GRAY_DYE, "&f下界之星碎片"));
        VECTOR_THRUSTER = new SlimefunItemStack("LAB_VECTOR_THRUSTER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGFhY2I0MzU5OGY2MjM1YTY5OGFkOTg4NWU3Y2Y3NTM5YzE3YTMwYjc2NmU0ZTlmMDc4OGUzODc1YzVmNDVmMCJ9fX0="),
                        "&b矢量推进器"));

        LAMA_POWER_CRYSTAL = new SlimefunItemStack("LAB_LAMA_POWER_CRYSTAL",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU1ZjQ2YTVkNWVmYWUwZWI5MzVmNWI0ODI4MWRlMjgyZTVkZGZhNjY1N2JlZGNkMzM3M2Y2NGFhYTQzNWJlNiJ9fX0="),
                        "&9&l兰波顿能量水晶"));
        GRENCO_POWER_CRYSTAL = new SlimefunItemStack("LAB_GRENCO_POWER_CRYSTAL",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjcxNGIyNWU0OGYyYjBkZjhjYWUzMjdkYmRlMWMyZTEyN2QzYTEwZmY5YWEzMjJiNTRmY2RiZWFlMjUxODJkMyJ9fX0="),
                        "&a&l格林科能量水晶",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));

        NEUTRON_REFLECTOR = new SlimefunItemStack("LAB_NEUTRON_REFLECTOR", new CustomItemStack(Material.PAPER, "&b中子反射板"));
        MT_CORE = new SlimefunItemStack("LAB_MT_CORE",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDkxNjQyNjk1NGNiM2IyNTcyMWRhMzNmNWFlYmZhOGQ4MTNjMWYwODBmYjQ1M2FjZWZmMjI2M2IxMzBjMCJ9fX0="),
                        "&bMT 核心",
                        "",
                        "&f分子重组仪的核心部件",
                        "&f用于操纵分子"));
        QUANTUM_CORE = new SlimefunItemStack("LAB_QUANTUM_CORE",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYxZmJiODRiMGI4Mzc1OTQ2OWJjNDMwYjg3ODdhOWExZDhiODE4ZDUyYjVjZDViMjg1ZTFhYmY1YmViZjM5ZCJ9fX0="),
                        "&d量子核心",
                        "",
                        "&f制作高级量子部件时的必需品"));
        VECTOR_CORE = new SlimefunItemStack("LAB_VECTOR_CORE",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGM3YTQwMTNiNzcxMDMyODdlNmE0YTA0NTlmYTI4MzQ3YzQxZjY2MjZhZjdjYjA1MTJlYmZjNDUxOTc0MDA2In19fQ=="),
                        "&6矢量核心"));

        SUNNARIUM_PIECE = new SlimefunItemStack("LAB_SUNNARIUM_PIECE", new CustomItemStack(Material.GLOWSTONE_DUST, "&e阳光化合物粉尘"));

        SUNNARIUM = new SlimefunItemStack("LAB_SUNNARIUM",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc4MmZlYTA1YWJkOTMzZTk5MWE2YmY2M2Q5NzI3MGE5YjY5MmJiY2MzMmEyMjczMjVhMjE0ZmQ4MmU1NTNmMSJ9fX0="),
                        "&e阳光化合物"));
        SUNNARIUM_ALLOY = new SlimefunItemStack("LAB_SUNNARIUM_ALLOY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE4ODI3YmUyNjk3MDJlMTI0ODZkOTZiODExYjc2MTczN2JhNmJiZjZiMDI0ZWQ3ZWI3MDM3OWI0ZjcwNGUzZCJ9fX0="),
                        "&e阳光合金块"));
        IRRADIANT_URANIUM = new SlimefunItemStack("LAB_IRRADIANT_URANIUM",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E4MDJhZjRlODk4ZTViZWY1ZGFjYzUzZGVkYTRmNWQ0N2VhY2Q2YTZjNjc1NmM4ZmUyYmUyNDUyMDdiMTgifX19"),
                        "&a光辉铀块",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));
        ENRICHED_SUNNARIUM = new SlimefunItemStack("LAB_ENRICHED_SUNNARIUM",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGUwMDhmMGNiM2FjYjI0Njc0NjliYzFlNjYyNzFkZWI4OGJkNWVmOGRhYzk3NmYyOGZkZDVjN2QzZTc1ZThmMyJ9fX0="),
                        "&a富集阳光化合物",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));
        ENRICHED_SUNNARIUM_ALLOY = new SlimefunItemStack("LAB_ENRICHED_SUNNARIUM_ALLOY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI3YzI0NzFlMDgwNDhmYjAzODQyMTZmYjkzMDM2OTExNmEyZGY0YTJkNjU1ZDAxNmIwY2VkOGRjM2NmYmYzZCJ9fX0="),
                        "&a富集阳光化合物合金块",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));

        RUSTY_MECHANICAL_PARTS = new SlimefunItemStack("LAB_RUSTY_MECHANICAL_PARTS", new CustomItemStack(Material.RAIL, "&8生锈的机械零件"));

        IRRADIANT_GLASS = new SlimefunItemStack("LAB_IRRADIANT_GLASS", new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&a光辉玻璃"));
        IRIDIUM_IRON_PLATE = new SlimefunItemStack("LAB_IRIDIUM_IRON_PLATE", new CustomItemStack(Material.PAPER, "&b铱铁板"));
        REINFORCED_IRIDIUM_IRON_PLATE = new SlimefunItemStack("LAB_REINFORCED_IRIDIUM_IRON_PLATE", new CustomItemStack(Material.PAPER, "&b强化铱铁板"));
        IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE = new SlimefunItemStack("LAB_IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE", new CustomItemStack(Material.PAPER, "&b光辉强化铱铁板"));

        SUPER_MAGNET = new SlimefunItemStack("LAB_SUPER_MAGNET",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="),
                        "&c再磁化磁铁"));
        SUPER_ELECTRO_MAGNET = new SlimefunItemStack("LAB_SUPER_ELECTRO_MAGNET",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="),
                        "&c超级电磁铁"));
        SUPER_CIRCUIT_BOARD = new SlimefunItemStack("LAB_SUPER_CIRCUIT_BOARD", new CustomItemStack(Material.DETECTOR_RAIL, "&b进阶电路板"));
        ULTIMATE_CIRCUIT_BOARD = new SlimefunItemStack("LAB_ULTIMATE_CIRCUIT_BOARD", new CustomItemStack(Material.DETECTOR_RAIL, "&4终极电路板"));

        MAGNETIZED_REINFORCED_PLATE = new SlimefunItemStack("LAB_MAGNETIZED_REINFORCED_PLATE", new CustomItemStack(Material.PAPER, "&7磁化强化板"));
        SUPER_HEATING_COIL = new SlimefunItemStack("LAB_SUPER_HEATING_COIL",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzYmM0ODkzYmE0MWEzZjczZWUyODE3NGNkZjRmZWY2YjE0NWU0MWZlNmM4MmNiN2JlOGQ4ZTk3NzFhNSJ9fX0="),
                        "&c复合型加热线圈"));
        SUPER_ELECTRO_MOTOR = new SlimefunItemStack("LAB_SUPER_ELECTRO_MOTOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNiY2EwMTJmNjdlNTRkZTlhZWU3MmZmNDI0ZTA1NmMyYWU1OGRlNWVhY2M5NDlhYjJiY2Q5NjgzY2VjIn19fQ=="),
                        "&c强化电动马达"));

        HEAT_CONDUCTOR = new SlimefunItemStack("LAB_HEAT_CONDUCTOR", new CustomItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, "&c热传导器"));

        SPEED_INGOT_BUG = new SlimefunItemStack("LAB_SPEED_INGOT_BUG", new CustomItemStack(Material.IRON_INGOT, "&b&l加速锭", "", "&2辐射等级: 高", "&4需要穿着防护服使用"));
        SPEED_INGOT = new SlimefunItemStack("LAB_SPEED_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b&l强化加速锭", "", "&2辐射等级: 高", "&4需要穿着防护服使用"));

        REINFORCED_SILICON_ALLOY = new SlimefunItemStack("LAB_REINFORCED_SILICON_ALLOY", new CustomItemStack(Material.IRON_INGOT, "&b&l强化硅铁合金锭"));

        SPECTRUM_FILTER = new SlimefunItemStack("LAB_SPECTRUM_FILTER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTJlZDJmYjRkOWQ3ODRiOTM2ZGNhMWM3OGYzNzdhMDJlOWNkNzFhODI0OTQ2ZTA2NDYwOTdjY2NkMWZlNyJ9fX0="),
                        "&b光谱过滤器"));
        OPTICAL_COMPONENT = new SlimefunItemStack("LAB_OPTICAL_COMPONENT",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWMzNDE1MTdmNGQxODY1YmViY2UzM2Y0YTFhZmI1MmYxM2FhYjhkMjZlZDY0ZTdlMjI5YThiNThiMWY1YjNkIn19fQ=="),
                        "&d光学组件"));

        REDSTONE_ELEMENT = new SlimefunItemStack("LAB_REDSTONE_ELEMENT", new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&c多功能红石元件"));

        CERTUS_QUARTZ = new SlimefunItemStack("LAB_CERTUS_QUARTZ",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNlZWI0NDA0YTIyZTNjNWZiZGQ0ODM2YzcyYTdmNTljMTYxNTU4OGE5YzU3ZDI4NzE1NTQ1MzcyOGFlYSJ9fX0="),
                        "&f赛特斯石英块",
                        "&c在下界通过石英钻机获取"));
        CHARGED_CERTUS_QUARTZ = new SlimefunItemStack("LAB_CHARGED_CERTUS_QUARTZ",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E1YTA3MTVjNjIxMjJkZWQ2NWFmNGVhZTA5NjlmMjNmNTcxYjBhZmE1MGNmOTNmYzllZTJhZjRjN2IzNGUxMiJ9fX0="),
                        "&b充能赛特斯石英块",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));

        PELLETS_OF_RTG_FUEL = new SlimefunItemStack("LAB_PELLETS_OF_RTG_FUEL",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTlmMWU3ZDViNjM4NTQwNTU4OTcxNjAxMWIwMjQ2YjRiYTg2YjMwYWE3N2Q4NTZkNWRjNWMyNGY3MDMxZjI0YSJ9fX0="),
                        "&6放射性同位素靶丸",
                        "",
                        "&f被安全保存的压缩核燃料",
                        "&f放入同位素温差发电机能无限发电"));
        LEAD_IRON_ALLOY = new SlimefunItemStack("LAB_LEAD_IRON_ALLOY", new CustomItemStack(Material.IRON_INGOT, "&b铅铁合金锭"));

        RAW_RUBBER = new SlimefunItemStack("LAB_RAW_RUBBER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRjMDAxYzJiYjhkZDkzZGFlNzAyNTRmZWEzYmY1N2I2NDJkZTllZjA1ZjMyOWVlN2Y5NWQxNTYwMTk4MDI2MiJ9fX0="),
                        "&e生橡胶"));
        RUBBER_BALL = new SlimefunItemStack("LAB_RUBBER_BALL",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTdkODEyODYyODFiMWM3YjEzNDIwZjcwYjBmNDM4NjdhYTM4ZWVlMTA5YjY0YzNhMzdiNjk5ZWEwNWU4ZWMwNSJ9fX0="),
                        "&8橡胶球"));

        MV_TRANSFORMER = new SlimefunItemStack("LAB_MV_TRANSFORMER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFhZDRhOWU5ODM5MDkxYzBmOWFlZGIzMTJiNjI1ZGRkMDRiMTUxNmE2OTAzZGI4YmExYTQ0YWFlNmE1NTkwOSJ9fX0="),
                        "&a中压变压器"));
        HV_TRANSFORMER = new SlimefunItemStack("LAB_HV_TRANSFORMER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI4NDRmNmRmOGY0NDkyNzBiZjBiYTU3NTkyY2UwMzc0MmEyMzkzMzMwMmZjN2U4Yzc3OWQ3OWY3NWQ1MmU4NyJ9fX0="),
                        "&9高压变压器"));
        EV_TRANSFORMER = new SlimefunItemStack("LAB_EV_TRANSFORMER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I1NjVhYjczNGRkZjY5ZGM3YjliMzhlZDJmMTMwZTBjM2RjZTVmZDVhMWRlMGE5YTAyY2JjMjBkZDJmMzFjMCJ9fX0="),
                        "&d超高压变压器"));
        ARC_REACTOR = new SlimefunItemStack("LAB_ARC_REACTOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTUyZmZkMDg1MjhlYzgxODNiMzVhYWM2NThiMjkyMjZhZDhiOWFhY2FjOGRkOWUwNGNmMTg2YjExMDY0Y2E0ZCJ9fX0="),
                        "&b电弧发生器",
                        "",
                        "&7&o滋滋滋滋滋滋......"));

        PLASMA_CELL = new SlimefunItemStack("LAB_PLASMA_CELL",
                Color.PURPLE,
                new PotionEffect(PotionEffectType.HARM, 10, 2, false, false),
                "&d等离子单元", "", "&f&m请勿食用", "&f&l放心食用");

        PLASMA_BALL = new SlimefunItemStack("LAB_PLASMA_BALL",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkzNTg3MDNhYjc3MjdkZjMzMjQzMzY5NjllODFkNmY5MmI3YWE3OWVkYjk2NmMwYmU5MWFiMTYxYmFkMWYwMSJ9fX0="),
                        "&5等离子球"));

        RADIUM = new SlimefunItemStack("LAB_RADIUM",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDIwN2M1ZTc2NGM2MWMxNWNiOTdhYmI2MjA0Y2Q2ZjVmYzk2M2UwNzI2NGIxNDA0MzBkMzk3MmEyNzFhN2RhIn19fQ=="),
                        "&9镭",
                        "",
                        "&2辐射等级: 穿透性",
                        "&4需要穿着反穿透防护服使用"));
        IRRADIANT_RADIUM = new SlimefunItemStack("LAB_IRRADIANT_RADIUM",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzU2NTc3ZGIzMDdmYzg0OWE4Zjc5MWE3MjRhZGQxMWNjY2I3MjcyOTI2MTQxNzNmMDQ4NjMyMjc4YTA4YjQyOSJ9fX0="),
                        "&a光辉镭块",
                        "",
                        "&2辐射等级: 穿透性",
                        "&4需要穿着反穿透防护服使用"));

        ALUMAG_INGOT = new SlimefunItemStack("LAB_ALUMAG_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b防锈铝合金锭"));
        ALUMAG_ALLOY = new SlimefunItemStack("LAB_ALUMAG_ALLOY", new CustomItemStack(Material.PAPER, "&7防锈铝合金板"));
        ALUMAG_BLOCK = new SlimefunItemStack("LAB_ALUMAG_BLOCK",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI2Yjc3MjMyOWNmMzJmODY0M2M0OTI4NjI2YjZhMzI1MjMzZmY2MWFhOWM3NzI1ODczYTRiZDY2ZGIzZDY5MiJ9fX0="),
                        "&b防锈铝合金块"));

        U.addDyeItem(A_PIECE_OF_NETHER_STAR);

        U.setPenetratingRadioactive(RADIUM);
        U.setPenetratingRadioactive(IRRADIANT_RADIUM);

        U.addUnplaceableItem(HEAT_CONDUCTOR);

        ItemStack ferrosiliconIngots = SlimefunItems.FERROSILICON.clone();
        ferrosiliconIngots.setAmount(Variables.plug.getConfig().getInt("items.reinforced-silicon-alloy.count"));
        (new SlimefunItem(cr, REINFORCED_SILICON_ALLOY, RecipeType.COMPRESSOR, U.firsts(ferrosiliconIngots)))
                .register(Laboratory.instance);

        (new AlloyIngot(cr, ALUMAG_INGOT, new ItemStack[]{
                SlimefunItems.ALUMINUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.ALUMINUM_INGOT
        }))
                .register(Laboratory.instance);

        ItemStack alumagIngots = ALUMAG_INGOT.clone();
        alumagIngots.setAmount(Variables.cfg.getInt("items.alumag-alloy.count"));
        (new SlimefunItem(cm, ALUMAG_ALLOY, RecipeType.COMPRESSOR, U.firsts(alumagIngots))).register(Laboratory.instance);

        ItemStack alumagAlloys = ALUMAG_ALLOY.clone();
        alumagAlloys.setAmount(Variables.cfg.getInt("items.alumag-alloy.block.count"));
        (new SlimefunItem(cm, ALUMAG_BLOCK, RecipeType.COMPRESSOR, U.firsts(alumagAlloys))).register(Laboratory.instance);

        (new AlloyIngot(cr, LEAD_IRON_ALLOY, new ItemStack[]{
                SlimefunItems.IRON_DUST, SlimefunItems.LEAD_INGOT, U.mat(Material.IRON_INGOT)
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, RAW_RUBBER, LOGS, U.midr(null))).register(Laboratory.instance);

        (new SlimefunItem(cm, RUBBER_BALL, RecipeType.SMELTERY, U.midr(RAW_RUBBER))).register(Laboratory.instance);

        (new SlimefunItem(cm, A_PIECE_OF_NETHER_STAR, STARC, U.midr(U.mat(Material.NETHER_STAR))))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, IRIDIUM_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, U.allr(IRIDIUM)))
                .register(Laboratory.instance);

        (new SlimefunItem(cr, CERTUS_QUARTZ, QDRILL, U.midr(null))).register(Laboratory.instance);

        ItemStack[] charged_certus_quartz_recipe = U.halfr2(SlimefunItems.URANIUM, SlimefunItems.ENRICHED_NETHER_ICE);
        charged_certus_quartz_recipe[4] = CERTUS_QUARTZ.clone();
        (new RadioactiveItem(
                cr,
                Radioactivity.HIGH,
                CHARGED_CERTUS_QUARTZ,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                charged_certus_quartz_recipe))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, REINFORCED_IRIDIUM_PLATE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                IRIDIUM,
                U.mat(Material.DIAMOND),
                IRIDIUM,
                U.mat(Material.DIAMOND),
                SlimefunItems.REINFORCED_PLATE,
                U.mat(Material.DIAMOND),
                IRIDIUM,
                U.mat(Material.DIAMOND),
                IRIDIUM
        }))
                .register(Laboratory.instance);

        ItemStack[] lama_power_crystal_recipe = U.halfr2(SlimefunItems.POWER_CRYSTAL, A_PIECE_OF_NETHER_STAR);
        lama_power_crystal_recipe[4] = SUPER_CIRCUIT_BOARD;
        (new SlimefunItem(c, LAMA_POWER_CRYSTAL, RecipeType.ENHANCED_CRAFTING_TABLE, lama_power_crystal_recipe))
                .register(Laboratory.instance);

        (new RadioactiveItem(
                c,
                Radioactivity.HIGH,
                GRENCO_POWER_CRYSTAL,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                U.halfr3(LAMA_POWER_CRYSTAL, IRRADIANT_GLASS, SlimefunItems.BOOSTED_URANIUM)))
                .register(Laboratory.instance);

        (new SlimefunItem(c, VECTOR_THRUSTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null,
                IRIDIUM,
                MAGNETIZED_REINFORCED_PLATE,
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                MAGNETIZED_REINFORCED_PLATE,
                CHARGED_CERTUS_QUARTZ,
                LAMA_POWER_CRYSTAL,
                CHARGED_CERTUS_QUARTZ
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, NEUTRON_REFLECTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.COPPER_INGOT, SlimefunItems.CARBON, SlimefunItems.COPPER_INGOT,
                SlimefunItems.CARBON, SlimefunItems.REINFORCED_PLATE, SlimefunItems.CARBON,
                SlimefunItems.COPPER_INGOT, SlimefunItems.CARBON, SlimefunItems.COPPER_INGOT
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, MT_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.WITHER_PROOF_GLASS, NEUTRON_REFLECTOR, SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_GLASS, U.mat(Material.NETHER_STAR), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_GLASS, NEUTRON_REFLECTOR, SlimefunItems.WITHER_PROOF_GLASS
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, QUANTUM_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                ENRICHED_SUNNARIUM_ALLOY,
                U.mat(Material.NETHER_STAR),
                ENRICHED_SUNNARIUM_ALLOY,
                U.mat(Material.NETHER_STAR),
                MT_CORE,
                U.mat(Material.NETHER_STAR),
                ENRICHED_SUNNARIUM_ALLOY,
                U.mat(Material.NETHER_STAR),
                ENRICHED_SUNNARIUM_ALLOY
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, VECTOR_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SUPER_ELECTRO_MAGNET, VECTOR_THRUSTER, SUPER_ELECTRO_MAGNET,
                VECTOR_THRUSTER, LAMA_POWER_CRYSTAL, VECTOR_THRUSTER,
                IRIDIUM, IRIDIUM, IRIDIUM
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, SUPER_MAGNET, MZER, new ItemStack[]{SlimefunItems.MAGNET})).register(Laboratory.instance);

        (new SlimefunItem(c, SUPER_ELECTRO_MAGNET, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.NICKEL_INGOT, SUPER_MAGNET, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, SUPER_CIRCUIT_BOARD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SOLDER_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SOLDER_INGOT,
                SlimefunItems.REDSTONE_ALLOY, SUPER_ELECTRO_MAGNET, SlimefunItems.REDSTONE_ALLOY,
                SlimefunItems.SOLDER_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SOLDER_INGOT
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, ULTIMATE_CIRCUIT_BOARD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.FERROSILICON,
                SUPER_CIRCUIT_BOARD,
                SlimefunItems.FERROSILICON,
                REINFORCED_SILICON_ALLOY,
                SUPER_ELECTRO_MAGNET,
                REINFORCED_SILICON_ALLOY,
                SlimefunItems.FERROSILICON,
                SUPER_CIRCUIT_BOARD,
                SlimefunItems.FERROSILICON
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, MAGNETIZED_REINFORCED_PLATE, MZER, new ItemStack[]{SlimefunItems.REINFORCED_PLATE}))
                .register(Laboratory.instance);

        (new SlimefunItem(c, SUPER_HEATING_COIL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HEATING_COIL,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.SILVER_INGOT,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.HEATING_COIL
        }))
                .register(Laboratory.instance);

        (new RadioactiveAlloyIngot(cr, Radioactivity.HIGH, SPEED_INGOT, new ItemStack[]{
                SlimefunItems.CARBONADO,
                SlimefunItems.BLISTERING_INGOT_3,
                SlimefunItems.BLISTERING_INGOT_3,
                SlimefunItems.SILVER_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, SPECTRUM_FILTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_GLASS,
                SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.HARDENED_GLASS,
                REINFORCED_IRIDIUM_PLATE,
                SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.HARDENED_GLASS,
                SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.WITHER_PROOF_GLASS
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, OPTICAL_COMPONENT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                SPECTRUM_FILTER,
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                IRIDIUM,
                U.mat(Material.BEACON),
                IRIDIUM,
                SUPER_CIRCUIT_BOARD,
                SlimefunItems.POWER_CRYSTAL,
                SUPER_CIRCUIT_BOARD
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, REDSTONE_ELEMENT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SILVER_INGOT,
                U.mat(Material.REDSTONE),
                SlimefunItems.REDSTONE_ALLOY,
                U.mat(Material.REDSTONE),
                SlimefunItems.COPPER_INGOT,
                U.mat(Material.REDSTONE),
                SlimefunItems.REDSTONE_ALLOY,
                U.mat(Material.REDSTONE),
                SlimefunItems.SILVER_INGOT
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, HEAT_CONDUCTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                RUBBER_BALL,
                RUBBER_BALL,
                RUBBER_BALL,
                SlimefunItems.COPPER_INGOT,
                SlimefunItems.COPPER_INGOT,
                SlimefunItems.COPPER_INGOT,
                RUBBER_BALL,
                RUBBER_BALL,
                RUBBER_BALL
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, SUNNARIUM_PIECE, MT, U.midr(U.mat(Material.GLOWSTONE_DUST)))).register(Laboratory.instance);
        (new SlimefunItem(cm, SUNNARIUM, MT, U.midr(U.mat(Material.GLOWSTONE)))).register(Laboratory.instance);
        (new SlimefunItem(cm, SUNNARIUM_ALLOY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                IRIDIUM, IRIDIUM, IRIDIUM,
                IRIDIUM, SUNNARIUM, IRIDIUM,
                IRIDIUM, IRIDIUM, IRIDIUM
        }))
                .register(Laboratory.instance);
        (new RadioactiveItem(
                cr, Radioactivity.HIGH, IRRADIANT_URANIUM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                U.mat(Material.GLOWSTONE_DUST), U.mat(Material.GLOWSTONE_DUST),
                U.mat(Material.GLOWSTONE_DUST),
                U.mat(Material.GLOWSTONE_DUST), SlimefunItems.URANIUM, U.mat(Material.GLOWSTONE_DUST),
                U.mat(Material.GLOWSTONE_DUST), U.mat(Material.GLOWSTONE_DUST),
                U.mat(Material.GLOWSTONE_DUST)
        }))
                .register(Laboratory.instance);
        (new RadioactiveItem(
                cm, Radioactivity.HIGH, ENRICHED_SUNNARIUM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                IRRADIANT_URANIUM, IRRADIANT_URANIUM, IRRADIANT_URANIUM,
                IRRADIANT_URANIUM, SUNNARIUM, IRRADIANT_URANIUM,
                IRRADIANT_URANIUM, IRRADIANT_URANIUM, IRRADIANT_URANIUM
        }))
                .register(Laboratory.instance);
        (new RadioactiveItem(
                cm,
                Radioactivity.HIGH,
                ENRICHED_SUNNARIUM_ALLOY,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null,
                        ENRICHED_SUNNARIUM,
                        ENRICHED_SUNNARIUM,
                        SUNNARIUM_ALLOY,
                        ENRICHED_SUNNARIUM,
                        ENRICHED_SUNNARIUM
                }))
                .register(Laboratory.instance);
        (new SlimefunItem(cm, IRRADIANT_GLASS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HARDENED_GLASS,
                U.mat(Material.GLASS),
                SlimefunItems.HARDENED_GLASS,
                IRRADIANT_URANIUM,
                U.mat(Material.GLOWSTONE_DUST),
                IRRADIANT_URANIUM,
                SlimefunItems.HARDENED_GLASS,
                U.mat(Material.GLASS),
                SlimefunItems.HARDENED_GLASS
        }))
                .register(Laboratory.instance);
        (new SlimefunItem(cm, IRIDIUM_IRON_PLATE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                U.mat(Material.IRON_INGOT), U.mat(Material.IRON_INGOT), U.mat(Material.IRON_INGOT),
                U.mat(Material.IRON_INGOT), IRIDIUM, U.mat(Material.IRON_INGOT),
                U.mat(Material.IRON_INGOT), U.mat(Material.IRON_INGOT), U.mat(Material.IRON_INGOT)
        }))
                .register(Laboratory.instance);
        (new SlimefunItem(cm, REINFORCED_IRIDIUM_IRON_PLATE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBON_CHUNK,
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.CARBON_CHUNK, IRIDIUM_IRON_PLATE, SlimefunItems.CARBON_CHUNK,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBON_CHUNK,
                SlimefunItems.REINFORCED_ALLOY_INGOT
        }))
                .register(Laboratory.instance);
        (new SlimefunItem(
                cm,
                IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        U.mat(Material.REDSTONE), SUNNARIUM_PIECE, U.mat(Material.REDSTONE),
                        U.mat(Material.LAPIS_BLOCK), REINFORCED_IRIDIUM_IRON_PLATE, U.mat(Material.LAPIS_BLOCK),
                        U.mat(Material.REDSTONE), SlimefunItems.SYNTHETIC_DIAMOND, U.mat(Material.REDSTONE)
                }))
                .register(Laboratory.instance);

        ItemStack rtg_core = IRRADIANT_URANIUM;
        if (Variables.plug.getConfig().getBoolean("items.rtg.easy-recipe")) {
            rtg_core = U.mat(Material.IRON_INGOT);
        }
        (new SlimefunItem(cm, PELLETS_OF_RTG_FUEL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.BOOSTED_URANIUM,
                SlimefunItems.BOOSTED_URANIUM,
                SlimefunItems.BOOSTED_URANIUM,
                LEAD_IRON_ALLOY,
                rtg_core,
                LEAD_IRON_ALLOY,
                SlimefunItems.BOOSTED_URANIUM,
                SlimefunItems.BOOSTED_URANIUM,
                SlimefunItems.BOOSTED_URANIUM
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, RUSTY_MECHANICAL_PARTS, RecipeType.NULL, new ItemStack[0])).register(Laboratory.instance);

        (new SlimefunItem(c, MV_TRANSFORMER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                RUBBER_BALL,
                SlimefunItems.COPPER_INGOT,
                RUBBER_BALL,
                SlimefunItems.BILLON_INGOT,
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                SlimefunItems.BILLON_INGOT,
                RUBBER_BALL,
                SlimefunItems.COPPER_INGOT,
                RUBBER_BALL
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, HV_TRANSFORMER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null,
                U.mat(Material.GOLD_INGOT),
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                MV_TRANSFORMER,
                SlimefunItems.BATTERY,
                U.mat(Material.GOLD_INGOT)
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, EV_TRANSFORMER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null,
                SlimefunItems.STEEL_INGOT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                HV_TRANSFORMER,
                SlimefunItems.POWER_CRYSTAL,
                SlimefunItems.STEEL_INGOT
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(c, ARC_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ALUMINUM_BRASS_INGOT, EV_TRANSFORMER, SlimefunItems.ALUMINUM_BRASS_INGOT,
                Resource.GRAPHITE, U.mat(Material.GLASS), Resource.GRAPHITE,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, EV_TRANSFORMER, SlimefunItems.ADVANCED_CIRCUIT_BOARD
        }))
                .register(Laboratory.instance);

        (new SlimefunItem(cm, PLASMA_CELL, PLASMAG, U.midr(U.mat(Material.GLASS_BOTTLE)))).register(Laboratory.instance);

        (new SlimefunItem(c, PLASMA_BALL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                U.mat(Material.GLASS), U.mat(Material.GLASS), U.mat(Material.GLASS),
                U.mat(Material.GLASS), PLASMA_CELL, U.mat(Material.GLASS),
                U.mat(Material.GLASS), U.mat(Material.GLASS), U.mat(Material.GLASS)
        }))
                .register(Laboratory.instance);

        (new RadioactiveItem(cr, Radioactivity.VERY_HIGH, RADIUM, MT, U.midr(SlimefunItems.PLUTONIUM))).register(Laboratory.instance);

        (new RadioactiveItem(cr, Radioactivity.VERY_HIGH, IRRADIANT_RADIUM, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SUNNARIUM_PIECE, SUNNARIUM_PIECE, SUNNARIUM_PIECE,
                SUNNARIUM_PIECE, RADIUM, SUNNARIUM_PIECE,
                SUNNARIUM_PIECE, SUNNARIUM_PIECE, SUNNARIUM_PIECE
        }))
                .register(Laboratory.instance);

        if (Variables.plug.getConfig().getBoolean("items.circuit.new-recipe")) {
            SlimefunItem.getById("BASIC_CIRCUIT_BOARD").setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE);
            SlimefunItem.getById("BASIC_CIRCUIT_BOARD").setRecipe(new ItemStack[]{
                    RUBBER_BALL,
                    U.mat(Material.REDSTONE),
                    RUBBER_BALL,
                    SlimefunItems.COPPER_INGOT,
                    REDSTONE_ELEMENT,
                    SlimefunItems.COPPER_INGOT,
                    RUBBER_BALL,
                    U.mat(Material.REDSTONE),
                    RUBBER_BALL
            });

            SlimefunItem.getById("ADVANCED_CIRCUIT_BOARD").setRecipe(new ItemStack[]{
                    SlimefunItems.BILLON_INGOT, REDSTONE_ELEMENT, SlimefunItems.BILLON_INGOT,
                    SlimefunItems.SYNTHETIC_EMERALD, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.SYNTHETIC_EMERALD,
                    SlimefunItems.BILLON_INGOT, REDSTONE_ELEMENT, SlimefunItems.BILLON_INGOT
            });
        }

        if (Variables.plug.getConfig().getBoolean("items.solar-generator.new-recipe")) {

            SlimefunItem.getById("SOLAR_GENERATOR").setRecipe(new ItemStack[]{
                    U.mat(Material.GLASS),
                    U.mat(Material.GLASS),
                    U.mat(Material.GLASS),
                    SlimefunItems.COMPRESSED_CARBON,
                    REINFORCED_SILICON_ALLOY,
                    SlimefunItems.COMPRESSED_CARBON,
                    SlimefunItems.BASIC_CIRCUIT_BOARD,
                    SlimefunItems.COAL_GENERATOR,
                    SlimefunItems.BASIC_CIRCUIT_BOARD
            });
            SlimefunItem.getById("SOLAR_GENERATOR_2").setRecipe(new ItemStack[]{
                    IRRADIANT_GLASS,
                    IRRADIANT_GLASS,
                    IRRADIANT_GLASS,
                    SlimefunItems.REINFORCED_ALLOY_INGOT,
                    SlimefunItem.getById("SOLAR_GENERATOR").getItem(),
                    SlimefunItems.REINFORCED_ALLOY_INGOT,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD
            });
            SlimefunItem.getById("SOLAR_GENERATOR_3").setRecipe(new ItemStack[]{
                    REINFORCED_SILICON_ALLOY,
                    U.mat(Material.LAPIS_BLOCK),
                    REINFORCED_SILICON_ALLOY,
                    SlimefunItems.CARBONADO,
                    SlimefunItem.getById("SOLAR_GENERATOR_2").getItem(),
                    SlimefunItems.CARBONADO,
                    ENRICHED_SUNNARIUM_ALLOY,
                    SlimefunItems.CARBONADO,
                    ENRICHED_SUNNARIUM_ALLOY
            });
            SlimefunItem.getById("SOLAR_GENERATOR_4").setRecipe(new ItemStack[]{
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    SlimefunItem.getById("SOLAR_GENERATOR_3").getItem(),
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    SlimefunItem.getById("SOLAR_GENERATOR_3").getItem(),
                    QUANTUM_CORE,
                    SlimefunItem.getById("SOLAR_GENERATOR_3").getItem(),
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    SlimefunItem.getById("SOLAR_GENERATOR_3").getItem(),
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD
            });
        }

        if (Variables.cfg.getBoolean("items.hazmat-suit.new-recipe")) {
            SlimefunItem.getById("SCUBA_HELMET").setRecipe(new ItemStack[]{
                    null,
                    new CustomItemStack(Material.RED_DYE),
                    null,
                    RUBBER_BALL,
                    U.mat(Material.GLASS),
                    RUBBER_BALL,
                    RUBBER_BALL,
                    U.mat(Material.IRON_BARS),
                    RUBBER_BALL
            });
            SlimefunItem.getById("HAZMAT_CHESTPLATE").setRecipe(new ItemStack[]{
                    RUBBER_BALL,
                    null,
                    RUBBER_BALL,
                    RUBBER_BALL,
                    new CustomItemStack(Material.RED_DYE),
                    RUBBER_BALL,
                    RUBBER_BALL,
                    RUBBER_BALL,
                    RUBBER_BALL
            });
            SlimefunItem.getById("HAZMAT_LEGGINGS").setRecipe(new ItemStack[]{
                    RUBBER_BALL, new CustomItemStack(Material.RED_DYE), RUBBER_BALL,
                    RUBBER_BALL, null, RUBBER_BALL,
                    RUBBER_BALL, null, RUBBER_BALL
            });
            SlimefunItem.getById("RUBBER_BOOTS").setRecipe(new ItemStack[]{
                    RUBBER_BALL, null, RUBBER_BALL,
                    RUBBER_BALL, null, RUBBER_BALL,
                    RUBBER_BALL, U.mat(Material.WHITE_WOOL), RUBBER_BALL
            });
        }

        IRIDIUM_CAPACITY = new SlimefunItemStack("LAB_IRIDIUM_CAPACITY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="),
                        "&a铱电容器",
                        "",
                        "&4终极电容器",
                        "&8⇨ &e⚡ &71048576 J 容量"));
        (new Capacitor(lockedCategory, 1048576, IRIDIUM_CAPACITY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                REINFORCED_IRIDIUM_PLATE,
                SlimefunItems.REDSTONE_ALLOY,
                REINFORCED_IRIDIUM_PLATE,
                U.mat(Material.REDSTONE),
                SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                U.mat(Material.REDSTONE),
                REINFORCED_IRIDIUM_PLATE,
                SlimefunItems.REDSTONE_ALLOY,
                REINFORCED_IRIDIUM_PLATE
        }))
                .register(Laboratory.instance);

        CREATOR_GENERATOR = new SlimefunItemStack("LAB_CREATOR_GENERATOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5ZjM1NmY1ZmU3ZDFiYzkyY2RkZmFlYmEzZWU3NzNhYzlkZjFjYzRkMWMyZjhmZTVmNDcwMTMwMzJjNTUxZCJ9fX0="),
                        "&6创世神发电机",
                        "",
                        "&f被红石激活时发电",
                        "",
                        "&5创造发电机",
                        "&8⇨ &e⚡ &78192 J/s"));

        (new CreatorGenerator(lockedCategory, CREATOR_GENERATOR, Variables.Non, new ItemStack[0]) {
            @Override
            public int getGeneratedOutput(
                    @NotNull Location l, @NotNull me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                int energy = 0;
                if (l.getBlock().getBlockPower() > 0) energy = 4096;
                return energy;
            }

            @NotNull
            @Override
            public ItemStack getProgressBar() {
                return new ItemStack(Material.FLINT_AND_STEEL);
            }
        })
                .register(Laboratory.instance);

        CARBONADO_EGDED_COAL_GENERATOR = new SlimefunItemStack("LAB_CARBONADO_EGDED_COAL_GENERATOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlkYzQ4YmE1MzI2YTQwNzhkNzMxY2IwMDQ0MWU2MmJhOTQwMjQwMGMwZDE3NWI5YWM3MzRkMmQ1MmNmMjMyOSJ9fX0="),
                        "&c进阶煤炭发电机",
                        "",
                        "&6高级发电机",
                        "&8⇨ &e⚡ &7256 J 缓存",
                        "&8⇨ &e⚡ &748 J/s"));

        (new AGenerator(
                lockedCategory,
                CARBONADO_EGDED_COAL_GENERATOR,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SUPER_HEATING_COIL,
                        SlimefunItems.ENHANCED_FURNACE_4,
                        SUPER_HEATING_COIL,
                        SlimefunItems.CARBONADO,
                        SlimefunItems.ELECTRIC_MOTOR,
                        SlimefunItems.CARBONADO,
                        ALUMAG_ALLOY,
                        SlimefunItems.CARBONADO,
                        ALUMAG_ALLOY
                }) {
            public void registerDefaultFuelTypes() {
                registerFuel(new MachineFuel(40, new ItemStack(Material.COAL_BLOCK)));
                registerFuel(new MachineFuel(8, new ItemStack(Material.BLAZE_ROD)));
            }

            public ItemStack getProgressBar() {
                return new ItemStack(Material.FLINT_AND_STEEL);
            }

            public String getInventoryTitle() {
                return Tech.CARBONADO_EGDED_COAL_GENERATOR.getItemMeta().getDisplayName();
            }

            public int getEnergyProduction() {
                return 24;
            }

            public int getCapacity() {
                return 1024;
            }
        })
                .register(Laboratory.instance);

        EGG_GENERATOR = new SlimefunItemStack("LAB_EGG_GENERATOR",
                new CustomItemStack(
                        Material.OBSIDIAN,
                        "&d龙蛋发电机",
                        "",
                        "&f将龙蛋中逸散出的末影幽能转化为焦尔",
                        "&f发电时将龙蛋置于发电机上方",
                        "&f发电过程不消耗龙蛋",
                        "",
                        "&4终级发电机",
                        "&8⇨ &e⚡ &7768 J/s"));

        new AGenerator(lockedCategory, EGG_GENERATOR, NON, new ItemStack[0]) {
            @NotNull
            @Override
            public ItemStack getProgressBar() {
                return new ItemStack(Material.FLINT_AND_STEEL);
            }

            @Override
            protected void registerDefaultFuelTypes() {
            }

            public int getGeneratedOutput(Location loc, SlimefunBlockData data) {
                int rt = 0;

                Location locup = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() + 1), loc.getBlockZ());

                Location locupmid =
                        new Location(locup.getWorld(), locup.getX() + 0.5D, locup.getY() + 0.5D, locup.getZ() + 0.5D);

                if (locup.getBlock().getType().equals(Material.DRAGON_EGG)) {
                    rt = Variables.cfg.getInt("items.egg-generator.energy-production");
                    try {
                        locupmid.getWorld().spawnParticle(Particle.END_ROD, locupmid, 5);
                        locupmid.getWorld().spawnParticle(Particle.DRAGON_BREATH, locupmid, 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return rt;
            }

            public int getCapacity() {
                return 1024;
            }

            public int getEnergyProduction() {
                return 768;
            }
        }.register(Laboratory.instance);

        ENDER_GENERATOR = new SlimefunItemStack("LAB_ENDER_GENERATOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM4ZmZlN2Q2MDdkMTBiYzk3MTJkY2IxOWU4YjVkZjFlNTlkNGQ3MWQ1N2NlOTNlYWRiYTFhYzc2NmI3MTA2ZSJ9fX0="),
                        "&5末影发电机",
                        "",
                        "&a中级发电机",
                        "&8⇨ &e⚡ &764 J 缓存",
                        "&8⇨ &e⚡ &74 J/s"));

        (new AGenerator(lockedCategory, ENDER_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SUPER_HEATING_COIL,
                ALUMAG_ALLOY,
                SUPER_HEATING_COIL,
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                CERTUS_QUARTZ,
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                ALUMAG_ALLOY,
                SlimefunItems.SMALL_CAPACITOR,
                ALUMAG_ALLOY
        }) {
            public int getEnergyProduction() {
                return 2;
            }

            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public @NotNull ItemStack getProgressBar() {
                return U.mat(Material.END_PORTAL_FRAME);
            }

            public void registerDefaultFuelTypes() {
                registerFuel(new MachineFuel(1, U.mat(Material.ENDER_PEARL)));
                registerFuel(new MachineFuel(2, U.mat(Material.ENDER_EYE)));
            }

            @Override
            public int getCapacity() {
                return 64;
            }
        })
                .register(Laboratory.instance);

        RADIOISOTOPE_THERMOELECTRIC_GENERATOR = new SlimefunItemStack("LAB_RADIOISOTOPE_THERMOELECTRIC_GENERATOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQyZTE1ZDExZWUzZWIyMDQwNDVhZDNjY2IxOWNiODk3NmFmNThjY2E2NjY1OWZiOGQ5NzQzMTYwOGM3MTBlYSJ9fX0="),
                        "&2放射性同位素温差发电机",
                        "",
                        "&r放入放射性同位素燃料靶丸来发电",
                        "&r靶丸越多发电效率越高",
                        "&r发电时不消耗靶丸",
                        "",
                        "&6发电机组",
                        "&8⇨ &e⚡ &764 J 缓存"));

        (new RadioisotopeThermoelectricGenerator(
                lockedCategory,
                RADIOISOTOPE_THERMOELECTRIC_GENERATOR,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        LEAD_IRON_ALLOY, LEAD_IRON_ALLOY, LEAD_IRON_ALLOY,
                        SlimefunItems.HARDENED_GLASS, U.mat(Material.CHEST), SlimefunItems.HARDENED_GLASS,
                        SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COAL_GENERATOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD
                }) {
            @Override
            protected void registerDefaultFuelTypes() {
            }

            public int getCapacity() {
                return 764;
            }

            public int getEnergyProduction() {
                return 64;
            }
        }).register(Laboratory.instance);

        REDSTONE_GENERATOR = new SlimefunItemStack("LAB_REDSTONE_GENERATOR",
                new CustomItemStack(
                        Material.RED_TERRACOTTA, "&c红石发电机", "", "&a中级发电机", "&8⇨ &e⚡ &7128 J 缓存", "&8⇨ &e⚡ &78 J/s"));

        (new AGenerator(lockedCategory, REDSTONE_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HEATING_COIL, SlimefunItems.HEATING_COIL, SlimefunItems.HEATING_COIL,
                SlimefunItems.BASIC_CIRCUIT_BOARD, CERTUS_QUARTZ, SlimefunItems.BASIC_CIRCUIT_BOARD,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.ELECTRIC_MOTOR
        }) {
            public int getEnergyProduction() {
                return 4;
            }

            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public @NotNull ItemStack getProgressBar() {
                return U.mat(Material.COMPARATOR);
            }

            public void registerDefaultFuelTypes() {
                registerFuel(new MachineFuel(1, U.mat(Material.REDSTONE)));
                registerFuel(new MachineFuel(9, U.mat(Material.REDSTONE_BLOCK)));
            }

            @Override
            public int getCapacity() {
                return 128;
            }
        })
                .register(Laboratory.instance);

        ADVANCED_BIO_REACTOR = new SlimefunItemStack("LAB_ADVANCED_BIO_REACTOR",
                new CustomItemStack(
                        Material.RED_TERRACOTTA, "&2进阶生化反应器", "", "&6发电机组", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &724 J/s"));

        (new AGenerator(lockedCategory, ADVANCED_BIO_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HEATING_COIL,
                SlimefunItems.BIO_REACTOR,
                SlimefunItems.HEATING_COIL,
                ALUMAG_INGOT,
                SlimefunItems.ELECTRIC_MOTOR,
                ALUMAG_INGOT,
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                SlimefunItems.STEEL_INGOT,
                SlimefunItems.BASIC_CIRCUIT_BOARD
        }) {
            public void registerDefaultFuelTypes() {
                registerFuel(new MachineFuel(2, new ItemStack(Material.BONE)));
                registerFuel(new MachineFuel(18, new ItemStack(Material.BONE_BLOCK)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.APPLE)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.MELON)));
                registerFuel(new MachineFuel(27, new ItemStack(Material.MELON_SLICE)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.PUMPKIN)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.WHEAT)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.CARROT)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.POTATO)));
                registerFuel(new MachineFuel(3, new ItemStack(Material.NETHER_WART)));
                registerFuel(new MachineFuel(2, new ItemStack(Material.RED_MUSHROOM)));
                registerFuel(new MachineFuel(2, new ItemStack(Material.BROWN_MUSHROOM)));
                registerFuel(new MachineFuel(1, new ItemStack(Material.OAK_LEAVES)));
                registerFuel(new MachineFuel(1, new ItemStack(Material.SPRUCE_LEAVES)));
                registerFuel(new MachineFuel(1, new ItemStack(Material.BIRCH_LEAVES)));
                registerFuel(new MachineFuel(1, new ItemStack(Material.JUNGLE_LEAVES)));
                registerFuel(new MachineFuel(1, new ItemStack(Material.ACACIA_LEAVES)));
                registerFuel(new MachineFuel(1, new ItemStack(Material.DARK_OAK_LEAVES)));
            }

            public @NotNull ItemStack getProgressBar() {
                return new ItemStack(Material.GOLDEN_HOE);
            }

            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public int getEnergyProduction() {
                return 12;
            }

            public int getCapacity() {
                return 256;
            }
        })
                .register(Laboratory.instance);

        ALLOY_REACTOR = new SlimefunItemStack("LAB_ALLOY_REACTOR",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM4ZDY3ZDdiODViODlhODlhNWFkNGIxNjhlYzY3ZGJkZGIxZTU4YzY0OGFjYjFkMmQ2MDJjZGUzZDlmYjgyIn19fQ=="),
                        "&6先祖洪炉",
                        "",
                        "&4终级发电机",
                        "&8⇨ &e⚡ &716384 J 缓存",
                        "&8⇨ &e⚡ &7512 J/s"));

        (new AGenerator(lockedCategory, ALLOY_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.FLUID_PUMP,
                SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.LAVA_GENERATOR,
                SlimefunItems.NUCLEAR_REACTOR,
                SlimefunItems.ANDROID_INTERFACE_ITEMS,
                SUPER_CIRCUIT_BOARD,
                SlimefunItems.LARGE_CAPACITOR,
                SUPER_CIRCUIT_BOARD
        }) {
            public void registerDefaultFuelTypes() {
                registerFuel(new MachineFuel(60, SlimefunItems.BLISTERING_INGOT));
                registerFuel(new MachineFuel(45, SlimefunItems.HARDENED_METAL_INGOT));
                registerFuel(new MachineFuel(240, SlimefunItems.REINFORCED_ALLOY_INGOT));
                registerFuel(new MachineFuel(40, Tech.CERTUS_QUARTZ));
                registerFuel(new MachineFuel(240, Tech.CHARGED_CERTUS_QUARTZ));
                registerFuel(new MachineFuel(2400, Tech.SPEED_INGOT));
            }

            public @NotNull ItemStack getProgressBar() {
                try {
                    return SkullUtil.getByBase64(
                            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ==");
                } catch (Exception e) {
                    Laboratory.debugException(e);
                    return U.mat(Material.FIRE);
                }
            }

            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public int getEnergyProduction() {
                return 512;
            }

            public int getCapacity() {
                return 16384;
            }
        })
                .register(Laboratory.instance);

        MAGNETIZER_1 = new SlimefunItemStack("LAB_MAGNETIZER_1",
                new CustomItemStack(
                        Material.IRON_BLOCK, "&b磁化机 &7- &eI", "", "&6高级机器", "&8⇨ &7速度: 1x", "&8⇨ &e⚡ &764 J/s"));

        (new Magnetizer(lockedCategory, MAGNETIZER_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HEATING_COIL, SlimefunItems.REDSTONE_ALLOY,
                SlimefunItems.REDSTONE_ALLOY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REDSTONE_ALLOY
        }) {

            public int getEnergyConsumption() {
                return 64;
            }

            public int getSpeed() {
                return 1;
            }

            public int getCapacity() {
                return 64;
            }
        })
                .register(Laboratory.instance);

        MAGNETIZER_2 = new SlimefunItemStack("LAB_MAGNETIZER_2",
                new CustomItemStack(
                        Material.IRON_BLOCK, "&b磁化机 &7- &eII", "", "&6高级机器", "&8⇨ &7速度: 3x", "&8⇨ &e⚡ &764 J/s"));

        (new Magnetizer(lockedCategory, MAGNETIZER_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SUPER_ELECTRO_MAGNET,
                SUPER_ELECTRO_MAGNET,
                SUPER_ELECTRO_MAGNET,
                SlimefunItems.REDSTONE_ALLOY,
                SlimefunItems.HEATING_COIL,
                SlimefunItems.REDSTONE_ALLOY,
                MAGNETIZED_REINFORCED_PLATE,
                MAGNETIZER_1,
                MAGNETIZED_REINFORCED_PLATE
        }) {

            public int getEnergyConsumption() {
                return 64;
            }

            public int getSpeed() {
                return 3;
            }

            public int getCapacity() {
                return 64;
            }
        })
                .register(Laboratory.instance);

        CONVERTER_1 = new SlimefunItemStack("LAB_CONVERTER_1",
                new CustomItemStack(
                        Material.BEACON,
                        "&b分子重组仪 &7- &eI",
                        "",
                        "&f将高能粒子射线发射到物质中",
                        "&f使物质的分子根据程序重新组合",
                        "&f从而完成对物质的转换",
                        "",
                        "&4终级机器",
                        "&8⇨ &e⚡ &72048 J 缓存",
                        "&8⇨ &7速度: 1x",
                        "&8⇨ &e⚡ &764 J/s"));

        (new Converter(lockedCategory, CONVERTER_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                ALUMAG_ALLOY,
                MAGNETIZED_REINFORCED_PLATE,
                ALUMAG_ALLOY,
                SlimefunItems.WITHER_PROOF_OBSIDIAN,
                MT_CORE,
                SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SUPER_CIRCUIT_BOARD,
                SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                SUPER_CIRCUIT_BOARD
        }) {

            public int getEnergyConsumption() {
                return 32;
            }

            public int getSpeed() {
                return 1;
            }

            @Override
            public int getCapacity() {
                return 2048;
            }
        })
                .register(Laboratory.instance);

        CONVERTER_2 = new SlimefunItemStack("LAB_CONVERTER_2",
                new CustomItemStack(
                        Material.BEACON,
                        "&b分子重组仪 &7- &eII",
                        "",
                        "&f将高能粒子射线发射到物质中",
                        "&f使物质的分子根据程序重新组合",
                        "&f从而完成对物质的转换",
                        "",
                        "&4终级机器",
                        "&8⇨ &e⚡ &72048 J 缓存",
                        "&8⇨ &7速度: 2x",
                        "&8⇨ &e⚡ &7128 J/s"));

        (new Converter(lockedCategory, CONVERTER_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                OPTICAL_COMPONENT,
                SlimefunItems.BLISTERING_INGOT_3,
                OPTICAL_COMPONENT,
                SlimefunItems.WITHER_PROOF_OBSIDIAN,
                CONVERTER_1,
                SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SUPER_CIRCUIT_BOARD,
                SlimefunItems.BLISTERING_INGOT_3,
                SUPER_CIRCUIT_BOARD
        }) {

            public int getEnergyConsumption() {
                return 64;
            }

            public int getSpeed() {
                return 2;
            }

            @Override
            public int getCapacity() {
                return 2048;
            }
        })
                .register(Laboratory.instance);

        CONVERTER_3 = new SlimefunItemStack("LAB_CONVERTER_3",
                new CustomItemStack(
                        Material.BEACON,
                        "&b分子重组仪 &7- &eIII",
                        "",
                        "&f将高能粒子射线发射到物质中",
                        "&f使物质的分子根据程序重新组合",
                        "&f从而完成对物质的转换",
                        "",
                        "&4终级机器",
                        "&8⇨ &e⚡ &72048 J 缓存",
                        "&8⇨ &7速度: 8x",
                        "&8⇨ &e⚡ &7512 J/s"));

        (new Converter(lockedCategory, CONVERTER_3, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SPEED_INGOT, SPEED_INGOT, SPEED_INGOT,
                SUPER_CIRCUIT_BOARD, CONVERTER_2, SUPER_CIRCUIT_BOARD,
                OPTICAL_COMPONENT, SlimefunItems.ELECTRIC_MOTOR, OPTICAL_COMPONENT
        }) {

            public int getEnergyConsumption() {
                return 256;
            }

            public int getSpeed() {
                return 8;
            }

            @Override
            public int getCapacity() {
                return 2048;
            }
        })
                .register(Laboratory.instance);

        CONVERTER_4 = new SlimefunItemStack("LAB_CONVERTER_4",
                new CustomItemStack(
                        Material.BEACON,
                        "&b分子重组仪 &7- &eIV",
                        "",
                        "&f将高能粒子射线发射到物质中",
                        "&f使物质的分子根据程序重新组合",
                        "&f从而完成对物质的转换",
                        "",
                        "&4终级机器",
                        "&8⇨ &e⚡ &72048 J 缓存",
                        "&8⇨ &7速度: 16x",
                        "&8⇨ &e⚡ &71024 J/s"));

        (new Converter(lockedCategory, CONVERTER_4, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                IRIDIUM_BLOCK, IRIDIUM_BLOCK, IRIDIUM_BLOCK,
                SUPER_CIRCUIT_BOARD, CONVERTER_3, SUPER_CIRCUIT_BOARD,
                OPTICAL_COMPONENT, SlimefunItems.ELECTRIC_MOTOR, OPTICAL_COMPONENT
        }) {

            public int getEnergyConsumption() {
                return 512;
            }

            public int getSpeed() {
                return 16;
            }

            public int getCapacity() {
                return 2048;
            }
        })
                .register(Laboratory.instance);

        QUARTZ_DRILL = new SlimefunItemStack("LAB_QUARTZ_DRILL",
                new CustomItemStack(
                        Material.WHITE_STAINED_GLASS,
                        "&f石英钻机",
                        "",
                        "&r用于挖掘赛特斯石英",
                        "",
                        "&4终级机器",
                        "&8⇨ &7速度: 1x",
                        "&8⇨ &e⚡ &764 J/s",
                        "",
                        "&c&l! &c使用前请先扫描想使用的区块"));

        (new QuartzDrill(lockedCategory, QUARTZ_DRILL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.HARDENED_GLASS,
                SlimefunItems.ELECTRIC_INGOT_PULVERIZER,
                SlimefunItems.HARDENED_GLASS,
                SUPER_ELECTRO_MAGNET,
                SlimefunItems.ELECTRIC_MOTOR,
                SUPER_ELECTRO_MAGNET
        }) {
            @Override
            public int getCapacity() {
                return 4096;
            }
        })
                .register(Laboratory.instance);

        if (useSelfIridium) {
            Iridium.instance.register();
            IRIDIUM_DRILL = new SlimefunItemStack("LAB_IRIDIUM_DRILL",
                    new CustomItemStack(
                            Material.IRON_BLOCK,
                            "&b铱钻机",
                            "",
                            "&r用于挖掘铱",
                            "",
                            "&4终级机器",
                            "&8⇨ &7速度: 1x",
                            "&8⇨ &e⚡ &764 J/s",
                            "",
                            "&c&l! &c使用前请先扫描想使用的区块"));
            (new IridiumDrill(lockedCategory, IRIDIUM_DRILL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                    SUPER_ELECTRO_MAGNET,
                    SlimefunItems.REINFORCED_PLATE,
                    SUPER_ELECTRO_MAGNET,
                    SlimefunItems.HARDENED_GLASS,
                    SlimefunItems.ELECTRIC_INGOT_PULVERIZER,
                    SlimefunItems.HARDENED_GLASS,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    SlimefunItems.ELECTRIC_MOTOR,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD,
            }) {
                @Override
                public int getCapacity() {
                    return 4096;
                }
            })
                    .register(Laboratory.instance);
        }

        ADVANCED_CHARGING_BENCH = new SlimefunItemStack("LAB_ADVANCED_CHARGING_BENCH",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWY5YzM2MGVhOGJhNGIxNTJjYjhlZGFlOGMzMzdjYjMxNjIwNDE4ZGQ0MjgwYjNlOWY3NjkwYjVhZWY4MDRjMiJ9fX0="),
                        "&9高级充电架",
                        "",
                        "&r可以为耗电物品充电",
                        "",
                        "&6高级机器",
                        "&8⇨ &e⚡ &7256 J 缓存",
                        "&8⇨ &e⚡ &740 J/s",
                        "&8⇨ &e⚡ &7能量损耗: &c25%"));

        (new AdvancedChargingBench(
                lockedCategory, ADVANCED_CHARGING_BENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null,
                SUPER_ELECTRO_MAGNET,
                SlimefunItems.BATTERY,
                ALUMAG_ALLOY,
                SlimefunItems.BATTERY,
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                SlimefunItems.MEDIUM_CAPACITOR,
                SlimefunItems.BASIC_CIRCUIT_BOARD
        }) {
            @Override
            public int getCapacity() {
                return 256;
            }
        })
                .register(Laboratory.instance);

        ITEM_FREEZER = new SlimefunItemStack("LAB_ITEM_FREEZER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFhY2M0Njg3ZDA4NzJkZmZhMTI0OGM4MzUzNzI5YWMxZTIxZWQzOGE5MDIxNTdiMWY5NmQxZThmNjZhNTAzYyJ9fX0="),
                        "&a冷冻机",
                        "",
                        "&r给一些物品降温",
                        "",
                        "&a中级机器",
                        "&8⇨ &e⚡ &7128 J 缓存",
                        "&8⇨ &e⚡ &724 J/s"));

        (new ItemFreezer(lockedCategory, ITEM_FREEZER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                ALUMAG_ALLOY,
                SlimefunItems.ALUMINUM_INGOT,
                ALUMAG_ALLOY,
                SlimefunItems.ELECTRIC_MOTOR,
                new ItemStack(Material.PACKED_ICE),
                SlimefunItems.ELECTRIC_MOTOR,
                ALUMAG_ALLOY,
                SlimefunItems.SMALL_CAPACITOR,
                ALUMAG_ALLOY
        }) {
            public int getEnergyConsumption() {
                return 12;
            }

            public int getSpeed() {
                return 1;
            }

            @Override
            public int getCapacity() {
                return 128;
            }
        })
                .register(Laboratory.instance);

        ADVANCED_ELECTRIC_INGOT_PULVERIZER = new SlimefunItemStack("LAB_ADVANCED_ELECTRIC_INGOT_PULVERIZER",
                new CustomItemStack(
                        new ItemStack(Material.DROPPER),
                        "&c进阶电力金属锭粉碎机",
                        "",
                        "&f将金属锭粉碎成金属粉",
                        "",
                        "&6高级机器",
                        "&8⇨ &7速度: 3x",
                        "&8⇨ &e⚡ &7256 J 缓存",
                        "&8⇨ &e⚡ &742 J/s"));

        (new AdvancedAContainer(
                lockedCategory,
                ADVANCED_ELECTRIC_INGOT_PULVERIZER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_INGOT_PULVERIZER,
                        SlimefunItems.BASIC_CIRCUIT_BOARD,
                        SlimefunItems.STEEL_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.STEEL_INGOT,
                        SlimefunItems.STEEL_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.STEEL_INGOT
                }) {
            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public ItemStack getProgressBar() {
                return new ItemStack(Material.DIAMOND_PICKAXE);
            }

            public void registerDefaultRecipes() {
                registerRecipe(3, new ItemStack[]{SlimefunItems.ALUMINUM_INGOT}, new ItemStack[]{
                        SlimefunItems.ALUMINUM_DUST
                });
                registerRecipe(3, new ItemStack[]{SlimefunItems.COPPER_INGOT}, new ItemStack[]{
                        SlimefunItems.COPPER_DUST
                });
                registerRecipe(
                        3, new ItemStack[]{SlimefunItems.GOLD_4K}, new ItemStack[]{SlimefunItems.GOLD_DUST});
                registerRecipe(3, new ItemStack[]{new ItemStack(Material.IRON_INGOT)}, new ItemStack[]{
                        SlimefunItems.IRON_DUST
                });
                registerRecipe(
                        3, new ItemStack[]{SlimefunItems.LEAD_INGOT}, new ItemStack[]{SlimefunItems.LEAD_DUST
                        });
                registerRecipe(3, new ItemStack[]{SlimefunItems.MAGNESIUM_INGOT}, new ItemStack[]{
                        SlimefunItems.MAGNESIUM_DUST
                });
                registerRecipe(3, new ItemStack[]{SlimefunItems.SILVER_INGOT}, new ItemStack[]{
                        SlimefunItems.SILVER_DUST
                });
                registerRecipe(
                        3, new ItemStack[]{SlimefunItems.TIN_INGOT}, new ItemStack[]{SlimefunItems.TIN_DUST});
                registerRecipe(
                        3, new ItemStack[]{SlimefunItems.ZINC_INGOT}, new ItemStack[]{SlimefunItems.ZINC_DUST
                        });
            }

            public int getEnergyConsumption() {
                return 21;
            }

            public int getSpeed() {
                return 3;
            }

            public @NotNull String getMachineIdentifier() {
                return getId();
            }

            public int getCapacity() {
                return 256;
            }
        })
                .register(Laboratory.instance);

        ELECTROLYZER_1 = new SlimefunItemStack("LAB_ELECTROLYZER_1",
                new CustomItemStack(
                        Material.IRON_BLOCK,
                        "&b电解机 &7- &eI",
                        "",
                        "&a中级机器",
                        "&8⇨ &7速度: 1x",
                        "&8⇨ &e⚡ &7256 J 缓存",
                        "&8⇨ &e⚡ &732 J/s"));

        (new AdvancedAContainer(lockedCategory, ELECTROLYZER_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REDSTONE_ALLOY,
                U.mat(Material.GLASS),
                SlimefunItems.REDSTONE_ALLOY,
                ALUMAG_ALLOY,
                MAGNETIZER_1,
                ALUMAG_ALLOY,
                SlimefunItems.BASIC_CIRCUIT_BOARD,
                Resource.GRAPHITE,
                SlimefunItems.BASIC_CIRCUIT_BOARD
        }) {

            public void registerDefaultRecipes() {
                int time = Variables.cfg.getInt("items.electrolyzer.time");

                for (ItemStack[] iti : Variables.electrolyzer_r.keySet()) {
                    registerRecipe(time, iti, Variables.electrolyzer_r.get(iti));
                }
            }

            public int getSpeed() {
                return 1;
            }

            public ItemStack getProgressBar() {
                return SlimefunItems.MAGNET;
            }

            public @NotNull String getMachineIdentifier() {
                return getId();
            }

            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public int getEnergyConsumption() {
                return 16;
            }

            @Override
            public int getCapacity() {
                return 256;
            }
        })
                .register(Laboratory.instance);

        ELECTROLYZER_2 = new SlimefunItemStack("LAB_ELECTROLYZER_2",
                new CustomItemStack(
                        Material.IRON_BLOCK,
                        "&b电解机 &7- &eII",
                        "",
                        "&6高级机器",
                        "&8⇨ &7速度: 2x",
                        "&8⇨ &e⚡ &7512 J 缓存",
                        "&8⇨ &e⚡ &764 J/s"));

        (new AdvancedAContainer(lockedCategory, ELECTROLYZER_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REDSTONE_ALLOY,
                SlimefunItems.HARDENED_GLASS,
                SlimefunItems.REDSTONE_ALLOY,
                ALUMAG_ALLOY,
                MAGNETIZER_2,
                ALUMAG_ALLOY,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                Resource.GRAPHITE,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD
        }) {

            public void registerDefaultRecipes() {
                int time = Variables.cfg.getInt("items.electrolyzer.time");

                for (ItemStack[] iti : Variables.electrolyzer_r.keySet()) {
                    registerRecipe(time, iti, Variables.electrolyzer_r.get(iti));
                }
            }

            public int getSpeed() {
                return 2;
            }

            public ItemStack getProgressBar() {
                return SlimefunItems.MAGNET;
            }

            public @NotNull String getMachineIdentifier() {
                return getId();
            }

            public @NotNull String getInventoryTitle() {
                return getItemName();
            }

            public int getEnergyConsumption() {
                return 32;
            }

            @Override
            public int getCapacity() {
                return 512;
            }
        })
                .register(Laboratory.instance);

        NETHER_STAR_CRUSHER = new SlimefunItemStack("LAB_NETHER_STAR_CRUSHER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTM5YWMyNTdmNGUwNGFkYzZhYjNlNDZmZDNiMjg3NDllODY2ZmFhOWNlNmExZDZkNTI2ZTBlMzg2NGQ5MjIyIn19fQ=="),
                        "&6下界之星粉碎机",
                        "",
                        "&r刀片进过特殊处理的粉碎机",
                        "&r其强度足以破坏下界之星",
                        "",
                        "&d量子机器",
                        "&8⇨ &e⚡ &71024 J 缓存",
                        "&8⇨ &e⚡ &7512 J/s"));

        (new NetherStarCrusher(
                lockedCategory, NETHER_STAR_CRUSHER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                IRIDIUM,
                IRIDIUM,
                SlimefunItems.ELECTRIC_MOTOR,
                QUANTUM_CORE,
                SlimefunItems.ELECTRIC_MOTOR,
                CHARGED_CERTUS_QUARTZ,
                SUPER_CIRCUIT_BOARD,
                CHARGED_CERTUS_QUARTZ
        }) {

            public int getEnergyConsumption() {
                return 256;
            }

            public int getSpeed() {
                return super.getSpeed();
            }

            @Override
            public int getCapacity() {
                return 1024;
            }
        })
                .register(Laboratory.instance);

        PLASMA_GENERATOR = new SlimefunItemStack("LAB_PLASMA_GENERATOR",
                new CustomItemStack(
                        Material.PURPLE_STAINED_GLASS,
                        "&9等离子生成机",
                        "",
                        "&d量子机器",
                        "&8⇨ &e⚡ &71024 J 缓存",
                        "&8⇨ &e⚡ &71024 J/s"));

        (new PlasmaGenerator(lockedCategory, PLASMA_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SUPER_CIRCUIT_BOARD,
                NEUTRON_REFLECTOR,
                SUPER_ELECTRO_MOTOR,
                SlimefunItems.WITHER_PROOF_GLASS,
                QUANTUM_CORE,
                SlimefunItems.WITHER_PROOF_GLASS,
                A_PIECE_OF_NETHER_STAR,
                SlimefunItems.LARGE_CAPACITOR,
                A_PIECE_OF_NETHER_STAR
        }) {

            @Override
            public void registerDefaultRecipes() {
                registerRecipe(10, U.mat(Material.GLASS_BOTTLE), PLASMA_CELL);
            }

            public int getEnergyConsumption() {
                return 512;
            }

            public int getSpeed() {
                return 1;
            }

            public int getCapacity() {
                return 1024;
            }
        })
                .register(Laboratory.instance);

        PROGRAMMABLE_ENDER_ANDROID_FISHERMAN = new SlimefunItemStack("LAB_PROGRAMMABLE_ENDER_ANDROID_FISHERMAN",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjg0MTJkNDg2ZTQ1OWMwYWRiZDJiNzY1OGNjYjAwNjNlOGViOGFlYzQ0YzNjOTA0MTNiMjYwNDIzN2I1NGYzOSJ9fX0="),
                        "&5可编程末影机器人 &7(捕鱼版)",
                        "",
                        "&8⇨ &7功能: 捕鱼",
                        "&8⇨ &7成功率: 40%",
                        "&8⇨ &7燃料效率: 2.0x"));

        (new ProgrammableAndroid(
                lockedCategory,
                3,
                PROGRAMMABLE_ENDER_ANDROID_FISHERMAN,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        U.mat(Material.END_ROD),
                        CHARGED_CERTUS_QUARTZ,
                        U.mat(Material.END_ROD),
                        U.mat(Material.FISHING_ROD),
                        SlimefunItems.PROGRAMMABLE_ANDROID_2_FISHERMAN,
                        U.mat(Material.FISHING_ROD),
                        ENDER_GENERATOR,
                        SlimefunItems.ELECTRIC_MOTOR,
                        ENDER_GENERATOR
                }) {
            {
                this.fuelTypes.clear();
                this.registerFuelType(new MachineFuel(2500, SlimefunItems.URANIUM));
                this.registerFuelType(new MachineFuel(1200, SlimefunItems.NEPTUNIUM));
                this.registerFuelType(new MachineFuel(3000, SlimefunItems.BOOSTED_URANIUM));

                this.registerFuelType(new MachineFuel(12, U.mat(Material.ENDER_PEARL)));
                this.registerFuelType(new MachineFuel(32, U.mat(Material.ENDER_EYE)));
            }

            public float getFuelEfficiency() {
                return 2.0F;
            }

            public int getTier() {
                return 3;
            }

            public AndroidType getAndroidType() {
                return AndroidType.FISHERMAN;
            }
        })
                .register(Laboratory.instance);

        PROGRAMMABLE_ENDER_ANDROID_MINER = new SlimefunItemStack("LAB_PROGRAMMABLE_ENDER_ANDROID_MINER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzg4OTQ4MTcxNGJkMmNhZDQwZTdlMmVlMTIyNGE2NjI3ZGEzOTllNWQ0MzQ4MTFlYWIyYmUxZjIyNDFjZmI4OSJ9fX0="),
                        "&5可编程末影机器人 &7(挖矿版)",
                        "",
                        "&8⇨ &7功能: 挖矿",
                        "&8⇨ &7燃料效率: 2.0x"));

        (new ProgrammableAndroid(
                lockedCategory,
                3,
                PROGRAMMABLE_ENDER_ANDROID_MINER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        U.mat(Material.END_ROD),
                        CHARGED_CERTUS_QUARTZ,
                        U.mat(Material.END_ROD),
                        U.mat(Material.DIAMOND_PICKAXE),
                        SlimefunItems.PROGRAMMABLE_ANDROID_MINER,
                        U.mat(Material.DIAMOND_PICKAXE),
                        ENDER_GENERATOR,
                        SlimefunItems.ELECTRIC_MOTOR,
                        ENDER_GENERATOR
                }) {
            {
                this.fuelTypes.clear();
                this.registerFuelType(new MachineFuel(2500, SlimefunItems.URANIUM));
                this.registerFuelType(new MachineFuel(1200, SlimefunItems.NEPTUNIUM));
                this.registerFuelType(new MachineFuel(3000, SlimefunItems.BOOSTED_URANIUM));

                this.registerFuelType(new MachineFuel(12, U.mat(Material.ENDER_PEARL)));
                this.registerFuelType(new MachineFuel(32, U.mat(Material.ENDER_EYE)));
            }

            public float getFuelEfficiency() {
                return 2.0F;
            }

            public int getTier() {
                return 3;
            }

            public AndroidType getAndroidType() {
                return AndroidType.MINER;
            }
        })
                .register(Laboratory.instance);

        PROGRAMMABLE_ENDER_ANDROID_BUTCHER = new SlimefunItemStack("LAB_PROGRAMMABLE_ENDER_ANDROID_BUTCHER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFjOTZlOGNmODNjYmFkZTU1ZmZhNjY3MTk3ZWE2OTkwMjkwZTVjN2RjNjc5MTA0MzMyY2FlYWQ5N2VlZjA5In19fQ=="),
                        "&5可编程末影机器人 &7(攻击版)",
                        "",
                        "&8⇨ &7功能: 杀死生物",
                        "&8⇨ &7燃料效率: 2.0x"));

        (new ProgrammableAndroid(
                lockedCategory,
                3,
                PROGRAMMABLE_ENDER_ANDROID_BUTCHER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        U.mat(Material.END_ROD),
                        CHARGED_CERTUS_QUARTZ,
                        U.mat(Material.END_ROD),
                        U.mat(Material.DIAMOND_SWORD),
                        SlimefunItems.PROGRAMMABLE_ANDROID_2_BUTCHER,
                        U.mat(Material.DIAMOND_SWORD),
                        ENDER_GENERATOR,
                        SlimefunItems.ELECTRIC_MOTOR,
                        ENDER_GENERATOR
                }) {
            {
                this.fuelTypes.clear();
                this.registerFuelType(new MachineFuel(2500, SlimefunItems.URANIUM));
                this.registerFuelType(new MachineFuel(1200, SlimefunItems.NEPTUNIUM));
                this.registerFuelType(new MachineFuel(3000, SlimefunItems.BOOSTED_URANIUM));

                this.registerFuelType(new MachineFuel(12, U.mat(Material.ENDER_PEARL)));
                this.registerFuelType(new MachineFuel(32, U.mat(Material.ENDER_EYE)));
            }

            public float getFuelEfficiency() {
                return 2.0F;
            }

            public int getTier() {
                return 3;
            }

            public AndroidType getAndroidType() {
                return AndroidType.FIGHTER;
            }
        })
                .register(Laboratory.instance);

        Research grenco_power_crystal =
                U.newResearch("Grenco Power Crystal", Variables.cfg.getInt("researches.grenco-power-crystal.level"));
        grenco_power_crystal.addItems(SlimefunItem.getByItem(GRENCO_POWER_CRYSTAL));
        grenco_power_crystal.register();

        CHEMICAL_REACTOR = new SlimefunItemStack("LAB_CHEMICAL_REACTOR",
                new CustomItemStack(
                        Material.IRON_BLOCK,
                        "&b化学反应器",
                        "",
                        "&7用于制造化学反应",
                        "&7并将其转化为物质",
                        "",
                        "&8⇨ &7速度: 1x",
                        "&8⇨ &e⚡ &7256 J 缓存",
                        "&8⇨ &e⚡ &732 J/s"));
        new ChemicalReactor(lockedCategory, CHEMICAL_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.HEATING_COIL,
                SlimefunItems.PLASTIC_SHEET,
                SlimefunItems.HEATING_COIL,
                Tech.ALUMAG_ALLOY,
                SlimefunItems.HEATED_PRESSURE_CHAMBER_2,
                Tech.ALUMAG_ALLOY,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.COOLING_UNIT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD
        }) {
            @Override
            public int getSpeed() {
                return 1;
            }

            @Override
            public int getCapacity() {
                return 256;
            }

            @Override
            public int getEnergyConsumption() {
                return 16;
            }
        }.register(Laboratory.instance);
    }
}
