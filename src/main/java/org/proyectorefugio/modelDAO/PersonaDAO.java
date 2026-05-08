package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    /**
     * --------------------Sentencias SQL--------------------
     **/
    private final static String SQL_FIND_BY_DNI = "SELECT * FROM persona WHERE dni = ?";
    private final static String SQL_FIND_ALL = "SELECT * FROM persona";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM persona WHERE nombre LIKE ?";
    private final static String SQL_FIND_BY_LAST_NAME = "SELECT * FROM persona WHERE apellido LIKE ?";


    /**------------------------------------------------------**/

    /////////////////////// FIND ///////////////////////
    /**
     * Método que busca y devuelve un objeto Persona según su DNI
     *
     * @param dni --> dni pasado por parámetro
     * @return --> devuelve un objeto Persona
     */
    public static Persona findByDni(String dni) {
        Persona p = null;
        if (!dni.isEmpty()) {
            return null;
        }
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DNI)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String direccion = rs.getString("direccion");

                p = new Persona(dni, nombre, apellidos, telefono, correo, direccion);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    /**
     * Método que crea una lista con todas las personas de la base de datos
     * @return --> devuelve una lista con todas personas y sus datos
     */
    public static List<Persona> findAll() {
        List<Persona> listaPersonas = new ArrayList<>();
        Persona p = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                String dni = rs.getString("dni");
                p = findByDni(dni);
                listaPersonas.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPersonas;
    }

    /**
     * Método que busca a las personas que tengan un nombre específico
     * @param name --> nombre pasado por parámetro
     * @return --> devuelve una lista de personas que compartan el mismo nombre
     */
    public static List<Persona> findByName(String name) {
        List<Persona> listaPersonas = new ArrayList<>();
        Persona p = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String dni = rs.getString("dni");
                p = findByDni(dni);
                listaPersonas.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPersonas;
    }

    /**
     * Método que busca a las personas que tengan un apellido específico
     * @param lastName --> apellido pasado por parámetro
     * @return --> devuelve una lista de personas que compartan el mismo apellido
     */
    public static List<Persona> findByLastName(String lastName) {
        List<Persona> listaPersonas = new ArrayList<>();
        Persona p = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_LAST_NAME)) {
            ps.setString(1, "%" + lastName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String dni = rs.getString("dni");
                p = findByDni(dni);
                listaPersonas.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPersonas;
    }
}
