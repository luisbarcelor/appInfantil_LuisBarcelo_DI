package com.appinfantil.interfaz.games;

import com.appinfantil.interfaz.App;
import com.appinfantil.interfaz.controllers.ColorzController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Colorz extends Application {
    public static ColorzController controller;
    private static Scene scene;
    private static Stage stage;

    public Colorz() {
        start(new Stage());
    }

    @Override
    public void start(Stage stage) {
        Colorz.stage = stage;
        scene = new Scene(loadFXML("colorz"));
        stage.setScene(scene);
        stage.setTitle("Colorz");
        stage.setResizable(false);
        stage.show();

        generateGoat();

        stage.setOnCloseRequest(windowEvent -> {
            App.stage.show();
            App.audio.loop(Clip.LOOP_CONTINUOUSLY);
            App.audio.start();
        });
    }

    public static void generateGoat() {
        Image goat;
        int goatNumber = (int) (Math.random() * 10);
        String goatName = "";

        switch (goatNumber) {
            case 1 -> goatName = "amarillo";
            case 2 -> goatName = "azul";
            case 3 -> goatName = "blanco";
            case 4 -> goatName = "marron";
            case 5 -> goatName = "morado";
            case 6 -> goatName = "naranja";
            case 7 -> goatName = "negro";
            case 8 -> goatName = "rojo";
            case 9 -> goatName = "verde";
            default -> goatName = "amarillo";
        }
        goat = new Image(String.valueOf(App.class.getResource("img/cabra/" + goatName + ".png")));
        controller.goat.setImage(goat);
    }

    public static void playSound(String id) {
        URL audioFile = null;

        switch (id) {
            case "amarillo" -> audioFile = App.class.getResource("audio/amarillo.wav");
            case "rojo" -> audioFile = App.class.getResource("audio/rojo.wav");
            case "azul" -> audioFile = App.class.getResource("audio/azul.wav");
            case "verde" -> audioFile = App.class.getResource("audio/verde.wav");
            case "naranja" -> audioFile = App.class.getResource("audio/naranja.wav");
            case "morado" -> audioFile = App.class.getResource("audio/morado.wav");
            case "blanco" -> audioFile = App.class.getResource("audio/blanco.wav");
            case "negro" -> audioFile = App.class.getResource("audio/negro.wav");
            case "marron" -> audioFile = App.class.getResource("audio/marron.wav");
        }

        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e1) {
            System.out.println("ERROR! EL ARCHIVO DE AUDIO NO ES SOPORTADO");
        } catch (IOException e2) {
            System.out.println("ERROR! NO SE ENCONTRO EL ARCHIVO DE AUDIO");
        } catch (LineUnavailableException e3) {
            System.out.println("ERROR! NO SE PUDO CREAR EL CLIP DE AUDIO");
        }
    }


    public static Parent loadFXML(String fxml) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            System.out.println("ERROR WHILE LOADING FXML");
        }

        controller = loader.getController();

        return parent;
    }

}
