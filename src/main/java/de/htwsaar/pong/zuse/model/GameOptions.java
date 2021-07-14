package de.htwsaar.pong.zuse.model;

public final class GameOptions {
    private static GameMode gameMode;


    private GameOptions() {};


    public static enum GameMode{
        SINGLEPLAYER, MULTIPLAYER
    }

  public static GameMode getGameMode() {
    return gameMode;
  }

  public static void setGameMode(GameMode gameMode) {
    GameOptions.gameMode = gameMode;
  }
}
