class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(num);
                strStack.push(curr);
                curr = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                int k = countStack.pop();
                StringBuilder prev = strStack.pop();
                while (k-- > 0) {
                    prev.append(curr);
                }
                curr = prev;
            } else {
                curr.append(c);
            }
        }

        return curr.toString();
    }
}