package com.olek.typinggame;

import com.olek.typinggame.enums.GameDifficulty;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    private GameDifficulty gameDifficulty;
    private Stack<String> randomWords = new Stack<>();

    @FXML
    AnchorPane gameView;

    @FXML
    TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rectangle clip = new Rectangle(gameView.getPrefWidth(), gameView.getPrefHeight());
        gameView.setClip(clip);

        List<String> allWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/wordlist.csv"))){
            String line;

            while((line = reader.readLine())!=null) {
                allWords.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.shuffle(allWords);

        for(int i = 0; i < 100;i++)
            randomWords.add(allWords.get(i));

    }

    public void enterText(ActionEvent e) {
        String text = textField.getText();
        System.out.println(text);
        spawnWord(text);
    }

    public void spawnWord(String text) {
        Label word = new Label(randomWords.pop());

        word.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 18));
        word.setTextFill(Color.WHITE);

        word.setLayoutX(100);
        word.setLayoutY(getRandomYPosition());

        gameView.getChildren().add(word);
    }

    public int getRandomYPosition(){
        double maxHeight = gameView.getHeight();

        return (int)(Math.random()*((int)((maxHeight+40)/40)))*40;
    }

    public int calculateWPM(int charCount, int secondsElapsed){
        double words = charCount/5.0;
        double minutes = secondsElapsed/60.0;

        return (int)(words/minutes);
    }
}
