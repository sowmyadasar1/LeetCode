class Solution {
    public String validIPAddress(String queryIP) {

        if (queryIP.contains(".")) {
            return isIPv4(queryIP) ? "IPv4" : "Neither";
        }

        if (queryIP.contains(":")) {
            return isIPv6(queryIP) ? "IPv6" : "Neither";
        }

        return "Neither";
    }

    private boolean isIPv4(String s) {
        String[] parts = s.split("\\.", -1);

        // Must have exactly 4 parts
        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {

            // Empty or too long
            if (part.length() == 0 || part.length() > 3) {
                return false;
            }

            // Leading zero
            if (part.length() > 1 && part.charAt(0) == '0') {
                return false;
            }

            int value = 0;

            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }

                value = value * 10 + (c - '0');
            }

            if (value > 255) {
                return false;
            }
        }

        return true;
    }

    private boolean isIPv6(String s) {
        String[] parts = s.split(":", -1);

        // Must have exactly 8 groups
        if (parts.length != 8) {
            return false;
        }

        for (String part : parts) {

            // Each group has 1 to 4 characters
            if (part.length() < 1 || part.length() > 4) {
                return false;
            }

            for (char c : part.toCharArray()) {
                if (!isHex(c)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isHex(char c) {
        return (c >= '0' && c <= '9') ||
               (c >= 'a' && c <= 'f') ||
               (c >= 'A' && c <= 'F');
    }
}