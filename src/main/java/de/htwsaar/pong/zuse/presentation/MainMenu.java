package de.htwsaar.pong.zuse.presentation;

import de.htwsaar.pong.zuse.model.ButtonHandler;
import de.htwsaar.pong.zuse.model.GameButton;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MainMenu{
    protected Stage menuStage;
    private AnchorPane menuPane;
    private Scene menuScene;
    private static final String MAINMENUBUTTON = "-fx-border-width: 2px; -fx-text-fill: white; -fx-border-color: #145374; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";
    public MainMenu() throws FileNotFoundException{
        menuStage = new Stage();
        menuStage.setTitle("PONG Game Team Zuse");
        menuStage.setMaximized(false);  //weil kb auf relative Groessen TODO: fix this pls
        menuStage.setResizable(false); //weil kb auf relative Groessen TODO: fix this pls
        menuStage.setWidth(1280); //weil kb auf relative Groessen TODO: fix this pls
        menuStage.setHeight(720); //weil kb auf relative Groessen TODO: fix this pls
        menuPane = new AnchorPane();
        menuPane.setStyle("-fx-background-color: BLACK;");
        menuScene = new Scene(menuPane);
        menuStage.setScene(menuScene);
        mainMenuButtons();
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    private void mainMenuButtons() throws FileNotFoundException {
        GameButton buttonSinglePlayer = new GameButton("Single Player", MAINMENUBUTTON, 0, 0);
        GameButton buttonMultiPlayer = new GameButton("Multiplayer", MAINMENUBUTTON, 50, 50);
        GameButton buttonSettingsMenu = new GameButton("Settings", MAINMENUBUTTON, 100, 100);
        GameButton buttonExit = new GameButton("Exit", MAINMENUBUTTON, 150, 150);

        buttonSinglePlayer.setOnAction(e -> ButtonHandler.MenuSingleplayer(menuStage));
        buttonMultiPlayer.setOnAction(e -> ButtonHandler.MenuMultiplayer(menuStage));
        buttonSettingsMenu.setOnAction(e -> ButtonHandler.MenuOptions(menuStage));
        buttonExit.setOnAction(e -> ButtonHandler.MenuExit(menuStage));

        menuPane.getChildren().addAll(buttonSinglePlayer, buttonMultiPlayer, buttonSettingsMenu, buttonExit);
    }
}
