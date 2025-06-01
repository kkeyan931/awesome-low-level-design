package parkinglot_.feesstrategy.impl;

import parkinglot_.Ticket;
import parkinglot_.feesstrategy.FeesStrategy;

public class SimpleFeesStrategy implements FeesStrategy {
    private static final double DEFAULT_FEE_PER_HOUR = 10;
    @Override
    public double calculateFee(Ticket ticket) {
        long differenceInMilli = ticket.getStartTime() - ticket.getEndTime();
        return DEFAULT_FEE_PER_HOUR * ((double) (differenceInMilli) / (1000 * 60 * 60)) + 1;
    }
}
