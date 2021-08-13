/*************************************************//**
 *  \brief     Kurzbeschreibung: Class GameButton
 *  \details   Spezieller Button für PongGame
 *  \author    Maram Albaali
 *  \author    Amin Yousif
 *  \version   1.0
 ***********************************************/

package de.htwsaar.pong.zuse.model;

import javafx.scene.control.Button;

/**
 * Klasse GameButton
 * repräsentiert einen Knopf im Spiel mit gewissen Attributen
 */
public class GameButton extends Button {

  /**
   * Konstruktor GameButton
   * - erzeugt ein GameButton mit gewählten Eigenschaften
   * @param buttonText Text, welcher im Button angezeigt wird
   * @param x Lokation auf der x-Achse
   * @param y Höhe auf der y-Achse
   */
  public GameButton(String buttonText, int x, int y) {
    super();
    this.setText(buttonText);
    this.setScaleX(2);
    this.setScaleY(2);
    this.setLayoutX(x);
    this.setLayoutY(y);
  }

}