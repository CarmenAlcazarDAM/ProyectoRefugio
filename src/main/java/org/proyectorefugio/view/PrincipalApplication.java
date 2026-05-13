package org.proyectorefugio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/org/proyectorefugio/refugio-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 750);
        stage.setMinWidth(1500);
        stage.setMinHeight(780);
        stage.setMaxWidth(1500);
        stage.setMaxHeight(780);
        stage.setTitle("Refugio");
        stage.setScene(scene);
        stage.show();
    }
}
