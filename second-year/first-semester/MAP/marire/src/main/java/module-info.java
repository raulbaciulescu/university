module com.example.marire {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.marire to javafx.fxml;
    exports com.example.marire;

    opens com.example.marire.controller to javafx.fxml;
}