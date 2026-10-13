package algorithms;

public class ZFunction {

    public static int[] calculate(String s) {

        int n = s.length();

        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n
                    && s.charAt(z[i]) == s.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {

                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    public static boolean contains(String text, String pattern) {

        if (pattern.isEmpty()) {
            return true;
        }

        String combined = pattern + "$" + text;

        int[] z = calculate(combined);

        for (int value : z) {

            if (value == pattern.length()) {
                return true;
            }
        }

        return false;
    }
}