package models;

public class Incident {

    private String id;
    private String location;
    private String type;
    private String severity;
    private String description;
    private String requiredResources;

    public Incident(String id,
                    String location,
                    String type,
                    String severity,
                    String description,
                    String requiredResources) {

        this.id = id;
        this.location = location;
        this.type = type;
        this.severity = severity;
        this.description = description;
        this.requiredResources = requiredResources;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredResources() {
        return requiredResources;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredResources(String requiredResources) {
        this.requiredResources = requiredResources;
    }

    @Override
    public String toString() {

        return "\nIncident ID      : " + id +
                "\nLocation         : " + location +
                "\nEmergency Type   : " + type +
                "\nSeverity         : " + severity +
                "\nDescription      : " + description +
                "\nRequired Resource: " + requiredResources;
    }
}