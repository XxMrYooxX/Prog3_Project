/*************************************************//**
 *  \brief     Kurzbeschreibung: Class ChooseGameModeController
 *  \details   Controller zur View ChooseGameMode
 *  \author    Jorin Moritz Spiller
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameOptions;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 * Klasse ChooseGameModeController
 * repräsentiert den Controller der GameMode Auswahl
 */
public class ChooseGameModeController {

  @FXML
  private Label gm_label_back;
  @FXML
  private Label gm_label_singleplayer;
  @FXML
  private Label gm_label_multiplayer;

  /**
   * Methode goToSingleplayer
   * - reagiert auf OnMouseClick Event, festgelegt in der options.fxml
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToSingleplayer(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to Singleplayer");
    //Setzen der Optionen auf Singleplayer
    GameOptions.setGameMode(GameOptions.GameMode.SINGLEPLAYER);
    //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
    gm_label_singleplayer.getScene().setRoot(FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/gamescene.fxml"))));
    event.consume();
  }

  /**
   * Methode goToMultiplayer
   * - reagiert auf OnMouseClick Event, festgelegt in der options.fxml
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToMultiplayer(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to Multiplayer");
    //Setzen der Optionen auf Multiplayer
    GameOptions.setGameMode(GameOptions.GameMode.MULTIPLAYER);
    //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
    gm_label_multiplayer.getScene().setRoot(FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/gamescene.fxml"))));
    event.consume();
  }

  /**
   * Methode goToMainMenu
   * - reagiert auf OnMouseClick Event, festgelegt in der options.fxml
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToMainMenu(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to main menu");
    //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
    gm_label_back.getScene().setRoot(FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
    event.consume();
  }
}
