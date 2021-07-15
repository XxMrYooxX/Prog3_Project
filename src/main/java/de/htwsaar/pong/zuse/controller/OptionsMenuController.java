package de.htwsaar.pong.zuse.controller;

import de.htwsaar.pong.zuse.model.GameOptions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class OptionsMenuController {


    //Initiliasieren der Label, damit diese zum Finden der Scene verwendet werden können (siehe unten)
    //Labelnamen entsprechen den ID's , welche in der FXML Datei festgelegt wurden (Pflicht)
    @FXML
    private Label o_label_back;
    @FXML
    private Label o_label_pone_up;
    @FXML
    private Label o_label_pone_down;
    @FXML
    private Label o_label_ptwo_up;
    @FXML
    private Label o_label_ptwo_down;

    //Methode zum Wechseln in die Main Menu Scene
    @FXML
    public void goToMainMenu(javafx.scene.input.MouseEvent event) throws IOException { //Reagiert auf OnMouseClick Event, festgelegt in der options.fxml)
        System.out.println("go to main menu");
        //Findet über das Label (oben deklariert) die Root Scene und lädt dort die neue .fxml rein
        o_label_back.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainmenu.fxml"))));
        //Event abgeschlossen und "verbraucht"
        event.consume();
    }

    @FXML
    public void setPoneKeyUp(javafx.scene.input.MouseEvent event) throws  IOException {
        o_label_pone_up.getScene().setOnKeyPressed(e -> {
            System.out.println("setPoneKeyUp");
            System.out.println(e.getCode());
            o_label_pone_up.setText("ausgewählter Button: "+ e.getCode());
            GameOptions.setKeyCodePoneUp(e.getCode());
        });
    }

    @FXML
    public void setPoneKeyDown(javafx.scene.input.MouseEvent event) throws  IOException {
        o_label_pone_down.getScene().setOnKeyPressed(e -> {
            System.out.println("setPoneKeyDown");
            System.out.println(e.getCode());
            o_label_pone_down.setText("ausgewählter Button: "+ e.getCode());
            GameOptions.setKeyCodePoneDown(e.getCode());
        });
    }

    @FXML
    public void setPtwoKeyUp(javafx.scene.input.MouseEvent event) throws  IOException {
        o_label_ptwo_up.getScene().setOnKeyPressed(e -> {
            System.out.println("setPtwoKeyUp");
            System.out.println(e.getCode());
            o_label_ptwo_up.setText("ausgewählter Button: "+ e.getCode());
            GameOptions.setKeyCodePtwoUp(e.getCode());
        });
    }

    @FXML
    public void setPtwoKeyDown(javafx.scene.input.MouseEvent event) throws  IOException {
        o_label_ptwo_down.getScene().setOnKeyPressed(e -> {
            System.out.println("setPtwoKeyDown");
            System.out.println(e.getCode());
            o_label_ptwo_down.setText("ausgewählter Button: "+ e.getCode());
            GameOptions.setKeyCodePtwoDown(e.getCode());
        });
    }

}
