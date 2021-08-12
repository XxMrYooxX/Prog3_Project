package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Klasse GamePlayerKI
 * repräsentiert die KI des Gegners im Singleplayer
 */
public class GamePlayerKI extends Rectangle {
    private final GameBall ball;
    private static final int speed = 7;

    private static int inAcc = 20;

    private static final int PLAYERHEIGHT = 100;
    private static final int PLAYERWIDTH = 20;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    /**
     * Konstruktor GamePlayerKI
     * - erzeugt ein KI Paddle, welches sich an mitgegebenen Ball orientiert
     * @param ball GameBall, an welchem sich KI orientiert
     */
    public GamePlayerKI(GameBall ball)
    {
        this.ball = ball;
        createAnimationTimer();
        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);
        this.setFill(Color.GREEN);
        this.setLayoutX(WIDTH-PLAYERWIDTH-20); //20px Breite des Paddles + 20px Abstand zum Rand = 40px → 1280-40 = 1240
        //Da das Rechteck von oben nach unten gezeichnet wird, befindet sich der Mittelpunkt bei der Hälfte des Fensters, abzüglich der halben Länge des Paddles
        this.setLayoutY(((double)HEIGHT / 2) - ((double)PLAYERHEIGHT / 2));
    }

    /**
     * Methode createAnimationTimer
     * - erzeugt AnimationTimer (Background Thread) für move Methode und startet diesen
     */
    private void createAnimationTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };
        animationTimer.start();
    }

    /**
     * Methode move
     * - implementiert das Movement der KI
     * - orientiert sich hierbei an dem Movement des Balls
     */
    private void move()
    {
        //Wenn der Ball +-50px von der Y-Koordinate (Höhe) des Paddles entfernt ist, bewegt sich das Paddle zu dem Ball hin
        if (ball.getTranslateY() < this.getTranslateY() - inAcc)
        {
            this.setTranslateY(this.getTranslateY() - speed);
        }
        if (ball.getTranslateY() > this.getTranslateY() + inAcc)
        {
            this.setTranslateY(this.getTranslateY() + speed);
        }
    }

    public static void setInAcc(int inAcc) {
        GamePlayerKI.inAcc = inAcc;
    }
}
