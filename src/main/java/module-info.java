module org.example.projet_java_2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens org.example.projet_java_2024 to javafx.fxml, com.fasterxml.jackson.databind;
    opens org.example.projet_java_2024.frontend to javafx.base, javafx.fxml;
    opens org.example.projet_java_2024.backend to javafx.fxml, com.fasterxml.jackson.databind, javafx.base;

    exports org.example.projet_java_2024;
}