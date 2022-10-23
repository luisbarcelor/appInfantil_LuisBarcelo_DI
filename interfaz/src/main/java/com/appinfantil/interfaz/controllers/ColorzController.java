package com.appinfantil.interfaz.controllers;

import com.appinfantil.interfaz.games.Colorz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ColorzController {
    @FXML
    public Label title;
    public ImageView goat;

    @FXML
    public void buttonPressed(ActionEvent event) {
        Button source = (Button) event.getSource();
        Colorz.generateGoat();
        Colorz.playSound(source.getId());
    }
}
