import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        int[] freq = new int[26];

        // Frequency needed from p
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int needed = p.length();

        while (right < s.length()) {
            char c = s.charAt(right);

            // Add right character to window
            if (freq[c - 'a'] > 0) {
                needed--;
            }

            freq[c - 'a']--;
            right++;

            // Window is too large
            if (right - left > p.length()) {
                char removed = s.charAt(left);

                freq[removed - 'a']++;

                // Removed a character that was needed
                if (freq[removed - 'a'] > 0) {
                    needed++;
                }

                left++;
            }

            // Window contains exactly p.length() chars
            // and all required characters
            if (needed == 0) {
                result.add(left);
            }
        }

        return result;
    }
}