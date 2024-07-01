package com.taba.inventory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Login.fxml")));
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        stage.setTitle("Gestión de Inventario");
        stage.getIcons().add(new Image("/images/logo_palmesano.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if (HibernateUtil.setSessionFactory()) {
            launch(args);
            try {
                HibernateUtil.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Platform.startup(() -> {
                showAlert();
                Platform.exit();
            });
        }
    }

    private static void showAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setHeaderText("Error de conexión a la base de datos!");
            alert.setContentText("Por favor contacte con el administrador del sistema.");
            alert.showAndWait();
        });
    }
}

