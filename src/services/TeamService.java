package services;

import models.RescueTeam;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private final List<RescueTeam> teams;

    public TeamService() {

        teams = new ArrayList<>();

        loadTeams();
    }

    private void loadTeams() {

        teams.add(
                new RescueTeam(
                        "RT01",
                        "Rapid Rescue Unit",
                        "Flood Rescue",
                        "Vijayawada",
                        true
                )
        );

        teams.add(
                new RescueTeam(
                        "RT02",
                        "Medical Response Unit",
                        "Medical Emergency",
                        "Amaravati",
                        true
                )
        );

        teams.add(
                new RescueTeam(
                        "RT03",
                        "Disaster Search Unit",
                        "Search and Rescue",
                        "Guntur",
                        false
                )
        );

        teams.add(
                new RescueTeam(
                        "RT04",
                        "Coastal Emergency Unit",
                        "Cyclone Response",
                        "Kakinada",
                        true
                )
        );
    }

    public void displayTeams() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("               RESCUE TEAMS");
        System.out.println("==================================================");

        for (RescueTeam team : teams) {

            System.out.println(team);
            System.out.println("----------------------------------------------");
        }
    }

    public int getAvailableTeamCount() {

        int count = 0;

        for (RescueTeam team : teams) {

            if (team.isAvailable()) {
                count++;
            }
        }

        return count;
    }
}