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

import java.time.LocalDate;
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

    //region---------------FMXL BUSCAR-------------------


    @FXML
    public TextField buscarId;
    public TextField buscarNombre;
    public TextField buscarChip;
    public TextField buscarRaza;
    public TextField buscarColor;
    //endregion

    //region---------------FMXL MODIFICAR-------------------

    @FXML
    public AnchorPane panelModificacion;
    public TextField modificarChip;
    public DatePicker modificarFecha;
    public TextArea modificarObservaciones;
    public CheckBox modificarEsterilizado;
    public TextField modificarDniAdoptante;
    public Spinner modificarUbicacion;
    public TextField modificarPeso;
    public CheckBox modificarAgresivo;

    //endregion

    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        noAdoptado.setSelected(true);
        tablaPerros();
        ocultarTodosPaneles();
        mostrarInformacionAdicional();
    }

    /**
     * Se encarga de ocultar todos los AnchorPane que puedan estar visibles
     */
    private void ocultarTodosPaneles() {
        ventanaBuscar.setVisible(false);
        panelModificacion.setVisible(false);

    }


    @FXML
    /**
     * Selecciona el check de No adoptados y desmarca el de adoptados.
     * Recarga la tabla.
     */
    public void seleccionNoAdoptado() {
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
    public void seleccionAdoptado() {
        if (adoptado.isSelected()) {
            noAdoptado.setSelected(false);
        } else {
            noAdoptado.setSelected(true);
        }
        tablaPerros();
    }

    /**
     * Metodo que extrae los datos de los perros de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaPerros() {
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        razaCol.setCellValueFactory(new PropertyValueFactory<>("raza"));

        boolean buscarAdoptados = adoptado.isSelected();

        ObservableList<Perro> listaPerros =
                FXCollections.observableArrayList(PerroDAO.findAll(buscarAdoptados));

        tablaPerros.setItems(listaPerros);
    }


    /**
     * Metodo que muestra toda la información del Perro cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        ventanaBuscar.setVisible(false);
        tablaPerros.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {
                        Perro p = PerroDAO.findByID(seleccionado.getId());
                        if (p == null) return;

                        if (panelModificacion.isVisible()) return;

                        informacionAdicional.setVisible(true);
                        ventanaBuscar.setVisible(false);

                        String datosMostrar = "Id: " + p.getId() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Edad: " + p.getEdad() + "\n" +
                                "Sexo: " + p.getSexo() + "\n" +
                                "Raza: " + p.getRaza() + "\n" +
                                "Color: " + p.getColor() + "\n" +
                                "Peso: " + p.getPeso() + " Kg \n";

                        if (p.getNumeroChip() != null) {
                            datosMostrar += "Número Chip:  " + p.getNumeroChip() + "\n";
                        }
                        datosMostrar += "Esterilizado: " + p.isEsterilizadoTexto() + "\n" +
                                "Fecha Ingreso: " + p.getFechaIngreso() + "\n";
                        if (p.getObservaciones() != null) {
                            datosMostrar += "Observaciones: " + p.getObservaciones() + "\n";
                        }
                        if (p.getHistoria() != null) {
                            datosMostrar += "Historia: " + p.getHistoria() + "\n";
                        }
                        datosMostrar += "Agresivo: " + p.isAgresivoTexto() + "\n";

                        if (p.getFechaAlta() != null) {
                            datosMostrar += "Fecha Alta: " + p.getFechaAlta();
                        }
                        informacionAdicional.setText(datosMostrar);
                    }
                });
    }


    //region---------------INSERTAR-------------------

    /**
     * Metodo que cuando al pulsar el botón Añadir abrirá el formulario correspondiente
     *
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonInsertarPerro(ActionEvent event) {
        RegistroAnimalController.tipo = "perro";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/registroAnimal-view.fxml", "Formulario de Registro");
    }

    //endregion

    //region---------------ADOPTAR-------------------

    /**
     * Método que cuando al pulsar el botón Adoptar abrirá el formulario correspondiente
     *
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonAdoptar(ActionEvent event) {
        FormularioPersonaYAdoptarController.persona = "adoptante";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");
        tablaPerros();
    }

    //endregion

    //region---------------BUSCAR ANIMAL-------------------

    @FXML
    /**
     * Oculta el panel de información adicional y muestra el panel de búsqueda.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonBusqueda(ActionEvent event) {
        tablaPerros();
        panelModificacion.setVisible(false);
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    /**
     * Busca perros en la base de datos según los filtros introducidos (id, chip, nombre, raza, color).
     * Prioriza la búsqueda por id y por chip al ser identificadores únicos.
     * Elimina duplicados usando un HashSet antes de devolver los resultados.
     *
     * @return lista de perros que coinciden con los criterios de búsqueda
     */
    public List<Perro> buscarAnimal() {
        String idAnimalTexto = buscarId.getText();
        int idAnimal = 0;
        if (idAnimalTexto != null) {
            idAnimal = Utils.conversorInt(idAnimalTexto);
        }
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

    @FXML
    /**
     * Metodo que ejecuta la búsqueda y actualiza la tabla con los resultados obtenidos.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Perro> resultados =
                FXCollections.observableArrayList(buscarAnimal());

        tablaPerros.setItems(resultados);
    }

    //endregion

    //region---------------MODIFICAR-------------------

    @FXML
/**
 * Muestra el panel de modificación, oculta los demás paneles
 * e inicializa el Spinner de ubicación.
 * @param event --> acción que se va a llevar a cabo
 */
    public void botonModificar(ActionEvent event) {
        panelModificacion.setVisible(true);
        ventanaBuscar.setVisible(false);
        informacionAdicional.setVisible(false);
        modificarUbicacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        //todo --> cargar datos
    }

    /**
     * Recoge los campos del panel de modificación y actualiza en la base de datos
     * únicamente los campos que han sido rellenados.
     * Valida que haya un perro seleccionado y que al menos un campo esté relleno.
     *
     * @return true si al menos un campo fue actualizado correctamente, false en caso contrario
     */
    public boolean modificarAnimal() {
        informacionAdicional.setVisible(false);
        Perro pSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        Animal aSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();

        if (pSeleccionado == null || aSeleccionado == null) {
            // todo -> alerta: selecciona un elemento primero
            return false;
        }

        String chip = modificarChip.getText();
        LocalDate fecha = modificarFecha.getValue();
        String observaciones = modificarObservaciones.getText();
        String dniAdoptante = modificarDniAdoptante.getText();
        int ubicacion = (int) modificarUbicacion.getValue();
        Double peso = Utils.conversorDouble(modificarPeso.getText());

        if ((chip == null || chip.isEmpty()) && fecha == null && (observaciones == null || observaciones.isEmpty())
                && (dniAdoptante == null || dniAdoptante.isEmpty()) && ubicacion == 0 && (peso == null || peso == 0.0) &&
                !modificarEsterilizado.isSelected() && !modificarAgresivo.isSelected()) {
            //todo --> alerta de campos vacíos
            return false;
        }

        boolean actualizado = false;

        if (chip != null && !chip.trim().isEmpty()) {
            if (AnimalDAO.findByChip(chip) == null) {
                if (AnimalDAO.updateNumeroChip(aSeleccionado, chip)) {
                    // todo -> alerta éxito — chip actualizado correctamente
                    actualizado = true;
                } else {
                    // todo -> alerta error — no se pudo actualizar el chip
                }
            } else {
                // todo -> alerta error — ya existe un animal con ese chip
            }
        }

        if (fecha != null) {
            if (fecha.isAfter(LocalDate.now())) {
                // todo -> alerta error — la fecha no puede ser futura
                return false;
            } else if (AnimalDAO.updateFechaAlta(aSeleccionado, fecha)) {
                // todo -> alerta éxito — fecha actualizada correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar la fecha
            }
        }

        if (observaciones != null && !observaciones.trim().isEmpty()) {
            if (AnimalDAO.updateObservaciones(aSeleccionado, observaciones)) {
                // todo -> alerta éxito — observaciones actualizadas correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar las observaciones
            }
        }

        if (!aSeleccionado.isEsterilizado() && modificarEsterilizado.isSelected()) {
            if (AnimalDAO.updateEsterilizado(aSeleccionado, true)) {
                // todo -> alerta éxito — esterilizado actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar esterilizado
            }
        } // todo -> esto debería cambiar si cargo los datos

        if (!pSeleccionado.isAgresivo() && modificarAgresivo.isSelected()) {
            if (PerroDAO.updateAgresivo(pSeleccionado, true)) {
                // todo -> alerta éxito — agresivo actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar agresivo
            }
        } // todo -> esto debería cambiar si cargo los datos

        if (dniAdoptante != null && !dniAdoptante.trim().isEmpty()) {
            if (AnimalDAO.updateAdoptante(aSeleccionado, dniAdoptante)) {
                // todo -> alerta éxito — adoptante actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar el adoptante
            }
        }

        if (ubicacion > 0) {
            if (AnimalDAO.updateUbicacion(aSeleccionado, ubicacion)) {
                // todo -> alerta éxito — ubicación actualizada correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar la ubicación
            }
        }

        if (peso != null && peso > 0) {
            if (PerroDAO.updatePeso(pSeleccionado, peso)) {
                // todo -> alerta éxito — peso actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar el peso
            }
        }

        return actualizado;

    }

    /**
     * Limpia todos los campos del formulario de modificación dejándolos en su estado inicial.
     */
    public void limpiarCampos() {
        modificarChip.clear();
        modificarFecha.setValue(null);
        modificarObservaciones.clear();
        modificarDniAdoptante.clear();
        modificarUbicacion.getValueFactory().setValue(0);
        modificarPeso.clear();
        modificarEsterilizado.setSelected(false);
        modificarAgresivo.setSelected(false);
    }

    @FXML
    /**
     * Guarda los cambios del formulario de modificación, limpia los campos y recarga la tabla.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonGuardarModificacion(ActionEvent event) {
        modificarAnimal();
        limpiarCampos();
        panelModificacion.setVisible(false);
        tablaPerros();
        informacionAdicional.setVisible(false);
    }
    //endregion


    //region---------------ELIMINAR PERRO -------------------

    @FXML
    /**
     * Elimina de la base de datos el perro seleccionado en la tabla,
     * verificando previamente que existe en PerroDAO.
     * Si no hay ningún perro seleccionado, cancela la operación.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonEliminar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        Animal animalSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        // todo -> confirmacion de alerta de si quiere borrar o no

        if (animalSeleccionado == null) {
            // todo -> alerta: selecciona un elemento primero
            return;
        }

        if (PerroDAO.findByID(animalSeleccionado.getId()) != null) {
            //todo-> confirmacion
            AnimalDAO.deleteAnimalById(animalSeleccionado.getId());
        } else {
            //todo -> alerta: ese animal no es un gato
        }
        initialize();
    }
    //endregion
}


