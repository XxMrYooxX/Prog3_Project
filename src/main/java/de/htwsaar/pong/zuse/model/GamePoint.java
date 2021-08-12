package de.htwsaar.pong.zuse.model;

import java.util.Random;

/**
 * Klasse GamePoint
 * repräsentiert ein Gamedurchlauf als Thread
 */
public class GamePoint implements Runnable {

    /**
     * Pseudo-Random Ziffer der Startrichtung
     */
    int randNum;

    /**
     * run Methode
     * - startet Thread des Game Durchlauf
     * - entscheidet, in welche Richtung der Ball startet (anhand Pseudo-Random Ziffer)
     */
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
        GameBall.setXSpeed(GameBall.getInitialXSpeed() * directionX);
        GameBall.setYSpeed(GameBall.getInitialYSpeed() * directionY);
    }
}