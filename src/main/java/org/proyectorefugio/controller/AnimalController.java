package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;

import org.proyectorefugio.view.SceneManager;

import java.io.IOException;

public class AnimalController {
    @FXML
    private AnchorPane panelContenido;
//    private void cargarVista(String fxml) {
//        try {
//            Parent vista = FXMLLoader.load(getClass().getResource(fxml));
//
//            AnchorPane.setTopAnchor(vista, 0.0);
//            AnchorPane.setBottomAnchor(vista, 0.0);
//            AnchorPane.setLeftAnchor(vista, 0.0);
//            AnchorPane.setRightAnchor(vista, 0.0);
//
//            panelContenido.getChildren().setAll(vista);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
@FXML
    public void seleccionarPerro(ActionEvent event) {
       SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/perro-view.fxml");
    }
    @FXML
    public void seleccionarGato(ActionEvent event) {
        SceneManager.cargarVista(panelContenido,"/org/proyectorefugio/gato-view.fxml");
    }
}
