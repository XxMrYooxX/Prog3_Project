/*************************************************//**
 *  \brief     Kurzbeschreibung: Class HowToPlayController
 *  \details   Controller der HowtoPlay Scene
 *  \author    Maram Albaali
 *  \author    Amin Yousif
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.controller;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 * Klasse HowToPlayController
 * repräsentiert den Controller zur HowToPlay Scene
 */
public class HowToPlayController {

  @FXML
  private Label htop_label_back;

  //Methode zum Wechseln in die Main Menu Scene

  /**
   * Methode goToMainMenu
   * - zum Wechseln in die Main Menu Scene
   * @param event OnMouseClickEvent
   * @throws IOException Generic IOException
   */
  @FXML
  public void goToMainMenu(javafx.scene.input.MouseEvent event) throws IOException {
    System.out.println("go to main menu");
    //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
    htop_label_back.getScene().setRoot(FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
    //Event abgeschlossen und "verbraucht"
    event.consume();
  }

}
