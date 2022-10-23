package com.appinfantil.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class Snake {
    private Point snakeHeadPoint;
    private List<Point> snakeBodyPointList = new ArrayList<>();
    private Image snakeHeadImg = new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeHeadRight.png")));
    private final Image snakeBodyImgHor = new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeBodyHorizontal.png")));
    private final Image snakeBodyImgVer = new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeBodyVertical.png")));
    private Image snakeTailImg = new Image(String.valueOf(getClass().getResource("img/snakeParts/snakeTailRight.png")));
    private int tailIndex;

    public void generateSnake(Background background) {

        for (int i = 0, j = 5; i < 3; i++, j--) {
                snakeBodyPointList.add(new Point(j, background.getROWS() / 2));
        }

        snakeHeadPoint = snakeBodyPointList.get(0);
    }

    public void drawSnake(GraphicsContext gContext, Background background) {
        final double CELL_SIZE = background.getCELL_SIZE();
        tailIndex = snakeBodyPointList.size() - 1;
        gContext.setFill(Color.web(Palette.COLOR.GREEN()));

        gContext.drawImage(snakeHeadImg, snakeHeadPoint.x * CELL_SIZE, snakeHeadPoint.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        for (int i = 1; i < tailIndex; i++) {
            if (snakeHeadPoint.y == snakeBodyPointList.get(i).y) {
                gContext.drawImage(snakeBodyImgHor, snakeBodyPointList.get(i).x * CELL_SIZE, snakeBodyPointList.get(i).y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

            } else if (snakeHeadPoint.x == snakeBodyPointList.get(i).x) {
                gContext.drawImage(snakeBodyImgVer, snakeBodyPointList.get(i).x * CELL_SIZE, snakeBodyPointList.get(i).y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            if (snakeHeadPoint.y != snakeBodyPointList.get(i).y) {
                gContext.drawImage(snakeBodyImgVer, snakeBodyPointList.get(i).x * CELL_SIZE, snakeBodyPointList.get(i).y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

            } else if (snakeHeadPoint.x != snakeBodyPointList.get(i).x) {
                gContext.drawImage(snakeBodyImgHor, snakeBodyPointList.get(i).x * CELL_SIZE, snakeBodyPointList.get(i).y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            gContext.drawImage(snakeTailImg, snakeBodyPointList.get(tailIndex).x * CELL_SIZE, snakeBodyPointList.get(tailIndex).y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public void eatFruit(Fruit fruit, Background background) {
        if (snakeHeadPoint.y == fruit.getFruitPoint().y && snakeHeadPoint.x == fruit.getFruitPoint().x) {
            growSnake();
            fruit.generateFruit(background, this);
        }
    }

    public void growSnake() {
        snakeBodyPointList.add(tailIndex, new Point(snakeBodyPointList.get(tailIndex).x, snakeBodyPointList.get(tailIndex).y));
        SnakeGame.score++;
    }

    public Point getSnakeHeadPoint() {
        return snakeHeadPoint;
    }

    public List<Point> getSnakeBodyPointList() {
        return snakeBodyPointList;
    }

    public int getTailIndex() {
        return tailIndex;
    }

    public void setSnakeBodyPointList(List<Point> snakeBodyPointList) {
        this.snakeBodyPointList = snakeBodyPointList;
    }

    public void setSnakeHeadImg(Image snakeHeadImg) {
        this.snakeHeadImg = snakeHeadImg;
    }

    public void setSnakeTailImg(Image snakeTailImg) {
        this.snakeTailImg = snakeTailImg;
    }
}
