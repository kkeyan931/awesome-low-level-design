package vendingmachine_.overengineered.data;

import vendingmachine_.overengineered.data.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private final String id;
    private Integer price;
    private final Map<String, Product> productsMap;

    private Transaction transaction;

    public Cart(String id) {
        this.id = id;
        productsMap = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product) {
        productsMap.put(product.getId(), product);
    }

    public void removeProduct(Product product) {
        if (!productsMap.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with id " + product.getId() + " does not exist");
        }
        productsMap.remove(product.getId());
    }

    public String getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(productsMap.values());
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
