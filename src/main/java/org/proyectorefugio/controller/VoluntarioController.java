package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList<Voluntario> personasObservableList;
    private VoluntarioDAO dao = new VoluntarioDAO();

    @FXML
    public void  initialize(){
        personasObservableList = FXCollections.observableArrayList();

        listaVoluntarios.setItems(personasObservableList);

        actualizarLista();
    }

    public void actualizarLista() {

        List<Voluntario> listaDesdeDb = dao.findAll();
        personasObservableList.addAll(listaDesdeDb);
    }
}
