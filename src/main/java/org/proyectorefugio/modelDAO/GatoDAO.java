package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Gato.
 * Gestiona las operaciones de consulta, inserción y actualización sobre la tabla gato,
 * apoyándose en AnimalDAO para obtener los datos comunes heredados de Animal.
 */
public class GatoDAO {

    //region--------------------Sentencias SQL--------------------


    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, gato g WHERE a.id = g.idGato AND adoptado = ?";

    private final static String SQL_FIND_GATO = "SELECT leucemiaFelina FROM gato WHERE idGato = ?";

    private final static String SQL_INSERT = "INSERT INTO gato (idGato, leucemiaFelina) VALUES(?, ?)";

    private static String SQL_UPDATE_LEUCEMIA = "UPDATE gato SET leucemiaFelina = ? WHERE idGato = ?";
    //endregion------------------------------------------------------

    //region FIND

    /**
     * Metodo que devuelve una lista con todos los gatos de la base de datos
     *
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> lista con todos los gatos y sus datos.
     */
    public static List<Gato> findAll(boolean isAdopted) {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_ALL)) {
            ps.setBoolean(1, isAdopted);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Animal a = AnimalDAO.findByID(id);

                    gato = rellenarDatosGato(a);
                    listaGatos.add(gato);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }
    /**
     * Metodo que busca un Gato por id en la base de datos y extrae toda su informacion
     * @param id --> id pasado por parámetro
     * @return --> devuelve el objeto Gato encontrado o null si no lo encuentra
     */
    public static Gato findByID(int id) {
        Animal a = AnimalDAO.findByID(id);
        if (a == null) return null;
        return rellenarDatosGato(a);
    }

    /**
     * Metodo que busca un Gato por chip en la base de datos y extrae toda su informacion
     * @param chip --> número del chip pasado por parámetro
     * @return --> devuelve el objeto Gato encontrado o null si no lo encuentra
     */
    public static Gato findByChip(String chip) {
        Animal a = AnimalDAO.findByChip(chip);
        if (a == null) return null;
        return rellenarDatosGato(a);
    }

    /**
     * Metodo que extrae de la base de datos toda la información del objeto Gato
     * @param a --> Animal pasado por parámetro
     * @return --> devuelve el objeto Gato completo con la información de los atributos de la clase Animal
     * y de la clase Gato
     */
    private static Gato rellenarDatosGato(Animal a) {
        Gato g = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_GATO)) {

            ps.setInt(1, a.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    g = new Gato(
                            a.getId(),
                            a.getNombre(),
                            a.getRaza(),
                            a.getSexo(),
                            a.getColor(),
                            a.getEdad(),
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
     * Metodo que busca a los gatos que tengan un nombre específico
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con los gatos que tengan el nombre introducido por el usuario
     */
    public static List<Gato> findByName(String name, boolean isAdopted) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByName(name, isAdopted);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }
        return listaGatos;
    }

    /**
     * Metodo que busca a los gatos que tengan una raza específica
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con los gatos que tengan esa misma raza
     */
    public static List<Gato> findByBreed(String breed, boolean isAdopted) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByBreed(breed, isAdopted);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }
        return listaGatos;
    }

    /**
     * Metodo que busca a los gatos que tengan unos colores específicos
     *
     * @param colour --> color a buscar, introducido por el usuario
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con los gatos que tengan esos colores
     */
    public static List<Gato> findByColour(String colour, boolean isAdopted) {
        List<Gato> listaGatos = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByColour(colour, isAdopted);

        for (Animal a : animalesEncontrados) {
            Gato g = rellenarDatosGato(a);

            if (g != null) {
                listaGatos.add(g);
            }
        }
        return listaGatos;
    }
    //endregion

    //region ADD
    /**
     * Metodo que inserta un GATO en la base de datos dentro de la tabla gato
     * @param g --> objeto Gato pasado como parámetro
     * @param a --> objeto Animal pasado como parámetro
     * @return --> devuelve true si se ha insertado correctamente, false si no se ha insertado
     */
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

    //endregion UPDATE
    /**
     * Metodo que actualiza la información sobre la leucemia de un gato
     *
     * @param a --> animal al que vamos a actualizar la información
     * @param leucemia --> leucemia del gato, true si es agresivo, false si no lo es
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateLeucemia(Animal a, boolean leucemia) {
        boolean updated = false;
        if ((a != null) && AnimalDAO.findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_LEUCEMIA)) {
                ps.setBoolean(1, leucemia);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }
    //endregion
}
