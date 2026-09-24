class Solution {
    public int magicalString(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n <= 3) {
            return 1;
        }

        int[] arr = new int[n + 2];

        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 2;

        int read = 2;
        int write = 3;

        int countOnes = 1;

        int num = 1;

        while (write < n) {

            int times = arr[read];

            for (int i = 0; i < times && write < n; i++) {
                arr[write++] = num;

                if (num == 1) {
                    countOnes++;
                }
            }

            num = 3 - num; // 1 <-> 2
            read++;
        }

        return countOnes;
    }
}