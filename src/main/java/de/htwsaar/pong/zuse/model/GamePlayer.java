package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;

public class GamePlayer extends Rectangle {
    //Parameter für ActionListener/move Methode
    private final Scene scene;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private AnimationTimer animationTimer;

    private boolean playerTwo;

    //Konstanten für Größe, bzw. Speed der Paddles, Größe des Fensters
    private static final int PLAYERHEIGHT = 100;
    private static final int PLAYERWIDTH = 20;
    private static final int SPEED = 20;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    public GamePlayer(Scene scene, boolean playerTwo){
        this.playerTwo = playerTwo;
        this.scene = scene;
        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);
        //Prüfen ob Spieler 1 oder 2 erschaffen werden soll
        if (playerTwo) { //Spieler 2
            this.setFill(Color.GREEN);
            this.setLayoutX(WIDTH-PLAYERWIDTH-20); //20px Breite des Paddles + 20px Abstand zum Rand = 40px -> 1280-40 = 1240
        } else { //Spieler 1
            this.setFill(Color.WHITE);
            this.setLayoutX(PLAYERWIDTH); //20px Abstand zum Rand
        }
        this.setLayoutY((HEIGHT / 2) - (PLAYERHEIGHT / 2));
        //createAnimationTimer();
    }

    /*public void createAnimationTimer(){
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                movePlayer();
            }
        };
    }*/

    /**
     * Methode movePlayers
     * - Setzt Bewegungen abhängig von Tastendruck um
     * TODO: Neu machen :/
     */
    /*private void movePlayer() {
        System.out.println("i want to move it");
        scene.setOnKeyPressed((KeyEvent keyEvent) -> {
            if(keyEvent.getCode() == GameOptions.getKeyCodePoneUp()){
                this.setTranslateY(this.getTranslateY() - SPEED);
            }
            if(keyEvent.getCode() == GameOptions.getKeyCodePoneDown()){
                this.setTranslateY(this.getTranslateY() + SPEED);
            }
            if(GameOptions.getGameMode() == GameOptions.GameMode.MULTIPLAYER){
                if(keyEvent.getCode() == GameOptions.getKeyCodePtwoUp()) {
                    this.setTranslateY(this.getTranslateY() - SPEED);
                }
                if(keyEvent.getCode() == GameOptions.getKeyCodePtwoDown()){
                    this.setTranslateY(this.getTranslateY() + SPEED);
                }
            }
        });
    }*/
    /*@Override
    public void run() {
        createAnimationTimer();
    }*/
}
