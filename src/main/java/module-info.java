module org.proyectorefugio {
    requires java.xml.bind;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    // Abrimos el paquete a JAXB usando la NUEVA ruta completa
    opens org.proyectorefugio.dataAccess to java.xml.bind;

    // Si usas FXML, también tienes que abrir el paquete de los controladores a JavaFX
    opens org.proyectorefugio to javafx.fxml;

    exports org.proyectorefugio;
}