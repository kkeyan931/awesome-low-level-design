package vendingmachine_.overengineered.inventoryservice;

import vendingmachine_.overengineered.exception.VendingMachineException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryManager {
    private final Map<String, Integer> inventoryMap;

    private InventoryManager() {
        inventoryMap = new ConcurrentHashMap<>();
    }

    private static class InventoryManagerSingleton {
        private static final InventoryManager INSTANCE = new InventoryManager();
    }
    public static InventoryManager getInstance() {
        return InventoryManagerSingleton.INSTANCE;
    }

    public void updateInventory(String id, Integer quantity) {
        inventoryMap.put(id, quantity);
    }

    public Integer getInventory(String id) {
        return inventoryMap.get(id);
    }
    public void checkInventory(String id, Integer quantity) {
        if (!inventoryMap.containsKey(id)) {
            throw new VendingMachineException("Inventory does not exist for the product " + id);
        }
        Integer inventoryCount = inventoryMap.get(id);
        if (inventoryCount < quantity) {
            throw new VendingMachineException("Insufficient inventory for the product " + id);
        }
    }
    public void allocateInventory(String id, Integer quantity) {
        if (!inventoryMap.containsKey(id)) {
            throw new VendingMachineException("Inventory does not exist for the product " + id);
        }
        Integer inventoryCount = inventoryMap.get(id);

        if (inventoryCount < quantity) {
            throw new VendingMachineException("Insufficient inventory for the product " + id);
        }
        inventoryMap.put(id, inventoryCount - quantity);
    }

    public void deallocateInventory(String id, Integer quantity) {
        if (!inventoryMap.containsKey(id)) {
            throw new VendingMachineException("Inventory does not exist for the product " + id);
        }
        inventoryMap.put(id, inventoryMap.get(id) + quantity);
    }
}
