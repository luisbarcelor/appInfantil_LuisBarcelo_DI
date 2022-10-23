package com.appinfantil.snake.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.appinfantil.snake.Difficulty;
import com.appinfantil.snake.SnakeGame;

public class DifficultyController {
    @FXML
    public Button easy;
    @FXML
    public Button medium;
    @FXML
    public Button hard;

    @FXML
    public void difficultyEasy() {
        SnakeGame.difficulty = Difficulty.EASY;
        SnakeGame.resetGame();
        Difficulty.getStage().close();
    }
    @FXML
    public void difficultyMedium() {
        SnakeGame.difficulty = Difficulty.MEDIUM;
        SnakeGame.resetGame();
        Difficulty.getStage().close();
    }
    @FXML
    public void difficultyHard() {
        SnakeGame.difficulty = Difficulty.HARD;
        SnakeGame.resetGame();
        Difficulty.getStage().close();
    }
}
