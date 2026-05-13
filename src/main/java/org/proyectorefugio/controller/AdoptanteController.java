package org.proyectorefugio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.proyectorefugio.model.Adoptante;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AdoptanteDAO;
import org.proyectorefugio.modelDAO.AyudaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;


public class AdoptanteController {

    @FXML
    public TableView<Adoptante> tablaAdoptantes;
    @FXML
    public TableColumn<Adoptante, String> nombreCompletoCol;
    @FXML
    public TableColumn<Ayuda, String> dniCol;



    @FXML
    private void initialize() {
        tablaAdoptantes();

    }

    public void tablaAdoptantes(){
        nombreCompletoCol.setCellValueFactory(cellData -> {
            String dni = cellData.getValue().getDni();

            Adoptante adoptante = AdoptanteDAO.findByDni(dni);
            return new SimpleStringProperty(adoptante.getNombre() + " " + adoptante.getApellidos());
        });
        dniCol.setCellValueFactory(
                new PropertyValueFactory<>("dni")
        );

        ObservableList<Adoptante> listaAdoptantes =
                FXCollections.observableArrayList(AdoptanteDAO.findAll());

        tablaAdoptantes.setItems(listaAdoptantes);
    }
}
