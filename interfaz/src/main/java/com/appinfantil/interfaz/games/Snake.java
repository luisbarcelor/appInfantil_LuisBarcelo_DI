package com.appinfantil.interfaz.games;

import com.appinfantil.interfaz.App;
import com.appinfantil.snake.SnakeGame;
import com.appinfantil.snake.exceptions.FXMLException;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;

public class Snake {
    public Snake() throws FXMLException {
        Stage stage = new Stage();
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.start(stage);
        SnakeGame.resetGame();

        stage.setOnCloseRequest(windowEvent -> {
            App.stage.show();
            App.audio.loop(Clip.LOOP_CONTINUOUSLY);
            App.audio.start();
        });
    }
}
