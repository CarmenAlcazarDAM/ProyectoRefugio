package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Animal;
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
    private final static String SQL_FIND_BY_LAST_NAME = "SELECT * FROM persona WHERE apellidos LIKE ?";

    private static final String SQL_INSERT = "INSERT INTO persona VALUES (?)";

    private final static String SQL_DELETE = "DELETE persona WHERE dni = ?";

    private static String SQL_UPDATE_TELEFONO = "UPDATE persona SET telefono = ? WHERE id = ?";
    private static String SQL_UPDATE_CORREO = "UPDATE persona SET correo = ? WHERE id = ?";
    private static String SQL_UPDATE_DIRECCION = "UPDATE persona SET direccion = ? WHERE id = ?";




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
     *
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
     *
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
     *
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

    /////////////////////// INSERT ///////////////////////

    public static Persona addPersona(Persona p){
        Persona añadida = null;
        if(p!=null){
            try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setString(1, p.getDni());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getApellidos());
                ps.setString(4, p.getTelefono());
                ps.setString(5, p.getCorreo());
                ps.setString(6,p.getDireccion());

                ps.executeUpdate();

                añadida = findByDni(p.getDni());

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return añadida;
    }


    /////////////////////// DELETE ///////////////////////

    /**
     * Método que borra una persona de la base de datos
     * @param dni --> dni de la persona pasado por parámetro
     * @return --> devuelve true si se borra correctamente, false si no se borra
     */
    public static boolean deletePersona(String dni) {
        if (findByDni(dni) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setString(1, dni);
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /////////////////////// UPDATE ///////////////////////

    /**
     * Método que actualiza la información del télefono de contacto de una persona
     * @param p --> Persona a la que vamos a actualizar la información
     * @param telefono --> telefono de la persona pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateTelefono(Persona p, String telefono) {
        boolean updated = false;
        if ((p != null) && findByDni(p.getDni()) != null ){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_TELEFONO)) {
                ps.setString(1,telefono);
                ps.setString(2, p.getDni());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la información del correo de contacto de una persona
     * @param p --> Persona a la que vamos a actualizar la información
     * @param correo --> correo de la persona pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateEmail(Persona p, String correo) {
        boolean updated = false;
        if ((p != null) && findByDni(p.getDni()) != null ){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_CORREO)) {
                ps.setString(1,correo);
                ps.setString(2, p.getDni());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la información de la dirección de una persona
     * @param p --> Persona a la que vamos a actualizar la información
     * @param direccion --> dirección de la persona pasada por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateAdress(Persona p, String direccion) {
        boolean updated = false;
        if ((p != null) && findByDni(p.getDni()) != null ){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_DIRECCION)) {
                ps.setString(1,direccion);
                ps.setString(2, p.getDni());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }
}
