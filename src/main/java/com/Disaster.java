package com.swiftrelief;

public class Disaster {
    private int id;
    private String name;
    private String type;
    private String location;
    private String description;
    private String severity;
    private String status;

    public Disaster(int id, String name, String type, String location, String description, String severity, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
        this.severity = severity;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }

    // Setters (if needed)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setStatus(String status) { this.status = status; }
}
