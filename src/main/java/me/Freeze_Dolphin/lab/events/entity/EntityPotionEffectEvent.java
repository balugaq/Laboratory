package me.Freeze_Dolphin.lab.events.entity;

import lombok.Getter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
public class EntityPotionEffectEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final PotionEffect oldEffect;

    @Getter
    private final PotionEffect newEffect;

    @Getter
    private final Cause cause;

    @Getter
    private final Action action;

    private boolean cancel;

    @Getter
    private boolean override;

    public EntityPotionEffectEvent(
            LivingEntity livingEntity,
            PotionEffect oldEffect,
            PotionEffect newEffect,
            Cause cause,
            Action action,
            boolean override) {
        super(livingEntity);
        this.oldEffect = oldEffect;
        this.newEffect = newEffect;
        this.cause = cause;
        this.action = action;
        this.override = override;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public PotionEffectType getModifiedType() {
        return (this.oldEffect == null)
                ? ((this.newEffect == null) ? null : this.newEffect.getType())
                : this.oldEffect.getType();
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public enum Action {
        ADDED,
        CHANGED,
        CLEARED,
        REMOVED
    }

    public enum Cause {
        AREA_EFFECT_CLOUD,
        ARROW,
        ATTACK,
        BEACON,
        COMMAND,
        CONDUIT,
        CONVERSION,
        DEATH,
        DOLPHIN,
        EXPIRATION,
        FOOD,
        ILLUSION,
        MILK,
        PLUGIN,
        POTION_DRINK,
        POTION_SPLASH,
        SPIDER_SPAWN,
        TOTEM,
        TURTLE_HELMET,
        UNKNOWN,
        VILLAGER_TRADE
    }
}
