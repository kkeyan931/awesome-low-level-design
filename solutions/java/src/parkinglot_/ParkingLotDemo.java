package parkinglot_;

import parkinglot_.exception.ParkingLotException;
import parkinglot_.feesstrategy.FeesStrategy;
import parkinglot_.feesstrategy.impl.RefinedFeesStrategy;
import parkinglot_.parkingslot.ParkingSlot;
import parkinglot_.parkingslot.impl.ParkingSlotSafe;
import parkinglot_.parkingslot.impl.ParkingSlotUnSafe;
import parkinglot_.vehicle.*;

import java.util.*;

public class ParkingLotDemo {
    public static final int NUMBER_OF_FLOORS = 2;
    public static final int NUMBER_OF_SLOTS_PER_FLOOR = 10;
    public static final int NUMBER_OF_VEHICLE = 1000;
    public static final int SIMULATION_SIZE = 100;
    public static final boolean THREAD_SAFE_RUN = true;
    public static final boolean PRINT_OUTPUT = false;


    public static final Random random = new Random();

    public static void main(String[] args) {

        if (!PRINT_OUTPUT) {
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                @Override
                public void write(int b) {
                    // Do nothing (silently discard all output)
                }
            }));
        }

        int simulationSize = SIMULATION_SIZE;
        while (simulationSize > 0) {
            simulationSize--;
            simulate(THREAD_SAFE_RUN);
        }
    }

    public static void simulate(boolean safe) {
        VehicleType[] values = VehicleType.values();

        ParkingFloor[] parkingFloors = new ParkingFloor[NUMBER_OF_FLOORS];

        Map<String, Integer> slotCount = new HashMap<>();
        for (int floor = 0; floor < NUMBER_OF_FLOORS; ++floor) {
            ParkingSlot[] parkingSlots = new ParkingSlot[NUMBER_OF_SLOTS_PER_FLOOR];
            for (int i = 0; i < NUMBER_OF_SLOTS_PER_FLOOR; ++i) {
                VehicleType vehicleType = values[random.nextInt(values.length)];
                slotCount.put(vehicleType.name(), slotCount.getOrDefault(vehicleType.name(), 0) + 1);
                ParkingSlot parkingSlot;
                if (safe) {
                    parkingSlot = new ParkingSlotSafe(vehicleType, floor + "_" + i);
                } else {
                    parkingSlot = new ParkingSlotUnSafe(vehicleType, floor + "_" + i);
                }
                parkingSlots[i] = parkingSlot;
            }
            parkingFloors[floor] = new ParkingFloor(Integer.toString(floor), new ArrayList<>(List.of(parkingSlots)));
        }
        FeesStrategy feesStrategy = new RefinedFeesStrategy();

        ParkingLot parkingLot = new ParkingLot(new ArrayList<>(List.of(parkingFloors)), feesStrategy);

        System.out.println("\n--------FLOOR STRUCTURE START-----\n");
        System.out.println(parkingLot);
        System.out.println("\n--------FLOOR STRUCTURE END-----\n");

        Thread[] threads = new Thread[NUMBER_OF_VEHICLE];
        Map<String, Integer> vehicleCount = new HashMap<>();
        for (int vehicleNumber = 0; vehicleNumber < NUMBER_OF_VEHICLE; ++vehicleNumber) {
            Vehicle vehicle = getRandomVehicle(Integer.toString(vehicleNumber));
            vehicleCount.put(vehicle.getType().name(), vehicleCount.getOrDefault(vehicle.getType().name(), 0) + 1);
            threads[vehicleNumber] = new Thread(() -> {
                try {
                    parkingLot.park(vehicle);
                    System.out.printf("Vehicle parked: %s %n", vehicle);
                } catch (ParkingLotException e) {
                    System.out.println(e.getMessage());
                }
            });
        }

        System.out.println("\n--------VEHICLE COUNT VS SLOT COUNT START-----\n");
        for (Map.Entry<String, Integer> entry : vehicleCount.entrySet()) {
            System.out.println(entry.getKey() + " vehicle => " + entry.getValue() + " slot => " + slotCount.get(entry.getKey()));
        }
        System.out.println("\n--------VEHICLE COUNT VS SLOT COUNT END-----\n");


        System.out.println("\n--------VEHICLE PARKING SIMULATION START-----\n");
        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("\n--------VEHICLE PARKING SIMULATION END-----\n");


        System.out.println("\n--------FLOOR STRUCTURE START-----\n");
        System.out.println(parkingLot);
        System.out.println("\n--------FLOOR STRUCTURE END-----\n");


        Map<String, Integer> stats = parkingLot.getStats();

        System.out.println("\n--------VEHICLE PARKED COUNT VS SLOT COUNT START-----\n");
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + " vehicle => " + entry.getValue() + " slot => " + slotCount.get(entry.getKey()));
            if (entry.getValue().compareTo(slotCount.get(entry.getKey())) > 0) {
                throw new ParkingLotException("Race condition detected");
            }
        }
    }

    private static Vehicle getRandomVehicle(String licensePlat) {
        int choice = random.nextInt(3); // 0, 1, or 2
        return switch (choice) {
            case 0 -> new Car(licensePlat);
            case 1 -> new Bike(licensePlat);
            case 2 -> new Truck(licensePlat);
            default -> throw new ParkingLotException("Unexpected random value: " + choice);
        };
    }
}
