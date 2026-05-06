package org.proyectorefugio.modelDAO;

import org.proyectorefugio.model.Animal;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAO {
    private final static String SQL_FIND_BY_ID = "SELECT * FROM animal WHERE id = ?";


    /**
     * Método que busca y devuelve un objeto animal según su ID
     * @param id --> id específica de cada animal
     * @return --> devuelve un objeto animal
     */
    public static Animal findByID(int id) {
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));

                animal = new Animal(id, nombre, raza, sexo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

}
