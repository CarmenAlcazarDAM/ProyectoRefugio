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

    /**
     * --------------------Sentencias SQL--------------------
     **/

    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, perro p WHERE a.id = p.idPerro";
    private final static String SQL_FIND_ALL_NOT_ADOPTED = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, perro p WHERE a.id = p.idPerro AND adoptado = 0";

    private final static String SQL_FIND_PERRO = "SELECT peso, agresivo FROM perro WHERE idPerro = ?";

    private final static String SQL_INSERT = "INSERT INTO perro VALUES(?)";

    private static String SQL_UPDATE_PESO = "UPDATE perro SET peso = ? WHERE idPerro = ?";
    private static String SQL_UPDATE_AGRESIVO = "UPDATE perro SET agresivo = ? WHERE idPerro = ?";

    /**------------------------------------------------------**/

    /////////////////////// FIND ///////////////////////
    /**
     * Método que devuelve una lista con todos los perros de la base de datos
     *
     * @return --> lista con todos los perros y sus datos.
     */
    public static List<Perro> findAll() {
        List<Perro> listaPerros = new ArrayList<>();
        Perro perro = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                perro = new Perro(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaPerros.add(perro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPerros;
    }

    /**
     * Método que devuelve una lista con todos los perros de la base de datos
     *
     * @return --> lista con todos los perros y sus datos.
     */
    public static List<Perro> findAllNotAdopted() {
        List<Perro> listaPerros = new ArrayList<>();
        Perro perro = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL_NOT_ADOPTED)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                perro = new Perro(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaPerros.add(perro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPerros;
    }

    /**
     * Método que busca a los perros que tengan un nombre específico y estén en adopción
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @return --> devuelve una lista con los perros que tengan el nombre introducido por el usuario
     */
    public static List<Perro> findByNameNotAdopted(String name) {
        List<Perro> listaPerros = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByNameNotAdopted(name);

        for (Animal a : animalesEncontrados) {
            Perro p = rellenarDatosPerro(a);

            if (p != null) {
                listaPerros.add(p);
            }
        }

        return listaPerros;
    }

    private static Perro rellenarDatosPerro(Animal a) {
        Perro p = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_PERRO)) {

            ps.setInt(1, a.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    double peso = rs.getDouble("peso");
                    boolean agresivo = rs.getBoolean("agresivo");

                    p = new Perro(
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
                            rs.getDouble("peso"),
                            rs.getBoolean("agresivo")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    /**
     * Método que busca a los perros que tengan un nombre específico y ya han sido adoptados
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @return --> devuelve una lista con los perros que tengan el nombre introducido por el usuario
     */
    public static List<Perro> findByNameAdopted(String name) {
        List<Perro> listaPerros = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByNameAdopted(name);

        for (Animal a : animalesEncontrados) {
            Perro p = rellenarDatosPerro(a);

            if (p != null) {
                listaPerros.add(p);
            }
        }
        return listaPerros;
    }

    /**
     * Método que busca a los perros que tengan una raza específica y no han sido adoptados
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @return --> devuelve una lista con los perros que tengan esa misma raza
     */
    public static List<Perro> findByBreedNotAdopted(String breed) {
        List<Perro> listaPerros = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByBreedNotAdopted(breed);

        for (Animal a : animalesEncontrados) {
            Perro p = rellenarDatosPerro(a);

            if (p != null) {
                listaPerros.add(p);
            }
        }
        return listaPerros;
    }

    /**
     * Método que busca a los perros que tengan una raza específica y ya han sido adoptados
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @return --> devuelve una lista con los perros que tengan esa misma raza
     */
    public static List<Perro> findByBreedAdopted(String breed) {
        List<Perro> listaPerros = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByBreedAdopted(breed);

        for (Animal a : animalesEncontrados) {
            Perro p = rellenarDatosPerro(a);

            if (p != null) {
                listaPerros.add(p);
            }
        }
        return listaPerros;
    }


    //////////////////////// ADD ////////////////////
    /**
     * Método que inserta un PERRO en la base de datos dentro de la tabla perro
     * @param p --> objeto Perro pasado como parámetro
     * @param a --> objeto Animal pasado como parámetro
     * @return --> devuelve true si se ha insertado correctamente, false si no se ha insertado
     */
    public static boolean addPerro(Perro p, Animal a) {

        if ((p != null) && (a != null)) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setInt(1, a.getId());
                ps.setDouble(2, p.getPeso());
                ps.setBoolean(3, p.isAgresivo());

                ps.executeUpdate();

                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    //////////////////////// UPDATE ////////////////////
    /**
     * Método que actualiza la informacion del peso del perro
     * @param p --> perro al que vamos a actualizar la información
     * @param peso --> peso del perro pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updatePeso(Perro p, double peso) {
        boolean updated = false;
        if ((p != null) && AnimalDAO.findByID(p.getId()) != null){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_PESO)) {
                ps.setDouble(1,peso);
                ps.setInt(2, p.getId());

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
     * Método que actualiza la informacion sobre la agresividad del perro
     * @param p --> perro al que vamos a actualizar la información
     * @param agresivo --> agresividad del perro, true si es agresivo, false si no lo es
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateAgresivo(Perro p, boolean agresivo) {
        boolean updated = false;
        if ((p != null) && AnimalDAO.findByID(p.getId()) != null){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_AGRESIVO)) {
                ps.setBoolean(1,agresivo);
                ps.setInt(2, p.getId());

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


