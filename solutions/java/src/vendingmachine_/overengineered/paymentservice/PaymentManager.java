package vendingmachine_.overengineered.paymentservice;

import vendingmachine_.overengineered.data.Cart;
import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.data.transaction.Transaction;
import vendingmachine_.overengineered.data.transaction.TransactionStatus;
import vendingmachine_.overengineered.exception.VendingMachineException;
import vendingmachine_.overengineered.moneyservice.MoneyManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentManager {
    private final Map<String, Transaction> transactionMap;
    private final MoneyManager moneyManager;

    private PaymentManager() {
        this.transactionMap = new ConcurrentHashMap<>();
        this.moneyManager = MoneyManager.getInstance();
    }

    private static class PaymentManagerSingleton {
        private static final PaymentManager INSTANCE = new PaymentManager();
    }

    public static PaymentManager getInstance() {
        return PaymentManagerSingleton.INSTANCE;
    }

    public Transaction initiateTransaction(Cart cart, List<Money> money) {
        Integer total = money.stream().map(Money::getValue).reduce(0, Integer::sum);
        if (cart.getPrice() > total) {
            throw new VendingMachineException("Money mismatch for the cart " + cart.getId());
        }
        int remaining = total - cart.getPrice();
        if (remaining != 0 && !moneyManager.isRemainingChangesAvailable(remaining)) {
            throw new VendingMachineException("Changes not available " + cart.getId());
        }

        Transaction transaction = new Transaction(UUID.randomUUID().toString(), cart, money);
        transaction.setStatus(TransactionStatus.IN_PROGRESS);
        cart.setTransaction(transaction);
        transactionMap.put(transaction.getId(), transaction);
        return transaction;
    }

    public List<Integer> completeTransaction(Cart cart, List<Money> money) {
        Transaction transaction = transactionMap.get(cart.getTransaction().getId());
        Integer total = money.stream().map(Money::getValue).reduce(0, Integer::sum);
        moneyManager.storeMoney(money);
        transaction.setStatus(TransactionStatus.SUCCESS);
        cart.setTransaction(transaction);
        int remaining = total - cart.getPrice();
        if (remaining != 0) {
            List<Integer> changes = moneyManager.getRemainingChanges(remaining);
            moneyManager.deductChanges(total - cart.getPrice());
            return changes;
        }
        return new ArrayList<>();
    }

    public Transaction revertTransaction(Cart cart) {
        Transaction transaction = transactionMap.get(cart.getTransaction().getId());
        transaction.setStatus(TransactionStatus.FAILED);
        cart.setTransaction(transaction);
        return transaction;
    }
}
