module br.edu.fesa.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens br.edu.fesa.controller to javafx.fxml;
    exports br.edu.fesa.controller;
    exports br.edu.fesa.model;

}
