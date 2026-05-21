package org.proyectorefugio.dataAccess;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Utilidad genérica para serializar y deserializar objetos a/desde ficheros XML
 * mediante JAXB.
 */
public class XMLManager {
    /**
     * Serializa un objeto a un fichero XML (marshalling).
     * El fichero se genera con formato indentado y codificación UTF-8.
     *
     * @param <T> --> tipo del objeto a serializar
     * @param c --> objeto a escribir en el XML
     * @param filename -->ruta y nombre del fichero de destino
     * @return --> devuelve true si la escritura se completó correctamente, false si ocurrió un error de JAXB
     */
    public static <T> boolean writeXML(T c,String filename){
        boolean result=false;
        JAXBContext context;
        try{
            context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            m.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
            m.marshal(c,new File(filename));
            result=true;
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deserializa un fichero XML a un objeto del tipo indicado (unmarshalling).
     * Si la lectura falla, devuelve el objeto original recibido como parámetro.
     *
     * @param <T> --> tipo del objeto resultante
     * @param c  --> instancia del tipo esperado, usada para obtener la clase en tiempo de ejecución
     * @param filename --> ruta y nombre del fichero XML a leer
     * @return --> devuelve el objeto deserializado desde el XML, o lanza una excepción si ocurrió un error
     */
    public static<T> T readXML(T c,String filename){
        T result = c;
        JAXBContext context;

        try{
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(new File(filename));
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return result;
    }
}