package com.appinfantil.snake.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.appinfantil.snake.Background;
import com.appinfantil.snake.controllers.SnakeGameController;
import java.awt.*;

public class ResponsiveDesign {
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static void makeResponsive(SnakeGameController controller) {
        if (SCREEN_SIZE.width >= 1920 && SCREEN_SIZE.height >= 1080) {
            controller.vBox.setPrefHeight(925.0);
            controller.vBox.setPrefWidth(850.0);
            controller.hBox.setMaxWidth(850);
            controller.buttonContainer.setMaxWidth(600);
            controller.buttonContainer.setPrefWidth(600);
            controller.canvas.setHeight(800.0);
            controller.canvas.setWidth(800.0);
        }
    }

    public static void responsiveGameOver(GraphicsContext gContext, Background background) {
        if (SCREEN_SIZE.width >= 1920 && SCREEN_SIZE.height >= 1080) {
            gContext.setFill(Color.RED);
            gContext.setFont(new Font("SansSerif", 80));
            gContext.fillText("GAME OVER", background.getCOLUMNS() / 5.0 * background.getCELL_SIZE(), background.getROWS() / 1.85 * background.getCELL_SIZE());
        } else {
            gContext.setFill(Color.RED);
            gContext.setFont(new Font("SansSerif", 60));
            gContext.fillText("GAME OVER", background.getCOLUMNS() / 5.7 * background.getCELL_SIZE(), background.getROWS() / 1.85 * background.getCELL_SIZE());
        }
    }
}
