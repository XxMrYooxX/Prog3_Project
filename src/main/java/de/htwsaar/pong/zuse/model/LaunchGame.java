/*************************************************//**
 *  \brief     Kurzbeschreibung: Class LaunchGame
 *  \details   Startet Application
 *  \author    Maram Albaali
 *  \author    Amin Yousif
 *  \author    Isabelle Müller
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.model;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasse LaunchGame
 * repräsentiert die Implementierung der Application
 */
public class LaunchGame extends Application {

  /**
   * Methode launchGame
   * - führt launch Methode von Application aus
   * @param args args an Application
   */
  public static void launchGame(String[] args) {
    launch(args);
  }

  /**
   * Methode start
   * - Override der Application start-Methode
   * - setzt benötigte Parameter der Stage und lädt das Main Menu (fxml)
   * @param stage Stage
   * @throws Exception Generic Exception
   */
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
        .getResource("/mainmenu.fxml")));
    stage.setScene(new Scene(root, 1280, 720));
    stage.setResizable(false);
    stage.setMaximized(false);
    stage.setTitle("Pong by Team Zuse");
    stage.show();
  }
}
