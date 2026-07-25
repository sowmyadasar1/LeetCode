class Solution {
    public int lengthLongestPath(String input) {
        String[] parts = input.split("\n");
        int[] len = new int[parts.length + 1];
        int max = 0;

        for (String s : parts) {
            int level = s.lastIndexOf("\t") + 1;
            int currLen = len[level] + s.length() - level;

            if (s.contains(".")) {
                max = Math.max(max, currLen);
            } else {
                len[level + 1] = currLen + 1;
            }
        }

        return max;
    }
}