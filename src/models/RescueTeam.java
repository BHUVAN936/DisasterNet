package models;

public class RescueTeam {

    private String teamId;
    private String teamName;
    private String specialization;
    private String location;
    private boolean available;

    public RescueTeam(String teamId,
                      String teamName,
                      String specialization,
                      String location,
                      boolean available) {

        this.teamId = teamId;
        this.teamName = teamName;
        this.specialization = specialization;
        this.location = location;
        this.available = available;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {

        return "\nTeam ID          : " + teamId +
                "\nTeam Name        : " + teamName +
                "\nSpecialization   : " + specialization +
                "\nCurrent Location : " + location +
                "\nAvailability     : " + (available ? "Available" : "Assigned");
    }
}