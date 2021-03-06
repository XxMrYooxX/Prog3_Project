/*************************************************//**
 *  \brief     Kurzbeschreibung: Class GameViewController
 *  \details   Controller Klasse für die GameView
 *  \author    Jorin Moritz Spiller
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameHandler;
import de.htwsaar.pong.zuse.model.GameOptions;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

  @FXML
  private Button gs_button_start;

  @FXML
  public void initialize() {
    gs_button_start.setFocusTraversable(false);
  }

  /**
   * StartButton des Spiels
   * Erschaffen des GameHandlers beim Klick auf Start mit entsprechendem Modus (Single, Multi)
   *
   */
  @FXML
  public void startButton() {

    System.out.println(GameOptions.getKeyCodePoneUp());
    System.out.println(GameOptions.getKeyCodePoneDown());
    System.out.println(GameOptions.getKeyCodePtwoUp());
    System.out.println(GameOptions.getKeyCodePtwoDown());

    //Parameter für Übergabe an gameHandler festlegen
    Stage gameStage = (Stage) gs_ap_anchorPane.getScene().getWindow();
    AnchorPane gamePane = gs_ap_anchorPane;

    Scene gameScene = gamePane.getScene();

    //Anlegen des GameHandlers, der alles Weitere übernimmt
    GameHandler gameHandler = new GameHandler(gameStage, gamePane, gameScene);

    //Methoden zum Aufbau der GameScene
    gameHandler.createGameSubScene();
    gameHandler.createGameTitle();
    gameHandler.createGameTimer();
    gameHandler.createMenuButton();
    gameHandler.createScoreSubScene();
    gameHandler.keyListeners(gameScene);
    //Ändern des Fenstertitels entsprechend dem GameMode
    switch (GameOptions.getGameMode()) {
      case SINGLEPLAYER:
        gameStage.setTitle("Pong Singleplayer | Team Zuse");
        break;
      case MULTIPLAYER:
        gameStage.setTitle("Pong Multiplayer | Team Zuse");
        break;
    }
  }
}

