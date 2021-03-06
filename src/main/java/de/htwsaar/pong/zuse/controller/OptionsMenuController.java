/*************************************************//**
 *  \brief     Kurzbeschreibung: Class OptionsMenuController
 *  \details   Controller der OptionsView
 *  \author    Marcel Hesselbach
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

public class OptionsMenuController {

  @FXML
  private Label o_label_back;
  @FXML
  private Label o_label_pone_up;
  @FXML
  private Label o_label_pone_down;
  @FXML
  private Label o_label_ptwo_up;
  @FXML
  private Label o_label_ptwo_down;
  @FXML
  private Label o_label_keyassigned;

  /**
   * Methode initialize
   * - Initialisieren der Button Texte aus den Options
   */
  @FXML
  public void initialize() {
    o_label_pone_up.setText("ausgewählter Button: " + GameOptions.getKeyCodePoneUp().toString());
    o_label_pone_down.setText(
        "ausgewählter Button: " + GameOptions.getKeyCodePoneDown().toString());
    o_label_ptwo_up.setText("ausgewählter Button: " + GameOptions.getKeyCodePtwoUp().toString());
    o_label_ptwo_down.setText(
        "ausgewählter Button: " + GameOptions.getKeyCodePtwoDown().toString());
  }

  /**
   * Methode goToMainMenu
   * - reagiert auf OnMouseClick Event, festgelegt in der options.fxml
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToMainMenu(javafx.scene.input.MouseEvent event) throws IOException {
    if (GameOptions.doubleKeyAssignment()) {
      o_label_keyassigned.setVisible(true);
    } else {
      //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
      o_label_back.setOnKeyPressed(e -> System.out.println("Debug ins Menu"));
      o_label_back.getScene().setOnKeyPressed(e -> {
      });
      System.out.println(o_label_back.getScene());
      o_label_back.getScene().setRoot(
          FXMLLoader.load(Objects.requireNonNull(getClass().getResource(convertResPath("/mainmenu.fxml")))));
    }
    //Event abgeschlossen und "verbraucht"
    event.consume();
  }

  /**
   * Methode setPoneKeyDown
   * setzt Spieler 1 Key zur Bewegung: oben
   * @param event MouseEvent
   */
  @FXML
  public void setPoneKeyUp(javafx.scene.input.MouseEvent event) {
    o_label_pone_up.getScene().setOnKeyPressed(e -> {
      System.out.println("setPoneKeyUp");
      System.out.println(e.getCode());
      o_label_pone_up.setText("ausgewählter Button: " + e.getCode());
      GameOptions.setKeyCodePoneUp(e.getCode());
      e.consume();
    });
    o_label_keyassigned.setVisible(false);
    event.consume();
  }

  /**
   * Methode setPoneKeyDown
   * setzt Spieler 1 Key zur Bewegung: unten
   * @param event MouseEvent
   */
  @FXML
  public void setPoneKeyDown(javafx.scene.input.MouseEvent event) {
    o_label_pone_down.getScene().setOnKeyPressed(e -> {
      System.out.println("setPoneKeyDown");
      System.out.println(e.getCode());
      o_label_pone_down.setText("ausgewählter Button: " + e.getCode());
      GameOptions.setKeyCodePoneDown(e.getCode());
    });

    o_label_keyassigned.setVisible(false);
    event.consume();
  }

  /**
   * Methode setPtwoKeyUp
   * setzt Spieler 2 Key zur Bewegung: oben
   * @param event MouseEvent
   */
  @FXML
  public void setPtwoKeyUp(javafx.scene.input.MouseEvent event) {
    o_label_ptwo_up.getScene().setOnKeyPressed(e -> {
      System.out.println("setPtwoKeyUp");
      System.out.println(e.getCode());
      o_label_ptwo_up.setText("ausgewählter Button: " + e.getCode());
      GameOptions.setKeyCodePtwoUp(e.getCode());
      e.consume();
    });
    o_label_keyassigned.setVisible(false);
    event.consume();
  }

  /**
   * Methode setPtwoKeyDown
   * setzt Spieler 2 Key zur Bewegung: unten
   * @param event MouseEvent
   */
  @FXML
  public void setPtwoKeyDown(javafx.scene.input.MouseEvent event) {
    o_label_ptwo_down.getScene().setOnKeyPressed(e -> {
      System.out.println("setPtwoKeyDown");
      System.out.println(e.getCode());
      o_label_ptwo_down.setText("ausgewählter Button: " + e.getCode());
      GameOptions.setKeyCodePtwoDown(e.getCode());
    });
    o_label_keyassigned.setVisible(false);
    event.consume();
  }

  /**
   * Methode convertResPath
   * Konvertiert Path, sodass dieser durch festgelegte Ressourcen-Paths und durch manuelles Compilen genutzt werden kann
   * @param path ursprünglicher Path zum Ressourcen Ordner
   * @return neuer Path
   */
  public static String convertResPath (String path) {
    path = "..\\view\\" + path.substring(1);
    return path;
  }
}
