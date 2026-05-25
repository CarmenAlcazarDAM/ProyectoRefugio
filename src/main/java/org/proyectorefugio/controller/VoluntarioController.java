package org.proyectorefugio.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.proyectorefugio.model.*;
import org.proyectorefugio.modelDAO.*;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.Mensajes;
import org.proyectorefugio.view.SceneManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Controlador de la vista de gestión de voluntarios y sus tareas de ayuda.
 * Permite mostrar, buscar, añadir, modificar y eliminar voluntarios y tareas.
 */
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
    public ListView<Persona> listaVoluntarios;

    @FXML
    public Label informacionAdicional;
    public AnchorPane ventanaBuscar;

    @FXML
    public TextField buscarDNI;
    public TextField buscarNombre;
    public TextField buscarApellidos;
    public TextField buscarUbicacion;
    public DatePicker buscarFecha;

    @FXML
    public AnchorPane ventanaAñadirTarea;
    public TextArea insertarTareaTexto;
    public TextField insertarTareaDni;
    public Spinner<Integer> insertarTareaUbicacion;
    public DatePicker insertarTareaFecha;
    public Button botonGuardarTarea;

    public Button botonGuardarModificacion;
    public Text textoDni;


    //endregion


    @FXML
    /**
     * Metodo de inicialización
     * Se encarga de llevar a cabo las primeras acciones que
     * aparecerán al cargar el archivo fxml
     */
    private void initialize() {
        iniciarTabla();
        iniciarListaVoluntarios();
        mostrarInformacionAdicional();
        botonGuardarTarea.setVisible(false);
        botonGuardarModificacion.setVisible(false);

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
     * Metodo que rellena la lista de Voluntarios con todos los datos encontrados en la base de datos
     */
    public void iniciarListaVoluntarios() {
        ObservableList<Persona> observable = FXCollections.observableList(VoluntarioDAO.findAll());
        listaVoluntarios.setItems(observable);
    }

    //region ------------------- LABEL INFORMACIÓN ADICIONAL-------------------

    /**
     * Metodo que muestra toda la información del Voluntario cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     * Oculta los paneles secundarios al mostrar la información.
     */
    public void mostrarInformacionAdicional() {
        ventanaBuscar.setVisible(false);
        ventanaAñadirTarea.setVisible(false);
        botonGuardarModificacion.setVisible(false);


        listaVoluntarios.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionado) -> {
            if (seleccionado != null) {
                Voluntario v = VoluntarioDAO.findByDni(seleccionado.getDni());
                if (v == null) {
                    return;
                }

                informacionAdicional.setVisible(true);
                ventanaBuscar.setVisible(false);
                ventanaAñadirTarea.setVisible(false);
                botonGuardarModificacion.setVisible(false);
                botonGuardarTarea.setVisible(false);


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
    @FXML
    /**
     * Metodo que cuando al pulsar el botón "Nuevo Voluntario" abrirá el formulario correspondiente,
     *recarga la tabla y la lista al cerrar.
     *
     * @param event --> acción que se va a llevar a cabo al pulsar el botón
     */
    public void botonInsertarVoluntario(ActionEvent event) {

        FormularioPersonaYAdoptarController.persona = "voluntario";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");
        iniciarTabla();
        iniciarListaVoluntarios();
    }
    //endregion


    //region ------------------- GESTIÓN DE BÚSQUEDA DE TAREAS -------------------

    /**
     * Metodo que gestiona lo que aparece en pantalla al pulsar el botón Busqueda
     *
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonBusqueda(ActionEvent event) {
        iniciarTabla();
        iniciarListaVoluntarios();
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
        ventanaAñadirTarea.setVisible(false);
        botonGuardarTarea.setVisible(false);
        botonGuardarModificacion.setVisible(false);
    }

    /**
     * Metodo que busca las tareas que coincidan con los parámetros
     * introducidos por teclado.
     * Prioriza la búsqueda exacta por DNI + ubicación + fecha cuando los tres están presentes.
     *
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
     *
     * @return --> devuelve una lista con los resultados obtenidos,  null si no se introdujo ningún criterio
     */
    public List<Voluntario> buscarVoluntario() {
        String dniVoluntario = buscarDNI.getText();
        String nombreVoluntario = buscarNombre.getText();
        String apellidoVoluntario = buscarApellidos.getText();

        if (dniVoluntario.trim().isEmpty() && nombreVoluntario.trim().isEmpty() && apellidoVoluntario.trim().isEmpty()) {
            Mensajes.aletaObligatoriosCamposVacios("Introduce al menos un criterio de búsqueda");
            iniciarListaVoluntarios();
            return null;
        }


        List<Voluntario> voluntariosEncontrados = new ArrayList<>();

        if (dniVoluntario != null && !dniVoluntario.trim().isEmpty()) {
            Voluntario v = VoluntarioDAO.findByDni(dniVoluntario);
            List<Voluntario> resultados = new ArrayList<>();
            if (v != null) {
                resultados.add(v);
                return resultados;
            } else {
                Mensajes.alertaNoExiste("No existe ningún voluntario con el DNI / NIF: " + dniVoluntario);
            }
        }

        if (nombreVoluntario != null && !nombreVoluntario.trim().isEmpty()) {
            voluntariosEncontrados.addAll(VoluntarioDAO.findByName(nombreVoluntario));
        }

        if (apellidoVoluntario != null && !apellidoVoluntario.isEmpty()) {
            voluntariosEncontrados.addAll(VoluntarioDAO.findByLastName(apellidoVoluntario));
        }

        HashSet<Voluntario> busquedaFinal = new HashSet<>(voluntariosEncontrados);
        busquedaFinal.remove(null);

        if (busquedaFinal.isEmpty()) {
            Mensajes.alertaErrorDeRegistro("No se encontraron resultados en la base de datos");
            return new ArrayList<>();
        }

        return new ArrayList<>(busquedaFinal);

    }

    //endregion


    //region ------------------- BOTÓN CONTINUAR BÚSQUEDA -------------------

    @FXML
    /**
     * Metodo que gestiona lo que ocurre al pulsar el botón Continuar cuando estamos
     * realizando busqueda
     *
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Ayuda> resultados =
                FXCollections.observableArrayList(buscarTarea());

        tablaAyuda.setItems(resultados);

        ObservableList<Persona> resultadosVoluntarios =
                FXCollections.observableArrayList(buscarVoluntario());

        listaVoluntarios.setItems(resultadosVoluntarios);

    }
    //endregion

    //region ------------------- GESTIÓN AÑADIR TAREA -------------------

    @FXML
    /**
     * Muestra el panel de inserción de tarea y oculta el resto de paneles.
     * Inicializa el spinner de ubicación.
     *
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonAñadirTarea(ActionEvent event) {
        ventanaBuscar.setVisible(false);
        informacionAdicional.setVisible(false);
        ventanaAñadirTarea.setVisible(true);
        textoDni.setVisible(true);
        insertarTareaDni.setVisible(true);
        botonGuardarTarea.setVisible(true);
        insertarTareaUbicacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

    }

    /**
     * Recoge los datos del formulario de inserción, valida que todos los campos
     * obligatorios estén rellenos y registra la tarea en la base de datos.
     */
    public void insertarTarea() {
        try {
            String tarea = insertarTareaTexto.getText();
            String dniVoluntario = insertarTareaDni.getText();
            int idUbicacion = (int) insertarTareaUbicacion.getValue();
            LocalDate fecha = insertarTareaFecha.getValue();

            if (tarea == null || tarea.trim().isEmpty() || dniVoluntario == null ||
                    dniVoluntario.trim().isEmpty() || fecha == null || idUbicacion <= 0) {
                Mensajes.aletaObligatoriosCamposVacios("Los campos obligatorios no pueden estar vacíos");
                return;
            }

            Ayuda a = new Ayuda(dniVoluntario, idUbicacion, fecha, tarea);
            AyudaDAO.addAyuda(a);
            Ayuda encontrada = AyudaDAO.findSingle(dniVoluntario, idUbicacion, fecha);

            if (encontrada != null) {
                Mensajes.operacionCompletada("Tarea registrada con éxito");
            }

        } catch (Exception e) {
            Mensajes.alertaErrorDeRegistro("Lo sentimos, no se ha completar el registro");
            throw new RuntimeException(e);
        }
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarCampos() {
        insertarTareaTexto.clear();
        insertarTareaDni.clear();
        insertarTareaUbicacion.getValueFactory().setValue(0);
        insertarTareaFecha.setValue(null);
    }

    @FXML
    /**
     * Inserta la tarea en la base de datos, limpia el formulario y recarga
     * la tabla y lista de voluntarios.
     */
    public void botonGuardarTarea(ActionEvent event) {
        insertarTarea();
        limpiarCampos();
        iniciarTabla();
        iniciarListaVoluntarios();
    }

    //endregion


    //region ------------------- GESTIÓN MODIFICAR AYUDA -------------------
    @FXML
    /**
     * Muestra el panel de modificación de tarea ocultando el campo DNI
     * (no editable en una modificación) y el botón de guardar nueva tarea.
     *
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonModificar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        ventanaAñadirTarea.setVisible(true);
        botonGuardarTarea.setVisible(false);
        botonGuardarModificacion.setVisible(true);
        textoDni.setVisible(false);
        insertarTareaDni.setVisible(false);
        insertarTareaUbicacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

    }

    /**
     * Recoge los campos rellenos del formulario y actualiza los datos
     * de la tarea seleccionada en la base de datos.
     * Valida que al menos un campo esté informado y que la fecha no sea futura.
     *
     * @return --> true si al menos un campo fue actualizado correctamente,
     * false si no hay tarea seleccionada, todos los campos están vacíos
     * o la fecha es inválida
     */
    public boolean modificarAyuda() {
        Ayuda a = tablaAyuda.getSelectionModel().getSelectedItem();
        if (a == null) {
            Mensajes.alertaNoSeleccionado("Debe seleccionar al menos un elemento");
            return false;
        }
        String tarea = insertarTareaTexto.getText();
        int idUbicacion = (int) insertarTareaUbicacion.getValue();
        LocalDate fecha = insertarTareaFecha.getValue();

        if ((tarea == null || tarea.trim().isEmpty()) && idUbicacion <= 0 && fecha == null) {
            Mensajes.aletaObligatoriosCamposVacios("Ningún campo está relleno");
            return false;
        }
        boolean actualizado = false;
        if (tarea != null && !tarea.trim().isEmpty()) {
            if (AyudaDAO.updateTarea(a, tarea)) {
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la tarea");
            }
        }

        if (idUbicacion >= 1) {
            if (AyudaDAO.updateUbicacion(a, idUbicacion)) {
                actualizado = true;

            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la ubicación");
            }
        }

        if (fecha != null) {
            if (!Utils.validarFecha(fecha)) {
                Mensajes.alertaErrorDeRegistro("La fecha de registro no puede ser posterior a la fecha actual");
                return false;
            } else if (AyudaDAO.updateFecha(a, fecha)) {
                actualizado = true;

            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la fecha");
            }
        }
        return actualizado;
    }

    /**
     * Guarda la modificación la base de datos, limpia el formulario y recarga
     * la tabla y  lista de voluntarios.
     *
     * @param event --> acción que tiene lugar cuando pulsas el botón
     */
    public void botonGuardarModificacion(ActionEvent event) {
        modificarAyuda();
        limpiarCampos();
        iniciarTabla();
        iniciarListaVoluntarios();
    }


    //endregion


    //region ------------------- GESTIÓN DELETE VOLUNTARIO/AYUDA -------------------

    /**
     * Metodo que elimina una AYUDA o un VOLUNTARIO seleccionado de la BBDD
     * Comprueba también si el voluntario es, además, adoptante. En caso de no ser
     * adoptante borra al Voluntario también de Persona, eliminando cualquier registro de
     * la BBDD.
     *
     * @param event --> acción que ocurre cuando se pulsa el boton
     */
    public void botonEliminar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        Ayuda ayudaSeleccionada = tablaAyuda.getSelectionModel().getSelectedItem();
        Persona voluntarioSeleccionado = listaVoluntarios.getSelectionModel().getSelectedItem();

        if (ayudaSeleccionada == null && voluntarioSeleccionado == null) {
            Mensajes.alertaNoSeleccionado("No hay ningún elemento seleccionado");
            return;
        }
        if (Mensajes.confirmarEliminar("¿Desea eliminar el elemento de forma permanente?")) {
            AyudaDAO.deleteAyuda(ayudaSeleccionada.getDniVoluntario(), ayudaSeleccionada.getIdUbicacion(), ayudaSeleccionada.getFecha());
        } else {
            Mensajes.alertaNoSeleccionado("Debe seleccionar al menos un elemento");
        }
        initialize();
    }
    //endregion
}
