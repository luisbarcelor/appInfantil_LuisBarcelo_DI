package com.appinfantil.snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.appinfantil.snake.controllers.SnakeGameController;
import com.appinfantil.snake.exceptions.FXMLException;
import com.appinfantil.snake.util.ResponsiveDesign;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles all game components and puts them together to make the Snake Game.
 * It creates the main interface of the application.
 * @author Luis Barcelo
 */

public class SnakeGame extends Application {
    /** Stores the game's score */
    public static int score;
    /** Game Over status */
    public static boolean gameOver = false;
    /** Sets the difficulty level */
    public static int difficulty = Difficulty.MEDIUM;
    private Scene scene;
    private static GraphicsContext gContext;
    private static SnakeGameController primaryController;
    private static SnakeMovementHandler smm;
    private static Timeline animation;
    private static Background background;
    private static Fruit fruit;
    private static Snake snake;
    private static final Image favicon = new Image(String.valueOf(SnakeGame.class.getResource("img/apple.png")));

    /**
     * This method overrides the "start()" method from Application class. It is responsible for displaying all game
     * components, animation and interface.
     * @param stage The main stage of the application.
     * @throws FXMLException This exception is thrown if the needed FXML file to load all the nodes is missing.
     */
    @Override
    public void start(Stage stage) throws FXMLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("snakeGame.fxml"));

        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            throw new FXMLException("FXML FILE NOT FOUND");
        }

        primaryController = loader.getController();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(favicon);
        stage.setTitle("Snake");
        stage.show();

        gContext = primaryController.canvas.getGraphicsContext2D();

        background = new Background(primaryController.canvas.getHeight(), primaryController.canvas.getWidth(), 22);

        snake = new Snake();
        snake.generateSnake(background);

        fruit = new Fruit();
        fruit.generateFruit(background, snake);

        animation = new Timeline(new KeyFrame(Duration.millis(difficulty), e -> runGame()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        smm = new SnakeMovementHandler(scene);
        smm.handleSnakeMovement();

        runGame();
    }

    /**
     * This method puts together all snake game components and is executed periodically within a timeline
     * to animate the game.
     */
    public static void runGame() {
        background.drawBackground(gContext);
        snake.drawSnake(gContext, background);
        moveSnake();
        fruit.drawFruit(gContext, background);
        snake.eatFruit(fruit, background);
        primaryController.score.setText("Score: " + score);
        gameOver();
    }

    /**
     * The method responsible for the snake's movement, it makes the snake move towards different directions
     * (Up, Down, Left and Right) on the board.
     */
    public static void moveSnake() {
        for (int i = snake.getSnakeBodyPointList().size() - 1; i >= 1; i--) {
            snake.getSnakeBodyPointList().get(i).x = snake.getSnakeBodyPointList().get(i - 1).x;
            snake.getSnakeBodyPointList().get(i).y = snake.getSnakeBodyPointList().get(i - 1).y;
        }

        switch (smm.currentDir) {
            case SnakeMovementHandler.UP -> smm.moveUp(snake);
            case SnakeMovementHandler.RIGHT -> smm.moveRight(snake);
            case SnakeMovementHandler.DOWN -> smm.moveDown(snake);
            case SnakeMovementHandler.LEFT -> smm.moveLeft(snake);
        }
    }

    /**
     * A method that sets the status of the gameOver field when certain conditions are met.
     */
    public static void gameOver() {
        if (snake.getSnakeHeadPoint().x < 0 || snake.getSnakeHeadPoint().y < 0 || snake.getSnakeHeadPoint().x * background.getCELL_SIZE() >= background.getCANVAS_WIDTH() || snake.getSnakeHeadPoint().y * background.getCELL_SIZE() >= background.getCANVAS_HEIGHT()) {
            gameOver = true;
        }

        for (int i = 1; i < snake.getSnakeBodyPointList().size(); i++) {
            if (snake.getSnakeHeadPoint().x == snake.getSnakeBodyPointList().get(i).x && snake.getSnakeHeadPoint().y == snake.getSnakeBodyPointList().get(i).y) {
                gameOver = true;
                break;
            }
        }

        if (gameOver) {
            ResponsiveDesign.responsiveGameOver(gContext, background);
            animation.stop();
        }
    }

    /**
     * This method resets the game to its starting position.
     */
    public static void resetGame() {
        gameOver = false;
        score = 0;
        snake.setSnakeBodyPointList(new ArrayList<>());
        snake.setSnakeHeadImg(new Image(String.valueOf(SnakeGame.class.getResource("img/snakeParts/snakeHeadRight.png"))));
        snake.setSnakeTailImg(new Image(String.valueOf(SnakeGame.class.getResource("img/snakeParts/snakeTailRight.png"))));
        smm.currentDir = 1;

        background.drawBackground(gContext);
        snake.generateSnake(background);
        snake.drawSnake(gContext, background);
        fruit.generateFruit(background, snake);
        fruit.drawFruit(gContext, background);
        primaryController.score.setText("Score: " + score);
        animation.stop();
        animation = new Timeline(new KeyFrame(Duration.millis(difficulty), e -> runGame()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    /**
     * This method instantiates an object from Difficulty class that displays the difficulty functionality window.
     * @throws FXMLException This exception is thrown if the needed FXML file to load all the nodes is missing.
     */
    public static void changeDifficulty() throws FXMLException {
        new Difficulty().startWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}