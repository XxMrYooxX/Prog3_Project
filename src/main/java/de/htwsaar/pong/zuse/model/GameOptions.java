package de.htwsaar.pong.zuse.model;

import javafx.scene.input.KeyCode;

/**
 * Class GameOptions
 * Repräsentiert statisch die GameOptions
 */
public final class GameOptions {
    private static GameMode gameMode;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    //Standard Keybelegung
    private static KeyCode poneUp = KeyCode.W;
    private static KeyCode poneDown = KeyCode.S;
    private static KeyCode ptwoUp = KeyCode.UP;
    private static KeyCode ptwoDown = KeyCode.DOWN;

    /**
     * Enum GameMode
     * - Zur besseren Unterscheidung der SpielModi
     */
    public enum GameMode{
        SINGLEPLAYER, MULTIPLAYER
    }

    /**
     * Getter für Attribut gameMode
     * @return GameMode gameMode
     */
    public static GameMode getGameMode() { return gameMode; }

    /**
     * Getter für Attribut HEIGT
     * @return Höhe des Spielfelds
     */
    public static int getGameHeight() { return HEIGHT; }

    /**
     * Getter für Attribut WIDTH
     * @return Weite des Spielfelds
     */
    public static int getGameWidth() { return WIDTH; }

    /**
     * Getter für Attribut ptwoDown
     * @return Key Down von Player 2
     */
    public static KeyCode getKeyCodePtwoDown() { return ptwoDown; }

    /**
     * Getter für Attribut ptwoUp
     * @return Key Up von Player 2
     */
    public static KeyCode getKeyCodePtwoUp() { return ptwoUp; }

    /**
     * Getter für Attribut poneUp
     * @return Key Up von Player 1
     */
    public static KeyCode getKeyCodePoneUp() { return poneUp; }

    /**
     * Getter für Attribut poneDown
     * @return Key Down von Player 1
     */
    public static KeyCode getKeyCodePoneDown() { return poneDown; }

    /**
     * Setter für Attribut gameMode
     * @param gameMode Zu setzender GameMode
     */
    public static void setGameMode(GameMode gameMode) { GameOptions.gameMode = gameMode; }

    /**
     * Setter für Attribut ptwoDown
     * Setzt Key für Down von Player 2
     * @param key Key für Down
     */
    public static void setKeyCodePtwoDown(KeyCode key) { GameOptions.ptwoDown = key; }

    /**
     * Setter für Attribut ptwoUp
     * Setzt Key für Up von Player 2
     * @param key Key für Up
     */
    public static void setKeyCodePtwoUp(KeyCode key) { GameOptions.ptwoUp = key; }

    /**
     * Setter für Attribut poneDown
     * Setzt Key für Down von Player 1
     * @param key Key für Down
     */
    public static void setKeyCodePoneDown(KeyCode key) { GameOptions.poneDown = key; }

    /**
     * Setter für Attribut poneUp
     * Setzt Key für Up von Player 1
     * @param key Key für Up
     */
    public static void setKeyCodePoneUp(KeyCode key) { GameOptions.poneUp = key; }

}
