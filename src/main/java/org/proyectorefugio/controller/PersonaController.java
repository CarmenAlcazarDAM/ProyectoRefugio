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
import org.proyectorefugio.model.Persona;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AdoptanteDAO;
import org.proyectorefugio.modelDAO.PersonaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;
import org.proyectorefugio.view.Mensajes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Controlador de la vista de gestión de personas del refugio,
 * los cuales son voluntarios o adoptantes.
 * Permite mostrar, buscar, modificar y eliminar.
 */
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
    public AnchorPane panelModificacion;
    public TextField modificarNombre;
    public TextField modificarTelefono;
    public TextField modificarCorreo;
    public TextField modificarApellidos;
    public TextField modificarDireccion;


    @FXML
    /**
     * Metodo de inicialización
     * Se encarga de llevar a cabo las primeras acciones que
     * aparecerán al cargar el archivo fxml
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
        panelModificacion.setVisible(false);
    }

    @FXML
    /**
     * Selecciona el check de voluntarios y desmarca el de adoptantes.
     * Recarga la tabla.
     *
     * @param event --> evento al pulsar el checkbox
     */
    public void seleccionVoluntario(ActionEvent event) {
        if (voluntario.isSelected()) {
            adoptante.setSelected(false);
            tablaPersonas();
        }
    }

    @FXML
    /**
     * Selecciona el check de adoptantes y desmarca el de voluntarios.
     * Recarga la tabla.
     *
     * @param event --> evento al pulsar el checkbox
     */
    public void seleccionAdoptante(ActionEvent event) {
        if (adoptante.isSelected()) {
            voluntario.setSelected(false);
            tablaPersonas();
        }
    }

    /**
     * Metodo que extrae los datos de los adoptantes de la base de datos y clasifica la
     * información por columnas en una tabla.
     * Carga voluntarios o adoptantes según el checkbox activo.
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
                ventanaBuscar.setVisible(false);
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

    @FXML
    /**
     * Muestra el panel de búsqueda y oculta el resto de paneles.
     * @param event --> acción que se va a llevar a cabo al pulsar el botón
     */
    public void botonBusqueda(ActionEvent event) {
        tablaPersonas();
        panelModificacion.setVisible(false);
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    /**
     * Busca personas en la base de datos según los filtros introducidos.
     * Prioriza la búsqueda por DNI al ser un identificador único.
     * Para el resto de criterios acumula resultados y elimina duplicados con un {@link HashSet}.
     * @return --> lista de personas que coinciden con los criterios de búsqueda
     */
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
    @FXML
    /**
     * Metodo que ejecuta la búsqueda y actualiza la tabla con los resultados obtenidos.
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonContinuarBusqueda(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ObservableList<Persona> resultados =
                FXCollections.observableArrayList(buscarPersona());

        tablaPersonas.setItems(resultados);
    }

    //endregion

    //region---------------MODIFICAR-------------------
    @FXML
    /**
     * Muestra el panel de modificación, oculta los demás paneles.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonModificar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        panelModificacion.setVisible(true);
    }

    /**
     * Recoge los campos del panel de modificación y actualiza en la base de datos
     * únicamente los campos que han sido rellenados.
     * Valida que haya un perro seleccionado y que al menos un campo esté relleno.
     *
     * @return --> true si al menos un campo fue actualizado correctamente, false en caso contrario
     */
    public boolean modificarPersona() {
        Persona p = tablaPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Mensajes.alertaNoSeleccionado("Debe seleccionar al menos un elemento");
            return false;
        }

        String nombre = modificarNombre.getText();
        String apellidos = modificarApellidos.getText();
        String telefono = modificarTelefono.getText();
        String correo = modificarCorreo.getText();
        String direccion = modificarDireccion.getText();

        if (nombre.trim().isEmpty() && apellidos.trim().isEmpty() && telefono.trim().isEmpty() && correo.trim().isEmpty() && direccion.trim().isEmpty()) {
            Mensajes.aletaObligatoriosCamposVacios("Ningún campo está relleno");
        }
        boolean actualizado = false;
        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = p.getNombre();
        }
        if (apellidos == null || apellidos.trim().isEmpty()) {
            apellidos = p.getNombre();
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            telefono = p.getNombre();
        }
        if (correo == null || correo.trim().isEmpty()) {
            correo = p.getNombre();
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            direccion = p.getNombre();
        }

        Persona datosCompletos = new Persona(p.getDni(), nombre, apellidos, telefono, correo, direccion);
        if (datosCompletos != null) {
            PersonaDAO.updatePerson(datosCompletos);
            actualizado = true;
        } else {
            Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la tarea");
        }
        return actualizado;

    }

    /**
     * Metodo que limpia todos los campos del formulario de modificación dejándolos en su estado inicial.
     */
    public void limpiarCampos() {
        modificarNombre.clear();
        modificarApellidos.clear();
        modificarTelefono.clear();
        modificarCorreo.clear();
        modificarDireccion.clear();
    }
    @FXML
    /**
     * Guarda los cambios del formulario de modificación, limpia los campos y recarga la tabla.
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonGuardarModificacion(ActionEvent event) {

        if (modificarPersona()) {
            Mensajes.operacionCompletada("Modificación realizada con éxito");
            limpiarCampos();
        } else {
            Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la tarea");
        }
        tablaPersonas();

    }
    //endregion


    //region ------------------- GESTIÓN DELETE PERSONA -------------------

    @FXML
    /**
     * Elimina de la base de datos la persona seleccionada en la tabla,
     * previa confirmación del usuario.
     * Impide el borrado si la persona tiene un animal adoptado o tareas de voluntariado asociadas.
     *
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonEliminar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        Persona persona = tablaPersonas.getSelectionModel().getSelectedItem();

        if (persona == null) {
            Mensajes.alertaNoSeleccionado("No hay ningún elemento seleccionado");
            return;
        }
        Voluntario v = VoluntarioDAO.findByDni(persona.getDni());
        Adoptante a = AdoptanteDAO.findByDni(persona.getDni());

        if (v != null || a != null) {

            if (a != null) {
                Mensajes.noPuedeEliminar("No puedes eliminar a esta persona porque tiene un animal adoptado");
            } else {
                Mensajes.noPuedeEliminar("No puedes eliminar a esta persona porque tiene tareas asociadas");
            }
        }

        if (Mensajes.confirmarEliminar("¿Desea eliminar el elemento de forma permanente?")) {
            PersonaDAO.deletePersona(persona.getDni());
        }

        initialize();
    }
    //endregion
}
