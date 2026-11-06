class Solution {
    public String convertToBase7(int num) {

        if (num == 0) {
            return "0";
        }

        boolean negative = num < 0;

        // Use long to safely handle Integer.MIN_VALUE
        long n = Math.abs((long) num);

        StringBuilder sb = new StringBuilder();

        while (n > 0) {

            sb.append(n % 7);
            n /= 7;
        }

        if (negative) {
            sb.append('-');
        }

        return sb.reverse().toString();
    }
}