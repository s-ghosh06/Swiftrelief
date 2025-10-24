package com.swiftrelief;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResourceController {

    @FXML private TextField typeField;
    @FXML private TextField quantityField;
    @FXML private TextField locationField;
    @FXML private CheckBox availableCheck;

    @FXML private TableView<Resource> resourceTable;
    @FXML private TableColumn<Resource, String> typeColumn;
    @FXML private TableColumn<Resource, Integer> quantityColumn;
    @FXML private TableColumn<Resource, String> locationColumn;
    @FXML private TableColumn<Resource, Boolean> availableColumn;

    // ✅ Initialize: setup columns and load data
    @FXML
    public void initialize() {
        System.out.println("🔧 Initializing ResourceController...");

        // Proper property binding using getter names
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        refreshTable();
    }

    // ✅ Add new resource
    @FXML
    private void addResource() {
        try {
            String type = typeField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String location = locationField.getText().trim();
            boolean available = availableCheck.isSelected();

            if (type.isEmpty() || location.isEmpty()) {
                showError("Type and Location cannot be empty.");
                return;
            }

            Resource resource = new Resource(type, quantity, location, available);
            ResourceDAO.addResource(resource);

            refreshTable();
            clearFields();

            System.out.println("✅ Resource added: " + type);
        } catch (NumberFormatException e) {
            showError("Quantity must be a number.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error adding resource: " + e.getMessage());
        }
    }

    // ✅ Delete selected resource
    @FXML
    private void deleteSelected() {
        Resource selected = resourceTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ResourceDAO.deleteResource(selected);
            refreshTable();
            System.out.println("🗑️ Resource deleted: " + selected.getType());
        } else {
            showError("Please select a resource to delete.");
        }
    }

    // ✅ Refresh the table from DB
    @FXML
    public void refreshTable() {
        System.out.println("[DBUtil] Connecting to database...");
        ObservableList<Resource> list = ResourceDAO.getAllResources();
        resourceTable.setItems(list);

        System.out.println("✅ Table refreshed with " + list.size() + " resources.");
        for (Resource r : list) {
            System.out.println("→ " + r.getType() + " | " + r.getQuantity() + " | " + r.getLocation() + " | " + r.isAvailable());
        }
    }

    // ✅ Clear input fields
    private void clearFields() {
        typeField.clear();
        quantityField.clear();
        locationField.clear();
        availableCheck.setSelected(false);
    }

    // ✅ Show error message popup
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
