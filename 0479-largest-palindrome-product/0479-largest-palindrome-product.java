class Solution {
    public int largestPalindrome(int n) {

        if (n == 1) {
            return 9;
        }

        int upper = (int) Math.pow(10, n) - 1;
        int lower = (int) Math.pow(10, n - 1);

        for (int left = upper; left >= lower; left--) {

            String s = String.valueOf(left);
            String palindrome =
                    s + new StringBuilder(s).reverse();

            long pal = Long.parseLong(palindrome);

            for (long factor = upper; factor >= lower; factor--) {

                // If factor is too small, stop
                if (factor * factor < pal) {
                    break;
                }

                if (pal % factor == 0) {
                    long other = pal / factor;

                    if (other >= lower && other <= upper) {
                        return (int) (pal % 1337);
                    }
                }
            }
        }

        return 0;
    }
}