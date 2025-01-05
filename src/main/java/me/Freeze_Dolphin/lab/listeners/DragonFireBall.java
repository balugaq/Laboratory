package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.List;
import java.util.UUID;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.Main;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class DragonFireBall implements Listener {
    private static final int[] border =
            new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static final int[] plasma_slot = new int[] {10, 11, 12, 13, 14, 15, 16};

    private static final String inv_title = U.color("&8龙息发射器");

    public static void clean() {
        for (World w : Variables.plug.getServer().getWorlds()) {
            for (Entity ety : w.getEntities()) {
                if (ety.getType().equals(EntityType.ARMOR_STAND)) {
                    ArmorStand as = (ArmorStand) ety;
                    cleanArmorStand(as);
                }
            }
        }
    }

    private static void cleanArmorStand(ArmorStand as) {
        if (!as.isCustomNameVisible()) {

            try {
                if (as.getCustomName() == null) return;
                if (as.getCustomName().isEmpty()) return;
            } catch (NullPointerException ex) {
                Main.debugException(ex);
            }

            if (as.getCustomName().split(",")[0].equals("DFBDMGMDR")
                    && !as.hasGravity()
                    && !as.isInvulnerable()
                    && !as.isVisible()) {
                Main.debug("cleaned a dragon fire ball damage modifier(armor_stand) at: " + as.getLocation());
                as.remove();
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onClickAS(PlayerInteractEntityEvent e) {
        Entity ety = e.getRightClicked();
        if (ety.getType().equals(EntityType.ARMOR_STAND)) {
            ArmorStand as = (ArmorStand) ety;
            if (!as.isCustomNameVisible()
                    && as.getCustomName().split(",")[0].equals("DFBDMGMDR")
                    && !as.hasGravity()
                    && !as.isInvulnerable()
                    && !as.isVisible()) {
                Main.debug("cleaned a dragon fire ball damage modifier(armor_stand) at: " + as.getLocation() + " , by "
                        + e.getPlayer().getName());
                as.remove();
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasItem()) {

            Player p = e.getPlayer();
            ItemStack hand = p.getInventory().getItemInMainHand();

            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

                if (SlimefunUtils.isItemSimilar(hand, Lab.DRAGON_BREATH_GENERATOR, false)) {

                    if (p.isSneaking()) {

                        final ChestMenu m = new ChestMenu(inv_title);

                        m.setEmptySlotsClickable(true);
                        m.setPlayerInventoryClickable(true);

                        m.addMenuOpeningHandler(p13 -> {
                            if (!SlimefunUtils.isItemSimilar(
                                    p13.getInventory().getItemInMainHand(), Lab.DRAGON_BREATH_GENERATOR, false)) return;
                            ItemStack iimh = U.getItemInMainHand(p13);
                            int line = 0;
                            int plasmaLine = -1;
                            ItemMeta im = iimh.getItemMeta().clone();
                            List<String> lore = im.getLore();
                            for (String s : lore) {
                                line++;
                                if (s.matches("(.*)当前装载的等离子单元(.*)")) {
                                    plasmaLine = line - 1;
                                    break;
                                }
                            }
                            int plasma = Integer.parseInt(lore.get(plasmaLine).split(U.color(": &e"))[1]);

                            Main.debug("plasma loader: " + plasma);

                            int i = 0;
                            while (i < plasma) {
                                i++;
                                m.replaceExistingItem(i + 9, Tech.PLASMA_CELL.clone());
                            }

                            p13.playSound(p13.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 0.7F, 0.7F);
                        });

                        m.addMenuCloseHandler(p12 -> {
                            int plasma = 0;
                            byte b;
                            int i;
                            int[] arrayOfInt;
                            for (i = (arrayOfInt = DragonFireBall.plasma_slot).length, b = 0; b < i; ) {
                                int s = arrayOfInt[b];
                                if (SlimefunUtils.isItemSimilar(m.getItemInSlot(s), Tech.PLASMA_CELL, true)) {
                                    plasma++;
                                }
                                b++;
                            }

                            int plasmaC = plasma;
                            if (!SlimefunUtils.isItemSimilar(
                                    p12.getInventory().getItemInMainHand(), Lab.DRAGON_BREATH_GENERATOR, false)) return;
                            ItemStack iimh = U.getItemInMainHand(p12);
                            int line = 0;
                            int plasmaLine = -1;
                            ItemMeta im = iimh.getItemMeta().clone();
                            List<String> lore = im.getLore();
                            for (String s : lore) {
                                line++;
                                if (s.matches("(.*)当前装载的等离子单元(.*)")) {
                                    plasmaLine = line - 1;

                                    break;
                                }
                            }
                            Main.debug("plasma: " + plasmaC);

                            lore.set(plasmaLine, U.color("&f当前装载的等离子单元: &e") + plasmaC);
                            im.setLore(lore);
                            iimh.setItemMeta(im);
                            U.setItemInMainHand(p12, iimh);

                            p12.getWorld().playSound(p12.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0F, 1.0F);

                            if (plasmaC <= 0) return;
                            U.newDelayedTask(
                                    new BukkitRunnable() {

                                        public void run() {
                                            p12.getWorld()
                                                    .playSound(
                                                            p12.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0F, 2.0F);
                                        }
                                    },
                                    13L);
                        });
                        byte b;
                        int i;
                        int[] arrayOfInt;
                        for (i = (arrayOfInt = border).length, b = 0; b < i; ) {
                            int j = arrayOfInt[b];
                            m.addItem(j, new CustomItemStack(Material.GRAY_STAINED_GLASS_PANE, " "));
                            m.addMenuClickHandler(j, (p1, i1, it, ca) -> false);

                            b++;
                        }

                        m.open(p);

                    } else if (ItemEnergy.getStoredEnergy(hand) >= 24.0F) {

                        ItemStack tempItem = hand.clone();
                        ItemMeta im = tempItem.getItemMeta();
                        List<String> lore = im.getLore();
                        int line = 0;
                        int plasmaLine = -1;
                        for (String s : lore) {
                            line++;
                            if (s.matches("(.*)当前装载的等离子单元(.*)")) {
                                plasmaLine = line - 1;
                            }
                        }
                        if (plasmaLine <= 0) return;
                        int plasma = Integer.parseInt(lore.get(plasmaLine).split(U.color(": &e"))[1]);

                        if (plasma > 0) {

                            lore.set(plasmaLine, U.color("&f当前装载的等离子单元: &e") + (plasma - 1));
                            im.setLore(lore);
                            tempItem.setItemMeta(im);
                            U.setItemInMainHand(p, tempItem);
                            hand = p.getInventory().getItemInMainHand();
                            ItemEnergy.addStoredEnergy(hand, -24.0F);

                            DragonFireball dfb = p.launchProjectile(
                                    DragonFireball.class,
                                    p.getVelocity()
                                            .multiply(Variables.cfg.getDouble(
                                                    "items.dragon-breath-generator.vector-multiplier")));
                            dfb.setCustomNameVisible(false);
                            dfb.setCustomName("DragonBreathGenerator," + p.getUniqueId());
                            dfb.setGravity(true);
                            // dfb.setBounce(true);

                            p.getWorld().playSound(U.getHandLocation(p), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0F, 1.0F);
                        } else {

                            e.setCancelled(true);
                            p.sendMessage(U.getCfgMessage("messages.dragon-breath-generator.not-enough-plasma-cell"));
                        }

                    } else {

                        e.setCancelled(true);
                        p.sendMessage(U.getCfgMessage("messages.dragon-breath-generator.not-enough-power"));
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHit(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();

        if (projectile instanceof DragonFireball dfb) {
            if (dfb.getShooter() instanceof Player p) {
                if (dfb.getCustomName().matches("(.*),(.*)")
                        && p.getUniqueId()
                                .equals(UUID.fromString(dfb.getCustomName().split(",")[1]))) {
                    Location loc;
                    ArmorStand as;
                    if (e.getHitEntity() == null) {
                        loc = U.getBlockCenterLocation(e.getHitBlock());
                        as = (ArmorStand) e.getHitBlock().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                    } else {
                        loc = U.getBlockCenterLocation(
                                e.getHitEntity().getLocation().getBlock());
                        as = (ArmorStand) e.getHitEntity().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                    }

                    Main.debug(loc.toString());

                    as.setVisible(false);
                    as.setCustomNameVisible(false);
                    as.setCustomName("DFBDMGMDR," + p.getUniqueId());
                    as.setInvulnerable(true);
                    as.setGravity(false);

                    Main.debug(as + "[name]: " + as.getCustomName());

                    try {
                        loc.getWorld().spawnParticle(Particle.END_ROD, loc, 0, 0, 0, 0.05, 5);
                    } catch (Exception ex) {
                        Main.debugException(ex);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Main.debug("damage_cause: " + e.getCause().name());
        if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            Main.debugBreakpoint(1, getClass());
            for (Entity ety : e.getEntity().getNearbyEntities(5.0D, 5.0D, 5.0D)) {
                if (ety.getType().equals(EntityType.ARMOR_STAND)) {
                    Main.debugBreakpoint(2, getClass());
                    if (!ety.isCustomNameVisible()) {
                        Main.debugBreakpoint(3, getClass());
                        String cn = ety.getCustomName();

                        try {
                            if (cn == null) return;
                            if (cn.isEmpty()) return;
                        } catch (NullPointerException ex) {
                            Main.debugException(ex);
                        }

                        if (cn.matches("(.*),(.*)")) {
                            Main.debugBreakpoint(4, getClass());
                            String[] cnsp = cn.split(",");
                            if (cnsp[0].equals("DFBDMGMDR")) {

                                Main.debug("dfb dmg mdr");

                                if (e.getEntity() instanceof Player) {
                                    if (!Variables.cfg.getBoolean("items.dragon-breath-generator.ignore-pvp")
                                            && !ety.getWorld().getPVP()) {
                                        e.setCancelled(true);
                                        Main.debug("pvp is not allowed so cancelled");

                                        return;
                                    }
                                }

                                double orig_dmg = e.getDamage();
                                double dmg = e.getDamage()
                                        + Variables.cfg.getDouble("items.dragon-breath-generator.more-damage");
                                e.setDamage(dmg);

                                try {
                                    ety.getLocation()
                                            .getWorld()
                                            .spawnParticle(Particle.END_ROD, ety.getLocation(), 0, 0, 0, 0.05, 5);
                                } catch (Exception ex) {
                                    Main.debugException(ex);
                                }

                                Main.debug("original-dmg: " + orig_dmg + " , new-dmg: " + dmg);
                                ety.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();

        if (inv.getSize() == 27) {
            byte b;
            int i;
            int[] arrayOfInt;
            for (i = (arrayOfInt = border).length, b = 0; b < i; ) {
                int slot = arrayOfInt[b];
                if (inv.getItem(slot) == null) return;
                if (!inv.getItem(slot).hasItemMeta()) return;
                ItemMeta im = inv.getItem(slot).getItemMeta();
                if (!im.hasDisplayName()) return;
                if (!im.getDisplayName().equals(" ")) return;
                if (im.hasEnchants()) return;
                if (!inv.getItem(slot).getType().equals(Material.WHITE_STAINED_GLASS_PANE)) return;
                if (inv.getItem(slot).getData().getData() != 7) return;
                b++;
            }

            for (i = (arrayOfInt = plasma_slot).length, b = 0; b < i; ) {
                int slot = arrayOfInt[b];
                if (inv.getItem(slot) == null
                        || inv.getItem(slot).getType().equals(Material.AIR)
                        || SlimefunUtils.isItemSimilar(inv.getItem(slot), Tech.PLASMA_CELL, true)) {
                    b++;
                    continue;
                }
                return;
            }

            if (e.getCurrentItem() != null
                    && !e.getCurrentItem().getType().equals(Material.AIR)
                    && !SlimefunUtils.isItemSimilar(e.getCurrentItem(), Tech.PLASMA_CELL, true)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand as) {
            cleanArmorStand(as);
        }
    }

    @EventHandler
    public void onLeftClick(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ArmorStand as) {
            cleanArmorStand(as);
        }
    }
}
