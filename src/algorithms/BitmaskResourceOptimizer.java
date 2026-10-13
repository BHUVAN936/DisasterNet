package algorithms;

import java.util.ArrayList;
import java.util.List;

public class BitmaskResourceOptimizer {

    public static class Result {

        private List<Integer> selectedIndices;

        private int totalCost;

        private int totalPriority;

        public Result(List<Integer> selectedIndices,
                      int totalCost,
                      int totalPriority) {

            this.selectedIndices = selectedIndices;
            this.totalCost = totalCost;
            this.totalPriority = totalPriority;
        }

        public List<Integer> getSelectedIndices() {
            return selectedIndices;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public int getTotalPriority() {
            return totalPriority;
        }
    }

    public static Result optimize(
            int[] costs,
            int[] priorities,
            int budget) {

        int n = costs.length;

        int bestMask = 0;
        int bestPriority = -1;
        int bestCost = 0;

        int totalMasks = 1 << n;

        for (int mask = 0; mask < totalMasks; mask++) {

            int cost = 0;
            int priority = 0;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    cost += costs[i];
                    priority += priorities[i];
                }
            }

            if (cost <= budget) {

                if (priority > bestPriority
                        || (priority == bestPriority
                        && cost < bestCost)) {

                    bestPriority = priority;
                    bestCost = cost;
                    bestMask = mask;
                }
            }
        }

        List<Integer> selected = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if ((bestMask & (1 << i)) != 0) {
                selected.add(i);
            }
        }

        return new Result(
                selected,
                bestCost,
                bestPriority
        );
    }
}