package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetSubSystemHealth implements IFunction {
    @Override
    public String getName() {
        return "getSubSystemHealth";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final EnumSubsystemType subSystem = args.getEnum(0, EnumSubsystemType.class);
        try {
            return MethodResult.of(FunctionHelper.getSubsystem(tardis, subSystem).getDamage(tardis.getSubsystem(subSystem).orElse(null).getItem(tardis)));
        }
        catch (IllegalArgumentException exception){
            return MethodResult.of(0F);
        }
    }
}
