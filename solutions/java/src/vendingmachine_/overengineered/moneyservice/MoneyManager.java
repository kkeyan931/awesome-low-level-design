package vendingmachine_.overengineered.moneyservice;

import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.exception.VendingMachineException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MoneyManager {
    private final Map<Integer, Integer> moneyStore;

    private MoneyManager() {
        moneyStore = new ConcurrentHashMap<>();
    }

    private static class MoneyManagerSingleton {
        private static final MoneyManager INSTANCE = new MoneyManager();
    }

    public static MoneyManager getInstance() {
        return MoneyManagerSingleton.INSTANCE;
    }

    public void storeMoney(List<Money> money) {
        for (Money m : money) {
            if (moneyStore.containsKey(m.getValue())) {
                moneyStore.put(m.getValue(), moneyStore.get(m.getValue()) + 1);
            } else {
                moneyStore.put(m.getValue(), 1);
            }
        }
    }

    public Boolean isRemainingChangesAvailable(Integer remainingChanges) {
        return !getRemainingChanges(remainingChanges).isEmpty();
    }

    public synchronized List<Integer> getRemainingChanges(Integer remainingChanges) {
        List<Integer> storedMoney = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : moneyStore.entrySet()) {
            if (entry.getValue() > 0) {
                Integer[] arr = new Integer[entry.getValue()];
                Arrays.fill(arr, entry.getKey());
                storedMoney.addAll(Arrays.asList(arr));
            }
        }
        List<Integer> remainingChangeList = getRemainingChanges(storedMoney, remainingChanges);
        if (remainingChangeList.isEmpty()) {
            throw new VendingMachineException("Changes not found");
        }
        return remainingChangeList;
    }

    public synchronized void deductChanges(Integer remainingChanges) {
        List<Integer> remainingChangesList = getRemainingChanges(remainingChanges);

        for (Integer change : remainingChangesList) {
            moneyStore.put(change, moneyStore.get(change) - 1);
        }
    }

    public static List<Integer> getRemainingChanges(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] dp = new int[n + 1][target + 1];
        int INF = target + 1;

        // Initialize
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                // Don't pick current
                dp[i][j] = dp[i - 1][j];
                // Try to pick current
                if (j >= nums.get(i - 1) && dp[i - 1][j - nums.get(i - 1)] + 1 < dp[i][j]) {
                    dp[i][j] = dp[i - 1][j - nums.get(i - 1)] + 1;
                }
            }
        }

        if (dp[n][target] > target) return Collections.emptyList(); // No valid subset

        // Trace back to find elements
        List<Integer> result = new ArrayList<>();
        int i = n;
        int j = target;
        while (i > 0 && j > 0) {
            if (j >= nums.get(i - 1) && dp[i][j] == dp[i - 1][j - nums.get(i - 1)] + 1) {
                result.add(nums.get(i - 1));
                j -= nums.get(i - 1);
            }
            i--;
        }

        return result;
    }
}
