package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameHandler;
import de.htwsaar.pong.zuse.model.GameOptions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameViewController {


    @FXML
    private AnchorPane gs_ap_anchorpane;

    private Scene gameScene;

    //Erschaffen des GameHandlers beim Klick auf Start mit entsprechendem Modus (Single, Multi)
    @FXML
    public void startButton(ActionEvent actionEvent){

        //Parameter für Übergabe an gameHandler festlegen
        Stage gameStage = (Stage) gs_ap_anchorpane.getScene().getWindow();
        AnchorPane gamePane = gs_ap_anchorpane;
        gameScene = gamePane.getScene();

        //Anlegen des GameHandlers, der alles weitere übernimmt
        GameHandler gameHandler = new GameHandler(gameStage, gamePane, gameScene);

        //Methoden zum Aufbau der Gamescene
        gameHandler.createGameSubScene();
        gameHandler.createGameTitle();
        gameHandler.createGameTimer();
        gameHandler.createMenuButton();
        gameHandler.createScoreSubScene();

        //Ändern des Fenstertitels entsprechend dem GameMode
        switch (GameOptions.getGameMode()) {
            case SINGLEPLAYER:
                gameStage.setTitle("Pong Singleplayer | Team Zuse");
                break;
            case MULTIPLAYER:
                gameStage.setTitle("Pong Multiplayer | Team Zuse");
                //Hinzufügen von Listenern, ob Tasten gedrückt werden
                gameHandler.addListeners(gameScene);
                break;


        }
    }

    /* Nicht genutzt??

   public Scene getGameScene() { return gameScene; }

     */
}

