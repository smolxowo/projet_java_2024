module org.example.projet_java_2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens org.example.projet_java_2024 to javafx.fxml;
    exports org.example.projet_java_2024;
}