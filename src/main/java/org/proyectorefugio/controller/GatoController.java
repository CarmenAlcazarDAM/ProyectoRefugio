package org.proyectorefugio.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.modelDAO.GatoDAO;

public class GatoController {
    @FXML
    public TableView<Gato> tablaGatos;
    @FXML
    public TableColumn<Gato, String> nombreCol;
    @FXML
    public TableColumn<Gato, Integer> idCol;
    @FXML
    public TableColumn<Gato, String> sexoCol;
    @FXML
    public TableColumn<Gato, String> razaCol;

    @FXML
    public Label informacionAdicional;


    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        tablaGatos();
        mostrarInformacionAdicional();

    }

    /**
     * Método que extrae los datos de los gatos de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaGatos() {
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        razaCol.setCellValueFactory(new PropertyValueFactory<>("raza"));

        ObservableList<Gato> listaGatos =
                FXCollections.observableArrayList(GatoDAO.findAll(false/*check de adoptado o no */));

        tablaGatos.setItems(listaGatos);
    }

    /**
     * Método que muestra toda la información del Gato cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        tablaGatos.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {
                        informacionAdicional.setText(
                                "Id: " + seleccionado.getId() + "\n" +
                                        "Nombre: " + seleccionado.getNombre() + "\n" +
                                        "Color: " + seleccionado.getColor() + "\n" +
                                        "Número Chip:  " + seleccionado.getNumeroChip() + "\n" +
                                        "Esterilizado: " + seleccionado.isEsterilizado() + "\n" +
                                        "Fecha Ingreso: " + seleccionado.getFechaIngreso() + "\n" +
                                        "Observaciones: " + seleccionado.getObservaciones() + "\n" +
                                        "Historia: " + seleccionado.getHistoria() + "\n"
                        );
                        if (seleccionado.getFechaAlta() != null) {
                            informacionAdicional.setText("\n" + "Fecha Alta: " + seleccionado.getFechaAlta());
                        }
                    }
                });
    }
}



