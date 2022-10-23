module com.appinfantil.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports com.appinfantil.snake;
    exports com.appinfantil.snake.controllers;
    exports com.appinfantil.snake.exceptions;
    exports com.appinfantil.snake.util;
    opens com.appinfantil.snake to javafx.fxml;
}
