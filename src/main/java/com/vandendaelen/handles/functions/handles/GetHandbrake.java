package com.vandendaelen.handles.functions.handles;

import com.vandendaelen.handles.functions.IFunction;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.tileentities.ConsoleTile;

public class GetHandbrake implements IFunction {
    @Override
    public String getName() {
        return "isHandbrakeFree";
    }

    @Override
    public MethodResult run(ConsoleTile tardis, IArguments args) throws LuaException {
        return MethodResult.of(!(tardis.getControl(HandbrakeControl.class).orElseThrow(() -> new LuaException("handbrakeControl not found")).isFree()));
    }
}
