class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {

        if (n1 == 0) {
            return 0;
        }

        int index = 0;
        int s2Count = 0;

        // index in s2 -> {s1 blocks used, s2 repetitions obtained}
        int[] recall = new int[s2.length()];
        int[] count = new int[s2.length()];

        boolean[] seen = new boolean[s2.length()];

        int s1Count = 0;

        while (s1Count < n1) {
            for (int i = 0; i < s1.length(); i++) {

                if (s1.charAt(i) == s2.charAt(index)) {
                    index++;

                    if (index == s2.length()) {
                        index = 0;
                        s2Count++;
                    }
                }
            }

            s1Count++;

            // We have seen this s2 position before -> cycle
            if (seen[index]) {

                int prevS1 = recall[index];
                int prevS2 = count[index];

                int cycleS1 = s1Count - prevS1;
                int cycleS2 = s2Count - prevS2;

                int remaining = n1 - s1Count;

                int cycles = remaining / cycleS1;

                s1Count += cycles * cycleS1;
                s2Count += cycles * cycleS2;

            } else {
                seen[index] = true;
                recall[index] = s1Count;
                count[index] = s2Count;
            }
        }

        return s2Count / n2;
    }
}