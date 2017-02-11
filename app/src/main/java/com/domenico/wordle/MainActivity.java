package com.domenico.wordle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.domenico.wordle.UI.HDTMButton;
import com.domenico.wordle.UI.HDTMTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GuessTheWord game;
    private WordList wordList;
    private String words_table;
    private int dictionaryLength;
    private int rowToGuess;
    private HDTMTextView txtWordToGuess;
    private HDTMTextView txtMeaning;
    private HDTMButton btn11;
    private HDTMButton btn12;
    private HDTMButton btn13;
    private HDTMButton btn14;
    private HDTMButton btn15;
    private HDTMButton btn16;
    private HDTMButton btn21;
    private HDTMButton btn22;
    private HDTMButton btn23;
    private HDTMButton btn24;
    private HDTMButton btn25;
    private HDTMButton btn26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        wordList = new WordList(this);
        words_table = WordList.MEDIUM_WORDS_TABLE;
        dictionaryLength = wordList.getEasyWords();
        rowToGuess = (int) (Math.random() * dictionaryLength) + 1;
        initGame();
    }

    public void initLayout() {
        txtWordToGuess = (HDTMTextView) findViewById(R.id.txtWordToGuess);
        txtMeaning = (HDTMTextView) findViewById(R.id.description);
        btn11 = (HDTMButton) findViewById(R.id.btn11);
        btn12 = (HDTMButton) findViewById(R.id.btn12);
        btn13 = (HDTMButton) findViewById(R.id.btn13);
        btn14 = (HDTMButton) findViewById(R.id.btn14);
        btn15 = (HDTMButton) findViewById(R.id.btn15);
        btn16 = (HDTMButton) findViewById(R.id.btn16);
        btn21 = (HDTMButton) findViewById(R.id.btn21);
        btn22 = (HDTMButton) findViewById(R.id.btn22);
        btn23 = (HDTMButton) findViewById(R.id.btn23);
        btn24 = (HDTMButton) findViewById(R.id.btn24);
        btn25 = (HDTMButton) findViewById(R.id.btn25);
        btn26 = (HDTMButton) findViewById(R.id.btn26);
    }

    public void initGame() {
        game = new GuessTheWord();
        game.setMaxNumberOfLetters(12);
        game.setWordToGuess(wordList.getWord(rowToGuess, words_table));
        while (game.getWordToGuess().length() >= game.getMaxNumberOfLetters()) {
            rowToGuess = (int) (Math.random() * dictionaryLength) + 1;
            game.setWordToGuess(wordList.getWord(rowToGuess, words_table));
        }
        game.setMeaning(wordList.getMeaning(rowToGuess, words_table));
        txtMeaning.setText(game.getMeaning());
        Log.i("Main", "Parola da indovinare: "+game.getWordToGuess()+"\nDefinizione: "+game.getMeaning());
        game.generateLetters();
        setKeyboardUI();
        /*wordToGuess = wordList.getWord(rowToGuess, words_table);
        while (wordToGuess.length() >= 12) {
            rowToGuess++;
            wordToGuess = wordList.getWord(rowToGuess, words_table);
        }
        meaning = wordList.getMeaning(rowToGuess, words_table);
        txtMeaning.setText(meaning);
        Log.i("Main", "Parola da indovinare: "+wordToGuess+"\nDefinizione: "+meaning);
        generateLetters();
        setKeyboardUI();*/
    }

    /*public void generateLetters() {
        int missingLetters = 12 - wordToGuess.length();
        String lettersToRandomize = "abcdefghijklmnopqrstuvwxyz";
        letters = wordToGuess;
        for (int i=0; i<missingLetters; i++) {
            int rand = (int) (Math.random() * lettersToRandomize.length());
            letters += lettersToRandomize.charAt(rand);
        }
        Log.i("Main", letters+ "\tLunghezza: "+letters.length());

        ArrayList<Character> tempArLis = new ArrayList<Character>();
        for (int i=0; i<letters.length(); i++) {
            tempArLis.add(letters.charAt(i));
        }
        Log.i("Main", String.valueOf(tempArLis));
    }*/

    public void setKeyboardUI(){
        btn11.setText(""+game.getLetters().charAt(0));
        btn12.setText(""+game.getLetters().charAt(1));
        btn13.setText(""+game.getLetters().charAt(2));
        btn14.setText(""+game.getLetters().charAt(3));
        btn15.setText(""+game.getLetters().charAt(4));
        btn16.setText(""+game.getLetters().charAt(5));
        btn21.setText(""+game.getLetters().charAt(6));
        btn22.setText(""+game.getLetters().charAt(7));
        btn23.setText(""+game.getLetters().charAt(8));
        btn24.setText(""+game.getLetters().charAt(9));
        btn25.setText(""+game.getLetters().charAt(10));
        btn26.setText(""+game.getLetters().charAt(11));
    }

    public void keyBoardOnCick(View view) {
        HDTMButton button = (HDTMButton) view;
        String buttonText = (String) button.getText();
        txtWordToGuess.setText(txtWordToGuess.getText()+buttonText.toUpperCase());
        button.setVisibility(View.INVISIBLE);
        if (game.isGameEnded((String)txtWordToGuess.getText())) {
            Toast.makeText(this, "Hai indovinato!", Toast.LENGTH_SHORT).show();
            next();
        }
    }

    public void next(){
        if (rowToGuess+1 >= dictionaryLength)
            rowToGuess = 1;
        else
            rowToGuess++;
        reset();
        initGame();
    }

    public void txtWordToGuessOnClick(View view) {
        reset();
    }

    public void backOnClick(View view) {
        back();
    }

    public void reset() {
        txtWordToGuess.setText("");
        btn11.setVisibility(View.VISIBLE);
        btn12.setVisibility(View.VISIBLE);
        btn13.setVisibility(View.VISIBLE);
        btn14.setVisibility(View.VISIBLE);
        btn15.setVisibility(View.VISIBLE);
        btn16.setVisibility(View.VISIBLE);
        btn21.setVisibility(View.VISIBLE);
        btn22.setVisibility(View.VISIBLE);
        btn23.setVisibility(View.VISIBLE);
        btn24.setVisibility(View.VISIBLE);
        btn25.setVisibility(View.VISIBLE);
        btn26.setVisibility(View.VISIBLE);
    }

    public void back() {
        String temp = "";
        for (int i=0; i<txtWordToGuess.getText().length()-1; i++)
            temp += txtWordToGuess.getText().charAt(i);
        txtWordToGuess.setText(temp);
    }
}
