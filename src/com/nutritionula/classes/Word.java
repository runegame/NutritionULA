package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Angel C on 05/04/2016.
 */

public class Word extends JLabel {

    public static final int WORD_SELECTED = 0;
    public static final int WORD_UNSELECTED = 1;
    public static final int WORD_FOUND = 2;
    public static final int WORD_LOST = 3;

    private int wordCheckedState;
    private int wordSearchState;


    public Word (String word) {
        setOpaque(true);
        setWordCheckedState(WORD_UNSELECTED);
        setBackground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setText(word);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshWord(getWordCheckedState());
            }
        });
    }

    public void refreshWord(int wordCheckedState) {

        switch (wordCheckedState) {
            case WORD_UNSELECTED:
                setWordCheckedState(WORD_SELECTED);
                setBackground(Color.BLUE);
                break;
            case WORD_SELECTED:
                setWordCheckedState(WORD_UNSELECTED);
                setBackground(Color.WHITE);
        }
    }

    public int getWordCheckedState() {
        return wordCheckedState;
    }

    public void setWordCheckedState(int wordCheckedState) {
        this.wordCheckedState = wordCheckedState;
    }

    public int getWordSearchState() {
        return wordSearchState;
    }

    public void setWordSearchState(int wordSearchState) {
        this.wordSearchState = wordSearchState;
    }
}
