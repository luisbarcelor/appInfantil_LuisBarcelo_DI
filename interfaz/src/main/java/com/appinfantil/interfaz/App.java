package com.appinfantil.interfaz;

import com.appinfantil.interfaz.dao.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static Scene scene;
    public static Stage stage;
    public static Clip audio;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML("login"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        backgorundSound();
    }

    public static void backgorundSound() {
        URL file = App.class.getResource("audio/twinkle.wav");
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(file)) {
            audio = AudioSystem.getClip();
            audio.open(audioIn);
            audio.loop(Clip.LOOP_CONTINUOUSLY);
            audio.start();

        } catch (Exception e) {
            System.out.println("ERROR AL REPRODUCIR MUSICA DE FONDO");
        }
    }


    public static void setRoot(String fxml) throws IOException {
     scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}