package com.swiftrelief;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label statusLabel;

    private final AdminDAO adminDAO = new AdminDAO();

    @FXML
    private void handleCreateAdmin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("⚠️ All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            statusLabel.setText("⚠️ Passwords do not match.");
            return;
        }

        boolean success = adminDAO.addAdmin(username, password);
        if (success) {
            statusLabel.setText("✅ Admin created successfully!");
        } else {
            statusLabel.setText("❌ Failed to create admin (maybe username exists).");
        }
    }
}
