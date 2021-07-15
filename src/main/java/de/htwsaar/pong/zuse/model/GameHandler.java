package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * Class GameHandler
 * Steuert die gesamten Vorgehen während der GameScene
 *
 * @version 1.0
 */
public class GameHandler {

    private static GamePlayer player;
    private static GamePlayer player2;
    private static GamePlayerKI playerKI;
    private static GameBall ball;

    private AnimationTimer animationTimer;

    private static final int WIDTH = GameOptions.getGameWidth();
    private static final int HEIGHT = GameOptions.getGameHeight();

    private final Stage gameStage;
    private final Scene gameScene;
    private final AnchorPane gamePane;
    private Label playerOneScore;
    private Label playerTwoScore;
    private Button firstMenuButton;

    private final Random rnd = new Random();

    private final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #FFFFFF;";

    private int playerOneLivesLeft = 3;
    private int playerTwoLivesLeft = 3;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isWKeyPressed;
    private boolean isSKeyPressed;
    private static final int SPEED = 5;

    //Setzen der benötigten Parameter für weitere Methoden

    /**
     * Konstruktor des GameHandlers
     * Setzt die verwendete Stage, Pane und Scene
     * @param gameStage     Verwendete GameStage
     * @param gamePane      Verwendete GamePane
     * @param gameScene     Verwendete GameScene
     */
    public GameHandler(Stage gameStage, AnchorPane gamePane, Scene gameScene){
        this.gameStage = gameStage;
        this.gamePane = gamePane;
        this.gameScene = gameScene;
    }

    /**
     * Methode createGameSubScene
     * - Erschaffen der eigentlichen GameScene auf Basis der Game Scene view (root)
     * - Festlegen des Hintergrunds
     * - Anlegen des ersten Spielers unabhängig vom GameMode
     * - Anlegen des Balls
     * - Abhängig vom Spielmodus Anlegen von KI oder 2. Spieler
     * - SubScene wird GamePane hinzugefügt
     */
    public void createGameSubScene() {
        GameSubScene gameSubScene = new GameSubScene(WIDTH, HEIGHT, 0, 0);
        //Festlegen des Hintergrunds der Spielfläche
        gameSubScene.getPane().setStyle("-fx-background-color: #beeef7;"); //Helles Türkis

        //Anlegen des ersten Players, der unabhängig vom GameMode gebraucht wird
        player = new GamePlayer(gameScene, false);

        //Erstellt den Spielball
        ball = new GameBall(gameSubScene);

        //Abhängig vom GameMode wird ein KI Player, bzw. ein zweiter Spieler hinzugefügt
        if (GameOptions.getGameMode() == GameOptions.GameMode.SINGLEPLAYER) {
            System.out.println(GameOptions.getGameMode());
            System.out.println("Singleplayer Erstellung 1 Spieler");
            //Anlegen des KI Spielers mit Referenz auf den Ball, damit dieser folgen kann
            playerKI = new GamePlayerKI(ball);
            //Hinzufügen des neuen Elements zur GameSubScene
            gameSubScene.getPane().getChildren().addAll(ball, player, playerKI);

        } else {
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

    /**
     *  Methode createGameTitle
     *  - Erstellt titleLabel "PONG"
     *  - Setzt es an gamePane
     */
    public void createGameTitle()
    {
        Label titleLabel = new Label("PONG");

        titleLabel.setStyle("-fx-spacing: 20; -fx-text-fill: #000000;"); //Farbe schwarz
        titleLabel.setScaleX(5);
        titleLabel.setScaleY(5);
        AnchorPane.setLeftAnchor(titleLabel, 0.0);
        AnchorPane.setRightAnchor(titleLabel, 0.0);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setLayoutY(40); //40px Abstand zum obereren Rand

        //Hinzufügen zur GameScene
        gamePane.getChildren().add(titleLabel);
    }

    /**
     * Methode createGameTimer
     * - Erstellt und startet den AnimationTimer
     * - die Methoden checkCollision, checkPoints, endGame werden im Hintergrund immer wieder aufgerufen
     */
    public void createGameTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(GameOptions.getGameMode() == GameOptions.GameMode.MULTIPLAYER){
                    checkCollision();
                    movePlayers();
                }
                checkBallCollision();
                checkPoints();
            }
        };
        animationTimer.start();
    }

    /**
     * Methode endGame
     * - Überprüft ob das Spiel vorbei ist, also ein Spieler 0 Leben hat
     */
    private void endGame()  {
        if(playerOneLivesLeft == 0){
            createEndScoreSubScene(false);
            //Entfernt den Menu Button unten rechts, da ein neuer mit dem EndScore eingeblendet wird
            gamePane.getChildren().remove(firstMenuButton);
        }
        if(playerTwoLivesLeft == 0){
            createEndScoreSubScene(true);
            //Entfernt den Menu Button unten rechts, da ein neuer mit dem EndScore eingeblendet wird
            gamePane.getChildren().remove(firstMenuButton);
        }
    }

    /**
     * Methode checkCollision
     * - Kollisionserkennung der SpielerPaddles
     * - Abhängig von Spielmodus
     */
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

    /**
     * Methode checkPoints
     * - Setzen der Punkte, wenn Ball außerhalb des Spielbereichs fliegt
     * - Ball in zufällige Richtung starten lassen mit gamePointThread
     */
    private void checkPoints(){
        Thread gamePointThread;
        if(ball.getTranslateX() <= -600){
            updateScore(true);
            //Ball wieder auf Startposition bringen
            relocateBall();
            //Zufällige Richtung für nächsten Ball bestimmen
            gamePointThread = new Thread(new GamePoint(ball, this));
            gamePointThread.start();
        }
        if (ball.getTranslateX() >= 600)
        {
            updateScore(false);
            //Ball wieder auf Startposition bringen
            relocateBall();
            //Zufällige Richtung für nächsten Ball bestimmen
            gamePointThread = new Thread(new GamePoint(ball, this));
            gamePointThread.start();
        }
    }

    /**
     * Methode relocateBall
     * - Zentriert den Ball in der Mitte
     * - setzt den Speed auf 0
     */
    private void relocateBall()
    {
        GameBall.setXSpeed(0);
        GameBall.setYSpeed(0);
        ball.setTranslateX(0);
        ball.setTranslateY(0);
    }

    /**
     * Methode createMenuButton
     * - Erstellt den Button um zum Hauptmenü zurückzukommen
     */
    public void createMenuButton() {
        firstMenuButton = new GameButton("Main Menu", 1150, 670);
        firstMenuButton.setOnAction(e -> {
            try {
                //AnimationTimer Befehle stoppen
                animationTimer.stop();
                //Fenstertitel zurückändern
                gameStage.setTitle("Pong by Team Zuse");
                gameStage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gamePane.getChildren().add(firstMenuButton);
    }

    /**
     * Methode createScoreSubScene
     * - Erzeugen der Scene zum Anzeigen des Scores
     */
    public void createScoreSubScene() {
        GameSubScene scoreSubScene = new GameSubScene(300, 100, 500, 575);
        scoreSubScene.getPane().setStyle("-fx-background-color: transparent;");

        //Alles für das Layout
        playerOneScore = new Label("3"); //ToDo: eventuell dynamisch gestalten?
        Label dashLbl = new Label("  -  "); //ToDo: eventuell dynamisch gestalten?
        playerTwoScore = new Label("3"); //ToDo: eventuell dynamisch gestalten?

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

        //Hinzufügen zur Scene und anzeigen
        scoreSubScene.getPane().getChildren().addAll(playerOneScore, dashLbl, playerTwoScore);
        gamePane.getChildren().add(scoreSubScene);
    }

    /**
     * Methode updateScore
     * - Spieler verliert ein Leben
     *      - Wenn Leben = 0 -> endGame() & Stop Animation Timer
     * @param playerOne     Boolean, ob Spieler 1 Leben verliert
     *                          Wenn True: playerOneLivesLeft--
     *                          Wenn false: playerTwoLivesLeft--
     */
    private void updateScore(boolean playerOne){
        if(playerOne){
            playerOneLivesLeft--;
            playerOneScore.setText(String.valueOf(playerOneLivesLeft));
            if(playerOneLivesLeft == 0) {
                animationTimer.stop();
                ball.stopBallAnimation();
                ball.setVisible(false);
                endGame();
            }
        } else {
            playerTwoLivesLeft--;
            playerTwoScore.setText(String.valueOf(playerTwoLivesLeft));
            if(playerTwoLivesLeft == 0) {
                animationTimer.stop();
                ball.stopBallAnimation();
                ball.setVisible(false);
                endGame();
            }
        }
    }

    //Erstellt die EndScene, in der angezeigt wird, wer gewonnen hat, sowie ein Button um zurück ins Hauptmenü zu kommen

    /**
     * Methode createEndScoreSubScene
     * - Erstellt EndScene
     * - Erstellt "zurück ins Hauptmenü" Button
     * @param playerOneWins     Boolean, ob Spieler 1 gewinnt
     *                              true: spieler 1 gewinnt
     *                              false: spieler 2 gewinnt
     */
    private void createEndScoreSubScene(boolean playerOneWins){
        //Erstellen der SubScene mit halbtransparentem Hintergrund
        GameSubScene endGameSubScene = new GameSubScene(1280, 720, 0, 0);
        endGameSubScene.getPane().setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);");

        Label resultLabel = new Label("");

        //Gewinner bestimmen und Text setzen
        if (playerOneWins) resultLabel.setText("Player 1 Wins!");
        else resultLabel.setText("Player 2 Wins!");

        //Layout anpassen und zentrieren
        resultLabel.setScaleX(7);
        resultLabel.setScaleY(7);
        AnchorPane.setLeftAnchor(resultLabel, 0.0);
        AnchorPane.setRightAnchor(resultLabel, 0.0);
        resultLabel.setAlignment(Pos.CENTER);
        resultLabel.setLayoutY((HEIGHT >> 1) - 60);
        resultLabel.setStyle(LABEL_STYLE);

        //Button zum Hauptmenü erzeugen
        GameButton menuButton = new GameButton("Main Menu", 600, HEIGHT/2 + 40);
        //Layout des Buttons
        menuButton.setTextFill(Color.BLACK);
        menuButton.setScaleX(3);
        menuButton.setScaleY(3);

        //Laden des Hauptmenüs
        menuButton.setOnAction(e -> {
            try {
                //Fenstertitel zurückändern
                gameStage.setTitle("Pong by Team Zuse");
                gameStage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Scene übernehmen und anzeigen
        endGameSubScene.getPane().getChildren().addAll(resultLabel, menuButton);
        gamePane.getChildren().add(endGameSubScene);
    }

    /**
     * Methode movePlayers
     * - Setzt Bewegungen abhängig von Tastendruck um
     */
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

    /**
     * Methode checkBallCollision
     * - Prüft die Collision des Balls am Spieler 1
     * - Prüft abhängig von Spielmodus die Collision des Balls am Spieler 2 / KI
     */
    private void checkBallCollision() {
        if (ball.getTranslateY() >= player.getTranslateY() && ball.getTranslateY() <= player.getTranslateY() + 33) {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540) {
                //ball.setColor(playerColor);
                GameBall.setXSpeed(-GameBall.getXSpeed());
                GameBall.setYSpeed(8);
            }
        } else if (ball.getTranslateY() >= player.getTranslateY() + 66 && ball.getTranslateY() <= player.getTranslateY() + 100) {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540) {
                //ball.setColor(playerColor);
                GameBall.setXSpeed(-GameBall.getXSpeed());
                GameBall.setYSpeed(-8);
            }
        } else if (ball.getTranslateY() >= player.getTranslateY() + 33 && ball.getTranslateY() <= player.getTranslateY() + 66) {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540) {
                //ball.setColor(playerColor);
                GameBall.setXSpeed(-GameBall.getXSpeed());
                int randNum = rnd.nextInt(2) + 1;
                if (randNum == 1) {
                    GameBall.setYSpeed(-2);
                } else if (randNum == 2) {
                    GameBall.setYSpeed(2);
                }
            }
        }
        switch(GameOptions.getGameMode()){
            case MULTIPLAYER:
                if (ball.getTranslateY() >= player2.getTranslateY() && ball.getTranslateY() <= player2.getTranslateY() + 33) {
                    if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515) {
                        //ball.setColor(opponentColor);
                        GameBall.setXSpeed(-GameBall.getXSpeed());
                        GameBall.setYSpeed(8);
                    }
                } else if (ball.getTranslateY() >= player2.getTranslateY() + 66 && ball.getTranslateY() <= player2.getTranslateY() + 100) {
                    if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515) {
                        //ball.setColor(opponentColor);
                        GameBall.setXSpeed(-GameBall.getXSpeed());
                        GameBall.setYSpeed(-8);
                    }
                } else if (ball.getTranslateY() >= player2.getTranslateY() + 33 && ball.getTranslateY() <= player2.getTranslateY() + 66) {
                    if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515) {
                        //ball.setColor(opponentColor);
                        GameBall.setXSpeed(-GameBall.getXSpeed());
                        int randNum = rnd.nextInt(2) + 1;
                        if (randNum == 1) {
                            GameBall.setYSpeed(-2);
                        } else if (randNum == 2) {
                            GameBall.setYSpeed(2);
                        }
                    }
                }
                break;
            case SINGLEPLAYER:
                if (ball.getTranslateY() >= playerKI.getTranslateY() && ball.getTranslateY() <= playerKI.getTranslateY() + 33) {
                    if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515) {
                        //ball.setColor(opponentColor);
                        GameBall.setXSpeed(-GameBall.getXSpeed());
                        GameBall.setYSpeed(8);
                    }
                } else if (ball.getTranslateY() >= playerKI.getTranslateY() + 66 && ball.getTranslateY() <= playerKI.getTranslateY() + 100) {
                    if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515) {
                        //ball.setColor(opponentColor);
                        GameBall.setXSpeed(-GameBall.getXSpeed());
                        GameBall.setYSpeed(-8);
                    }
                } else if (ball.getTranslateY() >= playerKI.getTranslateY() + 33 && ball.getTranslateY() <= playerKI.getTranslateY() + 66) {
                    if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515) {
                        //ball.setColor(opponentColor);
                        GameBall.setXSpeed(-GameBall.getXSpeed());
                        int randNum = rnd.nextInt(2) + 1;
                        if (randNum == 1) {
                            GameBall.setYSpeed(-2);
                        } else if (randNum == 2) {
                            GameBall.setYSpeed(2);
                        }
                    }
                }
                break;
        }

    }

    /**
     * Methode addListeners
     * - Setzt nötige Attribute bei Drücken der Tasten zur Bewegung
     * @param gameScene     Scene, auf welcher der Listener hört
     */
    public void addListeners(Scene gameScene){
        gameScene.setOnKeyPressed(e -> {
            if(e.getCode() == GameOptions.getKeyCodePtwoUp()){
                isUpKeyPressed = true;
            }
            if(e.getCode() == GameOptions.getKeyCodePtwoDown()){
                isDownKeyPressed = true;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneUp()) {
                isWKeyPressed = true;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneDown()) {
                isSKeyPressed = true;
            }
        });
        gameScene.setOnKeyReleased(e ->
        {
            if (e.getCode() == GameOptions.getKeyCodePtwoUp()) {
                isUpKeyPressed = false;
            }
            if (e.getCode() == GameOptions.getKeyCodePtwoDown()) {
                isDownKeyPressed = false;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneUp()) {
                isWKeyPressed = false;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneDown()) {
                isSKeyPressed = false;
            }
        });
    }

}
