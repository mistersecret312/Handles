package com.vandendaelen.handles.helpers;

import dan200.computercraft.api.lua.LuaException;
import net.tardis.mod.controls.AbstractControl;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.items.SubsystemItem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.upgrades.Upgrade;

public class FunctionHelper {
    public static SubsystemItem getSubsystem(ConsoleTile tardis, EnumSubsystemType type) throws LuaException {
        return tardis.getSubsystem(type).orElse(null);
    }

    public static Upgrade getUpgrade(ConsoleTile tardis, String upgradePath) throws LuaException {
        return tardis.getUpgrades()
                .stream()
                .filter(it -> it.getEntry().getRegistryName().getPath().equals(upgradePath))
                .findFirst().orElseThrow(() ->new LuaException("Upgrade " + upgradePath + " not found"));
    }

    public static <T extends AbstractControl> T getTardisControl(ConsoleTile tardis, Class<T> clazz) throws IllegalArgumentException {
        return tardis.getControl(clazz).orElseThrow(() -> new IllegalArgumentException(clazz.getName() + " not found"));
    }

    public static SubsystemItem getTardisSubsystem(ConsoleTile tardis, EnumSubsystemType type) throws IllegalArgumentException {
        return tardis.getSubsystem(type).orElseThrow(() -> new IllegalArgumentException(type.name() + " not found"));
    }
}
