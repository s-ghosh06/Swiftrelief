package com.swiftrelief;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentPane;

    @FXML
    private void showVolunteer() {
        loadView("volunteer_view.fxml");
    }

    @FXML
    private void showResource() {
        loadView("resource_view.fxml");
    }

    @FXML
    private void showRequest() {
        loadView("request_view.fxml");
    }

    @FXML
    private void showDisaster() {
        loadView("disaster_view.fxml");
    }
    

    /**
     * Loads the given FXML file inside the content pane with a smooth fade transition.
     */
    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/swiftrelief/" + fxmlFile));

            if (loader.getLocation() == null) {
                System.err.println("❌ FXML not found: " + fxmlFile);
                return;
            }

            Node node = loader.load();

            // Replace content and apply transition
            contentPane.getChildren().setAll(node);
            applyFadeTransition(node);

            System.out.println("✅ Loaded view: " + fxmlFile);

        } catch (IOException e) {
            System.err.println("⚠️ Error loading view: " + fxmlFile);
            e.printStackTrace();
        }
    }

    /**
     * Applies a fade-in animation for smoother transitions.
     */
    private void applyFadeTransition(Node node) {
        FadeTransition fade = new FadeTransition(Duration.millis(500), node);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
