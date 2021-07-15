package de.htwsaar.pong.zuse.model;

import javafx.scene.control.Button;

public class GameButton extends Button {

    public GameButton(String buttonText, int x, int y) {
        super();
        //Festlegen des Button Layouts
        this.setText(buttonText);
        this.setScaleX(2);
        this.setScaleY(2);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

}