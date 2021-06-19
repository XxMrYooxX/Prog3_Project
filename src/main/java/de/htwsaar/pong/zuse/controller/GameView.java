package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GameView {

    private Stage previousStage;

    private Stage gameStage;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage menuStage;

    //Styles
    private static String BACKGROUNDBLACKSTYLE = "-fx-background-color: BLACK;";
    public static int GAMEVIEWHEIGHT = 720;
    public static int GAMEVIEWWIDTH = 1280;



    public GameView(GameHandler.GameMode gameMode) throws FileNotFoundException, InterruptedException {
        gameStage = new Stage();
        switch (gameMode){
            case SINGLEPLAYER:
                gameStage.setTitle("Pong Singleplayer | Team Zuse");
                break;
            case MULTIPLAYER:
                gameStage.setTitle("Pong Multiplayer | Team Zuse");
                break;
        }
        menuStage.setMaximized(false);  //weil kb auf relative Groessen TODO: fix this pls
        menuStage.setResizable(false); //weil kb auf relative Groessen TODO: fix this pls
        menuStage.setWidth(GAMEVIEWWIDTH); //weil kb auf relative Groessen TODO: fix this pls
        menuStage.setHeight(GAMEVIEWHEIGHT); //weil kb auf relative Groessen TODO: fix this pls
        gamePane = new AnchorPane();
        gamePane.setStyle(BACKGROUNDBLACKSTYLE);
        gameScene = new Scene(gamePane);
        gameStage.setScene(gameScene);
        GameHandler.createGameSubScene(gameScene, gamePane, gameMode);
        GameHandler.createScoreSubScene();
        GameHandler.createGameTimer();
        GameHandler.createGameTitle();
        GameHandler.createMenuButton();
    }

    public Scene getGameScene() {
        return gameScene;
    }
}
