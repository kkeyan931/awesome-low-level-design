package vendingmachine_.overengineered.priceservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PriceManager {
    private final Map<String, Integer> priceMap;

    private PriceManager() {
        this.priceMap = new ConcurrentHashMap<>();
    }

    private static class PriceMangerSingleton {
        private static final PriceManager INSTANCE = new PriceManager();
    }

    public static PriceManager getInstance() {
        return PriceMangerSingleton.INSTANCE;
    }

    public void addPrice(String id, Integer price) {
        priceMap.put(id, price);
    }

    public Integer getPrice(String id) {
        if (!priceMap.containsKey(id)) {
            throw new IllegalArgumentException("Price not found for id " + id);
        }
        return priceMap.get(id);
    }

    public void removePrice(String id) {
        priceMap.remove(id);
    }
}
