package parkinglot_;

import parkinglot_.parkingslot.ParkingSlot;
import parkinglot_.vehicle.Vehicle;

public class Ticket {
    private final String id;
    private final long startTime;
    private long endTime;
    private final Vehicle vehicle;
    private final ParkingSlot parkingSlot;

    public Ticket(String id, long startTime, Vehicle vehicle, ParkingSlot parkingSlot) {
        this.id = id;
        this.startTime = startTime;
        this.vehicle = vehicle;
        this.parkingSlot = parkingSlot;
    }

    public String getId() {
        return id;
    }
    public long getEndTime() {
        return endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public long getStartTime() {
        return startTime;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }
}
