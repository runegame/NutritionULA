package com.nutritionula.classes;

/**
 * Created by Angel C on 05/04/2016.
 */
public class Letter {

    private char characther;
    private int findable;

    public Letter() {
        this.characther = 'a';
        this.findable = Word.WORD_INFINDABLE;
    }

    public Letter(char characther, int findable) {
        setCharacther(characther);
        setFindable(findable);
    }

    public char getCharacther() {
        return characther;
    }

    public void setCharacther(char characther) {
        this.characther = characther;
    }

    public int getFindable() {
        return findable;
    }

    public void setFindable(int findable) {
        this.findable = findable;
    }
}
