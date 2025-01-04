package me.Freeze_Dolphin.lab.android;

public enum EnderAndroidType {
    NONE,
    MINER,
    FARMER,
    ADVANCED_FARMER,
    FIGHTER,
    FISHERMAN,
    NON_FIGHTER;

    public boolean isType(EnderAndroidType type) {
        return !(!type.equals(NONE) && !type.equals(this) && (!type.equals(NON_FIGHTER) || equals(FIGHTER)));
    }
}
