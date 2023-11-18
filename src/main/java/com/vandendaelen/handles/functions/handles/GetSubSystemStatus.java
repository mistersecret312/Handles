package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetSubSystemStatus implements IFunction {
    @Override
    public String getName() {
        return "getSubSystemStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final EnumSubsystemType subSystemPath = args.getEnum(0, EnumSubsystemType.class);
        try {
            return MethodResult.of(FunctionHelper.getSubsystem(tardis, subSystemPath).isActive());
        }
        catch (IllegalArgumentException exception){
            return MethodResult.of(false);
        }
    }
}
