package org.proyectorefugio.dataAccess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Almacena los parámetros de conexión a la base de datos.
 * Se mapea desde el fichero XML de configuración mediante JAXB,
 * usando el elemento raíz {@code <connection>}.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="connection")
public class ConnectionProperties implements Serializable {

    private String server;

    private String port;

    private String dataBase;

    private String user;

    private String password;

    /**
     * Constructor vacío requerido por JAXB.
     */
    public ConnectionProperties(){}

    /**
     * Constructor con todos los parámetros de conexión.
     *
     * @param server --> dirección del servidor
     * @param port --> puerto del servidor
     * @param dataBase  --> nombre de la base de datos
     * @param user --> usuario de la base de datos
     * @param password --> contraseña del usuario
     */
    public ConnectionProperties (String server, String port, String dataBase, String user, String password) {
        this.server = server;
        this.port = port;
        this.dataBase=dataBase;
        this.user=user;
        this.password=password;
    }

    /**
     * Devuelve el usuario de la base de datos.
     *
     * @return --> usuario
     */
    public String getUser(){return user;}

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return --> contraseña
     */
    public String getPassword(){return password;}

    /**
     * Construye y devuelve la URL de conexión JDBC a partir del servidor,
     * puerto y nombre de base de datos.
     *
     * @return --> url con formato jdbc:mysql://servidor:puerto/baseDeDatos
     */
    public String getURL(){ return "jdbc:mysql://"+server+":"+port+"/"+dataBase;}

}