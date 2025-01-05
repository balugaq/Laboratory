package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.LockedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.RadioactiveItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.reactors.Reactor;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.HashMap;
import java.util.Map;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Nuclear {
    public static final RecipeType fusedSaltReactor_r = new RecipeType(
            new NamespacedKey(Main.instance, "fused_salt_reactor_recipe"),
            new CustomItemStack(
                    Material.ORANGE_STAINED_GLASS,
                    "&6合金反应堆"
            )
    );
    public static final SlimefunItemStack FLUORIDE_MACHINE = new SlimefunItemStack("LAB_FLUORIDE_MACHINE",
            new CustomItemStack(
                    Material.ORANGE_STAINED_GLASS,
                    "&e氟化机",
                    "",
                    "&f将流体与氟混合并进行氟化反应",
                    "",
                    "&6高级机器",
                    "",
                    "&8⇨ &e⚡ &7512 J 缓存",
                    "&8⇨ &e⚡ &732 J/s"));
    public static final SlimefunItemStack THERMAL_CENTRIFUGE = new SlimefunItemStack("LAB_THERMAL_CENTRIFUGE",
            new CustomItemStack(
                    Material.ORANGE_STAINED_GLASS,
                    "&c热能离心机",
                    "",
                    "&f核燃料的回收再利用离不开它",
                    "",
                    "&6高级机器",
                    "",
                    "&8⇨ &e⚡ &768 J 缓存",
                    "&8⇨ &e⚡ &764 J/s"));
    public static final SlimefunItemStack GRAPHITE_CORE =
            new SlimefunItemStack("LAB_GRAPHITE_CORE", new CustomItemStack(Material.OBSIDIAN, "&8石墨堆芯"));
    public static final RecipeType FLUORIDEM = new RecipeType(
            new NamespacedKey(Main.instance, "fluoridem"),
            new CustomItemStack(Material.ORANGE_STAINED_GLASS, "&e氟化机", new String[] {"", "&a使用氟化机将物质氟化制取"}));
    private static final Map<ItemStack[], ItemStack[]> meltingm_r = new HashMap<>();
    private static final Map<ItemStack[], ItemStack[]> fluoridem_r = new HashMap<>();
    public static ItemGroup c;
    public static SlimefunItemStack MELTING_MACHINE;
    public static SlimefunItemStack LEAD_CELL_EMPTY;
    public static SlimefunItemStack URANIUM_FLUID_CELL;
    public static SlimefunItemStack BOOSTED_URANIUM_FLUID_CELL;
    public static SlimefunItemStack FLUORIDE_URANIUM_FLUID_CELL;
    public static SlimefunItemStack FLUORIDE_BOOSTED_URANIUM_FLUID_CELL;
    public static SlimefunItemStack DEPLETED_URANIUM_CELL;
    public static SlimefunItemStack DEPLETED_BOOSTED_URANIUM_CELL;
    public static SlimefunItemStack BERYLLIUM_FLUID_CELL;
    public static SlimefunItemStack LITHIUM_FLUID_CELL;
    public static SlimefunItemStack FLUORIDE_BERYLLIUM_FLUID_CELL;
    public static SlimefunItemStack FLUORIDE_LITHIUM_FLUID_CELL;
    public static SlimefunItemStack FUSED_SALT_REACTOR_COOLANT_CELL;
    public static SlimefunItemStack ENRICHED_FUSED_SALT_REACTOR_FUEL;
    public static SlimefunItemStack ENRICHED_BOOSTED_FUSED_SALT_REACTOR_FUEL;
    public static SlimefunItemStack FUSED_SALT_REACTOR;
    public static RecipeType MELTINGM;
    public static RecipeType CENTRIFUGEM = new RecipeType(
            new NamespacedKey(Main.instance, "centrifugem"),
            new CustomItemStack(Material.ORANGE_TERRACOTTA, "&c热能离心机", new String[] {"", "&a使用热能离心机处理物质"}));

    static {
        try {
            c = new LockedItemGroup(
                    new NamespacedKey(Main.instance, "nuclear"),
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODI5NmYwOTI1MjRhZTljMmEyZTg3ODgxY2I0MTVhZGIzNThkNmNiNzczYzg1ZGM5NzIwMmZlZmI3NTRjMSJ9fX0="),
                            "&7Consider 实验室核电",
                            new String[] {"", "&a> 点击打开"}),
                    5,
                    new NamespacedKey(Slimefun.instance(), "cargo"));

            LEAD_CELL_EMPTY = new SlimefunItemStack("LAB_LEAD_CELL_EMPTY",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmI0NjE3ZWM5ZDgzNDEwMGFmMmYzOTBlYjI3YzUzNzE4OTk5OWRjNTAzMGNlMzY2ZmNhN2E1Yjc0NDhhMzc5MyJ9fX0="),
                            "&b铅制单元"));
            URANIUM_FLUID_CELL = new SlimefunItemStack("LAB_URANIUM_FLUID_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDgwNjAzMDdlOTk0NjgxMjcxY2MzYWRlMzVlNTUwM2I0MWI0OWNiMjE4NmM4NDQ4NWZkN2Q5MjVmYWU4MGMzNSJ9fX0="),
                            "&2熔融铀单元",
                            "",
                            "&2辐射等级: 高",
                            "&4需要穿着防护服使用"));
            BOOSTED_URANIUM_FLUID_CELL = new SlimefunItemStack("LAB_BOOSTED_URANIUM_FLUID_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDgwNjAzMDdlOTk0NjgxMjcxY2MzYWRlMzVlNTUwM2I0MWI0OWNiMjE4NmM4NDQ4NWZkN2Q5MjVmYWU4MGMzNSJ9fX0="),
                            "&2熔融高能铀单元",
                            "",
                            "&2辐射等级: 高",
                            "&4需要穿着防护服使用"));

            FLUORIDE_URANIUM_FLUID_CELL = new SlimefunItemStack("LAB_FLUORIDE_URANIUM_FLUID_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDgwNjAzMDdlOTk0NjgxMjcxY2MzYWRlMzVlNTUwM2I0MWI0OWNiMjE4NmM4NDQ4NWZkN2Q5MjVmYWU4MGMzNSJ9fX0="),
                            "&e氟化铀单元",
                            "",
                            "&2辐射等级: 高",
                            "&4需要穿着防护服使用"));
            FLUORIDE_BOOSTED_URANIUM_FLUID_CELL = new SlimefunItemStack("LAB_FLUORIDE_BOOSTED_URANIUM_FLUID_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDgwNjAzMDdlOTk0NjgxMjcxY2MzYWRlMzVlNTUwM2I0MWI0OWNiMjE4NmM4NDQ4NWZkN2Q5MjVmYWU4MGMzNSJ9fX0="),
                            "&e氟化高能铀单元",
                            "",
                            "&2辐射等级: 高",
                            "&4需要穿着防护服使用"));

            String melten_metal_cell =
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdkMTQwMDlmMzUxODM0ZTU2YjkyNDgyZjg5NzhmM2JjNzJiMmQ4NzFjZGIyN2JkYjFiNjExMDEyODk5MWMwYyJ9fX0=";
            BERYLLIUM_FLUID_CELL = new SlimefunItemStack("LAB_BERYLLIUM_FLUID_CELL", new CustomItemStack(SkullUtil.getByBase64(melten_metal_cell), "&b熔融铍单元"));
            LITHIUM_FLUID_CELL = new SlimefunItemStack("LAB_LITHIUM_FLUID_CELL", new CustomItemStack(SkullUtil.getByBase64(melten_metal_cell), "&b熔融锂单元"));
            FLUORIDE_BERYLLIUM_FLUID_CELL = new SlimefunItemStack("LAB_FLUORIDE_BERYLLIUM_FLUID_CELL",
                    new CustomItemStack(SkullUtil.getByBase64(melten_metal_cell), "&e氟化铍单元"));
            FLUORIDE_LITHIUM_FLUID_CELL = new SlimefunItemStack("LAB_FLUORIDE_LITHIUM_FLUID_CELL",
                    new CustomItemStack(SkullUtil.getByBase64(melten_metal_cell), "&e氟化锂单元"));
            DEPLETED_URANIUM_CELL = new SlimefunItemStack("LAB_DEPLETED_URANIUM_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDgwNjAzMDdlOTk0NjgxMjcxY2MzYWRlMzVlNTUwM2I0MWI0OWNiMjE4NmM4NDQ4NWZkN2Q5MjVmYWU4MGMzNSJ9fX0="),
                            "&2熔融枯竭铀单元",
                            "",
                            "&2辐射等级: 极高",
                            "&4需要穿着防护服使用"));
            DEPLETED_BOOSTED_URANIUM_CELL = new SlimefunItemStack("LAB_DEPLETED_BOOSTED_URANIUM_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDgwNjAzMDdlOTk0NjgxMjcxY2MzYWRlMzVlNTUwM2I0MWI0OWNiMjE4NmM4NDQ4NWZkN2Q5MjVmYWU4MGMzNSJ9fX0="),
                            "&2熔融枯竭高能铀单元",
                            "",
                            "&2辐射等级: 极高",
                            "&4需要穿着防护服使用"));

            MELTING_MACHINE = new SlimefunItemStack("LAB_MELTING_MACHINE",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODI5NmYwOTI1MjRhZTljMmEyZTg3ODgxY2I0MTVhZGIzNThkNmNiNzczYzg1ZGM5NzIwMmZlZmI3NTRjMSJ9fX0="),
                            "&c熔炼机",
                            "",
                            "&f高温加热输入的固体物质",
                            "&f使其熔为流体",
                            "",
                            "&6高级机器",
                            "&8⇨ &e⚡ &7512 J 缓存",
                            "&8⇨ &e⚡ &764 J/s"));
            String fuel =
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk5YjgyZTBlMzdkYzJhYmU2ZWZhMTkwNzU1NGRhODhjZTQzYWFhNTBlNDE1NzY0YTgxN2U5OTc1NmNhNzg1NSJ9fX0=";
            ENRICHED_FUSED_SALT_REACTOR_FUEL = new SlimefunItemStack("LAB_ENRICHED_FUSED_SALT_REACTOR_FUEL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(fuel),
                            "&a浓缩熔融氟化铀",
                            "",
                            "&f这三种化合物的化学式应该是这样的: LiF(氟化锂), BeF₂(氟化铍), UF₄(四氟化铀)",
                            "",
                            "&2辐射等级: 极高",
                            "&4需要穿着防护服使用"));
            ENRICHED_BOOSTED_FUSED_SALT_REACTOR_FUEL = new SlimefunItemStack("LAB_ENRICHED_BOOSTED_FUSED_SALT_REACTOR_FUEL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(fuel),
                            "&a浓缩熔融氟化高能铀",
                            "",
                            "&f这四种化合物的化学式应该是这样的: LiF(氟化锂), BeF₂(氟化铍), UF₄(四氟化铀), PuF₄(四氟化钚)",
                            "",
                            "&2辐射等级: 极高",
                            "&4需要穿着防护服使用"));

            FUSED_SALT_REACTOR_COOLANT_CELL = new SlimefunItemStack("LAB_FUSED_SALT_REACTOR_COOLANT_CELL",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM3NjBjNTc2YTA4OGNiMjljZWIyNmUzMTQzYjdmM2M0NDZjZDZlNWM1MGJmMzMxNTE2NjFjZTYzNmQwNTk3ZSJ9fX0="),
                            "&c熔盐反应器冷却单元"));
            FUSED_SALT_REACTOR = new SlimefunItemStack("LAB_FUSED_SALT_REACTOR",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc0MTM2YTk1NzY3MzQ2ZjU2NDBkMDlkYjM2OGExODkyNjE1YTVlOWIyMWI1MGUyOWY0N2ZkMzc0YjFiOTNhMSJ9fX0="),
                            "&c熔盐反应器",
                            "",
                            "&r需要冷却!",
                            "&8⇨ &b必须完全浸入水中",
                            "&8⇨ &b必须配备熔盐反应器冷却单元",
                            "",
                            "&4终极发电机",
                            "&8⇨ &e⚡ &716384 J 缓存",
                            "&8⇨ &e⚡ &7896 J/s",
                            "&8⇨ &4会使周围未身着防护服的生物遭受火焰伤害"));

            MELTINGM = new RecipeType(
                    new NamespacedKey(Main.instance, "meltingm"),
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODI5NmYwOTI1MjRhZTljMmEyZTg3ODgxY2I0MTVhZGIzNThkNmNiNzczYzg1ZGM5NzIwMmZlZmI3NTRjMSJ9fX0="),
                            "&c熔炼机",
                            new String[] {"", "&a通过熔炼机将固体融为流体"}));
        } catch (Exception e) {
            Main.debugException(e);
        }
    }

    public Nuclear() {
        ItemStack graphites = Resource.GRAPHITE.clone();
        graphites.setAmount(Variables.cfg.getInt("items.graphite-core.graphite-number"));
        (new SlimefunItem(c, GRAPHITE_CORE, RecipeType.COMPRESSOR, U.firsts(graphites))).register(Main.instance);

        Variables.UnplaceableItems.add(GRAPHITE_CORE);

        (new SlimefunItem(c, LEAD_CELL_EMPTY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT,
                    SlimefunItems.LEAD_INGOT, U.mat(Material.GLASS_PANE), SlimefunItems.LEAD_INGOT,
                    SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT
                }))
                .register(Main.instance);

        registerCell(URANIUM_FLUID_CELL, SlimefunItems.URANIUM);
        registerCell(BOOSTED_URANIUM_FLUID_CELL, SlimefunItems.BOOSTED_URANIUM);

        registerCell(BERYLLIUM_FLUID_CELL, Resource.BERYLLIUM_INGOT);
        registerCell(LITHIUM_FLUID_CELL, Resource.LITHIUM_INGOT);

        registerFluorideCell(FLUORIDE_URANIUM_FLUID_CELL, URANIUM_FLUID_CELL);
        registerFluorideCell(FLUORIDE_BOOSTED_URANIUM_FLUID_CELL, BOOSTED_URANIUM_FLUID_CELL);

        registerFluorideCell(FLUORIDE_BERYLLIUM_FLUID_CELL, BERYLLIUM_FLUID_CELL);
        registerFluorideCell(FLUORIDE_LITHIUM_FLUID_CELL, LITHIUM_FLUID_CELL);

        (new RadioactiveItem(
                        c,
                        Radioactivity.HIGH,
                        ENRICHED_FUSED_SALT_REACTOR_FUEL,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            FLUORIDE_URANIUM_FLUID_CELL, FLUORIDE_BERYLLIUM_FLUID_CELL, FLUORIDE_LITHIUM_FLUID_CELL
                        }))
                .register(Main.instance);

        (new RadioactiveItem(
                        c,
                        Radioactivity.HIGH,
                        ENRICHED_BOOSTED_FUSED_SALT_REACTOR_FUEL,
                        RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                            FLUORIDE_BOOSTED_URANIUM_FLUID_CELL,
                            FLUORIDE_BERYLLIUM_FLUID_CELL,
                            FLUORIDE_LITHIUM_FLUID_CELL
                        }))
                .register(Main.instance);

        (new SlimefunItem(c, FUSED_SALT_REACTOR_COOLANT_CELL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.REACTOR_COOLANT_CELL, Resource.POTASSIUM_INGOT, Resource.SODIUM_INGOT
                }))
                .register(Main.instance);

        (new AdvancedAContainer(c, MELTING_MACHINE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRIC_FURNACE_2, SlimefunItems.LEAD_INGOT,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD, Tech.HEAT_CONDUCTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    Tech.SUPER_HEATING_COIL, Tech.SUPER_HEATING_COIL, Tech.SUPER_HEATING_COIL
                }) {

                    public void registerDefaultRecipes() {
                        int time = Variables.cfg.getInt("items.melting-machine.time");

                        for (ItemStack[] iti : Nuclear.meltingm_r.keySet()) {
                            registerRecipe(time, iti, Nuclear.meltingm_r.get(iti));
                        }
                    }

                    public int getSpeed() {
                        return 1;
                    }

                    public ItemStack getProgressBar() {
                        return U.mat(Material.LAVA_BUCKET);
                    }

                    public String getMachineIdentifier() {
                        return getId();
                    }

                    public String getInventoryTitle() {
                        return getItemName();
                    }

                    public int getEnergyConsumption() {
                        return 32;
                    }

                    public int getCapacity() {
                        return 512;
                    }
                })
                .register(Main.instance);

        (new AdvancedAContainer(c, FLUORIDE_MACHINE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR,
                            SlimefunItems.BASIC_CIRCUIT_BOARD,
                    Tech.ALUMAG_ALLOY, SlimefunItems.ELECTRIC_FURNACE_2, SlimefunItems.PLASTIC_SHEET,
                    SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HEATING_COIL
                }) {

                    public void registerDefaultRecipes() {
                        int time = Variables.cfg.getInt("items.fluoride-machine.time");

                        for (ItemStack[] iti : Nuclear.fluoridem_r.keySet()) {
                            registerRecipe(time, iti, Nuclear.fluoridem_r.get(iti));
                        }
                    }

                    public int getSpeed() {
                        return 1;
                    }

                    public ItemStack getProgressBar() {
                        return new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                    }

                    public String getMachineIdentifier() {
                        return getId();
                    }

                    public String getInventoryTitle() {
                        return getItemName();
                    }

                    public int getEnergyConsumption() {
                        return 16;
                    }

                    public int getCapacity() {
                        return 512;
                    }
                })
                .register(Main.instance);

        (new SlimefunItem(c, DEPLETED_URANIUM_CELL, fusedSaltReactor_r, new ItemStack[] {Nuclear.ENRICHED_FUSED_SALT_REACTOR_FUEL})).register(Main.instance);
        (new SlimefunItem(c, DEPLETED_BOOSTED_URANIUM_CELL, fusedSaltReactor_r, new ItemStack[] {Nuclear.ENRICHED_BOOSTED_FUSED_SALT_REACTOR_FUEL})).register(Main.instance);

        (new AdvancedAContainer(c, THERMAL_CENTRIFUGE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                            SlimefunItems.REINFORCED_ALLOY_INGOT,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD, Tech.SUPER_ELECTRO_MOTOR,
                            SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                    Tech.SUPER_HEATING_COIL, SlimefunItems.BIG_CAPACITOR, Tech.SUPER_HEATING_COIL
                }) {

                    public void registerDefaultRecipes() {
                        registerRecipe(300, new ItemStack[] {Nuclear.DEPLETED_URANIUM_CELL}, new ItemStack[] {
                            SlimefunItems.NEPTUNIUM
                        });
                        registerRecipe(300, new ItemStack[] {Nuclear.DEPLETED_BOOSTED_URANIUM_CELL}, new ItemStack[] {
                            SlimefunItems.PLUTONIUM
                        });

                        registerRecipe(
                                10,
                                new ItemStack[] {
                                    Nuclear.FLUORIDE_BERYLLIUM_FLUID_CELL, Nuclear.FLUORIDE_LITHIUM_FLUID_CELL
                                },
                                new ItemStack[] {Nuclear.FUSED_SALT_REACTOR_COOLANT_CELL});
                    }

                    public int getSpeed() {
                        return 1;
                    }

                    public ItemStack getProgressBar() {
                        return SlimefunItems.HEATING_COIL;
                    }

                    public String getMachineIdentifier() {
                        return getId();
                    }

                    public String getInventoryTitle() {
                        return getItemName();
                    }

                    public int getEnergyConsumption() {
                        return 32;
                    }

                    public int getCapacity() {
                        return 768;
                    }
                })
                .register(Main.instance);

        (new Reactor(c, FUSED_SALT_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                    Tech.ULTIMATE_CIRCUIT_BOARD, SlimefunItems.CARBONADO_EDGED_CAPACITOR, SlimefunItems.FLUID_PUMP,
                    SlimefunItems.REINFORCED_PLATE, GRAPHITE_CORE, SlimefunItems.REINFORCED_PLATE,
                    Tech.LEAD_IRON_ALLOY, Tech.REINFORCED_IRIDIUM_IRON_PLATE, Tech.LEAD_IRON_ALLOY
                }) {

                    public void registerDefaultFuelTypes() {
                        registerFuel(new MachineFuel(
                                1400, Nuclear.ENRICHED_FUSED_SALT_REACTOR_FUEL, Nuclear.DEPLETED_URANIUM_CELL));
                        registerFuel(new MachineFuel(
                                1700,
                                Nuclear.ENRICHED_BOOSTED_FUSED_SALT_REACTOR_FUEL,
                                Nuclear.DEPLETED_BOOSTED_URANIUM_CELL));
                    }

                    public ItemStack getProgressBar() {
                        return U.mat(Material.SUGAR);
                    }

                    public String getInventoryTitle() {
                        return getItemName();
                    }

                    public int getEnergyProduction() {
                        return 448;
                    }

                    public ItemStack getCoolant() {
                        return Nuclear.FUSED_SALT_REACTOR_COOLANT_CELL;
                    }

                    @NotNull @Override
                    public ItemStack getFuelIcon() {
                        return Nuclear.ENRICHED_FUSED_SALT_REACTOR_FUEL;
                    }

                    public void extraTick(final Location l) {
                        Bukkit.getScheduler()
                                .scheduleSyncDelayedTask(
                                        Variables.plug,
                                        new BukkitRunnable() {

                                            public void run() {
                                                if (U.chance(100, 5)) {

                                                    try {
                                                        l.getWorld()
                                                                .spawnParticle(
                                                                        Particle.BLOCK_CRACK,
                                                                        l.getBlock()
                                                                                .getLocation(),
                                                                        1,
                                                                        0.5F,
                                                                        0.0F,
                                                                        1.0F,
                                                                        Material.ORANGE_TERRACOTTA);
                                                    } catch (Exception e) {
                                                        Main.debugException(e);
                                                    }
                                                }
                                                for (Entity entity : l.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
                                                    if (entity instanceof LivingEntity) {
                                                        if (entity instanceof Player p) {

                                                            if (SlimefunUtils.isItemSimilar(
                                                                            SlimefunItems.SCUBA_HELMET,
                                                                            p.getInventory()
                                                                                    .getHelmet(),
                                                                            true)
                                                                    && SlimefunUtils.isItemSimilar(
                                                                            SlimefunItems.HAZMAT_CHESTPLATE,
                                                                            p.getInventory()
                                                                                    .getChestplate(),
                                                                            true)
                                                                    && SlimefunUtils.isItemSimilar(
                                                                            SlimefunItems.HAZMAT_LEGGINGS,
                                                                            p.getInventory()
                                                                                    .getLeggings(),
                                                                            true)
                                                                    && SlimefunUtils.isItemSimilar(
                                                                            SlimefunItems.HAZMAT_BOOTS,
                                                                            p.getInventory()
                                                                                    .getBoots(),
                                                                            true)) {
                                                                return;
                                                            }

                                                            if (SlimefunUtils.isItemSimilar(
                                                                            ArmorWeapon.REINFORCED_SCUBA_HELMET,
                                                                            p.getInventory()
                                                                                    .getHelmet(),
                                                                            true)
                                                                    && SlimefunUtils.isItemSimilar(
                                                                            ArmorWeapon
                                                                                    .REINFORCED_HAZMATSUIT_CHESTPLATE,
                                                                            p.getInventory()
                                                                                    .getChestplate(),
                                                                            true)
                                                                    && SlimefunUtils.isItemSimilar(
                                                                            ArmorWeapon.REINFORCED_HAZMATSUIT_LEGGINGS,
                                                                            p.getInventory()
                                                                                    .getLeggings(),
                                                                            true)
                                                                    && SlimefunUtils.isItemSimilar(
                                                                            SlimefunItems.HAZMAT_BOOTS,
                                                                            p.getInventory()
                                                                                    .getBoots(),
                                                                            true)) {
                                                                return;
                                                            }
                                                        }

                                                        if (entity instanceof org.bukkit.entity.ArmorStand) return;
                                                        entity.setFireTicks(Variables.cfg.getInt(
                                                                "items.fused-salt-reactor.fire-ticks"));
                                                    }
                                                }
                                            }
                                        },
                                        0L);
                    }

                    public int getCapacity() {
                        return 16384;
                    }
                })
                .register(Main.instance);
    }

    private void registerCell(SlimefunItemStack item, ItemStack inside) {
        (new RadioactiveItem(c, Radioactivity.HIGH, item, MELTINGM, new ItemStack[] {inside, LEAD_CELL_EMPTY}))
                .register(Main.instance);
        meltingm_r.put(new ItemStack[] {inside, LEAD_CELL_EMPTY}, new ItemStack[] {item});
    }

    private void registerFluorideCell(SlimefunItemStack item, ItemStack origin) {
        (new RadioactiveItem(c, Radioactivity.VERY_HIGH, item, FLUORIDEM, new ItemStack[] {origin, Resource.CAROBBIITE}))
                .register(Main.instance);
        fluoridem_r.put(new ItemStack[] {origin, Resource.CAROBBIITE}, new ItemStack[] {item});
    }
}
