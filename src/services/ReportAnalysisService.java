package services;

import algorithms.EditDistance;
import models.Incident;

public class ReportAnalysisService {

    private final DisasterService disasterService;

    public ReportAnalysisService(
            DisasterService disasterService) {

        this.disasterService = disasterService;
    }

    public void compareReports(
            String firstId,
            String secondId) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("             REPORT COMPARISON");
        System.out.println("==================================================");

        Incident first =
                disasterService.findIncident(firstId);

        Incident second =
                disasterService.findIncident(secondId);

        if (first == null || second == null) {

            System.out.println(
                    "One or both incident IDs were not found."
            );

            return;
        }

        String text1 =
                first.getDescription().toLowerCase();

        String text2 =
                second.getDescription().toLowerCase();

        int distance =
                EditDistance.distance(text1, text2);

        double similarity =
                EditDistance.similarity(text1, text2);

        System.out.println();

        System.out.println("Report A: " + first.getId());
        System.out.println(
                "Location: " + first.getLocation()
        );

        System.out.println(
                "Description: " + first.getDescription()
        );

        System.out.println();

        System.out.println("Report B: " + second.getId());
        System.out.println(
                "Location: " + second.getLocation()
        );

        System.out.println(
                "Description: " + second.getDescription()
        );

        System.out.println();
        System.out.println("----------------------------------------------");

        System.out.println(
                "Minimum transformations : " + distance
        );

        System.out.printf(
                "Text similarity          : %.2f%%%n",
                similarity
        );

        if (similarity >= 80) {

            System.out.println(
                    "Assessment: Reports are highly related."
            );

        } else if (similarity >= 50) {

            System.out.println(
                    "Assessment: Reports contain related information."
            );

        } else {

            System.out.println(
                    "Assessment: Reports are substantially different."
            );
        }
    }

    public void correctNoisyReport(String noisyText) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("            REPORT CORRECTION");
        System.out.println("==================================================");

        if (noisyText == null || noisyText.isBlank()) {

            System.out.println("Report cannot be empty.");
            return;
        }

        String bestWord = null;
        int bestDistance = Integer.MAX_VALUE;

        String[] emergencyTerms = {

                "severe",
                "flood",
                "flooding",
                "hospital",
                "medical",
                "assistance",
                "rescue",
                "ambulance",
                "earthquake",
                "cyclone",
                "fire",
                "emergency",
                "water",
                "road",
                "blocked",
                "damaged"
        };

        String[] words =
                noisyText.toLowerCase().split("\\s+");

        System.out.println();

        System.out.println("Original report:");
        System.out.println(noisyText);

        System.out.println();

        System.out.println("Possible corrections:");

        boolean correctionFound = false;

        for (String word : words) {

            String clean =
                    word.replaceAll("[^a-zA-Z]", "");

            if (clean.isEmpty()) {
                continue;
            }

            bestWord = null;
            bestDistance = Integer.MAX_VALUE;

            for (String term : emergencyTerms) {

                int distance =
                        EditDistance.distance(clean, term);

                if (distance < bestDistance) {

                    bestDistance = distance;
                    bestWord = term;
                }
            }

            if (bestWord != null
                    && bestDistance > 0
                    && bestDistance <= 2) {

                System.out.println(
                        clean + " -> " + bestWord
                                + "  (distance: "
                                + bestDistance + ")"
                );

                correctionFound = true;
            }
        }

        if (!correctionFound) {

            System.out.println(
                    "No significant spelling corrections detected."
            );
        }
    }
}