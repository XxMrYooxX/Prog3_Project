package de.htwsaar.pong.zuse.model;

import java.util.Random;

public class GamePoint implements Runnable {
    private GameBall ball;
    private GameHandler gameHandler;

    int randNum;

    private int directionX;
    private int directionY;


    public GamePoint(GameBall ball, GameHandler gameHandler)
    {
        this.ball = ball;
        this.gameHandler = gameHandler;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        Random rnd = new Random();
        randNum = rnd.nextInt(100) + 1;

        if (randNum <= 25)
        {
            directionX = 1;
            directionY = 1;
        }
        else if (randNum > 25 && randNum <= 50)
        {
            directionX = -1;
            directionY = 1;
        }
        else if (randNum > 50 && randNum <= 75)
        {
            directionX = -1;
            directionY = -1;
        }
        else
        {
            directionX = 1;
            directionY = -1;
        }

        ball.setXSpeed(10 * directionX);
        ball.setYSpeed(8 * directionY);

        gameHandler.setCooldown(false);
    }
}