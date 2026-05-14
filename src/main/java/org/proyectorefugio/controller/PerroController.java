package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.modelDAO.PerroDAO;

public class PerroController {
    @FXML
    public TableView<Perro> tablaPerros;
    @FXML
    public TableColumn<Perro, String> nombreCol;
    @FXML
    public TableColumn<Perro, Integer> idCol;
    @FXML
    public TableColumn<Perro, String> sexoCol;
    @FXML
    public TableColumn<Perro, String> razaCol;

    @FXML
    public Label informacionAdicional;
    public CheckBox noAdoptado;
    public CheckBox adoptado;


    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        tablaPerros();
        mostrarInformacionAdicional();
    }

    @FXML
    /**
     * Selecciona el check de No adoptados y desmarca el de adoptados.
     * Recarga la tabla.
     */
    private void seleccionNoAdoptado() {
        if (noAdoptado.isSelected()) {
            adoptado.setSelected(false);
        } else {
            adoptado.setSelected(true);
        }
        tablaPerros();
    }

    @FXML
    /**
     * Selecciona el check de Adoptados y desmarca el de no adoptados.
     * Mantiene siempre la opción de No Adoptado activa y recarga la tabla
     */
    private void seleccionAdoptado() {
        if (adoptado.isSelected()) {
            noAdoptado.setSelected(false);
        } else {
            noAdoptado.setSelected(true);
        }
        tablaPerros();
    }

    /**
     * Método que extrae los datos de los perros de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaPerros() {
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        razaCol.setCellValueFactory(new PropertyValueFactory<>("raza"));

       boolean buscarAdoptados;
       if(noAdoptado.isSelected()){
           buscarAdoptados = true;
       }else{
           buscarAdoptados = false;
       }

        ObservableList<Perro> listaPerros =
                FXCollections.observableArrayList(PerroDAO.findAll(buscarAdoptados));

        tablaPerros.setItems(listaPerros);
    }

    /**
     * Método que muestra toda la información del Perro cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        tablaPerros.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {

                        String datosMostrar = "Id: " + seleccionado.getId() + "\n" +
                                "Nombre: " + seleccionado.getNombre() + "\n" +
                                "Edad: " + seleccionado.getEdad() + "\n" +
                                "Sexo: " + seleccionado.getSexo() + "\n" +
                                "Raza: " + seleccionado.getRaza() + "\n" +
                                "Color: " + seleccionado.getColor() + "\n" +
                                "Peso: " + seleccionado.getPeso() + " Kg \n";

                        if (seleccionado.getNumeroChip() != null) {
                            datosMostrar += "Número Chip:  " + seleccionado.getNumeroChip() + "\n";
                        }
                        datosMostrar += "Esterilizado: " + seleccionado.isEsterilizadoTexto() + "\n" +
                                "Fecha Ingreso: " + seleccionado.getFechaIngreso() + "\n";
                        if (seleccionado.getObservaciones() != null) {
                            datosMostrar += "Observaciones: " + seleccionado.getObservaciones() + "\n";
                        }
                        if (seleccionado.getHistoria() != null) {
                            datosMostrar += "Historia: " + seleccionado.getHistoria() + "\n";
                        }
                        datosMostrar += "Agresivo: " + seleccionado.isAgresivoTexto() + "\n";

                        if (seleccionado.getFechaAlta() != null) {
                            datosMostrar += "Fecha Alta: " + seleccionado.getFechaAlta();
                        }
                        informacionAdicional.setText(datosMostrar);
                    }
                });
    }

    public boolean noAdoptado(ActionEvent event) {
        return false;
    }
}

