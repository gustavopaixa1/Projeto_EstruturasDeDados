module com.example.projetoed {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.projetoed to javafx.fxml;
    exports com.example.projetoed;
    exports com.example.projetoed.implementations;
    opens com.example.projetoed.implementations to javafx.fxml;
    exports com.example.projetoed.tools;
    opens com.example.projetoed.tools to javafx.fxml;
}