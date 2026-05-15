package org.proyectorefugio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AyudaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;
import org.proyectorefugio.view.SceneManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class VoluntarioController {
    @FXML
    public TableView<Ayuda> tablaAyuda;

    @FXML
    public TableColumn<Ayuda, String> voluntarioCol;
    @FXML
    public TableColumn<Ayuda, Integer> ubicacionCol;

    @FXML
    public TableColumn<Ayuda, LocalDate> fechaCol;

    @FXML
    public TableColumn<Ayuda, String> tareaCol;

    @FXML
    public ListView<Voluntario> listaVoluntarios;

    @FXML
    public Label informacionAdicional;

    @FXML
    private void initialize() {
        iniciarTabla();
        iniciarListaVoluntarios();
        mostrarInformacionAdicional();
    }

    public void iniciarTabla(){
        voluntarioCol.setCellValueFactory(cellData -> {
            String dni = cellData.getValue().getDniVoluntario();

            Voluntario voluntario = VoluntarioDAO.findByDni(dni);
            return new SimpleStringProperty(voluntario.getNombre() + " " + voluntario.getApellidos());
        });

        ubicacionCol.setCellValueFactory(
                new PropertyValueFactory<>("idUbicacion")
        );
        fechaCol.setCellValueFactory(
                new PropertyValueFactory<>("fecha")
        );
        tareaCol.setCellValueFactory(
                new PropertyValueFactory<>("tarea")
        );

        ObservableList<Ayuda> listaAyudas =
                FXCollections.observableArrayList(AyudaDAO.findAll());

        tablaAyuda.setItems(listaAyudas);
    }

    public void iniciarListaVoluntarios(){
        ObservableList<Voluntario> observable = FXCollections.observableList(VoluntarioDAO.findAll());
        listaVoluntarios.setItems(observable);
    }

    /**
     * Método que muestra toda la información del Voluntario cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        listaVoluntarios.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionado) -> {
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

    /**
     * Método que cuando al pulsar el botón "Nuevo Voluntario" abrirá el formulario correspondiente
     * @param event --> acción que se va a llevar a cabo
     */
    //todo -> podria hacer una opcion que pregunte antes si se ha registrado anteriormente como voluntario o adoptante
    public void botonInsertarVoluntario(ActionEvent event) {
        FormularioPersonaYAdoptarController.persona = "voluntario";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");
    }


}
