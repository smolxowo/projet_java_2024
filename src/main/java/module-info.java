module org.example.projet_java_2024 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projet_java_2024 to javafx.fxml;
    exports org.example.projet_java_2024;
}