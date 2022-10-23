package com.appinfantil.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Background {
    private final Color COLOR_1 = Color.web("#277CB9");
    public final Color COLOR_2 = Color.web("#53A2DB");
    private final double CANVAS_HEIGHT;
    private final double CANVAS_WIDTH;
    private final int ROWS;
    private final int COLUMNS;
    private final double CELL_SIZE;

    public Background(double canvasHeight, double canvasWidth, int canvasSize) {
        CANVAS_WIDTH = canvasWidth;
        CANVAS_HEIGHT = canvasHeight;
        ROWS = canvasSize;
        COLUMNS = ROWS;
        CELL_SIZE = (CANVAS_WIDTH + CANVAS_HEIGHT) / 2 / ROWS;
    }

    public void drawBackground(GraphicsContext gContext) {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {

                if ((i + j) % 2 == 0) {
                    gContext.setFill(COLOR_1);
                } else {
                    gContext.setFill(COLOR_2);
                }

                gContext.fillRect(i * CELL_SIZE,j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

    }

    public double getCANVAS_HEIGHT() {
        return CANVAS_HEIGHT;
    }

    public double getCANVAS_WIDTH() {
        return CANVAS_WIDTH;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public double getCELL_SIZE() {
        return CELL_SIZE;
    }
}
