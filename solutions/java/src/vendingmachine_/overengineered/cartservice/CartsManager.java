package vendingmachine_.overengineered.cartservice;

import vendingmachine_.overengineered.data.Cart;
import vendingmachine_.overengineered.data.Product;
import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.data.transaction.Transaction;
import vendingmachine_.overengineered.data.transaction.TransactionStatus;
import vendingmachine_.overengineered.exception.VendingMachineException;
import vendingmachine_.overengineered.inventoryservice.InventoryManager;
import vendingmachine_.overengineered.paymentservice.PaymentManager;
import vendingmachine_.overengineered.priceservice.PriceManager;
import vendingmachine_.overengineered.productservice.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CartsManager {
    private final Map<String, Cart> cartsMap;
    private final ProductManager productManager;
    private final InventoryManager inventoryManager;
    private final PriceManager priceManager;
    private final PaymentManager paymentManager;

    private CartsManager() {
        cartsMap = new ConcurrentHashMap<>();
        productManager = ProductManager.getInstance();
        inventoryManager = InventoryManager.getInstance();
        priceManager = PriceManager.getInstance();
        paymentManager = PaymentManager.getInstance();
    }

    private static class CartsManagerSingleton {
        private static final CartsManager INSTANCE = new CartsManager();
    }

    public static CartsManager getInstance() {
        return CartsManagerSingleton.INSTANCE;
    }

    public Cart createEmptyCart() {
        Cart cart = new Cart(UUID.randomUUID().toString());
        cartsMap.put(cart.getId(), cart);
        return cart;
    }

    public Cart addToCart(String cartId, String productId, Integer quantity) {
        Cart cart = cartsMap.get(cartId);
        if (cart == null) {
            throw new VendingMachineException("Cart with id " + cartId + " does not exist");
        }
        Product product = productManager.getProduct(productId);
        inventoryManager.checkInventory(product.getId(), quantity);

        Integer price = cart.getPrice() == null ? 0 : cart.getPrice();
        Integer productPrice = priceManager.getPrice(productId);
        cart.setPrice(price + productPrice);
        cart.addProduct(product);
        return cart;
    }

    public Cart removeFromCart(String cartId, String productId) {
        Cart cart = cartsMap.get(cartId);
        if (cart == null) {
            throw new VendingMachineException("Cart with id " + cartId + " does not exist");
        }
        Product product = productManager.getProduct(productId);
        cart.removeProduct(product);
        Integer price = cart.getPrice() == null ? 0 : cart.getPrice();

        Integer productPrice = priceManager.getPrice(productId);
        cart.setPrice(price - productPrice);

        return cart;
    }

    public List<Integer> placeOrder(String cartId, List<Money> money) {
        Cart cart = cartsMap.get(cartId);
        if (cart == null) {
            throw new VendingMachineException("Cart with id " + cartId + " does not exist");
        }
        Transaction initiateTransaction = paymentManager.initiateTransaction(cart, money);

        if (initiateTransaction == null || initiateTransaction.getStatus() != TransactionStatus.IN_PROGRESS) {
            throw new VendingMachineException("Transaction failed");
        }

        List<Product> allocatedProducts = new ArrayList<>();
        try {
            for (Product product : cart.getProducts()) {
                inventoryManager.allocateInventory(product.getId(), 1);
                allocatedProducts.add(product);
            }
        } catch (VendingMachineException e) {
            for (Product product : allocatedProducts) {
                inventoryManager.deallocateInventory(product.getId(), 1);
            }
            throw e;
        }
        return paymentManager.completeTransaction(cart, money);
    }
}
