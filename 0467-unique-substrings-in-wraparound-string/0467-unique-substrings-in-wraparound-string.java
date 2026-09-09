class Solution {
    public int findSubstringInWraproundString(String s) {

        int[] maxLen = new int[26];

        int currentLen = 0;

        for (int i = 0; i < s.length(); i++) {

            if (i > 0 && isConsecutive(s.charAt(i - 1), s.charAt(i))) {
                currentLen++;
            } else {
                currentLen = 1;
            }

            int index = s.charAt(i) - 'a';

            maxLen[index] = Math.max(maxLen[index], currentLen);
        }

        int answer = 0;

        for (int len : maxLen) {
            answer += len;
        }

        return answer;
    }

    private boolean isConsecutive(char prev, char curr) {
        return curr - prev == 1 ||
               (prev == 'z' && curr == 'a');
    }
}