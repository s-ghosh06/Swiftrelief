package com.swiftrelief;

import javafx.beans.property.*;

public class Volunteer {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty age;
    private final StringProperty contact;
    private final StringProperty skill;
    private final StringProperty location;
    private final BooleanProperty availability;

    // Constructor for new volunteers (no id yet)
    public Volunteer(String name, int age, String contact, String skill, String location, boolean availability) {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.contact = new SimpleStringProperty(contact);
        this.skill = new SimpleStringProperty(skill);
        this.location = new SimpleStringProperty(location);
        this.availability = new SimpleBooleanProperty(availability);
    }

    // Constructor when loading from DB (with id)
    public Volunteer(int id, String name, int age, String contact, String skill, String location, boolean availability) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.contact = new SimpleStringProperty(contact);
        this.skill = new SimpleStringProperty(skill);
        this.location = new SimpleStringProperty(location);
        this.availability = new SimpleBooleanProperty(availability);
    }

    // Getters
    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public int getAge() { return age.get(); }
    public String getContact() { return contact.get(); }
    public String getSkill() { return skill.get(); }
    public String getLocation() { return location.get(); }
    public boolean isAvailability() { return availability.get(); }

    // Property getters (for TableView bindings)
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty contactProperty() { return contact; }
    public StringProperty skillProperty() { return skill; }
    public StringProperty locationProperty() { return location; }
    public BooleanProperty availabilityProperty() { return availability; }

    // Setters
    public void setId(int id) { this.id.set(id); }
    public void setName(String name) { this.name.set(name); }
    public void setAge(int age) { this.age.set(age); }
    public void setContact(String contact) { this.contact.set(contact); }
    public void setSkill(String skill) { this.skill.set(skill); }
    public void setLocation(String location) { this.location.set(location); }
    public void setAvailability(boolean availability) { this.availability.set(availability); }
}
