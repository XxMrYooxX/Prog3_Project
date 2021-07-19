package de.htwsaar.pong.zuse.model;

import java.util.Random;

public class GamePoint implements Runnable {

    int randNum;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //generieren einer zufälligen Zahl
        Random rnd = new Random();
        randNum = rnd.nextInt(100) + 1;

        int directionX;
        int directionY;
                                        //oben rechts
        if (randNum <= 25) {
            directionX = 1;
            directionY = 1;
        } else if (randNum <= 50){      //oben links
            directionX = -1;
            directionY = 1;
        } else if (randNum <= 75){      //unten links
            directionX = -1;
            directionY = -1;
        } else {                        // unten rechts
            directionX = 1;
            directionY = -1;
        }

        //Übernehmen für GameBall
        GameBall.setXSpeed(10 * directionX);
        GameBall.setYSpeed(8 * directionY);
    }
}