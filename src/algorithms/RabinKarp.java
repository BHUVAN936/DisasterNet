package algorithms;

public class RabinKarp {

    private static final int BASE = 256;
    private static final long MOD = 1_000_000_007L;

    public static int search(String text, String pattern) {

        if (text == null || pattern == null) {
            return -1;
        }

        if (pattern.isEmpty()) {
            return 0;
        }

        if (pattern.length() > text.length()) {
            return -1;
        }

        int m = pattern.length();
        int n = text.length();

        long patternHash = 0;
        long textHash = 0;
        long highestPower = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }

        for (int i = 0; i < m; i++) {

            patternHash =
                    (patternHash * BASE + pattern.charAt(i)) % MOD;

            textHash =
                    (textHash * BASE + text.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {

                boolean match = true;

                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    return i;
                }
            }

            if (i < n - m) {

                textHash =
                        (textHash
                                - text.charAt(i) * highestPower % MOD
                                + MOD)
                                % MOD;

                textHash =
                        (textHash * BASE
                                + text.charAt(i + m))
                                % MOD;
            }
        }

        return -1;
    }
}