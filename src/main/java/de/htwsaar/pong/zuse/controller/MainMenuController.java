package de.htwsaar.pong.zuse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {

    //Initiliasieren der Label, damit diese zum Finden der Scene verwendet werden können (siehe unten)
    //Labelnamen entsprechen den ID's , welche in der FXML Datei festgelegt wurden (Pflicht)
    @FXML
    private Label mm_label_howtoplay;
    @FXML
    private Label mm_label_playnow;
    @FXML
    private Label mm_label_options;

    //Methode zum Wechseln in die How To Play Scene
    @FXML
    public void goToHowToPlay(javafx.scene.input.MouseEvent event) throws IOException { //Reagiert auf OnMouseClick Event, festgelegt in der mainmenu.fxml)
        System.out.println("go to how to play scene");
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        mm_label_howtoplay.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/howtoplay.fxml"))));
        //Event abgeschlossen und "verbraucht"
        event.consume();
    }

    //Exakt wie goToHowToPlay (siehe Kommentare oben)
    @FXML
    public void goToOptions(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("go to Options scene");
        mm_label_options.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/options.fxml"))));
        event.consume();
    }

    //Exakt wie goToHowToPlay (siehe Kommentare oben)
    @FXML
    public void goToPlay(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("go to gamemode scene");
        mm_label_playnow.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/choosegamemode.fxml"))));
        event.consume();
    }

    @FXML
    public void goToExit(javafx.scene.input.MouseEvent event) {
        event.consume();
        //Programm wird mit Return Code 0 beendet.
        System.exit(0);
    }
}
