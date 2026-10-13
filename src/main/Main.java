package main;

import models.Incident;
import services.DisasterService;
import services.SearchService;
import services.ReportAnalysisService;
import services.ResourceService;
import services.TeamService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final DisasterService disasterService =
            new DisasterService();

    private static final SearchService searchService =
            new SearchService(disasterService);

    private static final ReportAnalysisService reportAnalysisService =
            new ReportAnalysisService(disasterService);

    private static final ResourceService resourceService =
            new ResourceService();

    private static final TeamService teamService =
            new TeamService();

    public static void main(String[] args) {

        showWelcome();

        boolean running = true;

        while (running) {

            showMenu();

            int choice =
                    readInt("Enter your choice: ");

            System.out.println();

            switch (choice) {

                case 1:
                    registerIncident();
                    break;

                case 2:
                    disasterService.displayAllIncidents();
                    break;

                case 3:
                    searchEmergencyReports();
                    break;

                case 4:
                    detectMultipleKeywords();
                    break;

                case 5:
                    searchService.buildReportIndex();
                    break;

                case 6:
                    compareEmergencyReports();
                    break;

                case 7:
                    correctNoisyReport();
                    break;

                case 8:
                    resourcePlanning();
                    break;

                case 9:
                    resourceService.optimizeProcessingPipeline();
                    break;

                case 10:
                    performanceAnalysis();
                    break;

                case 11:
                    runCompleteDemo();
                    break;

                case 12:
                    teamService.displayTeams();
                    break;

                case 0:

                    System.out.println(
                            "DisasterNet closed successfully."
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please try again."
                    );
            }

            if (running) {

                System.out.println();
                System.out.println(
                        "Press ENTER to continue..."
                );

                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void showWelcome() {

        System.out.println();
        System.out.println(
                "============================================================"
        );

        System.out.println(
                "                     DISASTERNET"
        );

        System.out.println(
                "          EMERGENCY RESPONSE PLATFORM"
        );

        System.out.println(
                "============================================================"
        );

        System.out.println(
                "Manage emergency incidents, analyze field reports,"
        );

        System.out.println(
                "identify critical information and plan resources."
        );

        System.out.println(
                "============================================================"
        );
    }

    private static void showMenu() {

        System.out.println();
        System.out.println(
                "------------------------------------------------------------"
        );

        System.out.println(
                "                    MAIN OPERATIONS"
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        System.out.println(
                "1. Register Emergency Incident"
        );

        System.out.println(
                "2. View Incident Reports"
        );

        System.out.println(
                "3. Search Emergency Reports"
        );

        System.out.println(
                "4. Detect Multiple Emergency Keywords"
        );

        System.out.println(
                "5. Index and Analyze Incident Reports"
        );

        System.out.println(
                "6. Compare Emergency Reports"
        );

        System.out.println(
                "7. Correct Noisy Emergency Report"
        );

        System.out.println(
                "8. Plan Emergency Resource Allocation"
        );

        System.out.println(
                "9. Optimize Data Processing"
        );

        System.out.println(
                "10. Analyze System Performance"
        );

        System.out.println(
                "11. Run Complete Emergency Response Demo"
        );

        System.out.println(
                "12. View Rescue Teams"
        );

        System.out.println(
                "0. Exit"
        );

        System.out.println(
                "------------------------------------------------------------"
        );
    }

    private static void registerIncident() {

        System.out.println();
        System.out.println(
                "=================================================="
        );

        System.out.println(
                "          REGISTER EMERGENCY INCIDENT"
        );

        System.out.println(
                "=================================================="
        );

        String id =
                readString("Incident ID: ");

        if (disasterService.findIncident(id) != null) {

            System.out.println(
                    "Incident ID already exists."
            );

            return;
        }

        String location =
                readString("Location: ");

        String type =
                readString("Emergency type: ");

        String severity =
                readString("Severity: ");

        String description =
                readString("Incident description: ");

        String resources =
                readString("Required resources: ");

        Incident incident =
                new Incident(
                        id,
                        location,
                        type,
                        severity,
                        description,
                        resources
                );

        disasterService.registerIncident(incident);

        System.out.println();
        System.out.println(
                "Emergency incident registered successfully."
        );

        System.out.println(
                "Incident ID: " + id
        );
    }

    private static void searchEmergencyReports() {

        String keyword =
                readString(
                        "Enter emergency keyword or incident code: "
                );

        searchService.searchReports(keyword);
    }

    private static void detectMultipleKeywords() {

        System.out.println();
        System.out.println(
                "Enter emergency keywords separated by commas."
        );

        System.out.println(
                "Example: flood, rescue, medical, fire"
        );

        String input =
                readString("Keywords: ");

        if (input.isBlank()) {

            System.out.println(
                    "No keywords entered."
            );

            return;
        }

        List<String> keywords =
                Arrays.stream(input.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();

        searchService.detectMultipleKeywords(
                keywords
        );
    }

    private static void compareEmergencyReports() {

        String firstId =
                readString("First incident ID: ");

        String secondId =
                readString("Second incident ID: ");

        reportAnalysisService.compareReports(
                firstId,
                secondId
        );
    }

    private static void correctNoisyReport() {

        System.out.println();

        String report =
                readString(
                        "Enter emergency report containing possible spelling errors: "
                );

        reportAnalysisService.correctNoisyReport(
                report
        );
    }

    private static void resourcePlanning() {

        resourceService.displaySupplies();

        int budget =
                readInt(
                        "\nEnter available planning budget: "
                );

        if (budget <= 0) {

            System.out.println(
                    "Budget must be greater than zero."
            );

            return;
        }

        resourceService.optimizeResources(
                budget
        );
    }

    private static void performanceAnalysis() {

        System.out.println();
        System.out.println(
                "=================================================="
        );

        System.out.println(
                "             SYSTEM PERFORMANCE"
        );

        System.out.println(
                "=================================================="
        );

        int incidentCount =
                disasterService.getIncidents().size();

        int teamCount =
                teamService.getAvailableTeamCount();

        System.out.println();

        System.out.println(
                "Emergency reports managed : "
                        + incidentCount
        );

        System.out.println(
                "Available rescue teams    : "
                        + teamCount
        );

        System.out.println(
                "Available resource types  : 5"
        );

        System.out.println(
                "Text search capability    : Active"
        );

        System.out.println(
                "Multi-keyword analysis    : Active"
        );

        System.out.println(
                "Report indexing           : Active"
        );

        System.out.println(
                "Report comparison         : Active"
        );

        System.out.println(
                "Report correction         : Active"
        );

        System.out.println(
                "Resource planning         : Active"
        );

        System.out.println(
                "Processing optimization   : Active"
        );

        System.out.println();

        System.out.println(
                "System analysis completed."
        );
    }

    private static void runCompleteDemo() {

        System.out.println();

        System.out.println(
                "============================================================"
        );

        System.out.println(
                "           DISASTERNET EMERGENCY RESPONSE DEMO"
        );

        System.out.println(
                "============================================================"
        );

        System.out.println();
        System.out.println(
                "[1] Loading emergency incidents..."
        );

        System.out.println(
                "    "
                        + disasterService.getIncidents().size()
                        + " incidents available."
        );

        pause();

        System.out.println();
        System.out.println(
                "[2] Searching emergency reports..."
        );

        searchService.searchReports("flood");

        pause();

        System.out.println();
        System.out.println(
                "[3] Detecting multiple emergency keywords..."
        );

        searchService.detectMultipleKeywords(
                Arrays.asList(
                        "flood",
                        "rescue",
                        "medical",
                        "fire"
                )
        );

        pause();

        System.out.println();
        System.out.println(
                "[4] Building searchable report index..."
        );

        searchService.buildReportIndex();

        pause();

        System.out.println();
        System.out.println(
                "[5] Comparing emergency reports..."
        );

        reportAnalysisService.compareReports(
                "INC101",
                "INC105"
        );

        pause();

        System.out.println();
        System.out.println(
                "[6] Processing noisy emergency report..."
        );

        reportAnalysisService.correctNoisyReport(
                "sevree floodng near hospitl requirs medicl assistnce"
        );

        pause();

        System.out.println();
        System.out.println(
                "[7] Planning emergency resources..."
        );

        resourceService.optimizeResources(100);

        pause();

        System.out.println();
        System.out.println(
                "[8] Optimizing emergency data processing..."
        );

        resourceService.optimizeProcessingPipeline();

        pause();

        System.out.println();
        System.out.println(
                "============================================================"
        );

        System.out.println(
                "           EMERGENCY ANALYSIS COMPLETED"
        );

        System.out.println(
                "============================================================"
        );
    }

    private static void pause() {

        System.out.println();

        System.out.println(
                "------------------------------------------------------------"
        );

        System.out.println(
                "Continuing DisasterNet analysis..."
        );
    }

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }
}