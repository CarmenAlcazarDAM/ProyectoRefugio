package org.proyectorefugio.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PrincipalController {
    @FXML
    private AnchorPane panelContenido;

    /**
     * Carga de forma dinámica un archivo FXML dentro del AnchorPane.
     * @param fxml --> ruta del archivo.fxml que deseamos cargar
     */
    private void cargarVista(String fxml) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(fxml));

            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);

            panelContenido.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que abre la ventana animales-view.fxml cuando pulsamos el botón Animales en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirAnimales(ActionEvent event){
        cargarVista("/org/proyectorefugio/animales-view.fxml");
    }

    /**
     * Método que abre la ventana voluntario-view.fxml cuando pulsamos el botón Voluntarios en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirVoluntarios(ActionEvent event) {
        cargarVista("/org/proyectorefugio/voluntario-view.fxml");
    }

    /**
     * Método que abre la ventana adoptante-view.fxml cuando pulsamos el botón Adoptantes en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirAdoptantes(ActionEvent event) {
        cargarVista("/org/proyectorefugio/adoptante-view.fxml");
    }
    /**
     * Método que abre la ventana ubicacion-view.fxml cuando pulsamos el botón Ubicaciones en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirUbicaciones(ActionEvent event) {
        cargarVista("/org/proyectorefugio/ubicacion-view.fxml");
    }

    /**
     * Método que vuelve a la ventana principal cuando pulsamos el botón INICIO en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void volverInicio(ActionEvent event) {
        cargarVista("/org/proyectorefugio/bienvenida-view.fxml");
    }

}