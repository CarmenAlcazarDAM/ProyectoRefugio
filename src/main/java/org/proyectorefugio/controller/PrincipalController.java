package org.proyectorefugio.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.view.SceneManager;


public class PrincipalController {
    @FXML
    private AnchorPane panelContenido;

    @FXML
    /**
     * Metodo que abre la ventana animales-view.fxml cuando pulsamos el botón Animales en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirAnimales(ActionEvent event){
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/animales-view.fxml");
    }
    @FXML
    /**
     * Metodo que abre la ventana voluntario-view.fxml cuando pulsamos el botón Voluntarios en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirVoluntarios(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/voluntario-view.fxml");
    }
    @FXML
    /**
     * Metodo que abre la ventana persona-view.fxml cuando pulsamos el botón Adoptantes en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirAdoptantes(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/persona-view.fxml");
    }

    @FXML
    /**
     * Metodo que abre la ventana ubicacion-view.fxml cuando pulsamos el botón Ubicaciones en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void abrirUbicaciones(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/ubicacion-view.fxml");
    }
    @FXML
    /**
     * Metodo que vuelve a la ventana principal cuando pulsamos el botón INICIO en JavaFX
     * @param event --> evento de acción (clic del botón)
     */
    public void volverInicio(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/bienvenida-view.fxml");
    }

}