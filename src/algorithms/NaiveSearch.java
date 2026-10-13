package algorithms;

public class NaiveSearch {

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

        for (int i = 0; i <= text.length() - pattern.length(); i++) {

            int j = 0;

            while (j < pattern.length()
                    && text.charAt(i + j) == pattern.charAt(j)) {

                j++;
            }

            if (j == pattern.length()) {
                return i;
            }
        }

        return -1;
    }
}