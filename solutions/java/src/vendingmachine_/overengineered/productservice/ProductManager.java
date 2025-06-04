package vendingmachine_.overengineered.productservice;

import vendingmachine_.overengineered.data.Product;
import vendingmachine_.overengineered.data.ProductType;
import vendingmachine_.overengineered.exception.VendingMachineException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProductManager {
    private final Map<String, Product> productMap;

    private ProductManager() {
        this.productMap = new ConcurrentHashMap<>();
    }

    private static class ProductManagerSingleton {
        private static final ProductManager INSTANCE = new ProductManager();
    }

    public static ProductManager getInstance() {
        return ProductManagerSingleton.INSTANCE;
    }

    public Product getProduct(String id) {
        if (!productMap.containsKey(id)) {
            throw new VendingMachineException("Product with id " + id + " not found");
        }
        return productMap.get(id);
    }

    public Product createProduct(String name, ProductType productType) {
        Product product = new Product(UUID.randomUUID().toString(), name, productType);
        productMap.put(product.getId(), product);
        return product;
    }

    public void removeProduct(String id) {
        if (!productMap.containsKey(id)) {
            throw new VendingMachineException("Product with id " + id + " not found");
        }
        productMap.remove(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
}
