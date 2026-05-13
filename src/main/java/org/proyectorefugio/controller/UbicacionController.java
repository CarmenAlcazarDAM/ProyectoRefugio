package org.proyectorefugio.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Ubicacion;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.UbicacionDAO;

import java.sql.Time;
import java.time.LocalTime;
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
    /**
     * Método que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        iniciarTabla();
    }

    /**
     * Método que extrae los datos de las ubicaciones de la base de datos y clasifica la
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
     * Método que obtiene la cantidad de animales que hay en la actualidad en una determinada
     * ubicacion
     * @param idUbicacion --> id de la ubicacion de la que vamos a obtener la cantidad
     *                    de animales
     * @return --> devuelve cuantos animales están ocupando la ubicación
     */
    public int ocupacionDeUbicacion(int idUbicacion) {
        List<Animal> animalesEnUbicacion = AnimalDAO.findByUbicacion(idUbicacion);
        return animalesEnUbicacion.size();
    }
}
