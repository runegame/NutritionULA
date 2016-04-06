package com.nutritionula.classes;

/**
 * Created by Angel C on 05/04/2016.
 */
public class Letter {

    private char characther;
    private int numberOfWord;
    private String word;
    private int findable;

    public Letter() {
        this.characther = 'a';
        this.numberOfWord = 0;
        this.word = "";
        this.findable = Word.LETTER_INFINDABLE;
    }

    public Letter(char characther,int numberOfWord, String word, int findable) {
        setCharacther(characther);
        setNumberOfWord(numberOfWord);
        setWord(word);
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public int getNumberOfWord() {
        return numberOfWord;
    }

    public void setNumberOfWord(int numberOfWord) {
        this.numberOfWord = numberOfWord;
    }
}
