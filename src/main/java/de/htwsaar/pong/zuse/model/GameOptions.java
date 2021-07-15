package de.htwsaar.pong.zuse.model;

public final class GameOptions {
    private static GameMode gameMode;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private GameOptions() {};


    public static enum GameMode{
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


  public static void setGameMode(GameMode gameMode) {
    GameOptions.gameMode = gameMode;
  }
}
