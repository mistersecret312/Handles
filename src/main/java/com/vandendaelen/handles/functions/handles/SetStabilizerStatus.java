package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.subsystem.IStabilizerSubsystem;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetStabilizerStatus implements IFunction {
    @Override
    public String getName() {
        return "setStabilizerStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        boolean stabilizerStatus = args.getBoolean(0);

        try {
            final IStabilizerSubsystem stabilizerSubsystem = (IStabilizerSubsystem) FunctionHelper.getTardisSubsystem(tardis, EnumSubsystemType.STABILIZER);
            tardis.getLevel().getServer().tell(new TickDelayedTask(1,() -> stabilizerSubsystem.setControlActivated(stabilizerStatus, tardis)));

            return MethodResult.of(stabilizerStatus);
        } catch (IllegalArgumentException e) {
            throw new LuaException(e.getMessage());
        }
    }
}
