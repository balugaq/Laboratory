package me.Freeze_Dolphin.lab.listeners;

import org.bukkit.event.Listener;

public final class SapphireCapacity implements Listener {
    // todo: remove player move event listener and change to other event
    // todo: fix undefined variable: Lab.SAPPHIRE_CAPACITY
    /*
    @EventHandler
    public void sapphireCapacity(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        boolean hasSapphireCapacity = false;
        ItemStack ec1 = null;
        ArrayList<ItemStack> ci1 = new ArrayList<>();
        ItemStack[] ArmorContents1 = p.getInventory().getArmorContents();
        ItemStack[] StorageContents2 = p.getInventory().getStorageContents();
        for (ItemStack isa1 : StorageContents2) {
            if (SlimefunUtils.isItemSimilar(isa1, Lab.SAPPHIRE_CAPACITY, false)) {
                hasSapphireCapacity = true;
                if (ItemEnergy.getStoredEnergy(isa1) > 1F) {
                    ec1 = isa1;
                }
            }
            if (ItemEnergy.getMaxEnergy(isa1) > 0F) {
                if (!SlimefunUtils.isItemSimilar(isa1, Lab.SAPPHIRE_CAPACITY, false) && !SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_2, false)) {
                    if (ItemEnergy.getStoredEnergy(isa1) != ItemEnergy.getMaxEnergy(isa1)) {
                        ci1.add(isa1);
                    }
                }
            }
        }
        for (ItemStack isa1 : ArmorContents1) {
            if (SlimefunUtils.isItemSimilar(isa1, Lab.SAPPHIRE_CAPACITY, false)) {
                hasSapphireCapacity = true;
                if (ItemEnergy.getStoredEnergy(isa1) > 1F) {
                    ec1 = isa1;
                }
            }
            if (ItemEnergy.getMaxEnergy(isa1) > 0F) {
                if (!SlimefunUtils.isItemSimilar(isa1, Lab.SAPPHIRE_CAPACITY, false) && !SlimefunUtils.isItemSimilar(isa1, Lab.EMERALD_CAPACITY_2, false)) {
                    if (ItemEnergy.getStoredEnergy(isa1) != ItemEnergy.getMaxEnergy(isa1)) {
                        ci1.add(isa1);
                    }
                }
            }
        }
        if (hasSapphireCapacity && ec1 != null) {
            if (ItemEnergy.getStoredEnergy(ec1) >= (0.5F * ci1.size())) {
                ItemEnergy.chargeItem(ec1, (-0.5F * ci1.size()));
                for (ItemStack isb : ci1) {
                    ItemEnergy.chargeItem(isb, 0.5F);
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        for (Entity ety : e.getPlayer().getNearbyEntities(7, 7, 7)) {
            if (ety.getType().equals(EntityType.ARMOR_STAND)) {
                Block b = ety.getLocation().getBlock();
                Location loc = b.getLocation();
                List<Block> l = new ArrayList<>();
                l.add(new Location(loc.getWorld(), loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() + 1).getBlock());
                l.add(new Location(loc.getWorld(), loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() - 1).getBlock());
                l.add(new Location(loc.getWorld(), loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() + 1).getBlock());
                l.add(new Location(loc.getWorld(), loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() - 1).getBlock());
                for (Block bs : l) {
                    if (ChargeableBlock.isCapacitor(bs)) {
                        if (ChargeableBlock.getCharge(bs) >= 1) {
                            ItemStack[] StorageContents = e.getPlayer().getInventory().getStorageContents();
                            boolean hasCapacity = false;
                            int capacitySlot = 0;
                            for (ItemStack it : StorageContents) {
                                if (SlimefunUtils.isItemSimilar(it, Lab.SAPPHIRE_CAPACITY, false)) {
                                    hasCapacity = true;
                                    break;
                                } else {
                                    capacitySlot++;
                                }
                            }
                            if (hasCapacity) {
                                ChargeableBlock.setCharge(bs, ChargableBlock.getCharge(bs) - 1);
                                ItemEnergy.chargeItem(e.getPlayer().getInventory().getItem(capacitySlot), -1);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.SAPPHIRE_CAPACITY, false)) {
            e.setCancelled(true);
        }
    }

         */
}
