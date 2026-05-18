package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Adoptante;
import org.proyectorefugio.model.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdoptanteDAO {
    /**
     * --------------------Sentencias SQL--------------------
     **/
    private final static String SQL_FIND = "SELECT * FROM adoptante";
    private final static String SQL_FIND_ADOPTANTE = "SELECT * FROM adoptante WHERE dniAdoptante = ?";

    private final static String SQL_INSERT = "INSERT INTO adoptante VALUES(?)";

    /**
     * ------------------------------------------------------
     **/

    /////////////////////// FIND ///////////////////////
    /**
     * Metodo que crea una lista con todas los adoptantes de la base de datos
     *
     * @return --> devuelve una lista con todos los adoptantes y sus datos
     */
    public static List<Adoptante> findAll() {

        List<Adoptante> listaAdoptantes = new ArrayList<>();
        Adoptante a = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND)) {
            while (rs.next()) {
                String dni = rs.getString("dniAdoptante");
                Persona datosPersona = PersonaDAO.findByDni(dni);
                a = new Adoptante(datosPersona.getDni(), datosPersona.getNombre(), datosPersona.getApellidos(), datosPersona.getTelefono(), datosPersona.getCorreo(), datosPersona.getDireccion());
                listaAdoptantes.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAdoptantes;
    }

    /**
     * Metodo que verifica si un dni especifico existe dentro de la tabla adoptantes
     * @param dni --> dni a buscar
     * @return --> devuelve true si encuentra el dni dentro de adoptantes, false si no lo encuentra
     */
    private static boolean esAdoptante(String dni) {
        boolean existe = false;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_ADOPTANTE)) {
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
     * Metodo que busca a un adoptante por su dni específico
     * @param dni --> dni a buscar
     * @return --> devuelve el objeto Adoptante si lo encuentra
     *
     */
    public static Adoptante findByDni(String dni) {

        if (esAdoptante(dni)) {
            Persona p = PersonaDAO.findByDni(dni);
            if (p != null) {
                return rellenar(p);
            }
        }
        return null;
    }

    /**
     * Metodo que busca a los Adoptantes con un nombre específico
     * @param name --> nombre a buscar
     * @return --> devuelve una lista de Adoptantes que compartan el mismo nombre
     */
    public static List<Adoptante> findByName(String name) {
        List<Adoptante> listaAdoptantes = new ArrayList<>();

        List<Persona> personasEncontradas = PersonaDAO.findByName(name);

        for (Persona p : personasEncontradas) {
            if (esAdoptante(p.getDni())) {
                Adoptante a = rellenar(p);

                listaAdoptantes.add(a);
            }
        }
        return listaAdoptantes;
    }

    /**
     * Metodo que busca a los Adoptantes con un apellido específico
     * @param lastName --> apellido a buscar
     * @return --> devuelve una lista de Adoptantes que compartan el mismo apellido
     */
    public static List<Adoptante> findByLastName(String lastName) {
        List<Adoptante> listaAdoptantes = new ArrayList<>();

        List<Persona> personasEncontradas = PersonaDAO.findByLastName(lastName);

        for (Persona p : personasEncontradas) {
            if (esAdoptante(p.getDni())) {
                Adoptante a = rellenar(p);
                listaAdoptantes.add(a);
            }
        }
        return listaAdoptantes;
    }

    /**
     * Metodo que rellena el constructor del objeto Adoptante, para no tener que rellenarlo en todos los métodos en los que se use
     * en caso de que haya modificaciones de atributos en el objeto Voluntario
     * @param p --> objeto Persona (superclase).
     * @return --> devuelve el objeto Persona (superclase) convertido en Adoptante (subclase)
     */
    private static Adoptante rellenar(Persona p) {
        if (p == null) {
            return null;
        }
        return new Adoptante(p.getDni(), p.getNombre(), p.getApellidos(), p.getTelefono(), p.getCorreo(), p.getDireccion());
    }

    //////////////////////// ADD ////////////////////
    /**
     * Metodo que inserta un ADOPTANTE en la base de datos dentro de la tabla adoptante
     * @param p --> objeto Persona pasado como parámetro
     * @return --> devuelve true si se ha insertado correctamente, false si no se ha insertado
     */
    public static boolean addAdoptante(Persona p) {

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
