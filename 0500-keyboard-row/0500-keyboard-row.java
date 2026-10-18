
class Solution {
    public String[] findWords(String[] words) {

        String[] rows = {
            "qwertyuiop",
            "asdfghjkl",
            "zxcvbnm"
        };

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (char c : rows[i].toCharArray()) {
                map.put(c, i);
            }
        }

        List<String> result = new ArrayList<>();

        for (String word : words) {

            String lower = word.toLowerCase();

            int row = map.get(lower.charAt(0));

            boolean valid = true;

            for (char c : lower.toCharArray()) {
                if (map.get(c) != row) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }
}