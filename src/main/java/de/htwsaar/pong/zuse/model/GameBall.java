package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameBall extends Circle {
    private AnimationTimer animationTimer;
    private GameSubScene gameSubScene;

    private static final int SIZE = 20;
    private static int xSpeed = -10;
    private static int ySpeed = 8;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    public GameBall(GameSubScene gameSubScene){
        this.gameSubScene = gameSubScene;

        //Festelegen des Balllayouts
        this.setRadius(SIZE);
        this.setFill(Color.WHITE);
        this.setLayoutX(WIDTH/2);
        this.setLayoutY(HEIGHT/2);


        //Erstellt und startet den Animationtimer
        createTimer();
    }

    //Erstellt und startet den Animationtimer, die Methode Move() wird im Hintergrund immer wieder aufgerufen
    private void createTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };
        animationTimer.start();
    }

    //Absolut keine Ahnung was das hier macht, irgendwie den Ball bewegen, wenn er 250 hoch/tief ist //TODO Verstehen was das macht
    private void move() {
        if(this.getTranslateY() == -250 || this.getTranslateY() == 250){
            ySpeed = -ySpeed;
        }
        this.setTranslateX(this.getTranslateX() - xSpeed);
        this.setTranslateY(this.getTranslateY() - ySpeed);
    }

    //Festelegen der Ballgeschwindigkeit
    public static void setXSpeed(int xSpeed) {
        GameBall.xSpeed = xSpeed;
    }
    public static void setYSpeed(int ySpeed) {
        GameBall.ySpeed = ySpeed;
    }

    //Speed des Balls zurückgeben
    public static int getXSpeed() {
        return xSpeed;
    }

    //Farbe des Balls ändern
    public void setColor(Color color){
        this.setFill(color);
    }

    public void stopBallAnimation() {
        animationTimer.stop();
    }
}
