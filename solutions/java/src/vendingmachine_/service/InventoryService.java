package vendingmachine_.service;

import vendingmachine_.data.Product;
import vendingmachine_.VendingMachineException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {
    Map<Product, Integer> inventoryMap;

    private InventoryService() {
        this.inventoryMap = new ConcurrentHashMap<>();
    }

    private static class SingletonHolder {
        private static final InventoryService INSTANCE = new InventoryService();
    }

    public static InventoryService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean checkInventory(Product product, Integer quantity) {
        if (!inventoryMap.containsKey(product)) {
            throw new VendingMachineException("The product " + product.getName() + " does not exist");
        }
        Integer inventory = inventoryMap.get(product);
        return inventory >= quantity;
    }

    public void addInventory(Product product, int quantity) {
        inventoryMap.put(product, inventoryMap.getOrDefault(product, 0) + quantity);
    }

    public void reduceInventory(Product product, int quantity) {
        if (!inventoryMap.containsKey(product)) {
            throw new VendingMachineException("The product " + product.getName() + " does not exist");
        }
        inventoryMap.put(product, inventoryMap.get(product) - quantity);
    }
}
