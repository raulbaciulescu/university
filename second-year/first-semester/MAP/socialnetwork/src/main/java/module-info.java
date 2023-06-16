module com.example.socnet {

    requires com.dlsc.formsfx;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires org.jetbrains.annotations;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.pdfbox;

    opens com.example.socnet to javafx.fxml;
    opens com.example.socnet.controller_fx;

    exports com.example.socnet;
    exports com.example.socnet.controller_fx;
    exports com.example.socnet.domain.model;
}