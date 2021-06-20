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

    public GamePlayerKI(GameBall ball)
    {
        this.ball = ball;

        createAnimationTimer();

        this.setFill(Color.GREEN);
        this.setWidth(20);
        this.setHeight(100);
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
