package de.htwsaar.pong.zuse.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Klasse GamePlayer
 * repräsentiert einen Spieler (Paddle) im Spiel
 */
public class GamePlayer extends Rectangle {

    //Konstanten für Größe, bzw. Speed der Paddles, Größe des Fensters
    private static final int PLAYERHEIGHT = 100;
    private static final int PLAYERWIDTH = 20;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    /**
     * Konstruktor GamePlayer
     * erzeugt ein Paddle abhängig davon, ob es für Player 1 oder 2 ist
     * @param playerTwo boolean, ob Player 2 Paddle erstellt werden soll
     */
    public GamePlayer(boolean playerTwo){
        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);
        //Prüfen ob Spieler 1 oder 2 erschaffen werden soll
        if (playerTwo) { //Spieler 2
            this.setFill(Color.GREEN);
            this.setLayoutX(WIDTH-PLAYERWIDTH-20); //20px Breite des Paddles + 20px Abstand zum Rand = 40px → 1280-40 = 1240
        } else { //Spieler 1
            this.setFill(Color.WHITE);
            this.setLayoutX(PLAYERWIDTH); //20px Abstand zum Rand
        }
        this.setLayoutY(((double)HEIGHT / 2) - ((double)PLAYERHEIGHT / 2));
    }
}
