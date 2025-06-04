package vendingmachine_.overengineered;

import vendingmachine_.overengineered.cartservice.CartsManager;
import vendingmachine_.overengineered.data.Cart;
import vendingmachine_.overengineered.data.Product;
import vendingmachine_.overengineered.data.ProductType;
import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.inventoryservice.InventoryManager;
import vendingmachine_.overengineered.priceservice.PriceManager;
import vendingmachine_.overengineered.productservice.ProductManager;

import java.util.List;

public class VendingMachineSystem {
    private final CartsManager cartsManager;
    private final ProductManager productManager;
    private final InventoryManager inventoryManager;
    private final PriceManager priceManager;

    public VendingMachineSystem() {
        cartsManager = CartsManager.getInstance();
        productManager = ProductManager.getInstance();
        inventoryManager = InventoryManager.getInstance();
        priceManager = PriceManager.getInstance();
    }

    public Product createProduct(String name, ProductType productType) {
        return productManager.createProduct(name, productType);
    }

    public void updateInventory(String productId, Integer quantity) {
        Product product = productManager.getProduct(productId);
        inventoryManager.updateInventory(product.getId(), quantity);
    }

    public void updatePrice(String productId, Integer price) {
        Product product = productManager.getProduct(productId);
        priceManager.addPrice(product.getId(), price);
    }

    public Product getProduct(String productId) {
        return productManager.getProduct(productId);
    }

    public Integer getInventory(String productId) {
        return inventoryManager.getInventory(productId);
    }

    public Cart createEmptyCart() {
        return cartsManager.createEmptyCart();
    }

    public Cart addToCart(String cartId, String productId) {
        Product product = productManager.getProduct(productId);
        return cartsManager.addToCart(cartId, product.getId(), 1);
    }

    public Cart removeFromCart(String cartId, String productId) {
        Product product = productManager.getProduct(productId);
        return cartsManager.removeFromCart(cartId, product.getId());
    }

    public List<Integer> placeOrder(String cartId, List<Money> money) {
        return cartsManager.placeOrder(cartId, money);
    }

}
