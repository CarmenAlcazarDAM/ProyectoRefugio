module org.proyectorefugio {
    requires java.xml.bind;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;



    opens org.proyectorefugio.dataAccess to java.xml.bind;

    opens org.proyectorefugio to javafx.fxml;

    exports org.proyectorefugio;
    exports org.proyectorefugio.pruebas;
    opens org.proyectorefugio.pruebas to javafx.fxml;
}