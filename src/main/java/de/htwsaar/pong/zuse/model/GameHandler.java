package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameHandler {

    private static GameSubScene gameSubScene;
    private static GamePlayer player;
    private static GamePlayer player2;
    private static GamePlayerKI playerKI;
    private static GameBall ball;

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
    private final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #b206b0;";
    private final String TITLE_STYLE = "-fx-spacing: 20; -fx-text-fill: #ff8a5c;";

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
    public static void createGameSubScene(Scene gameScene, AnchorPane gamePane, GameMode gameMode) {
        gameSubScene = new GameSubScene(1170, 620, 100, 100);

        player = new GamePlayer(gameScene, gamePane, false);
        player.setLayoutX(40);
        player.setLayoutY(255);



        if(gameMode == GameMode.MULTIPLAYER){
            player2 = new GamePlayer(gameScene, gamePane, true);
            player2.setLayoutX(1090);
            player2.setLayoutY(225);
            gameSubScene.getPane().getChildren().addAll(ball, player, player2);
        } else {
            //playerKI = new GamePlayerKI();
            playerKI.setLayoutX(1090);
            playerKI.setLayoutY(225);
            gameSubScene.getPane().getChildren().addAll(ball, player, playerKI);
        }
        gamePane.getChildren().add(gameSubScene);
    }

    public static void createScoreSubScene() {
    }

    public static void createGameTimer() {

    }

    public static void createGameTitle() {
           
        Label titleLabel = new Label("PONG");
        titleLabel.setStyle(TITLE_STYLE);
        titleLabel.setFont(font);
        titleLabel.setScaleX(5);
        titleLabel.setScaleY(5);
        titleLabel.setLayoutX(630);
        titleLabel.setLayoutY(40);
        gamePane.getChildren().add(titleLabel);
    }

    public static void createMenuButton() throws FileNotFoundException
    {
        GameButton Button = new GameButton("Back to Menu", 880, 600, true);
        Button.setOnAction(e ->
        {
            gameStage.hide();
            try
            {
                MenuView menuView = new MenuView();
                menuView.changeScenes(gameStage);
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
        });
        gamePane.getChildren().add(Button);
    }
    
    public enum GameMode{
        SINGLEPLAYER, MULTIPLAYER
    }

}
