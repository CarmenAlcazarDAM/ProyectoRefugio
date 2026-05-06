package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerroDAO {
    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, perro p WHERE a.id = p.idPerro";

    /**
     * Método que devuelve una lista con todos los perros de la base de datos
     * @return --> lista con todos los perros y sus datos.
     */
    public static List<Perro> findAll(){
        List<Perro> listaPerros = new ArrayList<>();
        Perro perro = null;
        try(ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)){
            while(rs.next()){
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                perro = new Perro(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaPerros.add(perro);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaPerros;
    }

}
