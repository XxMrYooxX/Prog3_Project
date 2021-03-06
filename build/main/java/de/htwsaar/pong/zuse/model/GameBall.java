/*************************************************//**
 *  \brief     Kurzbeschreibung: Class GameBall
 *  \details   Übernimmt die Aufgaben des Balls
 *  \author    Marcel Hesselbach
 *  \author    Jorin Moritz Spiller
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Klasse GameBall
 * repräsentiert den Ball innerhalb des Spiels und dessen Logik
 */
public class GameBall extends Circle {

  private static final int SIZE = 20;
  private static final int initialXSpeed = -6;
  private static final int initialYSpeed = 4;
  private static final int HEIGHT = GameOptions.getGameHeight();
  private static final int WIDTH = GameOptions.getGameWidth();
  private static int xSpeed = -8;
  private static int ySpeed = 6;
  private static Bounds bounds;
  private AnimationTimer animationTimer;

  /**
   * Konstruktor GameBall
   * - erstellt den GameBall und initialisiert seine Größe
   * - erstellen des AnimationTimers
   */
  public GameBall(Bounds bounds) {
    //Festlegen des Balllayouts
    this.setRadius(SIZE);
    this.setFill(Color.WHITE);
    this.setLayoutX((double) WIDTH / 2);
    this.setLayoutY((double) HEIGHT / 2);
    GameBall.bounds = bounds;
    createTimer();
  }


  /**
   * Methode createTimer
   * - erstellt AnimationTimer, welcher als HintergrundThread move() ausführt
   * - startet AnimationTimer
   */
  private void createTimer() {
    animationTimer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        move();
      }
    };
    animationTimer.start();
  }

  /**
   * Methode move
   * - Setzt Ballgeschwindigkeit fest
   * - Kehrt Ball um, sollte dieser die maximale Höhe des Spielfeldes erreicht haben
   */
  private void move() {
    //Wenn Ball Spielfeldhöhe erreicht, Ball umkehren
    if (this.getBoundsInParent().getMaxY() > bounds.getMaxY()
        || this.getBoundsInParent().getMinY() < bounds.getMinY()) {
      ySpeed = -ySpeed;
    }
    this.setTranslateX(this.getTranslateX() - xSpeed);
    this.setTranslateY(this.getTranslateY() - ySpeed);
  }

  /**
   * Methode stopBallAnimation
   * - beendet den AnimationTimer für das Movement des Balls
   */
  public void stopBallAnimation() {
    animationTimer.stop();
  }
  /**
   * Getter des Attributs xSpeed
   * - gibt die Geschwindigkeit des Balls auf der x-Achse zurück
   * @return Geschwindigkeit des Balls auf der x-Achse
   */
  public static int getXSpeed() {
        return xSpeed;
    }

  /**
   * Setter des Attributs xSpeed
   * - setzt die Geschwindigkeit des Balls auf der x-Achse
   * @param xSpeed Geschwindigkeit des Balls auf der x-Achse
   */
  public static void setXSpeed(int xSpeed) {
        GameBall.xSpeed = xSpeed;
    }

  /**
   * Getter des Attributs ySpeed
   * - gibt die Geschwindigkeit des Balls auf der y-Achse zurück
   * @return Geschwindigkeit des Balls auf der y-Achse
   */
  public static int getYSpeed() {
      return ySpeed;
  }

  /**
   * Setter des Attributs ySpeed
   * - setzt die Geschwindigkeit des Balls auf der y-Achse
   * @param ySpeed Geschwindigkeit des Balls auf der y-Achse
   */
  public static void setYSpeed(int ySpeed) {
        GameBall.ySpeed = ySpeed;
    }

  /**
   * Getter des Attributs InitialXSpeed
   * - gibt die Geschwindigkeit des Balls auf der x-Achse zurück
   * @return Geschwindigkeit des Balls auf der x-Achse
   */
  public static int getInitialXSpeed() {
        return initialXSpeed;
    }

  /**
   * Getter des Attributs InitialYSpeed
   * - gibt die Geschwindigkeit des Balls auf der y-Achse zurück
   * @return Geschwindigkeit des Balls auf der y-Achse
   */
  public static int getInitialYSpeed() {
        return initialYSpeed;
    }

}
