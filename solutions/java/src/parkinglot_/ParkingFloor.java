package parkinglot_;

import parkinglot_.parkingslot.ParkingSlot;
import parkinglot_.vehicle.VehicleType;

import java.util.List;

public class ParkingFloor {
    private final List<ParkingSlot> parkingSlots;
    private final String floorNumber;

    public ParkingFloor(final String floorNumber, final List<ParkingSlot> parkingSlots) {
        this.floorNumber = floorNumber;
        this.parkingSlots = parkingSlots;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public ParkingSlot getNextAvailableParkingSlot(VehicleType vehicleType) {
        for (ParkingSlot parkingSlot : parkingSlots) {
            if (!parkingSlot.isOccupied() && parkingSlot.getParkingSlotType() == vehicleType) {
                return parkingSlot;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (ParkingSlot parkingSlot : parkingSlots) {
            builder.append(parkingSlot).append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
}
