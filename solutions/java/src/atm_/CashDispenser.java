package atm_;

public class CashDispenser {
    private int availableCash;

    public CashDispenser(int initialCash) {
        this.availableCash = initialCash;
    }

    public synchronized boolean dispenseCash(int amount) {
        if (amount > 0 && availableCash >= amount) {
            availableCash -= amount;
            return true;
        }
        return false;
    }

    public synchronized void loadCash(int amount) {
        if (amount > 0) {
            availableCash += amount;
        }
    }

    public int getAvailableCash() {
        return availableCash;
    }
}
