package de.htwsaar.pong.zuse.model;

public final class GameOptions {
  //  public static GameMode gamemode;
    public static GameMode gameMode;

    private GameOptions() {};


    public static enum GameMode{
        SINGLEPLAYER, MULTIPLAYER
    }


}
