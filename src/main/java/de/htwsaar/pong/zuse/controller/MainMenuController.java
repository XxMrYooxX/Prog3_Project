package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.ButtonHandler;
import de.htwsaar.pong.zuse.model.GameButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MainMenuController {

    //Initiliasieren der Label, damit diese zum Finden der Scene verwendet werden können (siehe unten)
    //Labelnamen entsprechen den ID's , welche in der FXML Datei festgelegt wurden (Pflicht)
    @FXML
    private Label mm_label_howtoplay;
    @FXML
    private Label mm_label_playnow;
    @FXML
    private Label mm_label_options;

    //Methode zum Wechseln in die How To Play Scene
    @FXML
    public void goToHowToPlay(javafx.scene.input.MouseEvent event) throws IOException { //Reagiert auf OnMouseClick Event, festgelegt in der mainmenu.fxml)
        System.out.println("go to how to play scene");
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        mm_label_howtoplay.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/howtoplay.fxml"))));
        //Event abgeschlossen und "verbraucht"
        event.consume();
    }

    //Exakt wie goToHowToPlay (siehe Kommentare oben)
    @FXML
    public void goToOptions(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("go to Options scene");
        mm_label_options.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/options.fxml"))));
        event.consume();
    }

    //Exakt wie goToHowToPlay (siehe Kommentare oben)
    @FXML
    public void goToPlay(javafx.scene.input.MouseEvent event) throws IOException {
        System.out.println("go to gamemode scene");
        mm_label_playnow.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/choosegamemode.fxml"))));
        event.consume();
    }

    @FXML
    public void goToExit(javafx.scene.input.MouseEvent event) {
        event.consume();
        //Programm wird mit Return Code 0 beendet.
        System.exit(0);
    }

    /* ALTER CODE
    public Stage menuStage;
    private AnchorPane menuPane;
    private Scene menuScene;
    private static final String MAINMENUBUTTON = "-fx-border-width: 2px; -fx-text-fill: white; -fx-border-color: #145374; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";
    public MainMenuController() throws FileNotFoundException{
        menuStage = new Stage();
        menuStage.setTitle("PONG Game Team Zuse");
        menuStage.setMaximized(false);  //weil kb auf relative Groessen
        menuStage.setResizable(false); //weil kb auf relative Groessen
        menuStage.setWidth(1280); //weil kb auf relative Groessen
        menuStage.setHeight(720); //weil kb auf relative Groessen
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
     */
}
