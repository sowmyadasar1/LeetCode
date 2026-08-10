class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();

        boolean lower = false, upper = false, digit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
        }

        int missing = (lower ? 0 : 1) + (upper ? 0 : 1) + (digit ? 0 : 1);

        int replace = 0;
        int one = 0, two = 0;

        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && password.charAt(j) == password.charAt(i)) j++;
            int len = j - i;

            if (len >= 3) {
                replace += len / 3;
                if (len % 3 == 0) one++;
                else if (len % 3 == 1) two++;
            }
            i = j;
        }

        if (n < 6) {
            return Math.max(missing, 6 - n);
        }

        if (n <= 20) {
            return Math.max(missing, replace);
        }

        int delete = n - 20;
        int remain = delete;

        int use = Math.min(remain, one);
        replace -= use;
        remain -= use;

        use = Math.min(remain, two * 2);
        replace -= use / 2;
        remain -= use;

        replace -= remain / 3;

        return delete + Math.max(missing, replace);
    }
}