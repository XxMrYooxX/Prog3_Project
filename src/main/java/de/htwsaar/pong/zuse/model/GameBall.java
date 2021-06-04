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

    public GameBall(GameSubScene gameSubScene){
        this.gameSubScene = gameSubScene;
        this.setRadius(SIZE);
        this.setFill(Color.WHITE);

        createTimer();
    }

    private void createTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };
        animationTimer.start();
    }

    private void move() {
        if(this.getTranslateY() == -250 || this.getTranslateY() == 250){
            ySpeed = -ySpeed;
        }
        this.setTranslateX(this.getTranslateX() - xSpeed);
        this.setTranslateY(this.getTranslateY() - ySpeed);
    }

    public static void setxSpeed(int xSpeed) {
        GameBall.xSpeed = xSpeed;
    }

    public static void setySpeed(int ySpeed) {
        GameBall.ySpeed = ySpeed;
    }

    public static int getxSpeed() {
        return xSpeed;
    }
    public void setColor(Color color){
        this.setFill(color);
    }
}
