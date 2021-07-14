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
    @FXML
    private Button gs_button_start;

    private GameHandler gameHandler;

    private Stage previousStage;

    private Stage gameStage;
    private AnchorPane gamePane;

    private Stage menuStage;

    //Styles
    private static String BACKGROUNDBLACKSTYLE = "-fx-background-color: BLACK;";
    public static int GAMEVIEWHEIGHT = 720;
    public static int GAMEVIEWWIDTH = 1280;

    private Scene gameScene;


    @FXML
    public void startButton(ActionEvent actionEvent){

        gameStage = (Stage) gs_ap_anchorpane.getScene().getWindow();
        gamePane = gs_ap_anchorpane;
        gameScene = gamePane.getScene();

        gamePane.setStyle(BACKGROUNDBLACKSTYLE);
        gameHandler = new GameHandler(gameStage, gamePane, gameScene);
        gameHandler.createGameSubScene(gameScene, gamePane);
        gameHandler.createScoreSubScene(gamePane);
        gameHandler.createGameTimer();
        gameHandler.createGameTitle(gamePane);
        gameHandler.createMenuButton(gamePane);

        switch (GameOptions.getGameMode()) {
            case SINGLEPLAYER:
                gameStage.setTitle("Pong Singleplayer | Team Zuse");
                break;
            case MULTIPLAYER:
                gameStage.setTitle("Pong Multiplayer | Team Zuse");
                gameHandler.addListeners(gameScene);
                break;


        }
    }

    public Scene getGameScene() { return gameScene; }

}

