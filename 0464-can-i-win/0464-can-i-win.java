
class Solution {

    private int max;
    private int target;
    private Map<Integer, Boolean> memo;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        // Sum of all available numbers is not enough
        int sum = maxChoosableInteger *
                  (maxChoosableInteger + 1) / 2;

        if (sum < desiredTotal) {
            return false;
        }

        // First player can immediately win
        if (desiredTotal <= 0) {
            return true;
        }

        this.max = maxChoosableInteger;
        this.target = desiredTotal;
        this.memo = new HashMap<>();

        return dfs(0, 0);
    }

    private boolean dfs(int usedMask, int currentSum) {

        if (memo.containsKey(usedMask)) {
            return memo.get(usedMask);
        }

        for (int num = 1; num <= max; num++) {

            int bit = 1 << (num - 1);

            // Already used
            if ((usedMask & bit) != 0) {
                continue;
            }

            // We can win immediately
            if (currentSum + num >= target) {
                memo.put(usedMask, true);
                return true;
            }

            // Choose num and force opponent into losing state
            if (!dfs(usedMask | bit, currentSum + num)) {
                memo.put(usedMask, true);
                return true;
            }
        }

        memo.put(usedMask, false);
        return false;
    }
}