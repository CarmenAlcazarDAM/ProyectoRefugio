package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Ubicacion;
import org.proyectorefugio.model.Ubicaciones;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class UbicacionDAO {
    /**
     * --------------------Sentencias SQL--------------------
     **/
    private final static String SQL_FIND_ALL = "SELECT * FROM ubicacion";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM ubicacion WHERE id = ?";
    private final static String SQL_FIND_BY_HOUR = "SELECT * FROM ubicacion WHERE horaRecreo = ?";

    private final static String SQL_INSERT = "INSERT INTO ubicacion (tipo, horaRecreo, minutosRecreo, capacidad) VALUES(?,?,?,?)";

    private static final String SQL_DELETE_BY_ID = "DELETE ubicacion FROM ubicacion WHERE id = ?";

    private static String SQL_UPDATE_RECESS_TIME = "UPDATE ubicacion SET horaRecreo = ? WHERE id = ?";
    private static String SQL_UPDATE_MINUTES = "UPDATE ubicacion SET minutosRecreo = ? WHERE id = ?";



    /**
     * ------------------------------------------------------
     **/

    /////////////////////// FIND ///////////////////////
    /**
     * Método que busca y devuelve un objeto de tipo Ubicacion según su id
     *
     * @param id --> id a buscar pasada por parámetro
     * @return --> devuelve el objeto Ubicacion si lo encuentra, null si no
     */
    public static Ubicacion findById(int id) {
        Ubicacion u = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ubicaciones tipo = Ubicaciones.valueOf(rs.getString("tipo").toUpperCase());
                Time horaRecreo = rs.getTime("horaRecreo");
                int minutosRecreo = rs.getInt("minutosRecreo");
                int capacidad = rs.getInt("capacidad");

                u = new Ubicacion(id, tipo, horaRecreo, minutosRecreo, capacidad);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    /**
     * Método que crea una lista con todos los objetos de tipo Ubicaciones de la base de datos
     *
     * @return --> devuelve una lista con todas las ubicaciones
     */
    public static List<Ubicacion> findAll() {
        List<Ubicacion> ubicaciones = new ArrayList<>();

        Ubicacion u = null;

        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                u = findById(id);
                ubicaciones.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ubicaciones;
    }

    /**
     * Método que crea una lista con las Ubicaciones que salen a una hora determinada
     *
     * @param hora --> hora de recreo a buscar
     * @return --> devuelve una lista con las ubicaciones que salen a la hora indicada
     */
    public static List<Ubicacion> findByHour(Time hora) {
        List<Ubicacion> ubicaciones = new ArrayList<>();

        Ubicacion u = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_HOUR)) {
            ps.setTime(1, hora);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                u = findById(id);
                ubicaciones.add(u);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ubicaciones;
    }

    /// ////////////////// ADD ///////////////////////
    /**
     * Método qu einserta una nueva ubicación en la base de datos
     *
     * @param u --> Objeto Ubicacion a insertar
     * @return --> devuelve el objeto añadido
     */
    public static Ubicacion addUbicacion(Ubicacion u) {
        Ubicacion añadida = null;

        if (u != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, u.getTipo().toString().toUpperCase());
                ps.setTime(2, u.getHoraRecreo());
                ps.setInt(3, u.getMinutosRecreo());
                ps.setInt(4, u.getCapacidad());

                ps.executeUpdate();

                añadida = findById(u.getId());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return añadida;
    }

    /////////////////////// DELETE ///////////////////////
    /**
     * Método que borra un objeto Ubicacion de la base de datos ubicacion,
     *
     * @param id --> id específica de la ubicación que queremos borrar
     * @return --> devuelve true si se borra correctamente, false si no se borra
     */
    public static boolean deleteUbicacionById(int id) {
        if (findById(id) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE_BY_ID)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }

    /// //////////////////// UPDATE ///////////////////////
    /**
     * Método que actualiza la hora de recreo de una ubicacion
     * @param u --> ubicacion a la que queremos actualizar la información
     * @param horaRecreo --> hora de recreo pasada por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateRecessTime(Ubicacion u, Time horaRecreo) {
        boolean updated = false;
        if ((u != null) && findById(u.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_RECESS_TIME)) {
                ps.setTime(1, horaRecreo);
                ps.setInt(2, u.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }
    /**
     * Método que actualiza la cantidad de minutos de recreo de una ubicacion
     * @param u --> ubicacion a la que queremos actualizar la información
     * @param minutos --> minutos de recreo pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateMinutes(Ubicacion u, int minutos) {
        boolean updated = false;
        if ((u != null) && findById(u.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_MINUTES)) {
                ps.setInt(1, minutos);
                ps.setInt(2, u.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }


}
