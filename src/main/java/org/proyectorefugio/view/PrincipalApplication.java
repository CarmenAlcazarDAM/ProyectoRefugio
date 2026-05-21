package org.proyectorefugio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 * Se encarga de cargar la vista raíz y configurar la ventana principal.
 */
public class PrincipalApplication extends Application {

    @Override
    /**
     * Inicializa y muestra la ventana principal de la aplicación.
     * Carga el FXML raíz, establece las dimensiones mínimas de la ventana
     * y la hace visible.
     *
     * @param stage --> ventana principal proporcionada por JavaFX
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/org/proyectorefugio/refugio-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 780);
        stage.setMinWidth(1500);
        stage.setMinHeight(780);
        stage.setResizable(true);
        stage.setTitle("Refugio");
        stage.setScene(scene);
        stage.show();
    }
}
