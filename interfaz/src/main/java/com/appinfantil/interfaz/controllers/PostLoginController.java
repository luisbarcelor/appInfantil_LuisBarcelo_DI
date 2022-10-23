package com.appinfantil.interfaz.controllers;

import com.appinfantil.interfaz.App;
import com.appinfantil.interfaz.games.Colorz;
import com.appinfantil.interfaz.games.Snake;
import com.appinfantil.snake.exceptions.FXMLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PostLoginController {
    @FXML
    public Button snake;
    @FXML
    public Button colorz;

    public void launchSnake() throws FXMLException {
        App.audio.stop();
        App.stage.close();
        new Snake();
    }

    public void launchColorz() {
        App.audio.stop();
        App.stage.close();
        new Colorz();
    }

    public void logout() throws IOException {
        App.stage.close();
        App.setRoot("login");
        App.stage.show();
    }
}
