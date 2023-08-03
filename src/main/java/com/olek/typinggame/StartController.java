package com.olek.typinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGameEasy(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gamepanel.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameNormal(ActionEvent e){

    }

    public void switchToGameHard(ActionEvent e){

    }
}
