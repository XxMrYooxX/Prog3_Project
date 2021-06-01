package de.htwsaar.pong.zuse.model;

import de.htwsaar.pong.zuse.presentation.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class LaunchGame extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        MainMenu menuPresentation = new MainMenu();
        stage = menuPresentation.getMenuStage();
        stage.setTitle("PONG by PROG3 Team Zuse");
        stage.show();
    }
    public static void launchGame(){
        launch();
    }
}
