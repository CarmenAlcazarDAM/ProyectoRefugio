package org.proyectorefugio.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    /**
     * Carga de forma dinámica un archivo FXML dentro del AnchorPane.
     * @param fxml --> ruta del archivo.fxml que deseamos cargar
     */
    public static void cargarVista(AnchorPane panelContenido, String fxml) {
        try {
            Parent vista = FXMLLoader.load(SceneManager.class.getResource(fxml));

            // Ajustamos los márgenes a 0 para que sea responsivo
            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);

            // Cambiamos el contenido del panel
            panelContenido.getChildren().setAll(vista);

        } catch (IOException e) {
            System.err.println("Error al cargar la vista FXML: " + fxml);
            e.printStackTrace();
        }
    }

    /**
     * Gestiona abrir las ventanas emergentes
     *
     * @param ruta   --> ruta donde está guardado el archivo .fxml
     * @param titulo --> el título que tendrá la ventana emergente.
     */
    public static void abrirVentanaEmergente(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(ruta));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.setResizable(false);


            // Bloquea la ventana principal hasta que se cierre esta
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait(); // showAndWait es ideal para modales

        } catch (IOException e) {
            System.err.println("Error al cargar la ventana: " + ruta);
            e.printStackTrace();
        }
    }

}