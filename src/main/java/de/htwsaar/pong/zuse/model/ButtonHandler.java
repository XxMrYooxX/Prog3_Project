package de.htwsaar.pong.zuse.model;

import de.htwsaar.pong.zuse.controller.GameViewController;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ButtonHandler {
    public static void MenuSingleplayer(Stage menuStage){
        menuStage.hide();
        try{
            GameViewController gameViewSingle = new GameViewController(GameHandler.GameMode.SINGLEPLAYER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void MenuMultiplayer(Stage menuStage){
        menuStage.hide();
        try{
            GameViewController gamePanelMulti = new GameViewController(GameHandler.GameMode.MULTIPLAYER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void MenuOptions(Stage menuStage){
        menuStage.hide();
        /*try{

            // ToDo: Einfuegen sobald Handler fuer Options existiert
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/ //ToDo: einklammern sobald Handler mit Exceptions existiert
    }
    public static void MenuExit(Stage menuStage){
        menuStage.hide();
        //ToDo: Programm ordnungsgemaess beenden lassen
    }
}
