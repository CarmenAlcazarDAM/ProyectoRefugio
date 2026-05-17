package org.proyectorefugio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AyudaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.SceneManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VoluntarioController {
    //region ------------------- FXML-------------------
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
    public AnchorPane ventanaBuscar;

    @FXML
    public TextField buscarDNI;
    public TextField buscarNombre;
    public TextField buscarApellidos;
    public TextField buscarUbicacion;
    public DatePicker buscarFecha;

    //endregion


    @FXML
    /**
     * Gestiona que es lo primero que aparece cuando abrimos el archivo.fxml
     */
    private void initialize() {
        iniciarTabla();
        iniciarListaVoluntarios();
        mostrarInformacionAdicional();
    }

    /**
     * Metodo que extrae todos los datos de la BBDD y organiza la información
     * en las columnas de la tabla
     */
    public void iniciarTabla() {
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

    /**
     * Metodo que rellena la tabla con todos los datos encontrados en la base de datos
     */
    public void iniciarListaVoluntarios() {
        ObservableList<Voluntario> observable = FXCollections.observableList(VoluntarioDAO.findAll());
        listaVoluntarios.setItems(observable);
    }

    //region ------------------- LABEL INFORMACIÓN ADICIONAL-------------------

    /**
     * Método que muestra toda la información del Voluntario cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        ventanaBuscar.setVisible(false);
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

    //endregion


    //region ------------------- INSERTAR VOLUNTARIO -------------------

    /**
     * Método que cuando al pulsar el botón "Nuevo Voluntario" abrirá el formulario correspondiente
     * @param event --> acción que se va a llevar a cabo
     */
    //todo -> podria hacer una opcion que pregunte antes si se ha registrado anteriormente como voluntario o adoptante
    public void botonInsertarVoluntario(ActionEvent event) {
        FormularioPersonaYAdoptarController.persona = "voluntario";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");
    }
    //endregion


    //region ------------------- GESTIÓN DE BÚSQUEDA DE TAREAS -------------------

    /**
     * Metodo que gestiona lo que aparece en pantalla al pulsar el botón Busqueda
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonBusqueda(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    /**
     * Metodo que busca a los tareas que coincidan con los parametros
     * introducidos por teclado.
     * @return --> devuelve una lista con los resultados obtenidos
     */
    public List<Ayuda> buscarTarea() {
        String dniVoluntario = buscarDNI.getText();
        String nombreVoluntario = buscarNombre.getText();
        String apellidoVoluntario = buscarApellidos.getText();
        String ubicacionTexto = buscarUbicacion.getText();
        int idUbicacion = 0;
        if (ubicacionTexto != null) {
            idUbicacion = Utils.conversorInt(ubicacionTexto);
        }
        LocalDate fecha = buscarFecha.getValue();


        List<Ayuda> tareasEncontradas = new ArrayList<>();

        if ((dniVoluntario != null && !dniVoluntario.isEmpty()) && (idUbicacion != 0) && (buscarFecha.getValue() != null)) {
            tareasEncontradas.clear();
            tareasEncontradas.add(AyudaDAO.findSingle(dniVoluntario, idUbicacion, fecha));
            return tareasEncontradas;
        }

        if (dniVoluntario != null && !dniVoluntario.isEmpty()) {
            List<Ayuda> resultados = AyudaDAO.findByDniVoluntario(dniVoluntario);
            return resultados;
        }

        if (nombreVoluntario != null && !nombreVoluntario.isEmpty()) {
            List<Voluntario> voluntariosEncontrados = VoluntarioDAO.findByName(nombreVoluntario);

            for (Voluntario v : voluntariosEncontrados) {
                List<Ayuda> ayudasVoluntario = AyudaDAO.findByDniVoluntario(v.getDni());
                tareasEncontradas.addAll(ayudasVoluntario);
            }
            return tareasEncontradas;
        }
        if (apellidoVoluntario != null && !apellidoVoluntario.isEmpty()) {
            List<Voluntario> voluntariosEncontrados = VoluntarioDAO.findByLastName(apellidoVoluntario);

            for (Voluntario v : voluntariosEncontrados) {
                List<Ayuda> ayudasVoluntario = AyudaDAO.findByDniVoluntario(v.getDni());
                tareasEncontradas.addAll(ayudasVoluntario);
            }
            return tareasEncontradas;
        }

        if (idUbicacion != 0) {
            List<Ayuda> resultados = AyudaDAO.findByIdUbicacion(idUbicacion);
            return resultados;
        }
        if (buscarFecha.getValue() != null) {
            List<Ayuda> resultados = AyudaDAO.findByFecha(fecha);
            return resultados;
        }
        return null;
    }

    //endregion

    //region ------------------- GESTIÓN DE BÚSQUEDA DE VOLUNTARIO -------------------

    /**
     * Metodo que busca a los voluntarios que coincidan con los parametros
     * introducidos por teclado
     * @return --> devuelve una lista con los resultados obtenidos
     */
    public List<Voluntario> buscarVoluntario() {
        //todo-> validaciones y alert
        //todo -> ¿que pasa si no rellenas nada?
        //todo -> si no rellenas nada volver a la informacion inicial

        String dniVoluntario = buscarDNI.getText();
        String nombreVoluntario = buscarNombre.getText();
        String apellidoVoluntario = buscarApellidos.getText();

        List<Voluntario> voluntariosEncontrados = new ArrayList<>();

        if (dniVoluntario != null && !dniVoluntario.isEmpty()) {
            voluntariosEncontrados.clear();
            voluntariosEncontrados.add(VoluntarioDAO.findByDni(dniVoluntario));
            return voluntariosEncontrados;
        }
        if (nombreVoluntario != null && !nombreVoluntario.isEmpty()) {
            List<Voluntario> resultados = VoluntarioDAO.findByName(nombreVoluntario);
            return resultados;
        }
        if (apellidoVoluntario != null && !apellidoVoluntario.isEmpty()) {
            List<Voluntario> resultados = VoluntarioDAO.findByLastName(apellidoVoluntario);
            return resultados;
        }

        return null;
    }

    //endregion


    //region ------------------- BOTÓN CONTINUAR BÚSQUEDA -------------------

    /**
     * Metodo que gestiona lo que ocurre al pulsar el botón Continuar cuando estamos
     * realizando busqueda
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Ayuda> resultados =
                FXCollections.observableArrayList(buscarTarea());


        tablaAyuda.setItems(resultados);

        ObservableList<Voluntario> resultadosVoluntarios =
                FXCollections.observableArrayList(buscarVoluntario());

        listaVoluntarios.setItems(resultadosVoluntarios);

    }
    //endregion
}
