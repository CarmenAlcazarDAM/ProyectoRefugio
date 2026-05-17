package org.proyectorefugio.controller;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.proyectorefugio.enums.Ubicaciones;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Ubicacion;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.UbicacionDAO;
import org.proyectorefugio.utils.Utils;


import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UbicacionController {
    @FXML
    public TableView<Ubicacion> tablaUbicaciones;

    @FXML
    public TableColumn<Ubicacion, String> tipoCol;

    @FXML
    public TableColumn<Ubicacion, Integer> idCol;
    @FXML
    public TableColumn<Ubicacion, String> salidaCol;
    @FXML
    public TableColumn<Ubicacion, Integer> tiempoCol;
    @FXML
    public TableColumn<Ubicacion, Integer> disponibilidadCol;

    @FXML
    public TableColumn<Ubicacion, Integer> capacidadCol;

    @FXML
    public AnchorPane panelInsertar;
    public ComboBox insertarTipo;
    public Spinner insertarTiempo;
    public Spinner insertarCapacidad;
    public TextField insertarHoraSalida;
    public Button botonInsertado;
    public Button botonContinuar;
    public Text cabeceraTiempo;
    public Text cabeceraCapacidad;


    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        iniciarTabla();
        panelInsertar.setVisible(false);
        botonInsertado.setVisible(false);


    }


    /**
     * Metodo que extrae los datos de las ubicaciones de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void iniciarTabla() {
        tipoCol.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        salidaCol.setCellValueFactory(cellData -> {
            String hora = cellData.getValue().getHoraEnTexto();
            return new SimpleStringProperty(hora);
        });
        tiempoCol.setCellValueFactory(
                new PropertyValueFactory<>("minutosRecreo")
        );

        disponibilidadCol.setCellValueFactory(cellData -> {

            int id = cellData.getValue().getId(); //id de la ubicación actual
            int capacidadUbicacion = cellData.getValue().getCapacidad(); //capacidad de la ubicación actual
            int disponibilidad = capacidadUbicacion - ocupacionDeUbicacion(id); //obtengo las plazas disponibles
            return new SimpleObjectProperty<>(disponibilidad);
        });


        capacidadCol.setCellValueFactory(
                new PropertyValueFactory<>("capacidad")
        );

        ObservableList<Ubicacion> listaUbicaciones =
                FXCollections.observableArrayList(UbicacionDAO.findAll());

        tablaUbicaciones.setItems(listaUbicaciones);
    }

    /**
     * Metodo que obtiene la cantidad de animales que hay en la actualidad en una determinada
     * ubicacion
     *
     * @param idUbicacion --> id de la ubicacion de la que vamos a obtener la cantidad
     *                    de animales
     * @return --> devuelve cuantos animales están ocupando la ubicación
     */
    public int ocupacionDeUbicacion(int idUbicacion) {
        List<Animal> animalesEnUbicacion = AnimalDAO.findByUbicacion(idUbicacion);
        return animalesEnUbicacion.size();
    }

    /*-------------------------------GESTIÓN INSERTAR UBICACIÓN------------------------------------*/

    /**
     * Metodo que extrae los datos obtenidos por teclado y llama a UbicacionDAO para insertar
     * una nueva Ubicacion
     */
    public void insertarUbicacion() {
        try {
            if (insertarTipo.getValue() == null || (int) insertarCapacidad.getValue() < 1) {
                // todo -> alerta
                return;
            }
            Ubicaciones tipo = Ubicaciones.valueOf(insertarTipo.getValue().toString().toUpperCase());
            String horaTexto = insertarHoraSalida.getText();
            LocalTime hora = Utils.validarHora(horaTexto);
            int minutos = (int) insertarTiempo.getValue();
            int capacidad = (int) insertarCapacidad.getValue();

            Ubicacion u = new Ubicacion(tipo, hora, minutos, capacidad);
            UbicacionDAO.addUbicacion(u);


        } catch (Exception e) {
            //todo --> alertas y la excepción -> illegalException
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que vacía los campos TextField una vez se ha realizado correctamente la inserción de la ubicación
     */
    public void limpiarCampos() {
        insertarTipo.setValue(null);
        insertarHoraSalida.clear();
        insertarTiempo.getValueFactory().setValue(0);
        insertarCapacidad.getValueFactory().setValue(0);
    }

    public void botonAñadirUbicacion(ActionEvent event) {
        panelInsertar.setVisible(true);
        botonInsertado.setVisible(true);
        asignarTiposUbicacion();
        cabeceraTiempo.setText("TIEMPO (min)");
        cabeceraCapacidad.setVisible(true);
        insertarCapacidad.setVisible(true);
        insertarTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        insertarCapacidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));

    }

    /**
     * Metodo que asigna las opciones de ubicaciones al ComboBox
     */
    public void asignarTiposUbicacion() {
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Chenil",
                "Jaula",
                "Cuarentena",
                "Agresivos"
        );

        insertarTipo.setItems(opciones);
    }

    public void botonGuardarUbicacion(ActionEvent event) {
        try {
            insertarUbicacion();
        } catch (Exception e) {
            //todo --> alertas y la excepción -> illegalException --> elarta si no se ha guardado
            throw new RuntimeException(e);
        }
        initialize();
        limpiarCampos();
    }

    /*-------------------------------GESTIÓN BUSCAR UBICACIÓN------------------------------------*/

    /**
     * Método que activa y desactiva los paneles necesarios
     *
     * @param event -> acción de pulsar el botón
     */
    public void botonBusqueda(ActionEvent event) {
        panelInsertar.setVisible(true);
        botonInsertado.setVisible(false);
        botonContinuar.setVisible(true);
        cabeceraTiempo.setText("ID");
        cabeceraCapacidad.setVisible(false);
        insertarCapacidad.setVisible(false);
        insertarTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        asignarTiposUbicacion();

    }

    /**
     * Metodo que recoge los datos obtenidos por teclado y llama a los metodos find correspondiente de UbicacionDAO
     * @return --> devuelve una lista con los datos encontrados
     */
    public List<Ubicacion> busquedaAccion() {

        try {

            String horaTexto = insertarHoraSalida.getText();
            LocalTime hora = Utils.validarHora(horaTexto);
            int minutos = (int) insertarTiempo.getValue();


            if(minutos>0){
                List<Ubicacion> resultadoUnico =  new ArrayList<>();
                resultadoUnico.add(UbicacionDAO.findById(minutos));
                return resultadoUnico;
            }

            List<Ubicacion> resultadosEncontrados = new ArrayList<>();

            if(insertarTipo.getValue() != null){
                resultadosEncontrados.addAll( UbicacionDAO.findByType(insertarTipo.getValue().toString().toUpperCase()));
            }

            if(hora != null){
                resultadosEncontrados.addAll( UbicacionDAO.findByHour(Time.valueOf(hora)));
            }

            return resultadosEncontrados;


        } catch (Exception e) {
            //todo --> alertas y la excepción -> illegalException
            throw new RuntimeException(e);
        }

    }

    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Ubicacion> resultados =
                FXCollections.observableArrayList(busquedaAccion());

        if (resultados == null || resultados.isEmpty()) {
            // todo -> alerta: no se encontraron resultados
            return;
        }

        tablaUbicaciones.setItems(resultados);
        limpiarCampos();
    }
}
