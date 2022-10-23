package com.appinfantil.snake;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.appinfantil.snake.exceptions.FXMLException;

import java.io.IOException;

public class Difficulty {
    public static final int EASY = 150;
    public static final int MEDIUM = 100;
    public static final int HARD = 50;
    private static Stage stage;
    public void startWindow() throws FXMLException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("difficulty.fxml"));
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
             throw new FXMLException("FXML FILE NOT FOUND");
        }

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose Difficulty");
        stage.show();
    }
    public static Stage getStage() {
        return stage;
    }
}
