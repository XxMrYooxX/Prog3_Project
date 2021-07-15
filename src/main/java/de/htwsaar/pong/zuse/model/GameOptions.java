package de.htwsaar.pong.zuse.model;

import javafx.scene.input.KeyCode;

public final class GameOptions {
    private static GameMode gameMode;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private GameOptions() {}

    private static KeyCode poneUp = KeyCode.W;
    private static KeyCode poneDown = KeyCode.S;
    private static KeyCode ptwoUp = KeyCode.UP;
    private static KeyCode ptwoDown = KeyCode.DOWN;


    public enum GameMode{
        SINGLEPLAYER, MULTIPLAYER
    }


  public static GameMode getGameMode() {
    return gameMode;
  }

  public static int getGameHeight() {
        return HEIGHT;
    }

  public static int getGameWidth() {
      return WIDTH;
  }

  public static KeyCode getKeyCodePtwoDown() {
        return ptwoDown;
    }

  public static KeyCode getKeyCodePtwoUp() {
        return ptwoUp;
    }

  public static KeyCode getKeyCodePoneUp() {
        return poneUp;
  }

  public static KeyCode getKeyCodePoneDown() {
        return poneDown;
    }

  public static void setGameMode(GameMode gameMode) {
    GameOptions.gameMode = gameMode;
  }

  public static void setKeyCodePtwoDown(KeyCode key) {
        GameOptions.ptwoDown = key;
    }

  public static void setKeyCodePtwoUp(KeyCode key) {
        GameOptions.ptwoUp = key;
    }

  public static void setKeyCodePoneDown(KeyCode key) {
        GameOptions.poneDown = key;
    }
  public static void setKeyCodePoneUp(KeyCode key) {
        GameOptions.poneUp = key;
  }

}
