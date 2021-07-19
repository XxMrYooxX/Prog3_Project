package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePlayerKI extends Rectangle {
    private final GameBall ball;
    private AnimationTimer animationTimer;
    private static final int speed = 10;

    private static final int PLAYERHEIGHT = 100;
    private static final int PLAYERWIDTH = 20;
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int WIDTH = GameOptions.getGameWidth();

    public GamePlayerKI(GameBall ball)
    {
        this.ball = ball;

        createAnimationTimer();

        this.setWidth(PLAYERWIDTH);
        this.setHeight(PLAYERHEIGHT);
        this.setFill(Color.GREEN);
        this.setLayoutX(WIDTH-PLAYERWIDTH-20); //20px Breite des Paddles + 20px Abstannd zum Rand = 40px -> 1280-40 = 1240
        //Da das Rechteck von oben nach unten gezeichnet wird, befindet sich der Mittelpunkt bei der Hälfte des Fensterns, abzüglich der halben Länge des Paddles
        this.setLayoutY((HEIGHT / 2) - (PLAYERHEIGHT / 2));
    }

    //Erstellt und startet den Animationtimer, die Methode Move() wird im Hintergrund immer wieder aufgerufen
    private void createAnimationTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };
        animationTimer.start();
    }

    //Methode, welche das KI-gesteuerte Paddle zum "Verfolgen des Balles" bringt
    private void move()
    {
        //Wenn der Ball +-50px von der Y-Koordinate (Höhe) des Paddles entfernt ist, bewegt sich das Paddle zu dem Ball hin
        if (ball.getTranslateY() < this.getTranslateY() + 50)
        {
            this.setTranslateY(this.getTranslateY() - speed);
        }
        if (ball.getTranslateY() > this.getTranslateY() + 50)
        {
            this.setTranslateY(this.getTranslateY() + speed);
        }
    }
}
