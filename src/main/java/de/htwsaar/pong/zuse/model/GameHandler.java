package de.htwsaar.pong.zuse.model;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicTreeUI;
import java.io.IOException;
import java.util.Objects;

/**
 * Class GameHandler
 * steuert das gesamte Vorgehen während der GameScene
 *
 * @version 1.0
 */
public class GameHandler {

    private static final int WIDTH = GameOptions.getGameWidth();
    private static final int HEIGHT = GameOptions.getGameHeight();
    private static final int SPEED = 15;
    private static Bounds bounds;
    private static GamePlayer player;
    private static GamePlayer player2;
    private static GamePlayerKI playerKI;
    private static GameBall ball;
    private final Stage gameStage;
    private final Scene gameScene;
    private final AnchorPane gamePane;
    private final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #FFFFFF;";
    private final String SCORE_SUBSCENE_STYLE = "-fx-background-color: transparent;";
    private final String GAME_SUBSCENE_BACKGROUND_STYLE = "-fx-background-color: #beeef7;";
    private final String GAME_TITLE_LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #000000;";
    private final String ENDGAME_SUBSCENE_STYLE = "-fx-background-color: rgba(0, 100, 100, 0.5);";
    private AnimationTimer animationTimer;
    private Label playerOneScore;
    private Label playerTwoScore;
    private Button firstMenuButton;
    private int playerOneLivesLeft = GameOptions.getRounds();
    private int playerTwoLivesLeft = GameOptions.getRounds();
    private boolean isPOneUpKeyPressed;
    private boolean isPOneDownKeyPressed;
    private boolean isPTwoUpKeyPressed;
    private boolean isPTwoDownKeyPressed;

    /**
     * Konstruktor des GameHandlers
     * setzt die verwendete Stage, Pane und Scene
     *
     * @param gameStage Verwendete GameStage
     * @param gamePane  Verwendete GamePane
     * @param gameScene Verwendete GameScene
     */
    public GameHandler(Stage gameStage, AnchorPane gamePane, Scene gameScene) {
        this.gameStage = gameStage;
        this.gamePane = gamePane;
        this.gameScene = gameScene;
        bounds = gamePane.getLayoutBounds();
    }

    /**
     * Methode createGameSubScene
     * - Erschaffen der eigentlichen GameScene auf Basis das Game Scene view (root)
     * - Festlegen des Hintergrunds
     * - Anlegen des ersten Spielers unabhängig vom GameMode
     * - Anlegen des Balls
     * - Abhängig vom Spielmodus Anlegen von KI oder 2. Spieler
     * - SubScene wird GamePane hinzugefügt
     */
    public void createGameSubScene() {
        GameSubScene gameSubScene = new GameSubScene(WIDTH, HEIGHT, 0, 0);
        //Festlegen des Hintergrunds der Spielfläche
        gameSubScene.getPane().setStyle(GAME_SUBSCENE_BACKGROUND_STYLE); //Helles Türkis TODO: Ersetzen mit css

        //Anlegen des ersten Players, der unabhängig vom GameMode gebraucht wird
        player = new GamePlayer(false);
        //Erstellt den Spielball
        ball = new GameBall(bounds);

        //Abhängig vom GameMode wird ein KI-Player, bzw. ein zweiter Spieler hinzugefügt
        if (GameOptions.getGameMode() == GameOptions.GameMode.SINGLEPLAYER) {
            System.out.println(GameOptions.getGameMode());
            System.out.println("Singleplayer Erstellung 1 Spieler");
            //Anlegen des KI-Spielers mit Referenz auf den Ball, damit dieser folgen kann
            playerKI = new GamePlayerKI(ball);
            //Hinzufügen des neuen Elements zur GameSubScene
            gameSubScene.getPane().getChildren().addAll(ball, player, playerKI);

        } else {
            System.out.println(GameOptions.getGameMode());
            System.out.println("Multiplayer Erstellung 2 Spieler");
            //Anlegen des Spielers mit Referenz der Scene für den ActionListener (Tastenanschläge)
            player2 = new GamePlayer(true);
            //Hinzufügen des neuen Elements zur GameSubScene
            gameSubScene.getPane().getChildren().addAll(ball, player, player2);

        }
        //Fügt die erstellte SubScene zum GamePane hinzu
        gamePane.getChildren().add(gameSubScene);
        //Fenster-Grenzen für Paddles und Ball (Collisiondetection)
        //Focus richtig gesetzt für Up Down Keys
        gameSubScene.requestFocus();
    }

    /**
     * Methode createGameTitle
     * - Erstellt titleLabel "PONG"
     * - Setzt es an gamePane
     */
    public void createGameTitle() {
        Label titleLabel = new Label("PONG");

        titleLabel.setStyle(GAME_TITLE_LABEL_STYLE); //Farbe schwarz TODO: Ersetze durch css
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
                movePlayer();
                checkBallCollision();
                checkPoints();
            }
        };
        animationTimer.start();
    }

    /**
     * Methode keyListeners
     * - EventHandler zum Checken der gedrückten Keys
     * - Setzt entsprechend der gedrückten Keys die booleans für movePlayer()
     *
     * @param gameScene Scene des aktuellen Games
     */
    public void keyListeners(Scene gameScene) {

        gameScene.setOnKeyPressed(e -> {
            if (e.getCode() == GameOptions.getKeyCodePtwoUp()) {
                isPTwoUpKeyPressed = true;
            }
            if (e.getCode() == GameOptions.getKeyCodePtwoDown()) {
                isPTwoDownKeyPressed = true;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneUp()) {
                isPOneUpKeyPressed = true;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneDown()) {
                isPOneDownKeyPressed = true;
            }
        });

        gameScene.setOnKeyReleased(e -> {
            if (e.getCode() == GameOptions.getKeyCodePtwoUp()) {
                isPTwoUpKeyPressed = false;
            }
            if (e.getCode() == GameOptions.getKeyCodePtwoDown()) {
                isPTwoDownKeyPressed = false;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneUp()) {
                isPOneUpKeyPressed = false;
            }
            if (e.getCode() == GameOptions.getKeyCodePoneDown()) {
                isPOneDownKeyPressed = false;
            }
        });
    }

    /**
     * Methode movePlayer
     * - Bewegt Spieler abhängig vom definierten Speed
     */
    public void movePlayer() {
        if (isPOneUpKeyPressed && (player.getBoundsInParent().getMinY() > (bounds.getMinY()))) {
            player.setTranslateY(player.getTranslateY() - SPEED);
        }
        if (isPOneDownKeyPressed && (player.getBoundsInParent().getMaxY() < (bounds.getMaxY()))) {
            player.setTranslateY(player.getTranslateY() + SPEED);
        }
        if (GameOptions.getGameMode() == GameOptions.GameMode.MULTIPLAYER) {
            if (isPTwoUpKeyPressed && (player2.getBoundsInParent().getMinY() > (bounds.getMinY()))) {
                player2.setTranslateY(player2.getTranslateY() - SPEED);
            }
            if (isPTwoDownKeyPressed && (player2.getBoundsInParent().getMaxY() < (bounds.getMaxY()))) {
                player2.setTranslateY(player2.getTranslateY() + SPEED);
            }
        }
    }

    /**
     * Methode endGame
     * - Überprüft, ob das Spiel vorbei ist - also ein Spieler 0 Leben hat
     */
    private void endGame(boolean playerOneWins) {
        animationTimer.stop();
        ball.stopBallAnimation();
        ball.setVisible(false);
        gameScene.setOnKeyPressed(e -> {});
        gameScene.setOnKeyReleased(e -> {});
        createEndScoreSubScene(playerOneWins);
        //Entfernt den Menu Button unten rechts, da ein neuer mit dem EndScore eingeblendet wird
        gamePane.getChildren().remove(firstMenuButton);
    }

    /**
     * Methode checkCollision
     * - Kollisionserkennung der SpielerPaddles
     * - Abhängig von Spielmodus
     */
    private void checkCollision() {
        if (player.getTranslateY() <= -(double)(HEIGHT / 2 - player.getHeight() / 3)) {
            player.setTranslateY(player.getTranslateY() + SPEED);
        }
        if (player.getTranslateY() >= ((double)HEIGHT / 2 - player.getHeight() / 3)) {
            player.setTranslateY(player.getTranslateY() - SPEED);
        }
        if (GameOptions.getGameMode() == GameOptions.GameMode.MULTIPLAYER) {
            if (player2.getTranslateY() <= ((double)HEIGHT / 2 - player2.getHeight() / 3)) {
                player2.setTranslateY(player2.getTranslateY() + SPEED);
            }
            if (player2.getTranslateY() >= -(double)(HEIGHT / 2 - player2.getHeight() / 3)) {
                player2.setTranslateY(player2.getTranslateY() - SPEED);
            }
        }
    }

    /**
     * Methode checkPoints
     * - Setzen der Punkte, wenn Ball außerhalb des Spielbereichs fliegt
     * - Ball in zufällige Richtung starten lassen mit gamePointThread
     */
    private void checkPoints() {
        Thread gamePointThread;
        if (ball.getBoundsInParent().getMinX() < bounds.getMinX()) {
            updateScore(true);
            //Ball wieder auf Startposition bringen
            relocateBall();
            //Zufällige Richtung für nächsten Ball bestimmen
            gamePointThread = new Thread(new GamePoint());
            gamePointThread.start();
        }
        if (ball.getBoundsInParent().getMaxX() > bounds.getMaxX()) {
            updateScore(false);
            //Ball wieder auf Startposition bringen
            relocateBall();
            //Zufällige Richtung für nächsten Ball bestimmen
            gamePointThread = new Thread(new GamePoint());
            gamePointThread.start();
        }
    }

    /**
     * Methode relocateBall
     * - Zentriert den Ball in der Mitte
     * - setzt den Speed auf 0
     */
    private void relocateBall() {
        GameBall.setXSpeed(0);
        GameBall.setYSpeed(0);
        ball.setTranslateX(0);
        ball.setTranslateY(0);
    }

    /**
     * Methode createMenuButton
     * - Erstellt den Button, um zum Hauptmenü zurückzukommen
     */
    public void createMenuButton() {
        firstMenuButton = new GameButton("Main Menu", 610, HEIGHT-40);
        firstMenuButton.setOnAction(e -> {
            try {
                //AnimationTimer Befehle stoppen
                animationTimer.stop();
                //Fenstertitel zurückändern
                gameStage.setTitle("Pong by Team Zuse");
                gameScene.setOnKeyPressed(ev -> {});
                gameScene.setOnKeyReleased(ev -> {});
                gameStage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gamePane.getChildren().add(firstMenuButton);
        firstMenuButton.setFocusTraversable(false);
    }

    /**
     * Methode createScoreSubScene
     * - Erzeugen der Scene zum Anzeigen des Scores
     * - Score wird initial von GameOptions geholt
     */
    public void createScoreSubScene() {
        GameSubScene scoreSubScene = new GameSubScene(300, 100, 500, 575);
        scoreSubScene.getPane().setStyle(SCORE_SUBSCENE_STYLE);

        //Alles für das Layout
        playerOneScore = new Label(String.valueOf(GameOptions.getRounds()));
        Label dashLbl = new Label("  -  ");
        playerTwoScore = new Label(String.valueOf(GameOptions.getRounds()));

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
     * - Wenn Leben = 0 → endGame() & Stop Animation Timer
     *
     * @param playerOne Boolean, ob Spieler 1 Leben verliert
     *                  Wenn True: playerOneLivesLeft--
     *                  Wenn false: playerTwoLivesLeft--
     */
    private void updateScore(boolean playerOne) {
        if (playerOne) {
            playerOneLivesLeft--;
            playerOneScore.setText(String.valueOf(playerOneLivesLeft));
            if (playerOneLivesLeft == 0) {
                endGame(false);
            }
        } else {
            playerTwoLivesLeft--;
            playerTwoScore.setText(String.valueOf(playerTwoLivesLeft));
            if (playerTwoLivesLeft == 0) {
                endGame(true);
            }
        }
    }

    /**
     * Methode createEndScoreSubScene
     * - Erstellt EndScene
     * - Erstellt "zurück ins Hauptmenü" Button
     *
     * @param playerOneWins Boolean, ob Spieler 1 gewinnt
     *                      true: spieler 1 gewinnt
     *                      false: spieler 2 gewinnt
     */
    private void createEndScoreSubScene(boolean playerOneWins) {
        //Erstellen der SubScene mit halbtransparentem Hintergrund
        GameSubScene endGameSubScene = new GameSubScene(1280, 720, 0, 0);
        endGameSubScene.getPane().setStyle(ENDGAME_SUBSCENE_STYLE);

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
        GameButton menuButton = new GameButton("Main Menu", 600, (HEIGHT >> 1) + 40);
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
     * Methode checkBallCollision
     * - Prüft die Collision des Balls am Spieler 1
     * - Prüft abhängig von Spielmodus die Collision des Balls am Spieler 2 / KI
     */
    private void checkBallCollision() {

        Shape intersectBallOne = Shape.intersect(ball, player);
        if (intersectBallOne.getBoundsInLocal().getWidth() != -1) {
            System.out.println("Collision detected: Ball and P1");
            GameBall.setXSpeed(-(GameBall.getXSpeed()));
            GameBall.setYSpeed(-(GameBall.getYSpeed()));
            if(GameOptions.getGameMode() == GameOptions.GameMode.SINGLEPLAYER) {
                playerKI.setInAcc((int)(Math.random() * (90 - 20)) + 20);
            }
        }
        switch (GameOptions.getGameMode()) {
            case SINGLEPLAYER:
                Shape intersectBallKI = Shape.intersect(ball, playerKI);
                if (intersectBallKI.getBoundsInLocal().getWidth() != -1) {
                    System.out.println("Collision detected: Ball and KI");
                    GameBall.setXSpeed(-(GameBall.getXSpeed()));
                    GameBall.setYSpeed(-(GameBall.getYSpeed()));
                }
                break;
            case MULTIPLAYER:
                Shape intersectBallTwo = Shape.intersect(ball, player2);
                if (intersectBallTwo.getBoundsInLocal().getWidth() != -1) {
                    System.out.println("Collision detected: Ball and P2");
                    GameBall.setXSpeed(-(GameBall.getXSpeed()));
                    //GameBall.setYSpeed(-(GameBall.getYSpeed()));
                }
                break;
        }
    }
}
