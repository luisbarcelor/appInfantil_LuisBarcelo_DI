package com.appinfantil.interfaz.controllers;


import java.io.IOException;

import com.appinfantil.interfaz.App;
import com.appinfantil.interfaz.dao.ConnectionManager;
import com.appinfantil.snake.exceptions.FXMLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    public TextField username;
    public Label warning;
    public PasswordField password;

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("sign_up");
    }
    @FXML
    private void switchToPostLogin() throws IOException, FXMLException {
        if (ConnectionManager.loginUser()) {
            App.stage.close();
            App.setRoot("post_login");
            App.stage.show();
        }
    }

    @FXML
    public void initialize() {
        ConnectionManager.loginController = this;
    }
}
