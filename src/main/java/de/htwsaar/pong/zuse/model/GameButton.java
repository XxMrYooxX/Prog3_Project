package de.htwsaar.pong.zuse.model;

import javafx.scene.control.Button;

public class GameButton extends Button {
    public GameButton(String buttonText, String style, int x, int y){
        super(); //find ich super!
        this.setText(buttonText);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setStyle(style);
    }
}
