package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.subsystem.IStabilizerSubsystem;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetStabilizerStatus implements IFunction {
    @Override
    public String getName() {
        return "getStabilizerStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        try {
            final IStabilizerSubsystem stabilizerSubsystem = (IStabilizerSubsystem) FunctionHelper.getTardisSubsystem(tardis, EnumSubsystemType.STABILIZER);
            return MethodResult.of(stabilizerSubsystem.isControlActivated(tardis));
        } catch (IllegalArgumentException e) {
            throw new LuaException(e.getMessage());
        }
    }
}
