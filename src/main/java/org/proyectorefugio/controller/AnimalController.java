package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.modelDAO.AnimalDAO;

import java.io.IOException;

public class AnimalController {
    @FXML
    private AnchorPane panelContenido;
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

    public void selectPerros(ActionEvent event) {
        cargarVista("/org/proyectorefugio/perro-view.fxml");
    }

    public void selectGatos(ActionEvent event) {
        cargarVista("/org/proyectorefugio/gato-view.fxml");
    }
}
