package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameHandler;
import de.htwsaar.pong.zuse.model.GameOptions;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller Class für GameView
 *
 * @version 1.1
 */
public class GameViewController {
    /**
     * AnchorPane der Scene
     */
    @FXML
    private AnchorPane gs_ap_anchorPane;

    /**
     * StartButton des Spiels
     * Erschaffen des GameHandlers beim Klick auf Start mit entsprechendem Modus (Single, Multi)
     *
     */
    //Erschaffen des GameHandlers beim Klick auf Start mit entsprechendem Modus (Single, Multi)
    @FXML
    public void startButton(){

        System.out.println(GameOptions.getKeyCodePoneUp());
        System.out.println(GameOptions.getKeyCodePoneDown());
        System.out.println(GameOptions.getKeyCodePtwoUp());
        System.out.println(GameOptions.getKeyCodePtwoDown());

        //Parameter für Übergabe an gameHandler festlegen
        Stage gameStage = (Stage) gs_ap_anchorPane.getScene().getWindow();
        AnchorPane gamePane = gs_ap_anchorPane;

        Scene gameScene = gamePane.getScene();

        //Anlegen des GameHandlers, der alles weitere übernimmt
        GameHandler gameHandler = new GameHandler(gameStage, gamePane, gameScene);

        //Methoden zum Aufbau der Game Scene
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
                //Hinzufügen von Listeners, ob Tasten gedrückt werden
                gameHandler.addListeners(gameScene);
                break;
        }
    }
}

