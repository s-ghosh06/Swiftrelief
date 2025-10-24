package com.swiftrelief;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RequestController {

    @FXML
    private TableView<Request> requestTable;
    @FXML
    private TableColumn<Request, String> requesterNameColumn;
    @FXML
    private TableColumn<Request, String> contactColumn;
    @FXML
    private TableColumn<Request, String> locationColumn;
    @FXML
    private TableColumn<Request, String> resourceNeededColumn;
    @FXML
    private TableColumn<Request, Integer> quantityColumn;
    @FXML
    private TableColumn<Request, String> statusColumn;

    @FXML
    private TextField requesterNameField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField resourceNeededField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> statusCombo;

    private final RequestDAO requestDAO = new RequestDAO();
    private final ObservableList<Request> requestList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up combo options
        statusCombo.setItems(FXCollections.observableArrayList("Pending", "Approved", "Completed"));

        // Link table columns
        requesterNameColumn.setCellValueFactory(cellData -> cellData.getValue().requesterNameProperty());
        contactColumn.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        resourceNeededColumn.setCellValueFactory(cellData -> cellData.getValue().resourceNeededProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        loadRequests();
    }

    /** Loads all requests from the database into the table */
    private void loadRequests() {
        requestList.clear();
        requestList.addAll(requestDAO.getAllRequests());
        requestTable.setItems(requestList);
    }

    /** Handles adding a new request */
    @FXML
    private void addRequest() {
        try {
            String requesterName = requesterNameField.getText();
            String contact = contactField.getText();
            String location = locationField.getText();
            String resourceNeeded = resourceNeededField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String status = statusCombo.getValue();

            if (requesterName.isEmpty() || contact.isEmpty() || location.isEmpty() || resourceNeeded.isEmpty() || status == null) {
                showError("Please fill in all fields.");
                return;
            }

            Request request = new Request(requesterName, contact, location, resourceNeeded, quantity, status);
            requestDAO.addRequest(request);

            loadRequests();
            clearForm();

            showInfo("Request added successfully!");
        } catch (NumberFormatException e) {
            showError("Quantity must be a number.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error adding request: " + e.getMessage());
        }
    }

    /** Handles deleting the selected request */
    @FXML
    private void deleteSelected() {
        Request selected = requestTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            requestDAO.deleteRequest(selected.getId());
            loadRequests();
            showInfo("Request deleted successfully.");
        } else {
            showError("Please select a request to delete.");
        }
    }

    /** ✅ Fix: Added refreshTable to resolve FXML load error */
    @FXML
    private void refreshTable() {
        loadRequests();
        requestTable.refresh();
        showInfo("Table refreshed.");
    }

    /** Clears input fields */
    private void clearForm() {
        requesterNameField.clear();
        contactField.clear();
        locationField.clear();
        resourceNeededField.clear();
        quantityField.clear();
        statusCombo.setValue(null);
    }

    /** Shows error alerts */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /** Shows information alerts */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
