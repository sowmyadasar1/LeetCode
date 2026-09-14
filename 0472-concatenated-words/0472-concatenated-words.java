
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        Set<String> set = new HashSet<>();

        for (String word : words) {
            set.add(word);
        }

        List<String> result = new ArrayList<>();

        for (String word : words) {
            // Temporarily remove itself
            set.remove(word);

            if (canForm(word, set)) {
                result.add(word);
            }

            set.add(word);
        }

        return result;
    }

    private boolean canForm(String word, Set<String> set) {

        int n = word.length();

        // dp[i] = true if word[0...i) can be formed
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < i; j++) {

                if (!dp[j]) {
                    continue;
                }

                String part = word.substring(j, i);

                if (set.contains(part)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}