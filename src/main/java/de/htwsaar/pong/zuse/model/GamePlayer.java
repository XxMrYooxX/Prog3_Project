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
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameHeight();


    //Konstanten für Größe, bzw. Speed der Paddles
    private static final int PLAYERHEIGHT = 100;
    private static final int PLAYERWIDTH = 20;
    private static final int SPEED = 10;

    public GamePlayer(Scene scene, boolean playerTwo){
        this.scene = scene;
        createTimer();

        if (!playerTwo) {
          addActionListeners();
          this.setLayoutX(20);
          this.setLayoutY((720 / 2) - 50);
        }
        this.setFill(Color.WHITE);
        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);
    }

    public void createTimer(){
        AnimationTimer animationTimer = new AnimationTimer() {
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

    private void addActionListeners() {
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP){
                isUpKeyPressed = true;
            }
            if (e.getCode() == KeyCode.DOWN){
                isDownKeyPressed = true;
            }
        });
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.UP){
                isUpKeyPressed = false;
            }
            if (e.getCode() == KeyCode.DOWN){
                isDownKeyPressed = false;
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
}
