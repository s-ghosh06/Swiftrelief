package com.swiftrelief;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.sql.SQLException;
import java.util.List;

public class VolunteerController {

    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField contactField;
    @FXML private TextField locationField;
    @FXML private TextField skillField;
    @FXML private CheckBox availabilityCheckBox;

    @FXML private TableView<Volunteer> volunteerTable;
    @FXML private TableColumn<Volunteer, String> nameColumn;
    @FXML private TableColumn<Volunteer, Number> ageColumn;
    @FXML private TableColumn<Volunteer, String> contactColumn;
    @FXML private TableColumn<Volunteer, String> locationColumn;
    @FXML private TableColumn<Volunteer, String> skillColumn;
    @FXML private TableColumn<Volunteer, Boolean> availabilityColumn;

    private final ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Setup table columns
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        ageColumn.setCellValueFactory(cell -> cell.getValue().ageProperty());
        contactColumn.setCellValueFactory(cell -> cell.getValue().contactProperty());
        locationColumn.setCellValueFactory(cell -> cell.getValue().locationProperty());
        skillColumn.setCellValueFactory(cell -> cell.getValue().skillProperty());
        availabilityColumn.setCellValueFactory(cell -> cell.getValue().availabilityProperty());

        volunteerTable.setItems(volunteers);
        loadFromDatabase();
    }

    /** Load volunteers from database */
    private void loadFromDatabase() {
        volunteers.clear();
        try {
            List<Volunteer> all = VolunteerDAO.getAllVolunteers();
            volunteers.addAll(all);
            applyFadeTransition(volunteerTable);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    /** Add a new volunteer */
    @FXML
    private void addVolunteer() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String contact = contactField.getText().trim();
        String location = locationField.getText().trim();
        String skill = skillField.getText().trim();
        boolean availability = availabilityCheckBox.isSelected();

        if (name.isEmpty() || ageText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Name and Age are required.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Age must be a number.");
            return;
        }

        Volunteer v = new Volunteer(name, age, contact, skill, location, availability);

        try {
            VolunteerDAO.insertVolunteer(v);
            volunteers.add(0, v);
            clearForm();
            applyFadeTransition(volunteerTable);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Volunteer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    /** Delete selected volunteer */
    @FXML
    private void deleteSelected() {
        Volunteer sel = volunteerTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.WARNING, "Delete", "Please select a volunteer first.");
            return;
        }

        try {
            VolunteerDAO.deleteVolunteer(sel.getId());
            volunteers.remove(sel);
            showAlert(Alert.AlertType.INFORMATION, "Deleted", "Volunteer removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    /** ✅ Added refreshTable() to match FXML */
    @FXML
    private void refreshTable() {
        loadFromDatabase();
        volunteerTable.refresh();
        showAlert(Alert.AlertType.INFORMATION, "Refreshed", "Volunteer list updated.");
    }

    /** On row click — populate form for editing or review */
    @FXML
    private void tableClicked(MouseEvent event) {
        Volunteer sel = volunteerTable.getSelectionModel().getSelectedItem();
        if (sel != null) {
            nameField.setText(sel.getName());
            ageField.setText(String.valueOf(sel.getAge()));
            contactField.setText(sel.getContact());
            locationField.setText(sel.getLocation());
            skillField.setText(sel.getSkill());
            availabilityCheckBox.setSelected(sel.isAvailability());
        }
    }

    /** Utility to clear form fields */
    private void clearForm() {
        nameField.clear();
        ageField.clear();
        contactField.clear();
        locationField.clear();
        skillField.clear();
        availabilityCheckBox.setSelected(false);
    }

    /** Reusable alert utility */
    private void showAlert(Alert.AlertType type, String title, String text) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(text);
        a.showAndWait();
    }

    /** Adds subtle fade-in transition when data refreshes */
    private void applyFadeTransition(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
