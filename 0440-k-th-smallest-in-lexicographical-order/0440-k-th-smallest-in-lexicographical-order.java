class Solution {
    public int findKthNumber(int n, int k) {
        int current = 1;
        k--; // current = 1 is already selected

        while (k > 0) {
            long steps = countSteps(n, current, current + 1);

            if (steps <= k) {
                // Skip this entire prefix
                current++;
                k -= steps;
            } else {
                // Go deeper into this prefix
                current *= 10;
                k--;
            }
        }

        return current;
    }

    private long countSteps(int n, long prefix, long nextPrefix) {
        long steps = 0;

        while (prefix <= n) {
            steps += Math.min((long) n + 1, nextPrefix) - prefix;

            prefix *= 10;
            nextPrefix *= 10;
        }

        return steps;
    }
}