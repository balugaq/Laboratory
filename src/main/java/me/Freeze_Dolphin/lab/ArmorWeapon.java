package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.RadioactiveItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmorWeapon {
    private static final ItemGroup c = new ItemGroup(
            new NamespacedKey(Main.instance, "armor_weapon"),
            new CustomItemStack(Material.DIAMOND_CHESTPLATE, "&7Consider 实验室装甲 & 武器", new String[] {"", "&a> 点击打开"}),
            4);
    public static SlimefunItemStack REINFORCED_SCUBA_HELMET;
    public static SlimefunItemStack REINFORCED_HAZMATSUIT_CHESTPLATE;
    public static SlimefunItemStack REINFORCED_HAZMATSUIT_LEGGINGS;
    public static SlimefunItemStack WITHER_PROOF_GLASS_HELMET;
    public static SlimefunItemStack WITHER_PROOF_GLASS_CHESTPLATE;
    public static SlimefunItemStack WITHER_PROOF_GLASS_LEGGINGS;
    public static SlimefunItemStack WITHER_PROOF_GLASS_BOOTS;
    public static SlimefunItemStack WITHER_PROOF_OBSIDIAN_HELMET;
    public static SlimefunItemStack WITHER_PROOF_OBSIDIAN_CHESTPLATE;
    public static SlimefunItemStack WITHER_PROOF_OBSIDIAN_LEGGINGS;
    public static SlimefunItemStack WITHER_PROOF_OBSIDIAN_BOOTS;
    public static SlimefunItemStack URANIUM_SWORD;
    public static SlimefunItemStack RADIUM_SWORD;
    public static SlimefunItemStack IRRADIANT_URANIUM_SWORD;
    public static SlimefunItemStack IRRADIANT_RADIUM_SWORD;

    public ArmorWeapon() {
        Armors();

        Weapons();
    }

    private static void Armors() {
        WitherProofObsidianArmors();
        WitherProofGlassArmors();
        AdvancedScubaArmors();
    }

    private static void Weapons() {
        RadioactivityWeapons();
    }

    private static void RadioactivityWeapons() {
        URANIUM_SWORD = new SlimefunItemStack("LAB_URANIUM_SWORD",
                new CustomItemStack(
                        Material.IRON_SWORD,
                        "&2铀剑",
                        "",
                        "&f剑刃由铀制成",
                        "&f攻击时能使目标被轻微辐射侵蚀",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));
        URANIUM_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        URANIUM_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        (new RadioactiveItem(c, Radioactivity.HIGH, URANIUM_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    null, SlimefunItems.URANIUM, SlimefunItems.URANIUM, SlimefunItems.REINFORCED_PLATE
                }))
                .register(Main.instance);

        IRRADIANT_URANIUM_SWORD = new SlimefunItemStack("LAB_IRRADIANT_URANIUM_SWORD",
                new CustomItemStack(
                        Material.IRON_SWORD,
                        "&a光辉铀剑",
                        "",
                        "&f剑刃由光辉铀制成",
                        "&f攻击时能使目标被辐射侵蚀",
                        "",
                        "&2辐射等级: 高",
                        "&4需要穿着防护服使用"));
        IRRADIANT_URANIUM_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        IRRADIANT_URANIUM_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        (new RadioactiveItem(c, Radioactivity.HIGH, IRRADIANT_URANIUM_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    null, Tech.IRRADIANT_URANIUM, Tech.IRRADIANT_URANIUM, SlimefunItems.REINFORCED_PLATE
                }))
                .register(Main.instance);

        RADIUM_SWORD = new SlimefunItemStack("LAB_RADIUM_SWORD",
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        "&9镭剑",
                        "",
                        "&f剑刃由镭制成",
                        "&f攻击时能使目标被轻微穿透性辐射侵蚀",
                        "",
                        "&2辐射等级: 穿透性",
                        "&4需要穿着反穿透防护服使用"));
        RADIUM_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        RADIUM_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

        (new RadioactiveItem(c, Radioactivity.VERY_HIGH, RADIUM_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    null, Tech.RADIUM, Tech.RADIUM, Tech.REINFORCED_IRIDIUM_IRON_PLATE
                }))
                .register(Main.instance);

        IRRADIANT_RADIUM_SWORD = new SlimefunItemStack("LAB_IRRADIANT_RADIUM_SWORD",
                new CustomItemStack(
                        Material.DIAMOND_SWORD,
                        "&a光辉镭剑",
                        "",
                        "&f剑刃由光辉铀制成",
                        "&f攻击时能使目标被穿透性辐射侵蚀",
                        "",
                        "&2辐射等级: 穿透性",
                        "&4需要穿着反穿透防护服使用"));
        IRRADIANT_RADIUM_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        IRRADIANT_RADIUM_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 3);

        (new RadioactiveItem(
                        c,
                        Radioactivity.VERY_HIGH,
                        IRRADIANT_RADIUM_SWORD,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            null,
                            Tech.IRRADIANT_RADIUM,
                            Tech.IRRADIANT_RADIUM,
                            Tech.IRRADIANT_REINFORCED_IRIDIUM_IRON_PLATE
                        }))
                .register(Main.instance);

        U.setPenetratingRadioactive(RADIUM_SWORD);
        U.setPenetratingRadioactive(IRRADIANT_RADIUM_SWORD);
    }

    private static void WitherProofObsidianArmors() {
        WITHER_PROOF_OBSIDIAN_HELMET = new SlimefunItemStack("LAB_WITHER_PROOF_OBSIDIAN_HELMET",
                new CustomArmor(new CustomItemStack(Material.LEATHER_HELMET, "&5抗凋零黑耀石头盔"), Color.BLACK));
        WITHER_PROOF_OBSIDIAN_CHESTPLATE = new SlimefunItemStack("LAB_WITHER_PROOF_OBSIDIAN_CHESTPLATE",
                new CustomArmor(new CustomItemStack(Material.LEATHER_CHESTPLATE, "&5抗凋零黑耀石胸甲"), Color.BLACK));
        WITHER_PROOF_OBSIDIAN_LEGGINGS = new SlimefunItemStack("LAB_WITHER_PROOF_OBSIDIAN_LEGGINGS",
                new CustomArmor(new CustomItemStack(Material.LEATHER_LEGGINGS, "&5抗凋零黑耀石护腿"), Color.BLACK));
        WITHER_PROOF_OBSIDIAN_BOOTS = new SlimefunItemStack("LAB_WITHER_PROOF_OBSIDIAN_BOOTS",
                new CustomArmor(new CustomItemStack(Material.LEATHER_BOOTS, "&5抗凋零黑耀石靴子"), Color.BLACK));

        (new SlimefunItem(c, WITHER_PROOF_OBSIDIAN_HELMET, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN
                }))
                .register(Main.instance);

        (new SlimefunItem(c, WITHER_PROOF_OBSIDIAN_CHESTPLATE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN
                }))
                .register(Main.instance);

        (new SlimefunItem(c, WITHER_PROOF_OBSIDIAN_LEGGINGS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN
                }))
                .register(Main.instance);

        (new SlimefunItem(c, WITHER_PROOF_OBSIDIAN_BOOTS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    null,
                    null,
                    null,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN,
                    SlimefunItems.WITHER_PROOF_OBSIDIAN
                }))
                .register(Main.instance);
    }

    private static void WitherProofGlassArmors() {
        WITHER_PROOF_GLASS_HELMET = new SlimefunItemStack("LAB_WITHER_PROOF_GLASS_HELMET", new CustomItemStack(Material.IRON_HELMET, "&5抗凋零玻璃头盔"));
        WITHER_PROOF_GLASS_CHESTPLATE = new SlimefunItemStack("LAB_WITHER_PROOF_GLASS_CHESTPLATE", new CustomItemStack(Material.IRON_CHESTPLATE, "&5抗凋零玻璃胸甲"));
        WITHER_PROOF_GLASS_LEGGINGS = new SlimefunItemStack("LAB_WITHER_PROOF_GLASS_LEGGINGS", new CustomItemStack(Material.IRON_LEGGINGS, "&5抗凋零玻璃护腿"));
        WITHER_PROOF_GLASS_BOOTS = new SlimefunItemStack("LAB_WITHER_PROOF_GLASS_BOOTS", new CustomItemStack(Material.IRON_BOOTS, "&5抗凋零玻璃靴子"));

        WITHER_PROOF_GLASS_HELMET.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        WITHER_PROOF_GLASS_CHESTPLATE.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        WITHER_PROOF_GLASS_LEGGINGS.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        WITHER_PROOF_GLASS_BOOTS.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemStack sp = SlimefunItems.STEEL_PLATE;
        ItemStack si = SlimefunItems.STEEL_INGOT;
        ItemStack o = SlimefunItems.WITHER_PROOF_GLASS;

        (new SlimefunArmorPiece(
                        c,
                        WITHER_PROOF_GLASS_HELMET,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {o, Tech.RUBBER_BALL, o, si, WITHER_PROOF_OBSIDIAN_HELMET, si},
                        new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)}))
                .register(Main.instance);

        (new SlimefunArmorPiece(
                        c,
                        WITHER_PROOF_GLASS_CHESTPLATE,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            o, sp, o,
                            o, WITHER_PROOF_OBSIDIAN_CHESTPLATE, o,
                            o, o, o
                        },
                        new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)}))
                .register(Main.instance);

        (new SlimefunArmorPiece(
                        c,
                        WITHER_PROOF_GLASS_LEGGINGS,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {o, WITHER_PROOF_OBSIDIAN_LEGGINGS, o, o, o, o, o},
                        new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)}))
                .register(Main.instance);

        (new SlimefunArmorPiece(
                        c,
                        WITHER_PROOF_GLASS_BOOTS,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {null, WITHER_PROOF_OBSIDIAN_BOOTS, o, Tech.RUBBER_BALL, o, o, sp, o},
                        new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)}))
                .register(Main.instance);
    }

    private static void AdvancedScubaArmors() {
        REINFORCED_SCUBA_HELMET = new SlimefunItemStack("LAB_REINFORCED_SCUBA_HELMET",
                new CustomItemStack(
                        Material.IRON_HELMET,
                        "&c加厚防护头盔",
                        "",
                        "&f比起普通防护服来说",
                        "&f这种加厚的防护服能够抵挡穿透性射线的伤害",
                        "&f但是由于其材质与普通防护服的巨大区别",
                        "&f导致其无法防止寻常的辐射对穿戴者的损伤",
                        "",
                        "&b反穿透防护服的一部分",
                        "&b提供火焰保护"));
        REINFORCED_HAZMATSUIT_CHESTPLATE = new SlimefunItemStack("LAB_REINFORCED_HAZMATSUIT_CHESTPLATE",
                new CustomItemStack(
                        Material.IRON_CHESTPLATE,
                        "&c加厚防护服",
                        "",
                        "&f比起普通防护服来说",
                        "&f这种加厚的防护服能够抵挡穿透性射线的伤害",
                        "&f但是由于其材质与普通防护服的巨大区别",
                        "&f导致其无法防止寻常的辐射对穿戴者的损伤",
                        "",
                        "&b反穿透防护服的一部分",
                        "&b提供抗性提升"));
        REINFORCED_HAZMATSUIT_LEGGINGS = new SlimefunItemStack("LAB_REINFORCED_HAZMATSUIT_LEGGINGS",
                new CustomItemStack(
                        Material.IRON_LEGGINGS,
                        "&c加厚防护裤",
                        "",
                        "&f比起普通防护服来说",
                        "&f这种加厚的防护服能够抵挡穿透性射线的伤害",
                        "&f但是由于其材质与普通防护服的巨大区别",
                        "&f导致其无法防止寻常的辐射对穿戴者的损伤",
                        "",
                        "&b反穿透防护服的一部分"));

        ItemStack rag = SlimefunItems.REINFORCED_ALLOY_INGOT;
        ItemStack pc = SlimefunItems.POWER_CRYSTAL;

        (new SlimefunArmorPiece(
                        c,
                        REINFORCED_SCUBA_HELMET,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {rag, rag, rag, rag, SlimefunItems.SCUBA_HELMET, rag, pc},
                        new PotionEffect[] {
                            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1),
                            new PotionEffect(PotionEffectType.SLOW, 300, 1)
                        }))
                .register(Main.instance);

        (new SlimefunArmorPiece(
                        c,
                        REINFORCED_HAZMATSUIT_CHESTPLATE,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            rag, pc, rag,
                            rag, SlimefunItems.HAZMAT_CHESTPLATE, rag,
                            rag, rag, rag
                        },
                        new PotionEffect[] {
                            new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1),
                            new PotionEffect(PotionEffectType.SLOW, 300, 1)
                        }))
                .register(Main.instance);

        (new SlimefunArmorPiece(
                        c,
                        REINFORCED_HAZMATSUIT_LEGGINGS,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            rag, rag, rag,
                            rag, SlimefunItems.HAZMAT_LEGGINGS, rag,
                            rag, pc, rag
                        },
                        new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 300, 1)}))
                .register(Main.instance);
    }
}
