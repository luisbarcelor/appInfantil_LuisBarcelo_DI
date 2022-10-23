package com.appinfantil.snake;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SnakeMovementHandler {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    private final Scene scene;
    public int currentDir = 1;


    public SnakeMovementHandler(Scene scene) {
        this.scene = scene;
    }

    public void handleSnakeMovement() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode key = event.getCode();
                if (key == KeyCode.D || key == KeyCode.RIGHT) {
                    if (currentDir != LEFT) {
                        currentDir = RIGHT;
                    }
                } else if (key == KeyCode.W || key == KeyCode.UP) {
                    if (currentDir != DOWN) {
                        currentDir = UP;
                    }
                } else if (key == KeyCode.A || key == KeyCode.LEFT) {
                    if (currentDir != RIGHT) {
                        currentDir = LEFT;
                    }
                } else if (key == KeyCode.S || key == KeyCode.DOWN) {
                    if (currentDir != UP) {
                        currentDir = DOWN;
                    }
                }
            }
        });
    }

    public void moveUp(Snake snake) {
        snake.setSnakeHeadImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeHeadUp.png"))));
        snake.getSnakeHeadPoint().y--;

        if (snake.getSnakeBodyPointList().get(snake.getTailIndex()).x == snake.getSnakeBodyPointList().get(snake.getTailIndex() - 1).x) {
            snake.setSnakeTailImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeTailUp.png"))));
        }
    }
    public void moveRight(Snake snake) {
        snake.setSnakeHeadImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeHeadRight.png"))));
        snake.getSnakeHeadPoint().x++;

        if (snake.getSnakeBodyPointList().get(snake.getTailIndex()).y == snake.getSnakeBodyPointList().get(snake.getTailIndex() - 1).y) {
            snake.setSnakeTailImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeTailRight.png"))));
        }
    }
    public void moveDown(Snake snake) {
        snake.setSnakeHeadImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeHeadDown.png"))));
        snake.getSnakeHeadPoint().y++;

        if (snake.getSnakeBodyPointList().get(snake.getTailIndex()).x == snake.getSnakeBodyPointList().get(snake.getTailIndex() - 1).x) {
            snake.setSnakeTailImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeTailDown.png"))));
        }
    }
    public void moveLeft(Snake snake) {
        snake.setSnakeHeadImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeHeadLeft.png"))));
        snake.getSnakeHeadPoint().x--;

        if (snake.getSnakeBodyPointList().get(snake.getTailIndex()).y == snake.getSnakeBodyPointList().get(snake.getTailIndex() - 1).y) {
            snake.setSnakeTailImg(new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeTailLeft.png"))));
        }
    }
}
