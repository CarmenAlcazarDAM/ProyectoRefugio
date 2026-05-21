package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.layout.Pane;
import org.proyectorefugio.view.SceneManager;

/**
 * Controlador para la gestión de animales del refugio.
 * Gestiona la navegación con botones entre los distintos tipos de animales.
 */
public class AnimalController {

    @FXML
    /** Panel contenedor donde se cargan dinámicamente las vistas de cada animal. */
    private Pane panelContenido;

    @FXML
    /**
     * Maneja la carga del fxml correspondiente a la vista de los perros
     * @param event --> acción que tiene lugar al pulsar el botón
     */
    public void seleccionarPerro(ActionEvent event) {
       SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/perro-view.fxml");
    }

    @FXML
    /**
     * Maneja la carga del fxml correspondiente a la vista de los gatos
     * @param event --> acción que tiene lugar al pulsar el botón
     */
    public void seleccionarGato(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/gato-view.fxml");
    }
}
