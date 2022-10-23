package com.appinfantil.snake.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.appinfantil.snake.SnakeGame;
import com.appinfantil.snake.exceptions.FXMLException;
import com.appinfantil.snake.util.ResponsiveDesign;

public class SnakeGameController {
    @FXML
    public Label score;
    @FXML
    public VBox vBox;
    @FXML
    public HBox hBox;
    @FXML
    public FlowPane buttonContainer;
    @FXML
    public Button difficulty;
    @FXML
    public Button reset;
    @FXML
    public Canvas canvas;

    @FXML
    public void initialize() {
        ResponsiveDesign.makeResponsive(this);
    }

    @FXML
    public void difficultyAction() throws FXMLException {
        SnakeGame.changeDifficulty();
    }

    @FXML
    public void resetAction() {
        SnakeGame.resetGame();
    }
}
