package org.proyectorefugio.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {

    /**
     * Gestiona abrir las ventanas emergentes
     * @param ruta --> ruta donde está guardado el archivo .fxml
     * @param titulo --> el título que tendrá la ventana emergente.
     */
    public static void abrirVentanaEmergente(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(ruta));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));

            // Bloquea la ventana principal hasta que se cierre esta
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait(); // showAndWait es ideal para modales

        } catch (IOException e) {
            System.err.println("Error al cargar la ventana: " + ruta);
            e.printStackTrace();
        }
    }
}