package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
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

    private static final int WIDTH = GameOptions.getGameWidth();
    private static final int HEIGHT = GameOptions.getGameHeight();

    private boolean cooldown = false;
    private Random rnd = new Random();
    private Thread gamePointThread;
    private GameSubScene scoreSubScene;
    private Stage gameStage;
    private Scene gameScene;
    private AnchorPane gamePane;
    private Label playerOneScore;
    private Label playerTwoScore;
    private Label dashLbl;
    private Button firstMenuButton;

    private final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #FFFFFF;";

    private GameSubScene endGameSubScene;
    private Label resultLabel;

    private int playerOneLivesLeft = 3;
    private int playerTwoLivesLeft = 3;
    private boolean gameDone = false;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isWKeyPressed;
    private boolean isSKeyPressed;
    private static final int SPEED = 5;

    //Setzen der benötigten Parameter für weitere Methoden
    public GameHandler(Stage gameStage, AnchorPane gamePane, Scene gameScene){
        this.gameStage = gameStage;
        this.gamePane = gamePane;
        this.gameScene = gameScene;
    }

    //Erschaffen der eigentlichen GameScene auf Basis der gamescene view (root)
    public void createGameSubScene() {
        gameSubScene = new GameSubScene(WIDTH, HEIGHT,0,0);
        //Festelegen des Hintergrunds der Spielfläche
        gameSubScene.getPane().setStyle("-fx-background-color: #beeef7;"); //Helles Türkis

        //Anlegen des ersten Players, der unabhängig vom Gamemode gebraucht wird
        player = new GamePlayer(gameScene, false);

        //Erstellt den Spielball
        ball = new GameBall(gameSubScene);


        //Abhängig vom GameMode wird ein KI Player, bzw. ein zweiter Spieler hinzugefügt
        if (GameOptions.getGameMode() == GameOptions.GameMode.SINGLEPLAYER) {
            //Debug
            System.out.println(GameOptions.getGameMode());
            System.out.println("Singleplayer Erstellung 1 Spieler");

            //Anlegen des KI Spielers mit Referenz auf den Ball, damit dieser folgen kann
            playerKI = new GamePlayerKI(ball);
            //Hinzufügen des neuen Elements zur GameSubScene
            gameSubScene.getPane().getChildren().addAll(ball, player, playerKI);

        } else {
            //Debug
            System.out.println(GameOptions.getGameMode());
            System.out.println("Multiplayer Erstellung 2 Spieler");

            //Anlegen des Spielers mit Referenz der Scene für den ActionListener (Tastenanschläge)
            player2 = new GamePlayer(gameScene, true);
            //Hinzufügen des neuen Elements zur GameSubScene
            gameSubScene.getPane().getChildren().addAll(ball, player, player2);

        }
        //Fügt die erstellte SubScene zum GamePane hinzu
        gamePane.getChildren().add(gameSubScene);

    }

    public void createGameTitle()
    {
        Label titleLabel = new Label("PONG");

        titleLabel.setStyle("-fx-spacing: 20; -fx-text-fill: #000000;"); //Farbe schwarz
        titleLabel.setScaleX(5);
        titleLabel.setScaleY(5);
        AnchorPane.setLeftAnchor(titleLabel, 0.0);
        AnchorPane.setRightAnchor(titleLabel, 0.0);
        titleLabel.setAlignment(Pos.CENTER);
        //titleLabel.setLayoutX(WIDTH/2); //Horizontal zentriert
        titleLabel.setLayoutY(40); //40px Abstand zum obereren Rand

        //Hinzufügen zur GameScene
        gamePane.getChildren().add(titleLabel);
    }

    //Erstellt und startet den Animationtimer, die Methoden checkCollision, checkPoints, endGame werden im Hintergrund immer wieder aufgerufen
    public void createGameTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                endGame(); //TODO Stattdessen Animation Timer beenden, wenn das Leben auf 0 fällt, statt immer wieder zu prüfen?
                checkCollision();
                checkPoints();
            }
        };
        animationTimer.start();
    }

    //Überprüft ob das Spiel vorbei ist, also ein Spieler 0 Leben hat //TODO Ball einfach unsichtbar machen mit "ball.setVisible(false)"
    private void endGame()  {
        if(!gameDone){
            if(playerOneLivesLeft == 0){
                gameDone = true;
                ball.setFill(Color.BLACK);
                //Beendet Ballbewegung und setzt ihn außerhalb des sichtbaren Bereichs
                GameBall.setXSpeed(0);
                GameBall.setYSpeed(0);
                ball.setTranslateX(0);
                ball.setTranslateY(-400);
                createEndScoreSubScene(false);
                //Entfernt den Menubutton unten rechts, da ein neuer mit dem EndScore eingeblendet wird
                gamePane.getChildren().remove(firstMenuButton);

            }
            if(playerTwoLivesLeft == 0){
                gameDone = true;
                ball.setFill(Color.BLACK);
                //Beendet Ballbewegung und setzt ihn außerhalb des sichtbaren Bereichs
                GameBall.setXSpeed(0);
                GameBall.setYSpeed(0);
                ball.setTranslateX(0);
                ball.setTranslateY(-400);
                createEndScoreSubScene(true);
                //Entfernt den Menubutton unten rechts, da ein neuer mit dem EndScore eingeblendet wird
                gamePane.getChildren().remove(firstMenuButton);
            }
        }

    }

    //Kollisionserkennung //TODO kann nicht getestet werden weil movement nicht funktioniert
    private void checkCollision(){
        if (player.getTranslateY() <= -225) {
            player.setTranslateY(player.getTranslateY() + SPEED);
        }
        if (player.getTranslateY() + 100 >= 225) {
            player.setTranslateY(player.getTranslateY() - SPEED);
        }
        if(GameOptions.getGameMode() == GameOptions.GameMode.MULTIPLAYER){
            if (player2.getTranslateY() <= -225) {
                player2.setTranslateY(player2.getTranslateY() + SPEED);
            }
            if (player2.getTranslateY() + 100 >= 225) {
                player2.setTranslateY(player2.getTranslateY() - SPEED);
            }
        }

    }

    //Setzen der Punkte, wenn Ball außerhalb des Spielbereichs fliegt, Ball in zufällige Richtung starten lassen mit gamePointThread
    private void checkPoints(){
        if(!gameDone){
            if(ball.getTranslateX() <= -600){
                if(!cooldown){
                    updateScore(true);
                    cooldown = true;
                    //Ball wieder auf Startposition bringen
                    relocateBall();
                    //Zufällige Richtung für nächsten Ball bestimmen
                    gamePointThread = new Thread(new GamePoint(ball, this));
                    gamePointThread.start();

                }
            }
            if (ball.getTranslateX() >= 600)
            {
                if (!cooldown)
                {
                    updateScore(false);
                    cooldown = true;
                    //Ball wieder auf Startposition bringen
                    relocateBall();
                    //Zufällige Richtung für nächsten Ball bestimmen
                    gamePointThread = new Thread(new GamePoint(ball, this));
                    gamePointThread.start();
                }
            }
        }
    }

    //Zentriert den Ball in der Mitte und setzt den Speed auf 0
    private void relocateBall()
    {
        GameBall.setXSpeed(0);
        GameBall.setYSpeed(0);
        ball.setTranslateX(0);
        ball.setTranslateY(0);
    }

    //Erstellt den Button um zum Hauptmenü zurückzukommen
    public void createMenuButton() {
        firstMenuButton = new GameButton("Main Menu", 1150, 670);
        firstMenuButton.setOnAction(e ->
        {
            try {
                animationTimer.start();
                gameStage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gamePane.getChildren().add(firstMenuButton);
    }


    private void createEndScoreSubScene(boolean playerOneWins){
        endGameSubScene = new GameSubScene(1280, 720, 0, 0);
        endGameSubScene.getPane().setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);");
        resultLabel = new Label("");
        if (playerOneWins) resultLabel.setText("Player 1 Wins!");
        else resultLabel.setText("Player 2 Wins!");
        resultLabel.setScaleX(7);
        resultLabel.setScaleY(7);

        AnchorPane.setLeftAnchor(resultLabel, 0.0);
        AnchorPane.setRightAnchor(resultLabel, 0.0);
        resultLabel.setAlignment(Pos.CENTER);
        resultLabel.setLayoutY(HEIGHT/2 -60);
        resultLabel.setStyle(LABEL_STYLE);

        GameButton menuButton = new GameButton("Main Menu", 600, HEIGHT/2 + 40);
        menuButton.setTextFill(Color.BLACK);
        menuButton.setScaleX(3);
        menuButton.setScaleY(3);
        menuButton.setOnAction(e -> {
            try {
                gameStage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        endGameSubScene.getPane().getChildren().addAll(resultLabel, menuButton);
        gamePane.getChildren().add(endGameSubScene);
    }
    public void createScoreSubScene() {
        scoreSubScene = new GameSubScene(300, 100, 500, 575);
        playerOneScore = new Label("3");
        dashLbl = new Label("  -  ");
        playerTwoScore = new Label("3");

        playerOneScore.setScaleX(8);
        playerOneScore.setScaleY(8);
        playerOneScore.setLayoutX(50);
        playerOneScore.setLayoutY(40);

        dashLbl.setScaleX(8);
        dashLbl.setScaleY(8);
        dashLbl.setLayoutX(140);
        dashLbl.setLayoutY(40);

        playerTwoScore.setScaleX(8);
        playerTwoScore.setScaleY(8);
        playerTwoScore.setLayoutX(240);
        playerTwoScore.setLayoutY(40);

        playerOneScore.setStyle(LABEL_STYLE);
        dashLbl.setStyle(LABEL_STYLE);
        playerTwoScore.setStyle(LABEL_STYLE);
        //playerOneScore.setFont(font);
        //dashLbl.setFont(font);
        //playerTwoScore.setFont(font);

        scoreSubScene.getPane().setStyle("-fx-background-color: transparent;");
        scoreSubScene.getPane().getChildren().addAll(playerOneScore, dashLbl, playerTwoScore);
        gamePane.getChildren().add(scoreSubScene);
    }
    private void updateScore(boolean playerOne){
        if(playerOne){
            playerOneLivesLeft--;
            playerOneScore.setText(String.valueOf(playerOneLivesLeft));
        } else {
            playerTwoLivesLeft--;
            playerTwoScore.setText(String.valueOf(playerTwoLivesLeft));
        }
    }

    private void movePlayers() {
        if (isUpKeyPressed && !isDownKeyPressed && isWKeyPressed && !isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() - SPEED);
            player.setTranslateY(player2.getTranslateY() - SPEED);
        } else if (!isUpKeyPressed && isDownKeyPressed && !isWKeyPressed && isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() + SPEED);
            player.setTranslateY(player.getTranslateY() + SPEED);
        } else if (isUpKeyPressed && !isDownKeyPressed && !isWKeyPressed && isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() - SPEED);
            player.setTranslateY(player.getTranslateY() + SPEED);
        } else if (!isUpKeyPressed && isDownKeyPressed && isWKeyPressed && !isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() + SPEED);
            player.setTranslateY(player.getTranslateY() - SPEED);
        } else if (isUpKeyPressed && !isDownKeyPressed && !isWKeyPressed && !isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() - SPEED);
        } else if (!isUpKeyPressed && isDownKeyPressed && !isWKeyPressed && !isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() + SPEED);
        } else if (!isUpKeyPressed && !isDownKeyPressed && isWKeyPressed && !isSKeyPressed) {
            player.setTranslateY(player.getTranslateY() - SPEED);
        } else if (!isUpKeyPressed && !isDownKeyPressed && !isWKeyPressed && isSKeyPressed) {
            player.setTranslateY(player.getTranslateY() + SPEED);
        }if (isUpKeyPressed && isDownKeyPressed && isWKeyPressed && !isSKeyPressed) {
            player.setTranslateY(player.getTranslateY() - SPEED);
        }if (isUpKeyPressed && isDownKeyPressed && !isWKeyPressed && isSKeyPressed) {
            player.setTranslateY(player.getTranslateY() + SPEED);
        }if (isUpKeyPressed && !isDownKeyPressed && isWKeyPressed && isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() - SPEED);
        }if (!isUpKeyPressed && isDownKeyPressed && isWKeyPressed && !isSKeyPressed) {
            player2.setTranslateY(player2.getTranslateY() + SPEED);
        }
    }

    public void addListeners(Scene gameScene){
        gameScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP){
                isUpKeyPressed = true;
            }
            if(e.getCode() == KeyCode.DOWN){
                isDownKeyPressed = true;
            }
            if (e.getCode() == KeyCode.W) {
                isWKeyPressed = true;
            }
            if (e.getCode() == KeyCode.S) {
                isSKeyPressed = true;
            }
        });
        gameScene.setOnKeyReleased(e ->
        {
            if (e.getCode() == KeyCode.UP) {
                isUpKeyPressed = false;
            }
            if (e.getCode() == KeyCode.DOWN) {
                isDownKeyPressed = false;
            }
            if (e.getCode() == KeyCode.W) {
                isWKeyPressed = false;
            }
            if (e.getCode() == KeyCode.S) {
                isSKeyPressed = false;
            }
        });
    }

    public void setCooldown(boolean b) {
        this.cooldown = b;
    }
}
