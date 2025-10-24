package com.swiftrelief;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final AdminDAO adminDAO = new AdminDAO();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (adminDAO.validateAdmin(username, password)) {
            messageLabel.setText("✅ Login successful!");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/swiftrelief/dashboard.fxml"));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("❌ Invalid username or password");
        }
    }

    // 🆕 Create Admin Window
    @FXML
    private void openCreateAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/swiftrelief/admin_view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create Admin");
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
