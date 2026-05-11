package org.proyectorefugio.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController {
    @FXML
    private AnchorPane panelContenido;

    private void cargarVista(String fxml) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(fxml));

            // Ajustar la vista para que ocupe todo el AnchorPane
            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);

            panelContenido.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void abrirAnimales(ActionEvent event){
        cargarVista("/org/proyectorefugio/animales-view.fxml");
    }

}
