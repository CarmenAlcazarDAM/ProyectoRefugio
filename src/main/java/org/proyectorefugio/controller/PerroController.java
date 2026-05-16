package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.PerroDAO;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.SceneManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


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
    public Button añadirPerroBD;
    public AnchorPane ventanaBuscar;


    @FXML
    public TextField buscarId;
    public TextField buscarNombre;
    public TextField buscarChip;
    public TextField buscarRaza;
    public TextField buscarColor;


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
        if (noAdoptado.isSelected()) {
            buscarAdoptados = true;
        } else {
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
        ventanaBuscar.setVisible(false);
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

    /**
     * Método que cuando al pulsar el botón Añadir abrirá el formulario correspondiente
     *
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonInsertarPerro(ActionEvent event) {
        RegistroAnimalController.tipo = "perro";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/registroAnimal-view.fxml", "Formulario de Registro");
    }

    /**
     * Método que cuando al pulsar el botón Adoptar abrirá el formulario correspondiente
     *
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonAdoptar(ActionEvent event) {
        FormularioPersonaYAdoptarController.persona = "adoptante";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");

    }

    public void botonBusqueda(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    public List<Perro> buscarAnimal() {
        String idAnimalTexto = buscarId.getText();
        int idAnimal = 0;
        if (idAnimalTexto != null) {
            idAnimal = Utils.conversorInt(idAnimalTexto);
        }
        ;
        String nombreAnimal = buscarNombre.getText();
        String chipAnimal = buscarChip.getText();
        String razaAnimal = buscarRaza.getText();
        String colorAnimal = buscarColor.getText();

        boolean buscarNoAdoptados = noAdoptado.isSelected();

        List<Perro> resultadosEncontrados = new ArrayList<>();

        if (idAnimalTexto != null && !idAnimalTexto.isEmpty() && idAnimal != 0) {
            Perro p = PerroDAO.findByID(idAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }
        }
        if (chipAnimal != null && !chipAnimal.isEmpty()) {
            Perro p = PerroDAO.findByChip(chipAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }
        }
        if (nombreAnimal != null && !nombreAnimal.isEmpty()) {
            resultadosEncontrados.addAll(PerroDAO.findByName(nombreAnimal, buscarNoAdoptados));
        }

        if (razaAnimal != null && !razaAnimal.isEmpty()) {
            resultadosEncontrados.addAll(PerroDAO.findByBreed(razaAnimal, buscarNoAdoptados));

        }
        if (colorAnimal != null && !colorAnimal.isEmpty()) {
            resultadosEncontrados.addAll(PerroDAO.findByColour(colorAnimal, buscarNoAdoptados));

        }

        HashSet<Perro> busquedaFinal = new HashSet<>(resultadosEncontrados);
        busquedaFinal.remove(null);
        return new ArrayList<>(busquedaFinal);
        //todo --> alertas
    }

    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Perro> resultados =
                FXCollections.observableArrayList(buscarAnimal());

        tablaPerros.setItems(resultados);
    }
}


