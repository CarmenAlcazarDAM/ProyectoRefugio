package org.proyectorefugio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Adoptante;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Persona;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AdoptanteDAO;
import org.proyectorefugio.modelDAO.PersonaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;
import org.proyectorefugio.view.Mensajes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class PersonaController {


    @FXML
    public TableColumn<Persona, String> nombreCompletoCol;
    @FXML
    public TableColumn<Persona, String> dniCol;

    @FXML
    public TableView<Persona> tablaPersonas;
    public Label informacionAdicional;
    public CheckBox voluntario;
    public CheckBox adoptante;

    public AnchorPane ventanaBuscar;
    public TextField buscarDNI;
    public TextField buscarNombre;
    public TextField buscarApellidos;


    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        adoptante.setSelected(true);
        tablaPersonas();
        mostrarInformacionAdicional();
        ocultarTodosPaneles();
    }

    /**
     * Se encarga de ocultar todos los AnchorPane que puedan estar visibles
     */
    private void ocultarTodosPaneles() {
        ventanaBuscar.setVisible(false);
        informacionAdicional.setVisible(false);
//        panelModificacion.setVisible(false);
    }

    public void seleccionVoluntario(ActionEvent event) {
        if (voluntario.isSelected()) {
            adoptante.setSelected(false);
            tablaPersonas();
        }
    }

    public void seleccionAdoptante(ActionEvent event) {
        if (adoptante.isSelected()) {
            voluntario.setSelected(false);
            tablaPersonas();
        }
    }

    /**
     * Metodo que extrae los datos de los adoptantes de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaPersonas() {
        nombreCompletoCol.setCellValueFactory(cellData -> {
            Persona p = cellData.getValue();
            return new SimpleStringProperty(p.getNombre() + " " + p.getApellidos());
        });

        dniCol.setCellValueFactory(new PropertyValueFactory<>("dni"));

        ObservableList<Persona> lista;

        if (voluntario.isSelected()) {
            lista = FXCollections.observableArrayList(VoluntarioDAO.findAll());
            tablaPersonas.setItems(lista);
        } else {
            lista = FXCollections.observableArrayList(AdoptanteDAO.findAll());
            tablaPersonas.setItems(lista);
        }

    }

    /**
     * Metodo que muestra toda la información de la persona cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionado) -> {
            if (seleccionado != null) {
                informacionAdicional.setVisible(true);
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

    //region ------------------- GESTIÓN DE BÚSQUEDAS -------------------


    public void botonBusqueda(ActionEvent event) {
        tablaPersonas();
//        panelModificacion.setVisible(false);
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    public List<Persona> buscarPersona() {
        String dni = buscarDNI.getText();
        String nombre = buscarNombre.getText();
        String apellido = buscarApellidos.getText();

        if (dni.trim().isEmpty() && nombre.trim().isEmpty() && apellido.trim().isEmpty()) {
            Mensajes.aletaObligatoriosCamposVacios("Introduce al menos un criterio de búsqueda");
            tablaPersonas();
            return null;
        }
        List<Persona> personasEncontradas = new ArrayList<>();

        if (dni != null && !dni.trim().isEmpty()) {
            Persona p = PersonaDAO.findByDni(dni);
            List<Persona> resultado = new ArrayList<>();
            if (p != null) {
                resultado.add(p);
                return resultado;

            } else {
                Mensajes.alertaNoExiste("No existe ningún voluntario con el DNI / NIF: " + dni);
            }
        }

        if (nombre != null && !nombre.trim().isEmpty()) {
            personasEncontradas.addAll(PersonaDAO.findByName(nombre));
        }

        if (apellido != null && !apellido.isEmpty()) {
            personasEncontradas.addAll(PersonaDAO.findByLastName(apellido));
        }

        HashSet<Persona> busquedaFinal = new HashSet<>(personasEncontradas);
        busquedaFinal.remove(null);

        if (busquedaFinal.isEmpty()) {
            Mensajes.alertaErrorDeRegistro("No se encontraron resultados en la base de datos");
            return new ArrayList<>();
        }

        return new ArrayList<>(busquedaFinal);


    }

    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Persona> resultados =
                FXCollections.observableArrayList(buscarPersona());

        tablaPersonas.setItems(resultados);
    }
//endregion
}
