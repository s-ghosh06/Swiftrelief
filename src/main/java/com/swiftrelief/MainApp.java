package com.swiftrelief;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/swiftrelief/login_view.fxml"));
        Scene scene = new Scene(loader.load());

        // --- Add global stylesheet ---
        URL cssURL = getClass().getResource("/com/swiftrelief/style.css");
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
            System.out.println("[CSS] Loaded style.css successfully.");
        } else {
            System.out.println("[CSS] style.css not found! Check resource path.");
        }

        primaryStage.setTitle("SwiftRelief - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
