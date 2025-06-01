package parkinglot_.parkingslot.impl;

import parkinglot_.parkingslot.ParkingSlot;
import parkinglot_.vehicle.VehicleType;

public class ParkingSlotUnSafe extends ParkingSlot {
    private boolean occupied = false;
    public ParkingSlotUnSafe(VehicleType parkingSlotType, String slotNumber) {
        super(parkingSlotType, slotNumber);
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public boolean compareAndSetOccupied(boolean expectedValue, boolean newValue) {
        this.occupied = newValue;
        return true;
    }
}
