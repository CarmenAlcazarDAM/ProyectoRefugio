module org.proyectorefugio {
    requires java.xml.bind;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jshell;

    requires javafx.graphics;
    requires javafx.base;


    opens org.proyectorefugio.dataAccess to java.xml.bind;

    opens org.proyectorefugio to javafx.fxml;

    exports org.proyectorefugio;
    exports org.proyectorefugio.pruebas;
    opens org.proyectorefugio.pruebas to javafx.fxml;
    exports org.proyectorefugio.controller;
    opens org.proyectorefugio.controller to javafx.fxml;
    exports org.proyectorefugio.view;
    opens org.proyectorefugio.view to javafx.fxml;
    opens org.proyectorefugio.model to javafx.base;
    opens org.proyectorefugio.enums to javafx.base;

}