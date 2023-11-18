package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.items.SubsystemItem;
import net.tardis.mod.tileentities.ConsoleTile;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GetSubSystems implements IFunction {
    @Override
    public String getName() {
        return "getSubSystems";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        EnumSubsystemType[] values = EnumSubsystemType.values();
        Map<EnumSubsystemType, SubsystemItem> map = new HashMap<>();
        for(int i = 0; i<values.length-2; i++){
            map.put(values[i], tardis.getSubsystem(values[i]).orElse(null));
        }
        return MethodResult.of(map);
    }
}
