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

    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, perro p WHERE a.id = p.idPerro AND adoptado = ?";


    private final static String SQL_FIND_PERRO = "SELECT peso, agresivo FROM perro WHERE idPerro = ?";

    private final static String SQL_INSERT = "INSERT INTO perro (idPerro, peso, agresivo) VALUES(?, ?, ?)";

    private static String SQL_UPDATE_PESO = "UPDATE perro SET peso = ? WHERE idPerro = ?";
    private static String SQL_UPDATE_AGRESIVO = "UPDATE perro SET agresivo = ? WHERE idPerro = ?";

    /**------------------------------------------------------**/

    /////////////////////// FIND ///////////////////////
    /**
     * Método que busca todos los Perros de la base de datos.
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con todos los Perros encontrados
     */
    public static List<Perro> findAll(boolean isAdopted) {
        List<Perro> listaPerros = new ArrayList<>();
        Perro perro = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_ALL)) {
            ps.setBoolean(1, isAdopted);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String raza = rs.getString("raza");
                    Sexo sexo = Sexo.valueOf(rs.getString("sexo"));

                    perro = new Perro(id, nombre, raza, sexo);

                    listaPerros.add(perro);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPerros;
    }

    /**
     * Método que busca a los perros que tengan un nombre específico
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con los perros que tengan el nombre introducido por el usuario
     */
    public static List<Perro> findByName(String name, boolean isAdopted) {
        List<Perro> listaPerros = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByName(name, isAdopted);

        for (Animal a : animalesEncontrados) {
            Perro p = rellenarDatosPerro(a);

            if (p != null) {
                listaPerros.add(p);
            }
        }

        return listaPerros;
    }

    /**
     * Método que extrae de la base de datos toda la información del objeto Perro
     * @param a --> Animal pasado por parámetro
     * @return --> devuelve el objeto Perro completo con la información de los atributos de la clase Animal
     * y de la clase Perro
     */
    private static Perro rellenarDatosPerro(Animal a) {
        Perro p = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_PERRO)) {

            ps.setInt(1, a.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

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
     * Método que busca a los perros que tengan una raza específica
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con los perros que tengan esa misma raza
     */
    public static List<Perro> findByBreed(String breed, boolean isAdopted) {
        List<Perro> listaPerros = new ArrayList<>();

        List<Animal> animalesEncontrados = AnimalDAO.findByBreed(breed, isAdopted);

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
     *
     * @param p --> objeto Perro pasado como parámetro
     * @param a --> objeto Animal pasado como parámetro
     * @return --> devuelve true si se ha insertado correctamente, false si no se ha insertado
     */
    public static boolean addPerro(Perro p, Animal a) {
        boolean añadido = false;
        if ((p != null) && (a != null)) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setInt(1, a.getId());
                ps.setDouble(2, p.getPeso());
                ps.setBoolean(3, p.isAgresivo());

                ps.executeUpdate();

                añadido = true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return añadido;
    }

    //////////////////////// UPDATE ////////////////////
    /**
     * Método que actualiza la informacion del peso del perro
     *
     * @param a    --> animal al que vamos a actualizar la información
     * @param peso --> peso del perro pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updatePeso(Animal a, double peso) {
        boolean updated = false;
        if ((a != null) && AnimalDAO.findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_PESO)) {
                ps.setDouble(1, peso);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la informacion sobre la agresividad del perro
     *
     * @param a --> animal al que vamos a actualizar la información
     * @param agresivo --> agresividad del perro, true si es agresivo, false si no lo es
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateAgresivo(Animal a, boolean agresivo) {
        boolean updated = false;
        if ((a != null) && AnimalDAO.findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_AGRESIVO)) {
                ps.setBoolean(1, agresivo);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }
}


