package de.htwsaar.pong.zuse.model;

public class GameHandler {

    private GameSubScene gameSubScene;
    private Player player;
    private Player player2;
    private Ball ball;

    private AnimationTimer animationTimer;

    private Color playerColor = Color.rgb(178,34,34);
    private Color opponentColor = Color.rgb(50,122,178);

    private boolean cooldown = false;
    private Random rnd = new Random();
    private Thread newPointThread;
    private GameSubScene scoreSubScene;
    private Label playerScore;
    private Label dashLbl;
    private Label opponentScore;
    private static final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #b206b0;";
    private static final String TITLE_STYLE = "-fx-spacing: 20; -fx-text-fill: #ff8a5c;";

    private GameSubScene endGameSubScene;
    private Label resultLabel;

    private int playerLivesLeft = 3;
    private int opponentLivesLeft = 3;
    private boolean gameDone = false;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isWKeyPressed;
    private boolean isSKeyPressed;
    private static final int SPEED = 5;
    public void createGameSubScene() {
    }

    public void createScoreSubScene() {
    }

    public void createGameTimer() {

    }

    public void createGameTitle() {
    }

    public void createMenuButton() {
    }

    public enum GameMode{
        SINGLEPLAYER, MULTIPLAYER
    }

}
