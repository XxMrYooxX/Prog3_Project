package de.htwsaar.pong.zuse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class HowToPlayController {

    //Initiliasieren der Label, damit diese zum Finden der Scene verwendet werden können (siehe unten)
    //Labelnamen entsprechen den ID's , welche in der FXML Datei festgelegt wurden (Pflicht)
    @FXML
    private Label htop_label_back;

    //Methode zum Wechseln in die Main Menu Scene
    @FXML
    public void goToMainMenu(javafx.scene.input.MouseEvent event) throws IOException { //Reagiert auf OnMouseClick Event, festgelegt in der options.fxml)
        System.out.println("go to main menu");
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        htop_label_back.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
        //Event abgeschlossen und "verbraucht"
        event.consume();
    }

}
