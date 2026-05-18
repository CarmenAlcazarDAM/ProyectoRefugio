package org.proyectorefugio.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.GatoDAO;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.SceneManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    public CheckBox noAdoptado;
    public CheckBox adoptado;
    public Button añadirGatoBD;
    public AnchorPane ventanaBuscar;

    //region---------------FMXL BUSCAR-------------------

    @FXML
    public TextField buscarId;
    public TextField buscarNombre;
    public TextField buscarChip;
    public TextField buscarRaza;
    public TextField buscarColor;

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
    public CheckBox modificarLeucemia;
    public Text textoPeso;

    //endregion

    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        noAdoptado.setSelected(true);
        tablaGatos();
        ocultarTodosPaneles();
        mostrarInformacionAdicional();
    }

    /**
     * Se encarga de ocultar todos los AnchorPane que puedan estar visibles
     */
    private void ocultarTodosPaneles(){
        ventanaBuscar.setVisible(false);
        panelModificacion.setVisible(false);

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
        tablaGatos();
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
        tablaGatos();
    }

    /**
     * Metodo que extrae los datos de los gatos de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaGatos() {
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        razaCol.setCellValueFactory(new PropertyValueFactory<>("raza"));

        boolean buscarAdoptados = adoptado.isSelected(); //
        ObservableList<Gato> listaGatos =
                FXCollections.observableArrayList(GatoDAO.findAll(buscarAdoptados));

        tablaGatos.setItems(listaGatos);
    }

    /**
     * Metodo que muestra toda la información del Gato cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        ventanaBuscar.setVisible(false);
        tablaGatos.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {
                        Gato g = GatoDAO.findByID(seleccionado.getId());
                        if (g == null) return;

                        if (panelModificacion.isVisible()) return;

                        informacionAdicional.setVisible(true);
                        ventanaBuscar.setVisible(false);

                        String datosMostrar = "Id: " + g.getId() + "\n" +
                                "Nombre: " + g.getNombre() + "\n" +
                                "Edad: " + g.getEdad() + "\n" +
                                "Sexo: " + g.getSexo() + "\n" +
                                "Raza: " + g.getRaza() + "\n" +
                                "Color: " + g.getColor() + "\n";

                        if (g.getNumeroChip() != null) {
                            datosMostrar += "Número Chip:  " + g.getNumeroChip() + "\n";
                        }
                        datosMostrar += "Esterilizado: " + g.isEsterilizadoTexto() + "\n" +
                                "Fecha Ingreso: " + g.getFechaIngreso() + "\n";
                        if (g.getObservaciones() != null) {
                            datosMostrar += "Observaciones: " + g.getObservaciones() + "\n";
                        }
                        if (g.getHistoria() != null) {
                            datosMostrar += "Historia: " + g.getHistoria() + "\n";
                        }
                        datosMostrar += "Leucemia: " + g.isLeucemiaFelinaTexto() + "\n";

                        if (g.getFechaAlta() != null) {
                            datosMostrar += "Fecha Alta: " + g.getFechaAlta();
                        }
                        informacionAdicional.setText(datosMostrar);
                    }
                });
    }

    @FXML
    /**
     * Metodo que cuando al pulsar el botón Añadir abrirá el formulario correspondiente
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonInsertarGato(ActionEvent event) {
        RegistroAnimalController.tipo = "gato";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/registroAnimal-view.fxml", "Formulario de Registro");
    }

    @FXML
    /**
     * Metodo que busca gatos en la base de datos según los filtros introducidos (id, chip, nombre, raza, color).
     * Prioriza la búsqueda por id y por chip al ser identificadores únicos.
     * Elimina duplicados usando un HashSet antes de devolver los resultados.
     * @return --> lista de gatos que coinciden con los criterios de búsqueda
     */
    public void botonAdoptar(ActionEvent event) {
        FormularioPersonaYAdoptarController.persona = "adoptante";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");
        tablaGatos();

        //region---------------BUSCAR GATO-------------------

    }

    @FXML
    public void botonBusqueda(ActionEvent event) {
        tablaGatos();
        panelModificacion.setVisible(false);
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    public List<Gato> buscarAnimal() {
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

        List<Gato> resultadosEncontrados = new ArrayList<>();

        if (idAnimalTexto != null && !idAnimalTexto.isEmpty() && idAnimal != 0) {
            Gato p = GatoDAO.findByID(idAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }
        }
        if (chipAnimal != null && !chipAnimal.isEmpty()) {
            Gato g = GatoDAO.findByChip(chipAnimal);
            if (g != null) {
                resultadosEncontrados.add(g);
                return resultadosEncontrados;
            }
        }
        if (nombreAnimal != null && !nombreAnimal.isEmpty()) {
            resultadosEncontrados.addAll(GatoDAO.findByName(nombreAnimal, buscarNoAdoptados));
        }

        if (razaAnimal != null && !razaAnimal.isEmpty()) {
            resultadosEncontrados.addAll(GatoDAO.findByBreed(razaAnimal, buscarNoAdoptados));

        }
        if (colorAnimal != null && !colorAnimal.isEmpty()) {
            resultadosEncontrados.addAll(GatoDAO.findByColour(colorAnimal, buscarNoAdoptados));

        }

        HashSet<Gato> busquedaFinal = new HashSet<>(resultadosEncontrados);
        busquedaFinal.remove(null);
        return new ArrayList<>(busquedaFinal);
        //todo --> alertas
    }

    @FXML
    /**
     * Ejecuta la búsqueda y actualiza la tabla con los resultados obtenidos.
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Gato> resultados =
                FXCollections.observableArrayList(buscarAnimal());

        tablaGatos.setItems(resultados);
    }
    //endregion

    //region---------------MODIFICAR GATO-------------------
    @FXML

    /**
     * Muestra el panel de modificación y oculta los demás paneles.
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonModificar(ActionEvent event) {
        panelModificacion.setVisible(true);
        ventanaBuscar.setVisible(false);
        informacionAdicional.setVisible(false);
        modificarUbicacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        modificarPeso.setVisible(false);
        textoPeso.setVisible(false);
        //todo --> cargar datos
    }

    /**
     * Metodo que recoge los campos rellenos y actualiza la base de datos
     *
     * @return --> true si al menos un campo fue actualizado correctamente, false en caso contrario
     */
    public boolean modificarGato() {
        informacionAdicional.setVisible(false);
        Gato gSeleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        Animal aSeleccionado = tablaGatos.getSelectionModel().getSelectedItem();

        if (gSeleccionado == null || aSeleccionado == null) {
            // todo -> alerta: selecciona un elemento primero
            return false;
        }

        String chip = modificarChip.getText();
        LocalDate fecha = modificarFecha.getValue();
        String observaciones = modificarObservaciones.getText();
        String dniAdoptante = modificarDniAdoptante.getText();
        int ubicacion = (int) modificarUbicacion.getValue();

        if ((chip == null || chip.trim().isEmpty()) &&
                fecha == null &&
                (observaciones == null || observaciones.trim().isEmpty()) &&
                (dniAdoptante == null || dniAdoptante.trim().isEmpty()) &&
                ubicacion == 0 &&
                !modificarEsterilizado.isSelected() &&
                !modificarLeucemia.isSelected()) {
            // todo -> alerta: no ha rellenado ningún campo
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

        if (!gSeleccionado.isLeucemiaFelina() && modificarLeucemia.isSelected()) {
            if (GatoDAO.updateLeucemia(gSeleccionado, true)) {
                // todo -> alerta éxito — leucemia actualizada correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar leucemia
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

        return actualizado;
    }

    /**
     * Metodo que limpia todos los campos del formulario de modificación dejándolos en su estado inicial.
     */
    public void limpiarCamposGato() {
        modificarChip.clear();
        modificarFecha.setValue(null);
        modificarObservaciones.clear();
        modificarDniAdoptante.clear();
        modificarUbicacion.getValueFactory().setValue(0);
        modificarEsterilizado.setSelected(false);
        modificarLeucemia.setSelected(false);
    }

    @FXML
    /**
     * Guarda los cambios del formulario de modificación, limpia los campos y recarga la tabla.
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonGuardarModificacion(ActionEvent event) {
        modificarGato();
        limpiarCamposGato();
        panelModificacion.setVisible(false);
        tablaGatos();
        informacionAdicional.setVisible(false);
    }

    //endregion


    //region---------------ELIMINAR GATO-------------------

    @FXML
    /**
     * Metodo que elimina de la base de datos el gato seleccionado en la tabla,
     * verificando que existe en GatoDAO.
     * @param event -> acción que se realiza cuando se pulsa el botón
     */
    public void botonEliminar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        Animal animalSeleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        // todo -> confirmacion de alerta de si quiere borrar o no

        if (animalSeleccionado == null) {
            // todo -> alerta: selecciona un elemento primero
            return;
        }

        if (GatoDAO.findByID(animalSeleccionado.getId()) != null) {
            //todo-> confirmacion
            AnimalDAO.deleteAnimalById(animalSeleccionado.getId());
        } else {
            //todo -> alerta: ese animal no es un gato
        }
        initialize();
    }
    //endregion
}



