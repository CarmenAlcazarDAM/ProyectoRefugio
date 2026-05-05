module org.example.proyectorefugio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;


    opens org.proyectorefugio to javafx.fxml;
    exports org.proyectorefugio;
}