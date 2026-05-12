package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Persona;
import org.proyectorefugio.model.Voluntario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO {
    /**
     * --------------------Sentencias SQL--------------------
     **/
    private final static String SQL_FIND = "SELECT * FROM voluntario";
    private final static String SQL_FIND_VOLUNTARIO = "SELECT * FROM voluntario WHERE dniVoluntario = ?";

    private final static String SQL_INSERT = "INSERT INTO voluntario VALUES(?)";

    /**
     * ------------------------------------------------------
     **/

    /////////////////////// FIND ///////////////////////
    /**
     * Método que crea una lista con todas los voluntarios de la base de datos
     *
     * @return --> devuelve una lista con todos los voluntarios y sus datos
     */
    public static List<Voluntario> findAll() {

        List<Voluntario> listaVoluntarios = new ArrayList<>();
        Voluntario v = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND)) {
            while (rs.next()) {
                String dni = rs.getString("dniVoluntario");
                Persona datosPersona = PersonaDAO.findByDni(dni);
                v = new Voluntario(datosPersona.getDni(), datosPersona.getNombre(), datosPersona.getApellidos(), datosPersona.getTelefono(), datosPersona.getCorreo(), datosPersona.getDireccion());
                listaVoluntarios.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVoluntarios;
    }

    /**
     * Método que verifica si un dni especifico existe dentro de la tabla voluntarios
     * @param dni --> dni a buscar
     * @return --> devuelve true si encuentra el dni dentro de volutario, false si no lo encuentra
     */
    private static boolean esVoluntario(String dni) {
        boolean existe = false;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_VOLUNTARIO)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }

    /**
     * Método que busca a un voluntario por su dni específico
     * @param dni --> dni a buscar
     * @return --> devuelve el objeto Voluntario si lo encuentra
     *
     */
    public static Voluntario findByDni(String dni) {

        if (esVoluntario(dni)) {
            Persona p = PersonaDAO.findByDni(dni);
            if (p != null) {
                return rellenar(p);
            }
        }
        return null;
    }

    /**
     * Método que busca a los Voluntarios con un nombre específico
     * @param name --> nombre a buscar
     * @return --> devuelve una lista de Voluntarios que compartan el mismo nombre
     */
    public static List<Voluntario> findByName(String name) {
        List<Voluntario> listaVoluntarios = new ArrayList<>();

        List<Persona> voluntariosEncontrados = PersonaDAO.findByName(name);

        for (Persona p : voluntariosEncontrados) {
            if (esVoluntario(p.getDni())) {
                Voluntario v = rellenar(p);

                listaVoluntarios.add(v);
            }
        }
        return listaVoluntarios;
    }

    /**
     * Método que busca a los Voluntarios con un apellido específico
     * @param lastName --> apellido a buscar
     * @return --> devuelve una lista de Voluntarios que compartan el mismo apellido
     */
    public static List<Voluntario> findByLastName(String lastName) {
        List<Voluntario> listaVoluntarios = new ArrayList<>();

        List<Persona> personasEncontradas = PersonaDAO.findByLastName(lastName);

        for (Persona p : personasEncontradas) {
            if (esVoluntario(p.getDni())) {
                Voluntario v = rellenar(p);
                listaVoluntarios.add(v);
            }
        }
        return listaVoluntarios;
    }

    /**
     * Método que rellena el constructor del objeto Volutario, para no tener que rellenarlo en todos los métodos en los que se use
     * en caso de que haya modificaciones de atributos en el objeto Voluntario
     * @param p --> objeto Persona (superclase).
     * @return --> devuelve el objeto Persona (superclase) convertido en Voluntario (subclase)
     */
    private static Voluntario rellenar(Persona p) {
        if (p == null) {
            return null;
        }
        return new Voluntario(p.getDni(), p.getNombre(), p.getApellidos(), p.getTelefono(), p.getCorreo(), p.getDireccion());
    }

    //////////////////////// ADD ////////////////////
    /**
     * Método que inserta un VOLUNTARIO en la base de datos dentro de la tabla voluntario
     * @param p --> objeto Persona pasado como parámetro
     * @return --> devuelve true si se ha insertado correctamente, false si no se ha insertado
     */
    public static boolean addVoluntario(Persona p) {

        if ((p != null)) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, p.getDni());

                ps.executeUpdate();

                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
