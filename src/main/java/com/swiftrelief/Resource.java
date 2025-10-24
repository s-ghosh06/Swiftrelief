package com.swiftrelief;

import javafx.beans.property.*;

public class Resource {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final BooleanProperty available = new SimpleBooleanProperty();

    // Full constructor
    public Resource(int id, String type, int quantity, String location, boolean available) {
        this.id.set(id);
        this.type.set(type);
        this.quantity.set(quantity);
        this.location.set(location);
        this.available.set(available);
    }

    // Constructor for adding new (without id)
    public Resource(String type, int quantity, String location, boolean available) {
        this(0, type, quantity, location, available);
    }

    // --- Getters / Setters
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getType() { return type.get(); }
    public void setType(String type) { this.type.set(type); }
    public StringProperty typeProperty() { return type; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }

    public String getLocation() { return location.get(); }
    public void setLocation(String location) { this.location.set(location); }
    public StringProperty locationProperty() { return location; }

    public boolean isAvailable() { return available.get(); }
    public void setAvailable(boolean available) { this.available.set(available); }
    public BooleanProperty availableProperty() { return available; }

    // 👇 Add a readable string version for table display
    public String getAvailableText() {
        return available.get() ? "Yes" : "No";
    }
}
