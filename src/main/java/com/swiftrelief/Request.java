package com.swiftrelief;

import javafx.beans.property.*;

public class Request {
    private final IntegerProperty id;
    private final StringProperty requesterName;
    private final StringProperty contact;
    private final StringProperty location;
    private final StringProperty resourceNeeded;
    private final IntegerProperty quantity;
    private final StringProperty status;

    // Constructor for database loading (with ID)
    public Request(int id, String requesterName, String contact, String location,
                   String resourceNeeded, int quantity, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.requesterName = new SimpleStringProperty(requesterName);
        this.contact = new SimpleStringProperty(contact);
        this.location = new SimpleStringProperty(location);
        this.resourceNeeded = new SimpleStringProperty(resourceNeeded);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.status = new SimpleStringProperty(status);
    }

    // Constructor for new requests (no ID yet)
    public Request(String requesterName, String contact, String location,
                   String resourceNeeded, int quantity, String status) {
        this(0, requesterName, contact, location, resourceNeeded, quantity, status);
    }

    // ====== JavaFX Properties ======
    public IntegerProperty idProperty() { return id; }
    public StringProperty requesterNameProperty() { return requesterName; }
    public StringProperty contactProperty() { return contact; }
    public StringProperty locationProperty() { return location; }
    public StringProperty resourceNeededProperty() { return resourceNeeded; }
    public IntegerProperty quantityProperty() { return quantity; }
    public StringProperty statusProperty() { return status; }

    // ====== Getters & Setters ======
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }

    public String getRequesterName() { return requesterName.get(); }
    public void setRequesterName(String requesterName) { this.requesterName.set(requesterName); }

    public String getContact() { return contact.get(); }
    public void setContact(String contact) { this.contact.set(contact); }

    public String getLocation() { return location.get(); }
    public void setLocation(String location) { this.location.set(location); }

    public String getResourceNeeded() { return resourceNeeded.get(); }
    public void setResourceNeeded(String resourceNeeded) { this.resourceNeeded.set(resourceNeeded); }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
}
