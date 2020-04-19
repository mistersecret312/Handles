package com.vandendaelen.handles.blocks.tiles;

import com.vandendaelen.handles.blocks.HandlesBlocks;
import com.vandendaelen.handles.exceptions.NotATardisException;
import com.vandendaelen.handles.helpers.DimensionHelper;
import com.vandendaelen.handles.misc.TardisInterfacePeripheral;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.tileentities.ConsoleTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TardisInterfaceTile extends TileEntity implements IPeripheralTile {

    public TardisInterfaceTile() {
        super(HandlesBlocks.TARDISINTERFACE_TILE);
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        return super.write(tag);
    }

    private ConsoleTile getTardis() throws NotATardisException {
        if(this.getWorld().dimension.getType().equals(TDimensions.TARDIS)) throw new NotATardisException();
        return (ConsoleTile) world.loadedTileEntityList.stream().filter(tileEntity -> tileEntity instanceof ConsoleTile).findFirst().get();
    }

    public Object[] getTardisLocation() throws NotATardisException {
        BlockPos pos = getTardis().getLocation();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }

    public Object[] getTardisDestination() throws NotATardisException{
        BlockPos pos = getTardis().getDestination();
        return new Object[]{pos.getX(), pos.getY(), pos.getZ()};
    }

    public Object[] setTardisDestination(int x, int y, int z) throws NotATardisException{
        ConsoleTile tardis = getTardis();
        tardis.setDestination(tardis.getDestinationDimension(), new BlockPos(x, y, z));
        return null;
    }

    public Object[] setTardisDimensionDestination(int id) throws NotATardisException {
        ConsoleTile tardis = getTardis();
        tardis.setDestination(DimensionHelper.getDimension(id), tardis.getDestination());
        return null;
    }

    public Object[] getDimensions(){
        return DimensionHelper.getPrettyDimensionList().toArray();
    }

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull Direction direction) {
        return new TardisInterfacePeripheral(this);
    }
}
