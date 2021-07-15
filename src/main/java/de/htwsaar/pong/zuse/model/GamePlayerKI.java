package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePlayerKI extends Rectangle
{
    private AnimationTimer animationTimer;

    private GameBall ball;

    private static int speed = 10;

    private boolean isThreadActive = false;

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

    private void createAnimationTimer()
    {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                move();
            }
        };
        animationTimer.start();
    }

    private void move()
    {
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
