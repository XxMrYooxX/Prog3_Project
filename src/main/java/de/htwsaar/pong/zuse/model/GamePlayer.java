package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePlayer extends Rectangle {
    private Scene scene;
    private AnchorPane pane;
    private AnimationTimer animationTimer;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private static int PLAYERHEIGHT = 100;
    private static int PLAYERWIDTH = 20;
    private static int SPEED = 10;

    public GamePlayer(Scene scene, AnchorPane pane, boolean playerTwo){
        this.scene = scene;
        this.pane = pane;

        createTimer();
        if (playerTwo != true) {
          //  addActionListeners();
        }
        this.setFill(Color.WHITE);
        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);
    }

    public void createTimer(){
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };
        animationTimer.start();
    }
    public void move(){
        if(isUpKeyPressed && !isDownKeyPressed && this.getTranslateY() != -PLAYERHEIGHT){
            this.setTranslateY(this.getTranslateY() - SPEED);
        }
        if(isDownKeyPressed && !isUpKeyPressed && this.getTranslateY() + PLAYERHEIGHT != PLAYERHEIGHT){
            this.setTranslateY(this.getTranslateY() + SPEED);
        }
    }
}
