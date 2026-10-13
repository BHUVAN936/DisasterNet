package main;

import models.Incident;
import services.DisasterService;
import services.SearchService;
import services.ReportAnalysisService;
import services.ResourceService;
import services.TeamService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

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

    private static boolean inputEnded = false;

    public static void main(String[] args) {

        showWelcome();

        boolean running = true;

        while (running && !inputEnded) {

            showMenu();

            int choice = readInt("Enter your choice: ");

            if (inputEnded) {
                break;
            }

            System.out.println();

            try {

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
                                "DisasterNet closed successfully. Stay safe!"
                        );

                        running = false;
                        break;

                    default:

                        System.out.println(
                                "Invalid option. Please choose a number from 0 to 12."
                        );
                }

            } catch (Exception e) {

                if (inputEnded) {
                    break;
                }

                System.out.println();
                System.out.println(
                        "We couldn't complete this operation."
                );

                System.out.println(
                        "Please check your input and try again."
                );

                // Keep the technical exception out of the user-facing console.
                // During development, inspect e in the IDE debugger if necessary.
            }

            if (running && !inputEnded) {

                System.out.println();
                System.out.println(
                        "Press ENTER to return to the main menu..."
                );

                readLine();

            }
        }

        if (inputEnded) {

            System.out.println();
            System.out.println(
                    "Input has ended. DisasterNet is closing safely."
            );

            System.out.println(
                    "Please run the application again to continue."
            );
        }

        scanner.close();
    }

    // ============================================================
    // WELCOME SCREEN
    // ============================================================

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

    // ============================================================
    // MAIN MENU
    // ============================================================

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

    // ============================================================
    // OPTION 1: REGISTER EMERGENCY INCIDENT
    // ============================================================

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

        String id = readRequiredString("Incident ID: ");

        if (id == null) {
            return;
        }

        if (disasterService.findIncident(id) != null) {

            System.out.println();

            System.out.println(
                    "This incident ID already exists."
            );

            System.out.println(
                    "Please use a different incident ID."
            );

            return;
        }

        String location = readRequiredString("Location: ");

        if (location == null) {
            return;
        }

        String type = readRequiredString("Emergency type: ");

        if (type == null) {
            return;
        }

        String severity = readRequiredString("Severity: ");

        if (severity == null) {
            return;
        }

        String description =
                readRequiredString("Incident description: ");

        if (description == null) {
            return;
        }

        String resources =
                readRequiredString("Required resources: ");

        if (resources == null) {
            return;
        }

        Incident incident = new Incident(
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
                "=================================================="
        );

        System.out.println(
                "     EMERGENCY INCIDENT REGISTERED SUCCESSFULLY"
        );

        System.out.println(
                "=================================================="
        );

        System.out.println("Incident ID : " + id);
        System.out.println("Location    : " + location);
        System.out.println("Type        : " + type);
        System.out.println("Severity    : " + severity);

        System.out.println();

        System.out.println(
                "The incident has been added to DisasterNet."
        );
    }

    // ============================================================
    // OPTION 3: SEARCH EMERGENCY REPORTS
    // ============================================================

    private static void searchEmergencyReports() {

        String keyword = readRequiredString(
                "Enter emergency keyword or incident code: "
        );

        if (keyword == null) {
            return;
        }

        searchService.searchReports(keyword);
    }

    // ============================================================
    // OPTION 4: DETECT MULTIPLE EMERGENCY KEYWORDS
    // ============================================================

    private static void detectMultipleKeywords() {

        System.out.println();

        System.out.println(
                "Enter emergency keywords separated by commas."
        );

        System.out.println(
                "Example: flood, rescue, medical, fire"
        );

        String input = readRequiredString("Keywords: ");

        if (input == null) {
            return;
        }

        List<String> keywords = Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .toList();

        if (keywords.isEmpty()) {

            System.out.println();

            System.out.println(
                    "No valid keywords were entered."
            );

            System.out.println(
                    "Please enter at least one keyword."
            );

            return;
        }

        searchService.detectMultipleKeywords(keywords);
    }

    // ============================================================
    // OPTION 6: COMPARE EMERGENCY REPORTS
    // ============================================================

    private static void compareEmergencyReports() {

        String firstId = readRequiredString(
                "First incident ID: "
        );

        if (firstId == null) {
            return;
        }

        String secondId = readRequiredString(
                "Second incident ID: "
        );

        if (secondId == null) {
            return;
        }

        reportAnalysisService.compareReports(
                firstId,
                secondId
        );
    }

    // ============================================================
    // OPTION 7: CORRECT NOISY EMERGENCY REPORT
    // ============================================================

    private static void correctNoisyReport() {

        System.out.println();

        String report = readRequiredString(
                "Enter emergency report containing possible spelling errors: "
        );

        if (report == null) {
            return;
        }

        reportAnalysisService.correctNoisyReport(report);
    }

    // ============================================================
    // OPTION 8: EMERGENCY RESOURCE PLANNING
    // ============================================================

    private static void resourcePlanning() {

        resourceService.displaySupplies();

        if (inputEnded) {
            return;
        }

        int budget = readInt(
                "\nEnter available planning budget: "
        );

        if (inputEnded) {
            return;
        }

        if (budget <= 0) {

            System.out.println();

            System.out.println(
                    "The budget must be greater than zero."
            );

            System.out.println(
                    "Please enter a valid planning budget."
            );

            return;
        }

        resourceService.optimizeResources(budget);
    }

    // ============================================================
    // OPTION 10: SYSTEM PERFORMANCE REPORT
    // ============================================================

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
                "Emergency reports managed : " + incidentCount
        );

        System.out.println(
                "Available rescue teams    : " + teamCount
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
                "System analysis completed successfully."
        );
    }

    // ============================================================
    // OPTION 11: COMPLETE EMERGENCY RESPONSE DEMO
    // ============================================================

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

        if (inputEnded) {
            return;
        }

        System.out.println();

        System.out.println(
                "[2] Searching emergency reports..."
        );

        searchService.searchReports("flood");

        if (inputEnded) {
            return;
        }

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

        if (inputEnded) {
            return;
        }

        System.out.println();

        System.out.println(
                "[4] Building searchable report index..."
        );

        searchService.buildReportIndex();

        if (inputEnded) {
            return;
        }

        System.out.println();

        System.out.println(
                "[5] Comparing emergency reports..."
        );

        reportAnalysisService.compareReports(
                "INC101",
                "INC105"
        );

        if (inputEnded) {
            return;
        }

        System.out.println();

        System.out.println(
                "[6] Processing noisy emergency report..."
        );

        reportAnalysisService.correctNoisyReport(
                "sevree floodng near hospitl requirs medicl assistnce"
        );

        if (inputEnded) {
            return;
        }

        System.out.println();

        System.out.println(
                "[7] Planning emergency resources..."
        );

        resourceService.optimizeResources(100);

        if (inputEnded) {
            return;
        }

        System.out.println();

        System.out.println(
                "[8] Optimizing emergency data processing..."
        );

        resourceService.optimizeProcessingPipeline();

        if (inputEnded) {
            return;
        }

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

    // ============================================================
    // SAFE INTEGER INPUT
    // ============================================================

    private static int readInt(String message) {

        while (true) {

            String input = readLineWithPrompt(message);

            if (input == null) {
                return 0;
            }

            input = input.trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println();

                System.out.println(
                        "That doesn't look like a valid number."
                );

                System.out.println(
                        "Please enter a number and try again."
                );

            }
        }
    }

    // ============================================================
    // SAFE STRING INPUT
    // ============================================================

    private static String readString(String message) {

        return readLineWithPrompt(message);
    }

    // ============================================================
    // REQUIRED STRING INPUT
    // Empty fields are rejected.
    // ============================================================

    private static String readRequiredString(String message) {

        while (true) {

            String input = readLineWithPrompt(message);

            if (input == null) {
                return null;
            }

            input = input.trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println();

            System.out.println(
                    "This field cannot be empty."
            );

            System.out.println(
                    "Please enter a valid value."
            );
        }
    }

    // ============================================================
    // CENTRALIZED SAFE CONSOLE INPUT
    // ============================================================

    private static String readLineWithPrompt(String message) {

        if (inputEnded) {
            return null;
        }

        System.out.print(message);

        return readLine();
    }

    private static String readLine() {

        if (inputEnded) {
            return null;
        }

        if (!scanner.hasNextLine()) {

            inputEnded = true;

            System.out.println();

            System.out.println(
                    "Input is no longer available."
            );

            return null;
        }

        return scanner.nextLine();
    }
}