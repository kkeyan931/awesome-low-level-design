package parkinglot_;

import parkinglot_.exception.ParkingLotException;
import parkinglot_.feesstrategy.FeesStrategy;
import parkinglot_.parkingslot.ParkingSlot;
import parkinglot_.vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ParkingLot {

    private final List<ParkingFloor> parkingFloors;
    private final ConcurrentMap<String, Ticket> ticketsMap = new ConcurrentHashMap<>();
    private final FeesStrategy feesStrategy;

    public ParkingLot(List<ParkingFloor> parkingFloors, FeesStrategy feesStrategy) {
        this.parkingFloors = parkingFloors;
        this.feesStrategy = feesStrategy;
    }

    public Ticket park(Vehicle vehicle) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            ParkingSlot availableParkingSlot = parkingFloor.getNextAvailableParkingSlot(vehicle.getType());
            if (availableParkingSlot != null) {
                String id = UUID.randomUUID().toString();
                long entryTimestamp = new Date().getTime();
                try {
                    availableParkingSlot.park(vehicle);
                } catch(ParkingLotException e) {
                    continue;
                }
                Ticket ticket = new Ticket(id, entryTimestamp, vehicle, availableParkingSlot);
                ticketsMap.put(id, ticket);
                return ticket;
            }
        }
        throw new ParkingLotException("Parking is full " + vehicle.getLicensePlate());
    }

    public double unpark(Ticket ticket) {
        Ticket ticketEntry = ticketsMap.get(ticket.getId());
        if (ticketEntry == null) {
            throw new ParkingLotException("Ticket with id " + ticket.getId() + " not found");
        }
        ParkingSlot parkingSlot = ticketEntry.getParkingSlot();
        parkingSlot.unpark(ticketEntry.getVehicle());
        return this.feesStrategy.calculateFee(ticket);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ParkingFloor parkingFloor : parkingFloors) {
            builder.append("Floor ").append(parkingFloor.getFloorNumber()).append(" ").append(parkingFloor).append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }

    public Map<String, Integer> getStats() {
        Map<String, Integer> stats = new HashMap<>();
        for(Map.Entry<String, Ticket> entry : ticketsMap.entrySet()) {
            Ticket ticket = entry.getValue();
            stats.put(ticket.getVehicle().getType().name(), stats.getOrDefault(ticket.getVehicle().getType().name(), 0) + 1);
        }
        return stats;
    }
}
