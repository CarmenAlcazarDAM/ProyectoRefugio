package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GatoDAO {

    /**
     * --------------------Sentencias SQL--------------------
     **/

    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, gato g WHERE a.id = g.idGato";
    private final static String SQL_FIND_ALL_NOT_ADOPTED = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, gato g WHERE a.id = g.idGato AND adoptado = 0";

    private final static String SQL_FIND_GATO = "SELECT leucemiaFelina FROM gato WHERE idGato = ?";

    private final static String SQL_INSERT = "INSERT INTO perro VALUES(?)";
    /**------------------------------------------------------**/


    /**
     * Método que devuelve una lista con todos los gatos de la base de datos
     *
     * @return --> lista con todos los gatos y sus datos.
     */
    public static List<Gato> findAll() {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                gato = new Gato(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaGatos.add(gato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }

    /**
     * Método que devuelve una lista con todos los gatos de la base de datos
     *
     * @return --> lista con todos los gatos y sus datos.
     */
    public static List<Gato> findAllNotAdopted() {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL_NOT_ADOPTED)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                gato = new Gato(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaGatos.add(gato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }

    private static Gato rellenarDatosGato(Animal a) {
        Gato g = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_GATO)) {

            ps.setInt(1, a.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {


                    boolean leucemiaFelina = rs.getBoolean("leucemiaFelina");

                    g = new Gato(
                            a.getId(),
                            a.getNombre(),
                            a.getRaza(),
                            a.getSexo(),
                            a.getMarcasDistintivas(),
                            a.getNumeroChip(),
                            a.isEsterilizado(),
                            a.getHistoria(),
                            a.getObservaciones(),
                            a.getFechaIngreso(),
                            a.getIdUbicacion(),
                            rs.getBoolean("leucemiaFelina")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return g;
    }

    /**
     * Método que busca a los gatos que tengan un nombre específico y estén en adopción
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan el nombre introducido por el usuario
     */
    public static List<Gato> findByNameNotAdopted(String name) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByNameNotAdopted(name);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }

        return listaGatos;
    }

    /**
     * Método que busca a los gatos que tengan un nombre específico y ya han sido adoptados
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan el nombre introducido por el usuario
     */
    public static List<Gato> findByNameAdopted(String name) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByNameAdopted(name);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }

        return listaGatos;
    }

    /**
     * Método que busca a los gatos que tengan una raza específica y no han sido adoptados
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan esa misma raza
     */
    public static List<Gato> findByBreedNotAdopted(String breed) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByNameNotAdopted(breed);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }

        return listaGatos;
    }

    /**
     * Método que busca a los gatos que tengan una raza específica y han sido adoptados
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan esa misma raza
     */
    public static List<Gato> findByBreedAdopted(String breed) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByNameAdopted(breed);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }

        return listaGatos;
    }

    /**
     * Método que busca a los gatos que tengan unos colores específicos
     *
     * @param colour --> color a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan esos colores
     */
    public static List<Gato> findByColour(String colour) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByColour(colour);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }

        return listaGatos;
    }

    /// ///////////////////// AÑADIR ///////////////////////

    public static boolean addGato(Gato g, Animal a) {

        if ((g != null) && (a != null)) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setInt(1, a.getId());
                ps.setBoolean(2, g.isLeucemiaFelina());

                ps.executeUpdate();

                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
