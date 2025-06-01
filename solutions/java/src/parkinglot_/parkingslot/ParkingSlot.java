package parkinglot_.parkingslot;


import parkinglot_.exception.ParkingLotException;
import parkinglot_.vehicle.Vehicle;
import parkinglot_.vehicle.VehicleType;

public abstract class ParkingSlot {
    private final VehicleType parkingSlotType;
    private final String slotNumber;

    private Vehicle vehicle;

    protected ParkingSlot(VehicleType parkingSlotType, String slotNumber) {
        this.parkingSlotType = parkingSlotType;
        this.slotNumber = slotNumber;
    }

    public VehicleType getParkingSlotType() {
        return parkingSlotType;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new ParkingLotException("vehicle should not be null");
        }
        if (isOccupied()) {
            throw new ParkingLotException("parking lot already occupied");
        }
        if (!parkingSlotType.equals(vehicle.getType())) {
            throw new ParkingLotException("parking lot type mismatch");
        }
        if (!compareAndSetOccupied(false, true)) {
            throw new ParkingLotException("parking lot already occupied");
        }
        this.vehicle = vehicle;
    }

    public void unpark(Vehicle vehicle) {
        if (!isOccupied()) {
            throw new ParkingLotException("parking lot not occupied");
        }
        if (!this.vehicle.getLicensePlate().equals(vehicle.getLicensePlate())) {
            throw new ParkingLotException("vehicle license plate mismatch");
        }
        if (!compareAndSetOccupied(true, false)) {
            throw new ParkingLotException("parking lot not occupied");
        }
        this.vehicle = null;
    }

    @Override
    public String toString() {
        String status = isOccupied() ? "closed" : "open";
        return getParkingSlotType() + " " + getSlotNumber() + " " + status;
    }

    public abstract boolean isOccupied();

    public abstract boolean compareAndSetOccupied(boolean expectedValue, boolean newValue);

    public Vehicle getVehicle() {
        return vehicle;
    }
}
