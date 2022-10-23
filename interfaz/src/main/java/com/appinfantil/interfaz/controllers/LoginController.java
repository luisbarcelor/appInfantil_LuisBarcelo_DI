package com.appinfantil.interfaz.controllers;

import java.io.IOException;

import com.appinfantil.interfaz.App;
import com.appinfantil.snake.exceptions.FXMLException;
import javafx.fxml.FXML;

public class LoginController {
    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("sign_up");
    }
    @FXML
    private void switchToPostLogin() throws IOException, FXMLException {
        App.stage.close();
        App.setRoot("post_login");
        App.stage.show();
    }
}
