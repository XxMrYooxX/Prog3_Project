package de.htwsaar.pong.zuse.model;

import de.htwsaar.pong.zuse.controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Objects;

public class LaunchGame extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        //Angeben des Root Layouts (in diesem Fall Main Menu)
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml")));
        //Starten der Scene mit dem angegebenen Layout (Auflösung 1280x720)
        stage.setScene(new Scene(root, 1280, 720));
        //Fenster nicht veränderbar
        stage.setResizable(false);
        stage.setMaximized(false);
        //Fenster anzeigen
        stage.show();

        // Ende der "Main" ->>> Änderungen an der Szene werden jeweils von den Controllern ausgeführt (siehe /controller)


        /* ALTER CODE
        MainMenuController menuPresentation = new MainMenuController();
        stage = menuPresentation.getMenuStage();
        stage.setTitle("PONG by PROG3 Team Zuse");
        stage.show();
         */
    }
    public static void launchGame(String[] args){
        launch(args);
    }
}
