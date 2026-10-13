package services;

import models.Incident;

import java.util.ArrayList;
import java.util.List;

public class DisasterService {

    private final List<Incident> incidents;

    public DisasterService() {

        incidents = new ArrayList<>();

        loadSampleIncidents();
    }

    private void loadSampleIncidents() {

        incidents.add(
                new Incident(
                        "INC101",
                        "Vijayawada",
                        "Flood",
                        "Critical",
                        "Heavy flooding near residential area requires rescue and medical assistance",
                        "Rescue Boat, Medical Kit, Food Package"
                )
        );

        incidents.add(
                new Incident(
                        "INC102",
                        "Guntur",
                        "Earthquake",
                        "High",
                        "Strong earthquake damaged buildings and blocked roads",
                        "Ambulance, Medical Kit, Rescue Team"
                )
        );

        incidents.add(
                new Incident(
                        "INC103",
                        "Machilipatnam",
                        "Cyclone",
                        "Critical",
                        "Severe cyclone caused flooding and power disruption",
                        "Food Package, Water Tank, Rescue Boat"
                )
        );

        incidents.add(
                new Incident(
                        "INC104",
                        "Eluru",
                        "Fire",
                        "High",
                        "Large fire reported near a residential building",
                        "Fire Equipment, Ambulance, Medical Kit"
                )
        );

        incidents.add(
                new Incident(
                        "INC105",
                        "Tuni",
                        "Flood",
                        "Medium",
                        "Flood water entered several houses and roads",
                        "Food Package, Water Tank"
                )
        );

        incidents.add(
                new Incident(
                        "INC106",
                        "Amaravati",
                        "Medical Emergency",
                        "Critical",
                        "Multiple people require urgent medical assistance",
                        "Ambulance, Medical Kit"
                )
        );

        incidents.add(
                new Incident(
                        "INC107",
                        "Nellore",
                        "Flood",
                        "High",
                        "Heavy rainfall caused flooding near hospital roads",
                        "Rescue Boat, Ambulance, Water Tank"
                )
        );

        incidents.add(
                new Incident(
                        "INC108",
                        "Kakinada",
                        "Cyclone",
                        "Critical",
                        "Cyclone warning with heavy rain and strong winds",
                        "Shelter, Food Package, Water Tank"
                )
        );
    }

    public void registerIncident(Incident incident) {

        incidents.add(incident);
    }

    public List<Incident> getIncidents() {

        return incidents;
    }

    public Incident findIncident(String id) {

        for (Incident incident : incidents) {

            if (incident.getId().equalsIgnoreCase(id)) {
                return incident;
            }
        }

        return null;
    }

    public void displayAllIncidents() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("              INCIDENT REPORTS");
        System.out.println("==================================================");

        if (incidents.isEmpty()) {

            System.out.println("No incidents currently registered.");
            return;
        }

        for (Incident incident : incidents) {

            System.out.println("----------------------------------------------");
            System.out.println("Incident ID    : " + incident.getId());
            System.out.println("Location       : " + incident.getLocation());
            System.out.println("Emergency Type : " + incident.getType());
            System.out.println("Severity       : " + incident.getSeverity());
            System.out.println("Description    : " + incident.getDescription());
            System.out.println("Resources      : " + incident.getRequiredResources());
        }

        System.out.println("----------------------------------------------");
        System.out.println("Total incidents: " + incidents.size());
    }
}