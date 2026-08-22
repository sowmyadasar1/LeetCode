
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;

        @SuppressWarnings("unchecked")
        Map<Long, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        long answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                long diff = (long) nums[i] - nums[j];

                // Subsequences ending at j with same difference
                int previous = dp[j].getOrDefault(diff, 0);

                // Add new pair (nums[j], nums[i])
                dp[i].put(
                    diff,
                    dp[i].getOrDefault(diff, 0) + previous + 1
                );

                // Only subsequences of length >= 3 count
                answer += previous;
            }
        }

        return (int) answer;
    }
}