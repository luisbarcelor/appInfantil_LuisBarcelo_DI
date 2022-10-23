package com.appinfantil.interfaz.controllers;

import java.io.IOException;
import java.util.List;

import com.appinfantil.interfaz.App;
import com.appinfantil.interfaz.dao.ConnectionManager;
import com.appinfantil.interfaz.dao.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    public TextField username;
    public PasswordField password;
    public PasswordField confirm;
    public TextField age;
    public Label errors;

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    public void createAccount() throws IOException {
        ConnectionManager.createUser(this);
    }
}