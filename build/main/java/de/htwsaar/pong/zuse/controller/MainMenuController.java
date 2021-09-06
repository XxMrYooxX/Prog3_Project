/*************************************************//**
 *  \brief     Kurzbeschreibung: Class MainMenuController
 *  \details   Controller der MainMenuView
 *  \author    Isabelle Müller
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.controller;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 * Klasse MainMenuController
 * repräsentiert den Controller des Main Menu
 */
public class MainMenuController {

  @FXML
  private Label mm_label_howtoplay;
  @FXML
  private Label mm_label_playnow;
  @FXML
  private Label mm_label_options;

  /**
   * Methode goToHowToPlay
   * - zum Wechseln in die How To Play Scene
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToHowToPlay(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to how to play scene");
    //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
    mm_label_howtoplay.getScene().setRoot(
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/howtoplay.fxml"))));
    //Event abgeschlossen und "verbraucht"
    event.consume();
  }

  /**
   * Methode goToOptions
   * - zum Wechseln in die Options Scene
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToOptions(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to Options scene");
    mm_label_options.getScene().setRoot(FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/options.fxml"))));
    event.consume();
  }

  /**
   * Methode goToPlay
   * - zum Wechseln in die GameMode Scene
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToPlay(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to gamemode scene");
    mm_label_playnow.getScene().setRoot(FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/choosegamemode.fxml"))));
    event.consume();
  }

  /**
   * Methode goToExit
   * - beendet die Anwendung
   * @param event OnMouseClickEvent
   */
  @FXML
  public void goToExit(javafx.scene.input.MouseEvent event) {
    event.consume();
    //Programm wird mit Return Code 0 beendet.
    System.exit(0);
  }
}
