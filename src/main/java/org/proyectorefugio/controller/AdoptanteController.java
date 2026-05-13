package org.proyectorefugio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    public Label informacionAdicional;


    @FXML
    /**
     * Método que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        tablaAdoptantes();
        mostrarInformacionAdicional();

    }

    /**
     * Método que extrae los datos de los adoptantes de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaAdoptantes() {
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

    /**
     * Método que muestra toda la información del Adoptante cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        tablaAdoptantes.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionado) -> {
            if (seleccionado != null) {
                informacionAdicional.setText(
                        "Nombre: " + seleccionado.getNombre() + "\n" +
                                "DNI: " + seleccionado.getDni() + "\n" +
                                "Teléfono: " + seleccionado.getTelefono() + "\n" +
                                "Correo: " + seleccionado.getCorreo() + "\n" +
                                "Dirección: " + seleccionado.getDireccion()
                );
            }
        });
    }
}
