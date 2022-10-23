module com.appinfantil.interfaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.appinfantil.snake;
    requires java.validation;
    requires org.hibernate.validator;
    requires java.sql;
    requires mysql.connector.java;

    exports com.appinfantil.interfaz;
    opens com.appinfantil.interfaz to javafx.fxml;
    exports com.appinfantil.interfaz.controllers;
    opens com.appinfantil.interfaz.controllers to javafx.fxml;
    exports com.appinfantil.interfaz.games;
    opens com.appinfantil.interfaz.games to javafx.fxml;
    exports com.appinfantil.interfaz.dao;
    opens com.appinfantil.interfaz.dao to org.hibernate.validator;
}
