package parkinglot_.feesstrategy.impl;

import parkinglot_.Ticket;
import parkinglot_.feesstrategy.FeesStrategy;
import parkinglot_.vehicle.VehicleType;

import java.util.Map;

public class RefinedFeesStrategy implements FeesStrategy {
    Map<VehicleType, Double> feesMap = Map.of(VehicleType.BIKE, 10.0, VehicleType.CAR, 20.0, VehicleType.TRUCK, 30.0);

    @Override
    public double calculateFee(Ticket ticket) {
        double feePerHour = feesMap.get(ticket.getVehicle().getType());
        long startTime = ticket.getStartTime();
        long endTime = ticket.getEndTime();
        long differenceInMilli = endTime - startTime;
        return feePerHour * ((double) differenceInMilli / 1000 * 60 * 60) + 1;
    }
}
