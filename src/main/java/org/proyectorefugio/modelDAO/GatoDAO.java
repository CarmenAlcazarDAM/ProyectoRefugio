package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.model.Perro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GatoDAO {
    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, gato g WHERE a.id = g.idGato";

    /**
     * Método que devuelve una lista con todos los gatos de la base de datos
     * @return --> lista con todos los gatos y sus datos.
     */
    public static List<Gato> findAll(){
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;
        try(ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)){
            while(rs.next()){
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                gato = new Gato(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaGatos.add(gato);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaGatos;
    }
}
