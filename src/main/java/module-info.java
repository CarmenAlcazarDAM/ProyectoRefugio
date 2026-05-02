module org.example.proyectorefugio {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectorefugio to javafx.fxml;
    exports org.example.proyectorefugio;
}