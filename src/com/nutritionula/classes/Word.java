package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Angel C on 05/04/2016.
 */

public class Word extends JLabel {

    public static final int LETTER_SELECTED = 0;
    public static final int LETTER_UNSELECTED = 1;
    public static final int WORD_FOUND = 2;
    public static final int WORD_LOST = 3;
    public static final int LETTER_FINDABLE = 4;
    public static final int LETTER_INFINDABLE = 5;

    private int letterCheckedState;
    private int wordSearchState;
    private int letterFindableState;

    private int numberOfWord;

    public Word (char letter,int numberOfWord, int wordFindableState) {
        setOpaque(true);
        setWordSearchState(WORD_LOST);
        setLetterCheckedState(LETTER_UNSELECTED);
        setLetterFindableState(wordFindableState);
        setBackground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setText(Character.toString(letter));
        setNumberOfWord(numberOfWord);
    }

    public void refreshWord(int wordCheckedState) {

        switch (wordCheckedState) {
            case LETTER_UNSELECTED:
                setLetterCheckedState(LETTER_SELECTED);
                setBackground(Color.BLUE);
                break;
            case LETTER_SELECTED:
                setLetterCheckedState(LETTER_UNSELECTED);
                setBackground(Color.WHITE);
        }
    }

    public int getLetterCheckedState() {
        return letterCheckedState;
    }

    public void setLetterCheckedState(int letterCheckedState) {
        this.letterCheckedState = letterCheckedState;
    }

    public int getWordSearchState() {
        return wordSearchState;
    }

    public void setWordSearchState(int wordSearchState) {
        this.wordSearchState = wordSearchState;
    }

    public int getLetterFindableState() {
        return letterFindableState;
    }

    public void setLetterFindableState(int wordFindableState) {
        this.letterFindableState = wordFindableState;
    }

    public int getNumberOfWord() {
        return numberOfWord;
    }

    public void setNumberOfWord(int numberOfWord) {
        this.numberOfWord = numberOfWord;
    }
}
