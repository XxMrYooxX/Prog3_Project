package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePlayer extends Rectangle {
    private final Scene scene;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    //Konstanten für Größe, bzw. Speed der Paddles, Größe des Fensters
    private static final int PLAYERHEIGHT = 100;
    private static final int PLAYERWIDTH = 20;
    private static final int SPEED = 10;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    public GamePlayer(Scene scene, boolean playerTwo){
        this.scene = scene;

        //Erstellt und startet den Animationtimer
        createTimer();
        //Festelegen der Paddlegröße
        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);

        //Prüfen ob Spieler 1 oder 2 erschaffen werden soll
        if (playerTwo) { //Spieler 2
            //TODO ActionListener funktioniert nicht, Tastenanschläge werden nicht erkannt // addActionListeners();
            this.setFill(Color.GREEN);
            this.setLayoutX(WIDTH-PLAYERWIDTH-20); //20px Breite des Paddles + 20px Abstand zum Rand = 40px -> 1280-40 = 1240
            //Da das Rechteck von oben nach unten gezeichnet wird, befindet sich der Mittelpunkt bei der Hälfte des Fensterns, abzüglich der halben Länge des Paddles
            this.setLayoutY((HEIGHT / 2) - (PLAYERHEIGHT / 2));
        } else { //Spieler 1
            this.setFill(Color.WHITE);
            this.setLayoutX(PLAYERWIDTH); //20px Abstand zum Rand
            //Da das Rechteck von oben nach unten gezeichnet wird, befindet sich der Mittelpunkt bei der Hälfte des Fensterns, abzüglich der halben Länge des Paddles
            this.setLayoutY((HEIGHT / 2) - (PLAYERHEIGHT / 2));
        }

    }

    //Erstellt und startet den Animationtimer, die Methode Move() wird im Hintergrund immer wieder aufgerufen
    public void createTimer(){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
               //TODO Bewegen der Paddles funktioniert nicht //move();
            }
        };
        animationTimer.start();
    }

    /*

    public void move(){
        if(isUpKeyPressed && !isDownKeyPressed && this.getTranslateY() != -PLAYERHEIGHT){
            this.setTranslateY(this.getTranslateY() - SPEED);
        }
        if(isDownKeyPressed && !isUpKeyPressed && this.getTranslateY() + PLAYERHEIGHT != PLAYERHEIGHT){
            this.setTranslateY(this.getTranslateY() + SPEED);
        }
    }


    private void addActionListeners() {
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP){
                isUpKeyPressed = true;
                System.out.println("up key");            }
            if (e.getCode() == KeyCode.DOWN){
                isDownKeyPressed = true;
                System.out.println("down key");
            }
        });
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.UP){
                isUpKeyPressed = false;
                System.out.println("up released");
            }
            if (e.getCode() == KeyCode.DOWN){
                isDownKeyPressed = false;
                System.out.println("down released");
            }
        });
    }
    public boolean getIsUpKeyPressed()
    {
        return isUpKeyPressed;
    }

    public boolean getIsDownKeyPressed()
    {
        return isDownKeyPressed;
    }

     */
}
