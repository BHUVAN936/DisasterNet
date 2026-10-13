package algorithms;

public class MatrixChainOptimizer {

    public static class Result {

        private int minimumCost;

        private String parenthesization;

        public Result(int minimumCost,
                      String parenthesization) {

            this.minimumCost = minimumCost;
            this.parenthesization = parenthesization;
        }

        public int getMinimumCost() {
            return minimumCost;
        }

        public String getParenthesization() {
            return parenthesization;
        }
    }

    public static Result optimize(int[] dimensions) {

        int n = dimensions.length - 1;

        if (n <= 0) {
            return new Result(0, "");
        }

        int[][] dp = new int[n + 1][n + 1];

        int[][] split = new int[n + 1][n + 1];

        for (int length = 2; length <= n; length++) {

            for (int i = 1;
                 i <= n - length + 1;
                 i++) {

                int j = i + length - 1;

                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    long cost =
                            (long) dp[i][k]
                                    + dp[k + 1][j]
                                    + (long) dimensions[i - 1]
                                    * dimensions[k]
                                    * dimensions[j];

                    if (cost < dp[i][j]) {

                        dp[i][j] = (int) cost;

                        split[i][j] = k;
                    }
                }
            }
        }

        String order =
                buildParenthesization(split, 1, n);

        return new Result(dp[1][n], order);
    }

    private static String buildParenthesization(
            int[][] split,
            int i,
            int j) {

        if (i == j) {
            return "P" + i;
        }

        int k = split[i][j];

        return "("
                + buildParenthesization(split, i, k)
                + " × "
                + buildParenthesization(split, k + 1, j)
                + ")";
    }
}