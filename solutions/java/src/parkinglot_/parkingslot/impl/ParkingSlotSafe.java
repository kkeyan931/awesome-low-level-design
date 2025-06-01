package parkinglot_.parkingslot.impl;

import parkinglot_.parkingslot.ParkingSlot;
import parkinglot_.vehicle.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;


public class ParkingSlotSafe extends ParkingSlot {
    private final AtomicBoolean occupied;

    public ParkingSlotSafe(VehicleType parkingSlotType, String slotNumber) {
        super(parkingSlotType, slotNumber);
        occupied = new AtomicBoolean(false);
    }

    @Override
    public boolean isOccupied() {
        return occupied.get();
    }

    @Override
    public boolean compareAndSetOccupied(boolean expectedValue, boolean newValue) {
        return occupied.compareAndSet(expectedValue, newValue);
    }
}
