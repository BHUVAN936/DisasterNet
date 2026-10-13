package services;

import algorithms.AhoCorasick;
import algorithms.KMP;
import algorithms.RabinKarp;
import algorithms.SuffixArray;
import algorithms.KasaiLCP;
import algorithms.SuffixAutomaton;
import models.Incident;

import java.util.*;

public class SearchService {

    private final DisasterService disasterService;

    public SearchService(DisasterService disasterService) {

        this.disasterService = disasterService;
    }

    public void searchReports(String keyword) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("          EMERGENCY REPORT SEARCH");
        System.out.println("==================================================");

        if (keyword == null || keyword.isBlank()) {

            System.out.println("Search term cannot be empty.");
            return;
        }

        keyword = keyword.toLowerCase();

        List<Incident> results = new ArrayList<>();

        for (Incident incident : disasterService.getIncidents()) {

            String searchableText =
                    (
                            incident.getId()
                                    + " "
                                    + incident.getLocation()
                                    + " "
                                    + incident.getType()
                                    + " "
                                    + incident.getDescription()
                    ).toLowerCase();

            boolean found =
                    KMP.search(searchableText, keyword) != -1
                            || RabinKarp.search(searchableText, keyword) != -1;

            if (found) {
                results.add(incident);
            }
        }

        System.out.println();
        System.out.println("Search term: " + keyword);
        System.out.println("Matching incidents: " + results.size());

        if (results.isEmpty()) {

            System.out.println();
            System.out.println("No matching emergency reports found.");
            return;
        }

        for (Incident incident : results) {

            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("Incident ID : " + incident.getId());
            System.out.println("Location    : " + incident.getLocation());
            System.out.println("Type        : " + incident.getType());
            System.out.println("Severity    : " + incident.getSeverity());
            System.out.println("Description : " + incident.getDescription());
        }
    }

    public void detectMultipleKeywords(List<String> keywords) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("        EMERGENCY KEYWORD ANALYSIS");
        System.out.println("==================================================");

        if (keywords == null || keywords.isEmpty()) {

            System.out.println("No keywords supplied.");
            return;
        }

        List<String> cleanKeywords = new ArrayList<>();

        for (String keyword : keywords) {

            if (!keyword.isBlank()) {
                cleanKeywords.add(keyword.toLowerCase().trim());
            }
        }

        AhoCorasick matcher =
                new AhoCorasick(cleanKeywords);

        Map<String, Integer> totalCounts =
                new LinkedHashMap<>();

        for (String keyword : cleanKeywords) {
            totalCounts.put(keyword, 0);
        }

        for (Incident incident : disasterService.getIncidents()) {

            String text =
                    (
                            incident.getType()
                                    + " "
                                    + incident.getDescription()
                                    + " "
                                    + incident.getRequiredResources()
                    ).toLowerCase();

            Map<String, Integer> result =
                    matcher.search(text);

            for (Map.Entry<String, Integer> entry
                    : result.entrySet()) {

                totalCounts.put(
                        entry.getKey(),
                        totalCounts.getOrDefault(entry.getKey(), 0)
                                + entry.getValue()
                );
            }
        }

        for (Map.Entry<String, Integer> entry
                : totalCounts.entrySet()) {

            System.out.printf(
                    "%-18s -> %d occurrences%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }

        System.out.println();
        System.out.println("Keyword analysis completed.");
    }

    public void buildReportIndex() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("            INCIDENT REPORT INDEX");
        System.out.println("==================================================");

        StringBuilder allReports = new StringBuilder();

        for (Incident incident : disasterService.getIncidents()) {

            allReports.append(
                    incident.getDescription().toLowerCase()
            ).append(" ");
        }

        String text = allReports.toString().trim();

        if (text.isEmpty()) {

            System.out.println("No report text available.");
            return;
        }

        int[] suffixArray =
                SuffixArray.build(text);

        int[] lcp =
                KasaiLCP.build(text, suffixArray);

        SuffixAutomaton automaton =
                new SuffixAutomaton(text);

        System.out.println("Reports processed : "
                + disasterService.getIncidents().size());

        System.out.println("Text length       : "
                + text.length());

        System.out.println("Index entries     : "
                + suffixArray.length);

        int largestLCP = 0;

        for (int value : lcp) {
            largestLCP = Math.max(largestLCP, value);
        }

        System.out.println("Longest repeated text length: "
                + largestLCP);

        String testPhrase = "medical";

        System.out.println();

        System.out.println(
                "Can report index locate '" +
                        testPhrase +
                        "'? "
                        + automaton.contains(testPhrase)
        );

        System.out.println();
        System.out.println("Incident report indexing completed.");
    }
}