class Solution {
    public String smallestGoodBase(String n) {

        long num = Long.parseLong(n);

        // Maximum possible power when base = 2
        int maxPower = 63 - Long.numberOfLeadingZeros(num);

        for (int power = maxPower; power >= 2; power--) {

            long low = 2;
            long high = (long) Math.pow(num, 1.0 / power) + 1;

            while (low <= high) {

                long base = low + (high - low) / 2;

                int result = compareGeometricSum(
                    num, base, power
                );

                if (result == 0) {
                    return String.valueOf(base);
                }

                if (result < 0) {
                    low = base + 1;
                } else {
                    high = base - 1;
                }
            }
        }

        // Every number has base n - 1:
        // n = 1 + (n - 1)
        return String.valueOf(num - 1);
    }

    // Returns:
    //  0  -> sum == target
    // -1  -> sum < target
    //  1  -> sum > target
    private int compareGeometricSum(
            long target,
            long base,
            int power) {

        long sum = 1;

        for (int i = 0; i < power; i++) {

            // Avoid overflow
            if (sum > (target - 1) / base) {
                return 1;
            }

            sum = sum * base + 1;

            if (sum > target) {
                return 1;
            }
        }

        if (sum == target) {
            return 0;
        }

        return -1;
    }
}