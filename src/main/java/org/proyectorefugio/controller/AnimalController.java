package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;

import org.proyectorefugio.view.SceneManager;


public class AnimalController {
    //todo -> faltan comentarios
    @FXML
    private AnchorPane panelContenido;

    @FXML
    public void seleccionarPerro(ActionEvent event) {
       SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/perro-view.fxml");
    }
    @FXML
    public void seleccionarGato(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/gato-view.fxml");
    }
}
