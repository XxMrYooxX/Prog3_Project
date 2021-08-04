package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Klasse GameBall
 * repräsentiert den Ball innerhalb des Spiels und dessen Logik
 */
public class GameBall extends Circle {
    private AnimationTimer animationTimer;

    private static final int SIZE = 20;
    private static int xSpeed = -10;
    private static int ySpeed = 8;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    /**
     * Konstruktor GameBall
     * - erstellt den GameBall und initialisiert seine Größe
     * - erstellen des AnimationTimers
     */
    public GameBall(){
        //Festlegen des Balllayouts
        this.setRadius(SIZE);
        this.setFill(Color.WHITE);
        this.setLayoutX((double)WIDTH/2);
        this.setLayoutY((double)HEIGHT/2);
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
        if(this.getTranslateY() == -((double)GameOptions.getGameHeight()/2) || this.getTranslateY() == (double)(GameOptions.getGameHeight()/2)){
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
     * Setter des Attributs xSpeed
     * - setzt die Geschwindigkeit des Balls auf der x-Achse
     * @param xSpeed Geschwindigkeit des Balls auf der x-Achse
     */
    public static void setXSpeed(int xSpeed) {
        GameBall.xSpeed = xSpeed;
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
     * Getter des Attributs xSpeed
     * - gibt die Geschwindigkeit des Balls auf der x-Achse zurück
     * @return Geschwindigkeit des Balls auf der x-Achse
     */
    public static int getXSpeed() {
        return xSpeed;
    }

    /**
     * Getter des Attributs ySpeed
     * - gibt die Geschwindigkeit des Balls auf der y-Achse zurück
     * @return Geschwindigkeit des Balls auf der y-Achse
     */
    public static int getYSpeed() { return ySpeed; }

}
