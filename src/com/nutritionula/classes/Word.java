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
    public static final int WORD_FINDABLE = 4;
    public static final int WORD_INFINDABLE = 5;

    private int wordCheckedState;
    private int wordSearchState;
    private int wordFindableState;


    public Word (char word, int wordFindableState) {
        setOpaque(true);
        setWordCheckedState(WORD_UNSELECTED);
        setWordFindableState(wordFindableState);
        setBackground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setText(Character.toString(word));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshWord(getWordCheckedState());
                if (getWordFindableState()==WORD_FINDABLE) {
                    JOptionPane.showMessageDialog(null,"LETRA PERTENECE A UNA PALABRA");
                }
                else if (getWordFindableState()==WORD_INFINDABLE) {
                    JOptionPane.showMessageDialog(null,"LETRA NO PERTENECE A UNA PALABRA");
                }
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

    public int getWordFindableState() {
        return wordFindableState;
    }

    public void setWordFindableState(int wordFindableState) {
        this.wordFindableState = wordFindableState;
    }
}
