package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.gadgets.JetBoots;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.gadgets.Jetpack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Lab {
    public static SlimefunItemStack MIMUNG_BLASTER;
    public static SlimefunItemStack CONFIG_RELOADER;
    public static SlimefunItemStack REINFORCED_STOMPER;
    public static SlimefunItemStack ELECTRIC_AXE_1;
    public static SlimefunItemStack ELECTRIC_AXE_2;
    public static SlimefunItemStack FOOD_SYNTHESIZER;
    public static SlimefunItemStack VANISHER;
    public static SlimefunItemStack WHITE_VANISHER;
    public static SlimefunItemStack EMERALD_CAPACITY_1;
    public static SlimefunItemStack EMERALD_CAPACITY_2;
    public static SlimefunItemStack ELECTRIC_DIGGER;
    public static SlimefunItemStack EGG_GUARDER;
    public static SlimefunItemStack VECTOR_MANIPULATOR;
    public static SlimefunItemStack ARC_SWORD;
    public static SlimefunItemStack IRIDIUM_ARMORED_JETPACK;
    public static SlimefunItemStack IRIDIUM_ARMORED_JETBOOTS;
    public static SlimefunItemStack DRAGON_BREATH_GENERATOR;
    public static SlimefunItemStack BASIC_MOBILE_BATTERY;
    public static SlimefunItemStack ADVANCED_MOBILE_BATTERY;
    public static SlimefunItemStack ULTIMATE_MOBILE_BATTERY;
    public static SlimefunItemStack QUANTUM_MOBILE_BATTERY;

    public Lab() {
        String category_icon =
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Y1OWY4MjM0Yzk5OGQ5NjM3YWYxMmU4ZTM1NmRmYzFhZjJkODZkYWQwYjE5MWQ5MzU1ZjgyMmE1Y2I2ZTEifX19";

        ItemGroup c = new ItemGroup(
                new NamespacedKey(Main.instance, "lab"),
                new CustomItemStack(
                        SkullUtil.getByBase64(category_icon), "&7Consider 实验室科技产品", new String[] {"", "&a> 点击打开"}),
                4);
        RecipeType non = Variables.Non;

        CONFIG_RELOADER = new SlimefunItemStack("LAB_CONFIG_RELOADER",
                new CustomItemStack(Material.APPLE, "&9配置文件重载器", "", "&f用于重载配置文件", "&f无法重载分子重组仪的配置文件"));

        (new SlimefunItem(c, CONFIG_RELOADER, non, new ItemStack[9])).register(Main.instance);

        REINFORCED_STOMPER = new SlimefunItemStack("LAB_REINFORCED_STOMPER",
                new CustomItemStack(
                        Material.DIAMOND_BOOTS,
                        "&b装甲震地靴",
                        "",
                        "&f强化后的震地靴",
                        "&f各方面得到了质的提升",
                        "&f能将你受到的摔落伤害的三分之二给予区域内的实体",
                        "",
                        "&9- 掉落伤害"));

        REINFORCED_STOMPER.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        SlimefunItem reinforcedstomper_sf =
                new SlimefunItem(c, REINFORCED_STOMPER, RecipeType.ARMOR_FORGE, new ItemStack[] {
                    SlimefunItems.REINFORCED_ALLOY_INGOT,
                    SlimefunItems.REINFORCED_ALLOY_INGOT,
                    mat(Material.WHITE_WOOL),
                    Tech.REINFORCED_IRIDIUM_PLATE,
                    mat(Material.WHITE_WOOL),
                    mat(Material.STICKY_PISTON),
                    mat(Material.STICKY_PISTON)
                });

        reinforcedstomper_sf.register(Main.instance);

        ELECTRIC_AXE_1 = new SlimefunItemStack("LAB_ELECTRIC_AXE_1",
                new CustomItemStack(
                        Material.GOLDEN_AXE,
                        "&c电磁斧",
                        "",
                        "&f每次攻击附带电击效果",
                        "&f杨教授专用",
                        "",
                        "&8⇨ &7每次攻击消耗 &e2.0 J",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 1024 J"));

        ELECTRIC_AXE_1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        ELECTRIC_AXE_1.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        (new ChargeableItem(
                        c,
                        ELECTRIC_AXE_1,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            SlimefunItems.WITHER_PROOF_OBSIDIAN,
                            Tech.SUPER_HEATING_COIL,
                            Tech.SUPER_HEATING_COIL,
                            SlimefunItems.WITHER_PROOF_OBSIDIAN,
                            Tech.MAGNETIZED_REINFORCED_PLATE,
                            SlimefunItems.REDSTONE_ALLOY,
                            SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                            Tech.A_PIECE_OF_NETHER_STAR,
                            SlimefunItems.REDSTONE_ALLOY
                        },
                        1024))
                .register(Main.instance);

        ELECTRIC_AXE_2 = new SlimefunItemStack("LAB_ELECTRIC_AXE_2",
                new CustomItemStack(
                        Material.DIAMOND_AXE,
                        "&c强化电磁斧",
                        "",
                        "&f让你成为磁爆步兵!",
                        "&f电击效果能够使目标无法攻击",
                        "",
                        "&8⇨ &7每次攻击消耗 &e6.0 J",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 1024 J"));

        ELECTRIC_AXE_2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        ELECTRIC_AXE_2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        (new ChargeableItem(
                        c,
                        ELECTRIC_AXE_2,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.REINFORCED_IRIDIUM_PLATE, Tech.SUPER_HEATING_COIL, Tech.IRIDIUM_BLOCK,
                            Tech.REINFORCED_IRIDIUM_PLATE, Tech.SUPER_HEATING_COIL, Tech.IRIDIUM_BLOCK,
                            SlimefunItems.CARBONADO_EDGED_CAPACITOR, Tech.LAMA_POWER_CRYSTAL, Tech.SUPER_ELECTRO_MAGNET
                        },
                        1024))
                .register(Main.instance);

        VANISHER = new SlimefunItemStack("LAB_VANISHER",
                new CustomArmor(
                        new CustomItemStack(Material.LEATHER_BOOTS, "&7隐身器", new String[] {
                            "", "&f使用光扭曲技术", "&f在电容中有电时使你隐身", "", "&8⇨ &7每次移动消耗少量能量", "", "&c&o&8⇨ &e⚡ &70 / 2048 J"
                        }),
                        Color.BLACK));
        WHITE_VANISHER = new SlimefunItemStack("LAB_WHITE_VANISHER",
                new CustomArmor(
                        new CustomItemStack(Material.LEATHER_BOOTS, "&f白色隐身器", new String[] {
                            "", "&f隐身器的白色版本", "&f可适应某些特殊环境", "", "&c&o&8⇨ &e⚡ &70 / 2048 J"
                        }),
                        Color.WHITE));

        VANISHER.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        WHITE_VANISHER.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);

        ItemMeta vanisher_im = VANISHER.getItemMeta();
        ItemMeta white_vanisher_im = WHITE_VANISHER.getItemMeta();

        vanisher_im.setUnbreakable(true);
        white_vanisher_im.setUnbreakable(true);

        VANISHER.setItemMeta(vanisher_im);
        WHITE_VANISHER.setItemMeta(white_vanisher_im);

        (new ChargeableItem(
                        c,
                        VANISHER,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.OPTICAL_COMPONENT,
                            Tech.OPTICAL_COMPONENT,
                            Tech.OPTICAL_COMPONENT,
                            Tech.MAGNETIZED_REINFORCED_PLATE,
                            SlimefunItems.HARDENED_GLASS,
                            Tech.MAGNETIZED_REINFORCED_PLATE,
                            SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                            SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                            SlimefunItems.CARBONADO_EDGED_CAPACITOR
                        },
                        2048))
                .register(Main.instance);

        (new ChargeableItem(
                        c,
                        WHITE_VANISHER,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {null, null, null, null, VANISHER, new CustomItemStack(Material.BONE_MEAL)},
                        2048))
                .register(Main.instance);

        EMERALD_CAPACITY_1 = new SlimefunItemStack("LAB_EMERALD_CAPACITY_1",
                new CustomPotion(
                        "&a绿宝石电容 &7- &eI",
                        Color.LIME,
                        new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 0, 0, false, false),
                        new String[] {
                            "",
                            "&f移动时消耗电容中的电量为背包中的物品充电",
                            "&f不会为另一个绿宝石电容充电",
                            "",
                            "&c&o&8⇨ &7充电效率: &e0.01 J/move",
                            "&c&o&8⇨ &e⚡ &70 / 2048 J"
                        }));

        (new ChargeableItem(
                        c,
                        EMERALD_CAPACITY_1,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.SUPER_ELECTRO_MAGNET, SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                                    Tech.SUPER_ELECTRO_MAGNET,
                            Tech.SUPER_CIRCUIT_BOARD, mat(Material.EMERALD_BLOCK), Tech.SUPER_CIRCUIT_BOARD,
                            SlimefunItems.REINFORCED_PLATE, SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                                    SlimefunItems.REINFORCED_PLATE
                        },
                        2048))
                .register(Main.instance);

        EMERALD_CAPACITY_2 = new SlimefunItemStack("LAB_EMERALD_CAPACITY_2", 
                new CustomPotion(
                        "&a绿宝石电容 &7- &eII",
                        Color.LIME,
                        new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 0, 0, false, false),
                        new String[] {
                            "",
                            "&b&l&o急速充电中!",
                            "",
                            "&f可能会导致电量损耗",
                            "",
                            "&c&o&8⇨ &7充电效率: &e1 J/move",
                            "&c&o&8⇨ &e⚡ &70 / 8192 J"
                        }));

        (new ChargeableItem(
                        c,
                        EMERALD_CAPACITY_2,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.MAGNETIZED_REINFORCED_PLATE, SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                                    Tech.MAGNETIZED_REINFORCED_PLATE,
                            Tech.SUPER_CIRCUIT_BOARD, EMERALD_CAPACITY_1, Tech.SUPER_CIRCUIT_BOARD,
                            Tech.REINFORCED_IRIDIUM_PLATE, SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                                    Tech.REINFORCED_IRIDIUM_PLATE
                        },
                        8192))
                .register(Main.instance);

        ELECTRIC_DIGGER = new SlimefunItemStack("LAB_ELECTRIC_DIGGER",
                new CustomItemStack(
                        Material.DIAMOND_PICKAXE,
                        "&b电动钻头",
                        "",
                        "&f超大的电容量!",
                        "&f飞一般的挖掘速度!",
                        "",
                        "&c&o&8⇨ &7每次挖掘消耗 &e0.1 J",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 1024 J"));

        ELECTRIC_DIGGER.addEnchantment(Enchantment.DURABILITY, 3);

        (new ChargeableItem(
                        c,
                        ELECTRIC_DIGGER,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.REINFORCED_IRIDIUM_PLATE, Tech.REINFORCED_IRIDIUM_PLATE, Tech.REINFORCED_IRIDIUM_PLATE,
                            Tech.MAGNETIZED_REINFORCED_PLATE, SlimefunItems.PROGRAMMABLE_ANDROID_MINER,
                                    Tech.MAGNETIZED_REINFORCED_PLATE,
                            Tech.SPEED_INGOT, SlimefunItems.CARBONADO_EDGED_CAPACITOR, Tech.SPEED_INGOT
                        },
                        1024))
                .register(Main.instance);

        BASIC_MOBILE_BATTERY = new SlimefunItemStack("LAB_BASIC_MOBILE_BATTERY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRmMjFjZjVjMjM0ZmM5NmRiOTBhMGEzMTFkNmZiZTEyZjg3ODliN2ZhODE1NTcxNjc1N2ZkNTE2YjE4MTEifX19"),
                        "&2可充电电池",
                        "",
                        "&f可充电又可当作电源供电",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 64 J"));

        (new ChargeableItem(
                        c,
                        BASIC_MOBILE_BATTERY,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        U.halfr3(SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BATTERY, null),
                        64))
                .register(Main.instance);

        U.addUnplaceableItem(BASIC_MOBILE_BATTERY);

        ADVANCED_MOBILE_BATTERY = new SlimefunItemStack("LAB_ADVANCED_MOBILE_BATTERY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODUzYjRjYTdmZTlhOWUyOTlkMzc3ZWNlNGMwMzdlMWNkMDA5YTBiNTcyZDUzYzg0NjlmMGNlMGZmZTNiYThhMiJ9fX0="),
                        "&a高级可充电电池",
                        "",
                        "&f可充电又可当作电源供电",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 256 J"));

        (new ChargeableItem(
                        c,
                        ADVANCED_MOBILE_BATTERY,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        U.halfr3(SlimefunItems.BASIC_CIRCUIT_BOARD, BASIC_MOBILE_BATTERY, null),
                        256))
                .register(Main.instance);

        U.addUnplaceableItem(ADVANCED_MOBILE_BATTERY);

        ULTIMATE_MOBILE_BATTERY = new SlimefunItemStack("LAB_ULTIMATE_MOBILE_BATTERY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIxNjcxN2YxZGE5NGY5OWJlODI0MmE5MzFlMzliYmYzMjI1MWRlZGY0NmNkMjA3M2ZmYTg4OTY5ZDc0Zjk2MyJ9fX0="),
                        "&9终级可充电电池",
                        "",
                        "&f可充电又可当作电源供电",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 1024 J"));

        (new ChargeableItem(
                        c,
                        ULTIMATE_MOBILE_BATTERY,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        U.halfr3(SlimefunItems.ADVANCED_CIRCUIT_BOARD, ADVANCED_MOBILE_BATTERY, null),
                        1024))
                .register(Main.instance);

        U.addUnplaceableItem(ULTIMATE_MOBILE_BATTERY);

        QUANTUM_MOBILE_BATTERY = new SlimefunItemStack("LAB_QUANTUM_MOBILE_BATTERY",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgwZTJjMzNjNGM4MzI0NDUyYWNjZjkyMzU2NjllNjVmMDBkMTkyNmNjNTMzMTQ1MTkyNWNhMjZlNmFhNzIxIn19fQ=="),
                        "&d量子可充电电池",
                        "",
                        "&f可充电又可当作电源供电",
                        "",
                        "&c&o&8⇨ &e⚡ &70 / 8192 J"));

        (new ChargeableItem(
                        c,
                        QUANTUM_MOBILE_BATTERY,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        U.halfr3(Tech.SUPER_CIRCUIT_BOARD, ULTIMATE_MOBILE_BATTERY, null),
                        8192))
                .register(Main.instance);

        U.addUnplaceableItem(QUANTUM_MOBILE_BATTERY);

        EGG_GUARDER = new SlimefunItemStack("LAB_EGG_GUARDER",
                new CustomItemStack(
                        SkullUtil.getByBase64(
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVlOGNjOTliYjQyZGRhMmFhZmJmZjQ1Nzc1Njc3NmIyOGM4ZTM0ZWUyNDVjYzU1M2QyNjk0ZTZiMDRiNzIifX19"),
                        "&d龙蛋保护器",
                        "",
                        "&r保护你的龙蛋不会因交互而瞬移",
                        "&r使用时将保护器放置在龙蛋上方"));

        (new SlimefunItem(c, EGG_GUARDER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.MAGNET,
                    SlimefunItems.INFUSED_MAGNET,
                    SlimefunItems.MAGNET,
                    new CustomItemStack(Material.MAGENTA_WOOL),
                    new CustomItemStack(Material.MAGENTA_WOOL),
                    new CustomItemStack(Material.MAGENTA_WOOL),
                    new CustomItemStack(Material.MAGENTA_STAINED_GLASS),
                    new CustomItemStack(Material.MAGENTA_STAINED_GLASS),
                    new CustomItemStack(Material.MAGENTA_STAINED_GLASS)
                }))
                .register(Main.instance);

        VECTOR_MANIPULATOR = new SlimefunItemStack("LAB_VECTOR_MANIPULATOR", new CustomItemStack(Material.GOLDEN_HORSE_ARMOR, "&c矢量操纵器", new String[] {
                    "", "&r消耗电量来操纵矢量", "&r仅对使用者有效", "", "&c&o&8⇨ &7每次传送消耗: &e2.0 J", "&c&o&8⇨ &e⚡ &70 / 128 J"
                }));

        (new ChargeableItem(
                        c,
                        VECTOR_MANIPULATOR,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            SlimefunItems.PLASTIC_SHEET, SlimefunItems.SILVER_INGOT, SlimefunItems.PLASTIC_SHEET,
                            Tech.VECTOR_THRUSTER, Tech.VECTOR_CORE, Tech.VECTOR_THRUSTER,
                            SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR,
                                    SlimefunItems.ADVANCED_CIRCUIT_BOARD
                        },
                        128) {
                    @Override
                    public void preRegister() {
                        addItemHandler((ItemUseHandler) playerRightClickEvent -> {
                            Player p = playerRightClickEvent.getPlayer();
                            ItemStack item = playerRightClickEvent.getItem();
                            if (SlimefunUtils.isItemSimilar(item, Lab.VECTOR_MANIPULATOR, false)) {
                                if (ItemEnergy.getStoredEnergy(item) >= 2.0F) {
                                    ItemEnergy.chargeItem(item, -2.0F);
                                    Location loct = p.getTargetBlock(
                                                    null, Variables.cfg.getInt("items.vector-manipulator.strength"))
                                            .getLocation();
                                    Location loc = new Location(
                                            p.getWorld(),
                                            loct.getX(),
                                            loct.getY(),
                                            loct.getZ(),
                                            p.getLocation().getYaw(),
                                            p.getLocation().getPitch());
                                    p.teleport(loc);
                                } else {
                                    p.sendMessage(U.getCfgMessage("messages.vector-manipulator.not-enough-power"));
                                }
                            }
                        });
                    }
                })
                .register(Main.instance);

        ARC_SWORD = new SlimefunItemStack("LAB_ARC_SWORD", new CustomItemStack(Material.DIAMOND_SWORD, "&b电弧剑", new String[] {
                    "",
                    "&f攻击时激活电弧发生器",
                    "&f能够轻松破坏生物组织",
                    "&f对于导电的盔甲还有穿甲伤害",
                    "&f在未激活电弧发生器时",
                    "&f它就是一把普通的钻石剑",
                    "",
                    "&eShift + 右键点击 &7切换电弧发生器激活状态",
                    "",
                    "&c&o&8⇨ &f电弧发生器状态: &c禁用",
                    "&c&o&8⇨ &f激活电弧发生器时每次攻击消耗 4.0 J",
                    "",
                    "&c&o&8⇨ &e⚡ &70 / 1024 J"
                }));

        (new ChargeableItem(
                        c,
                        ARC_SWORD,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.ARC_REACTOR, Tech.ARC_REACTOR, Tech.ARC_REACTOR,
                            SlimefunItems.SYNTHETIC_DIAMOND, Tech.GRENCO_POWER_CRYSTAL, SlimefunItems.SYNTHETIC_DIAMOND,
                            Tech.SUPER_CIRCUIT_BOARD, SlimefunItems.CARBONADO_EDGED_CAPACITOR, Tech.SUPER_CIRCUIT_BOARD
                        },
                        1024))
                .register(Main.instance);

        IRIDIUM_ARMORED_JETPACK = new SlimefunItemStack("LAB_IRIDIUM_ARMORED_JETPACK", new CustomItemStack(Material.DIAMOND_CHESTPLATE, "&9防护型喷气背包", new String[] {
                    "&8⇨ &7材质: &b铱", "", "&c&o&8⇨ &e⚡ &70 / 300 J", "&8⇨ &7推进力: &c0.9", "", "&7按住 &eShift&7 以使用"
                }));
        IRIDIUM_ARMORED_JETPACK.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        (new Jetpack(
                        c,
                        IRIDIUM_ARMORED_JETPACK,
                        new ItemStack[] {
                            Tech.IRIDIUM,
                            Tech.REINFORCED_IRIDIUM_IRON_PLATE,
                            Tech.IRIDIUM,
                            SlimefunItems.REINFORCED_ALLOY_INGOT,
                            Tech.LAMA_POWER_CRYSTAL,
                            SlimefunItems.REINFORCED_ALLOY_INGOT,
                            Tech.VECTOR_THRUSTER,
                            SlimefunItems.LARGE_CAPACITOR,
                            Tech.VECTOR_THRUSTER
                        },
                        0.9D,
                        300))
                .register(Main.instance);

        IRIDIUM_ARMORED_JETBOOTS = new SlimefunItemStack("LAB_IRIDIUM_ARMORED_JETBOOTS", new CustomItemStack(Material.DIAMOND_BOOTS, "&9防护型喷气靴", new String[] {
                    "",
                    "&8⇨ &7材质: &b铱",
                    "&c&o&8⇨ &e⚡ &70 / 300 J",
                    "&8⇨ &7速度: &a0.9",
                    "&8⇨ &7精度: &e98%",
                    "",
                    "&7按住 &eShift&7 以使用"
                }));
        IRIDIUM_ARMORED_JETBOOTS.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        (new JetBoots(
                        c,
                        IRIDIUM_ARMORED_JETBOOTS,
                        new ItemStack[] {
                            null,
                            Tech.MAGNETIZED_REINFORCED_PLATE,
                            Tech.REINFORCED_IRIDIUM_IRON_PLATE,
                            Tech.LAMA_POWER_CRYSTAL,
                            Tech.REINFORCED_IRIDIUM_IRON_PLATE,
                            Tech.VECTOR_THRUSTER,
                            SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                            Tech.VECTOR_THRUSTER
                        },
                        0.9D,
                        300))
                .register(Main.instance);

        DRAGON_BREATH_GENERATOR = new SlimefunItemStack("LAB_DRAGON_BREATH_GENERATOR", new CustomItemStack(Material.MUSIC_DISC_STAL, "&5龙息生成器", new String[] {
                    "",
                    "&r利用内置的等离子球",
                    "&r通电并与末影能量混合",
                    "&r从而产生巨大范围伤害",
                    "",
                    "&f当前装载的等离子单元: &e1",
                    "&eShift + 右键点击 &7装填等离子单元",
                    "",
                    "&c&o&8⇨ &f每次发射消耗 24.0 J 和 一个等离子单元",
                    "",
                    "&c&o&8⇨ &e⚡ &70 / 1024 J"
                }));

        (new ChargeableItem(
                        c,
                        DRAGON_BREATH_GENERATOR,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            Tech.IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE, U.mat(Material.DRAGON_BREATH),
                                    Tech.IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE,
                            Tech.IRRADIANT_RADIUM, Tech.PLASMA_BALL, Tech.IRRADIANT_RADIUM,
                            Tech.LAMA_POWER_CRYSTAL, SlimefunItems.LARGE_CAPACITOR, Tech.LAMA_POWER_CRYSTAL
                        },
                        1024))
                .register(Main.instance);

        Research iridium_jettool =
                U.newResearch("Iridium Jetpack & Iridium Jetboots", U.getResearchLevelCost("iridium-jettools"));
        iridium_jettool.addItems(SlimefunItem.getById("LAB_IRIDIUM_ARMORED_JETPACK"));
        iridium_jettool.addItems(SlimefunItem.getById("LAB_IRIDIUM_ARMORED_JETBOOTS"));
        iridium_jettool.register();

        MIMUNG_BLASTER = new SlimefunItemStack("LAB_MIMUNG_BLASTER", new CustomItemStack(Material.BLAZE_POWDER, "&6米姆尤格斯弹匣", new String[] {
                    "",
                    "&f可将火焰喷射到周围",
                    "&f并对敌人造成巨大伤害",
                    "",
                    "&f当前装载的弹药: &e1",
                    "&eShift + 右键点击 &7装填弹药",
                    "",
                    "&c&o&8⇨ &f每次发射消耗 16.0 J 和 一个弹药",
                    "",
                    "&c&o&8⇨ &e⚡ &70 / 1024 J"
                }));
        (new ChargeableItem(
                        c,
                        MIMUNG_BLASTER,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET,
                            SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_DIAMOND,
                                    SlimefunItems.SYNTHETIC_DIAMOND,
                            SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR,
                                    SlimefunItems.ADVANCED_CIRCUIT_BOARD
                        },
                        1024))
                .register(Main.instance);
    }

    public static ItemStack mat(Material material) {
        return new ItemStack(material);
    }

    public static ItemStack sfi(String id) {
        return SlimefunItem.getById(id).getItem();
    }

    public static int random(int highest, int lowest) {
        return (new Random()).nextInt(highest - lowest + 1) + lowest;
    }
}
