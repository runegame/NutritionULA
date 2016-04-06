package com.nutritionula.classes;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import static com.nutritionula.classes.Word.WORD_FINDABLE;
import static com.nutritionula.classes.Word.WORD_INFINDABLE;

/**
 * Created by Angel C on 05/04/2016.
 */
public class WordSearchGenerator {

    private String[] wordsArray;
    private Letter [][] puzzleLetters;
    private char[][] puzzle;
    private int size;
    private Random random;

    public Letter[][] generate(String[] wa) {
        wordsArray = wa;
        init();

        int w = 0;

        while (w < wordsArray.length) {
            String word = wordsArray[w];
            int i = Math.abs(random.nextInt() % size);
            int j = Math.abs(random.nextInt() % size);
            int startChar = Math.abs(random.nextInt() % word.length());
            boolean direction = random.nextBoolean();

            if (puzzle[i][j] == 0 || puzzle[i][j] == word.charAt(startChar)) {
                if (placeWord(word, i, j, startChar, direction)) {
                    w++;
                }
            }
        }

        fillTheRest();

        return puzzleLetters;
    }

    private boolean placeWord(String word, int i, int j, int startChar,
                              boolean direction) {
        if (!fits(word, i, j, startChar, direction)) {
            return false;
        }

        int start;

        if (direction) {
            start = i - startChar;

            for (int k = start; k < start + word.length(); k++) {
                if (puzzle[k][j] != 0 && puzzle[k][j] != word.charAt(k - start)) {
                    return false;
                }
            }

            // Aqui es donde se le asigna el valor a la matriz
            for (int k = start; k < start + word.length(); k++) {
                puzzle[k][j] = word.charAt(k - start);
                puzzleLetters[k][j] = new Letter();
                puzzleLetters[k][j].setCharacther(word.charAt(k-start));
                puzzleLetters[k][j].setFindable(WORD_FINDABLE);
            }
        } else {
            start = j - startChar;

            for (int k = start; k < start + word.length(); k++) {
                if (puzzle[i][k] != 0 && puzzle[i][k] != word.charAt(k - start)) {
                    return false;
                }
            }

            // Aqui es donde se le asigna el valor a la matriz
            for (int k = start; k < start + word.length(); k++) {
                puzzle[i][k] = word.charAt(k - start);
                puzzleLetters[i][k] = new Letter();
                puzzleLetters[i][k].setCharacther(word.charAt(k-start));
                puzzleLetters[i][k].setFindable(WORD_FINDABLE);
            }
        }

        return true;
    }

    private boolean fits(String word, int i, int j, int startChar,
                         boolean direction) {
        if (direction && i - startChar >= 0
                && i - startChar + word.length() < size) {
            return true;
        }

        if (!direction && j - startChar >= 0
                && j - startChar + word.length() < size) {
            return true;
        }

        return false;
    }

    private void fillTheRest() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle[i][j] == 0) {
                    puzzle[i][j] = alphabet.charAt(Math.abs(random.nextInt()
                            % alphabet.length()));
                    puzzleLetters[i][j] = new Letter();
                    puzzleLetters[i][j].setCharacther(alphabet.charAt(Math.abs(random.nextInt()
                            % alphabet.length())));
                    puzzleLetters[i][j].setFindable(WORD_INFINDABLE);
                }
            }
        }
    }

    private void init() {
        sortWordsByLength();
        size = 20;
        puzzle = new char[size][size];
        puzzleLetters = new Letter[size][size];
        random = new Random();

        for (int i = 0; i < wordsArray.length; i++) {
            wordsArray[i] = wordsArray[i].toUpperCase();
        }
    }

    private void sortWordsByLength() {
        Arrays.sort(wordsArray, new Comparator<String>() {
            @Override
            public int compare(String s0, String s1) {
                int l0 = s0.length();
                int l1 = s1.length();
                return l0 > l1 ? -1 : (l0 < l1 ? 1 : 0);
            }
        });
    }
}
