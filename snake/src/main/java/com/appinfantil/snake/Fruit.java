package com.appinfantil.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fruit {
    private final String[] fruitList = new String[] {
        "img/apple.png",
        "img/coconut.png",
        "img/cherries.png",
        "img/orange.png",
        "img/pineapple.png",
        "img/watermelon.png",
        "img/strawberry.png",
        "img/blueberry.png"
    };
    private Point fruitPoint;
    private Image fruitImage;

    public void generateFruit(Background background, Snake snake) {
        int fruitIndex = (int) (Math.random() * fruitList.length);
        fruitPoint = new Point((int) (Math.random() * background.getCOLUMNS()), (int) (Math.random() * background.getROWS()));

        for (int i = 0; i < snake.getSnakeBodyPointList().size(); i++) {
            if (snake.getSnakeBodyPointList().get(i).x == fruitPoint.x && snake.getSnakeBodyPointList().get(i).y == fruitPoint.y) {
                generateFruit(background, snake);
            }
        }
        fruitImage = new Image(String.valueOf(getClass().getResource(fruitList[fruitIndex])));
    }

    public void drawFruit(GraphicsContext gContext, Background background) {
        final double CELL_SIZE = background.getCELL_SIZE();
        gContext.drawImage(fruitImage, fruitPoint.x * CELL_SIZE, fruitPoint.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public Point getFruitPoint() {
        return fruitPoint;
    }
}
