package de.htwsaar.pong.zuse.model;

import de.htwsaar.pong.zuse.controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class LaunchGame extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        MainMenuController menuPresentation = new MainMenuController();
        stage = menuPresentation.getMenuStage();
        stage.setTitle("PONG by PROG3 Team Zuse");
        stage.show();
    }
    public static void launchGame(){
        launch();
    }
}
