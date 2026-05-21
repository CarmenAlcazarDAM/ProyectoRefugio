package org.proyectorefugio.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión a la base de datos mediante el patrón Singleton.
 * Lee los parámetros de conexión desde un fichero XML y mantiene
 * una única instancia de Connection durante toda la ejecución.
 */
public class ConnectionBD {
    /** Nombre del fichero XML que contiene los parámetros de conexión. */
    private static final String FILE = "connection.xml";

    /** Conexión activa a la base de datos. */
    private static Connection con;

    /** Instancia única de esta clase (patrón Singleton). */
    private static ConnectionBD _instance;

    /**
     * Constructor privado que inicializa la conexión a la base de datos.
     * Lee la URL, usuario y contraseña desde el fichero XML y establece la conexión.
     */
    private ConnectionBD() {
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
        try{
            con = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPassword());
        }catch(SQLException e){
            e.printStackTrace();
            con=null;
        }
    }

    /**
     * Devuelve la conexión activa a la base de datos.
     * Si no existe ninguna instancia previa, la crea (lazy initialization).
     *
     * @return --> devuelve la conexión activa o  null si falló
     */
    public static Connection getConnection() {
        if(_instance==null){
            _instance = new ConnectionBD();
        }
        return con;
    }
}