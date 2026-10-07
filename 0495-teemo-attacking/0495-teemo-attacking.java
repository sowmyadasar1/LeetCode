class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {

        if (timeSeries.length == 0) {
            return 0;
        }

        int total = 0;

        for (int i = 1; i < timeSeries.length; i++) {
            total += Math.min(
                duration,
                timeSeries[i] - timeSeries[i - 1]
            );
        }

        // Last attack gets the full duration
        total += duration;

        return total;
    }
}