package services;

import algorithms.BitmaskResourceOptimizer;
import algorithms.MatrixChainOptimizer;
import models.Supply;

import java.util.ArrayList;
import java.util.List;

public class ResourceService {

    private final List<Supply> supplies;

    public ResourceService() {

        supplies = new ArrayList<>();

        loadSupplies();
    }

    private void loadSupplies() {

        supplies.add(
                new Supply(
                        "S01",
                        "Rescue Boat",
                        5,
                        40,
                        10
                )
        );

        supplies.add(
                new Supply(
                        "S02",
                        "Medical Kit",
                        20,
                        20,
                        9
                )
        );

        supplies.add(
                new Supply(
                        "S03",
                        "Food Package",
                        50,
                        30,
                        8
                )
        );

        supplies.add(
                new Supply(
                        "S04",
                        "Ambulance",
                        4,
                        50,
                        10
                )
        );

        supplies.add(
                new Supply(
                        "S05",
                        "Water Tank",
                        8,
                        25,
                        7
                )
        );
    }

    public void displaySupplies() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("             AVAILABLE RESOURCES");
        System.out.println("==================================================");

        for (Supply supply : supplies) {

            System.out.println(supply);
        }
    }

    public void optimizeResources(int budget) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("          EMERGENCY RESOURCE PLANNING");
        System.out.println("==================================================");

        int n = supplies.size();

        int[] costs = new int[n];
        int[] priorities = new int[n];

        for (int i = 0; i < n; i++) {

            costs[i] =
                    supplies.get(i).getCost();

            priorities[i] =
                    supplies.get(i).getPriority();
        }

        BitmaskResourceOptimizer.Result result =
                BitmaskResourceOptimizer.optimize(
                        costs,
                        priorities,
                        budget
                );

        System.out.println();

        System.out.println(
                "Available planning budget: " + budget
        );

        System.out.println();

        System.out.println("Recommended allocation:");

        if (result.getSelectedIndices().isEmpty()) {

            System.out.println(
                    "No resource combination fits the budget."
            );

            return;
        }

        for (int index :
                result.getSelectedIndices()) {

            System.out.println(
                    "✓ "
                            + supplies.get(index).getName()
                            + "  | Cost: "
                            + supplies.get(index).getCost()
            );
        }

        System.out.println();

        System.out.println(
                "Total allocation cost : "
                        + result.getTotalCost()
        );

        System.out.println(
                "Remaining budget      : "
                        + (budget - result.getTotalCost())
        );

        System.out.println(
                "Priority coverage     : "
                        + result.getTotalPriority()
        );

        System.out.println();

        System.out.println(
                "Resource planning completed."
        );
    }

    public void optimizeProcessingPipeline() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("          DATA PROCESSING OPTIMIZATION");
        System.out.println("==================================================");

        /*
         * Dimensions represent a simplified emergency
         * data-processing pipeline.
         *
         * P1 = incident records
         * P2 = search index
         * P3 = keyword analysis
         * P4 = comparison engine
         * P5 = response summary
         */

        int[] dimensions = {
                10,
                30,
                5,
                60,
                20,
                15
        };

        MatrixChainOptimizer.Result result =
                MatrixChainOptimizer.optimize(dimensions);

        System.out.println();

        System.out.println(
                "Emergency data processing stages: "
                        + (dimensions.length - 1)
        );

        System.out.println();

        System.out.println(
                "Optimized processing order:"
        );

        System.out.println(
                result.getParenthesization()
        );

        System.out.println();

        System.out.println(
                "Minimum estimated computation cost: "
                        + result.getMinimumCost()
        );

        System.out.println();

        System.out.println(
                "Processing sequence optimization completed."
        );
    }
}