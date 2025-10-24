package com.swiftrelief;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Duration;

public class DisasterController {

    @FXML private TextField nameField;
    @FXML private TextField typeField;
    @FXML private TextField locationField;
    @FXML private TextArea descriptionArea;
    @FXML private ComboBox<String> severityCombo;
    @FXML private ComboBox<String> statusCombo;

    @FXML private TableView<Disaster> disasterTable;
    @FXML private TableColumn<Disaster, String> nameColumn;
    @FXML private TableColumn<Disaster, String> typeColumn;
    @FXML private TableColumn<Disaster, String> locationColumn;
    @FXML private TableColumn<Disaster, String> severityColumn;
    @FXML private TableColumn<Disaster, String> statusColumn;

    private final DisasterDAO disasterDAO = new DisasterDAO();
    private final ObservableList<Disaster> disasterList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind columns to properties — use proper getters if no JavaFX properties exist
        nameColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        typeColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getType()));
        locationColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getLocation()));
        severityColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getSeverity()));
        statusColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getStatus()));

        // Combo options
        severityCombo.setItems(FXCollections.observableArrayList("Low", "Moderate", "High", "Severe"));
        statusCombo.setItems(FXCollections.observableArrayList("Active", "Under Control", "Resolved"));

        // Load table data
        refreshTable();
    }

    /** Add new disaster record */
    @FXML
    private void addDisaster(ActionEvent event) {
        try {
            String name = nameField.getText();
            String type = typeField.getText();
            String location = locationField.getText();
            String description = descriptionArea.getText();
            String severity = severityCombo.getValue();
            String status = statusCombo.getValue();

            if (name.isEmpty() || type.isEmpty() || location.isEmpty() || severity == null || status == null) {
                showAlert(Alert.AlertType.WARNING, "Validation", "All fields except description are required.");
                return;
            }

            Disaster disaster = new Disaster(0, name, type, location, description, severity, status);
            disasterDAO.addDisaster(disaster);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Disaster added successfully!");
            clearFields();
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Could not add disaster: " + e.getMessage());
        }
    }

    /** Delete selected disaster */
    @FXML
    private void deleteSelected(ActionEvent event) {
        Disaster selected = disasterTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Delete", "Please select a disaster first.");
            return;
        }

        disasterDAO.deleteDisaster(selected.getId());
        showAlert(Alert.AlertType.INFORMATION, "Deleted", "Disaster deleted successfully!");
        refreshTable();
    }

    /** Refresh table from DB */
    @FXML
    private void refreshTable() {
        disasterList.setAll(disasterDAO.getAllDisasters());
        disasterTable.setItems(disasterList);
        applyFadeTransition(disasterTable);
    }

    /** Clear input fields */
    private void clearFields() {
        nameField.clear();
        typeField.clear();
        locationField.clear();
        descriptionArea.clear();
        severityCombo.setValue(null);
        statusCombo.setValue(null);
    }

    /** Helper — alert display */
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /** Add smooth fade animation */
    private void applyFadeTransition(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
