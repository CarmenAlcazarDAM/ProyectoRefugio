package org.proyectorefugio.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.VoluntarioDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VoluntarioController {
    @FXML
    private AnchorPane panelVoluntarios;
    public ListView<Voluntario> listaVoluntarios;
    private VoluntarioDAO voluntarioDAO = new VoluntarioDAO();


    public void inicio(URL url, ResourceBundle resourceBundle) {
        List<Voluntario> lista = voluntarioDAO.findAll();
    }

    public void actualizarLista(ListView.EditEvent<Voluntario> voluntarioEditEvent) {
        List<Voluntario> voluntarios = voluntarioDAO.findAll();
        for (Voluntario v: voluntarios){
            System.out.println(v);

        }
    }
}
