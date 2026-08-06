class Solution {
    public int longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) return 1;
        int[] freq = new int[128];
        for(char c : s.toCharArray()) {
            freq[c]++;
        }
        int sum = 0;
        boolean hasOdd = false;
        for(int i = 0; i < 128; i++) {
            if(freq[i]%2 == 0) sum += freq[i];
            else {
                sum += freq[i] - 1;
                hasOdd = true;
            }
        }
        if(hasOdd) return sum + 1;
        else return sum;
    }
}