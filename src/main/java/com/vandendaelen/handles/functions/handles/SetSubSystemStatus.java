package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import com.vandendaelen.handles.helpers.FunctionHelper;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.items.SubsystemItem;
import net.tardis.mod.tileentities.ConsoleTile;

public class SetSubSystemStatus implements IFunction {
    @Override
    public String getName() {
        return "setSubSystemStatus";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        final EnumSubsystemType subSystemPath = args.getEnum(0, EnumSubsystemType.class);
        final boolean status = args.getBoolean(1);

        try {
            final SubsystemItem subsystem = FunctionHelper.getSubsystem(tardis, subSystemPath);
            tardis.getLevel().getServer().tell(new TickDelayedTask(1,() -> subsystem.setActive(status)));
        }
        catch (IllegalArgumentException ignored){
            return MethodResult.of(false);
        }
        return MethodResult.of(true);
    }
}
