package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameOptions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class ChooseGameModeController {

    //Initiliasieren der Label, damit diese zum Finden der Scene verwendet werden können (siehe unten)
    //Labelnamen entsprechen den ID's , welche in der FXML Datei festgelegt wurden (Pflicht)

    @FXML
    private Label gm_label_back;
    @FXML
    private Label gm_label_singleplayer;
    @FXML
    private Label gm_label_multiplayer;

    @FXML
    public void goToSingleplayer(javafx.scene.input.MouseEvent event) throws IOException{ //Reagiert auf OnMouseClick Event, festgelegt in der options.fxml)
        System.out.println("go to singeplayer");
        //Setzen der Optionen auf Singleplayer
        GameOptions.setGameMode(GameOptions.GameMode.SINGLEPLAYER);
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        gm_label_singleplayer.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gamescene.fxml"))));
        event.consume();
    }

    @FXML
    public void goToMultiplayer(javafx.scene.input.MouseEvent event) throws IOException{ //Reagiert auf OnMouseClick Event, festgelegt in der options.fxml)
        System.out.println("go to multiplayer");
        //Setzen der Optionen auf Multiplayer
        GameOptions.setGameMode(GameOptions.GameMode.MULTIPLAYER);
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        gm_label_multiplayer.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gamescene.fxml"))));
        event.consume();
    }

    @FXML
    public void goToMainMenu(javafx.scene.input.MouseEvent event) throws IOException { //Reagiert auf OnMouseClick Event, festgelegt in der options.fxml)
        System.out.println("go to main menu");
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        gm_label_back.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
        event.consume();
    }
}
