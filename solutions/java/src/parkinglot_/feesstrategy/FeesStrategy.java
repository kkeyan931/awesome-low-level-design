package parkinglot_.feesstrategy;

import parkinglot_.Ticket;

public interface FeesStrategy {
    double calculateFee(Ticket ticket);
}
