package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Angel C on 07/04/2016.
 */
public class ElAhorcado extends JFrame {

    public static final int STATE_CHECKED = 1;
    public static final int STATE_UNCHECKED = 2;

    private char [] word;
    private char [] abecedario = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };
    private Character [] characters;

    private int charactersFound;
    private int intentsFailed;

    private JPanel mainContainer;
    private JPanel wordContainer;
    private JPanel abecedarianContainer;

    public ElAhorcado() {
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        initComponents();
    }

    public void initComponents() {
        word = selectWord();

        mainContainer = new JPanel(new BorderLayout());

        wordContainer = new JPanel(new GridLayout(1,word.length-1));

        characters = new Character[word.length];

        for (int i = 0; i < word.length; i++) {
            characters[i] = new Character(STATE_UNCHECKED,word[i]);
            characters[i].setText("___");
            wordContainer.add(characters[i]);
        }

        abecedarianContainer = new JPanel(new GridLayout(3,9));

        for (int i = 0; i < 27;i++) {
            Character character = new Character(STATE_UNCHECKED,abecedario[i]);
            character.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    boolean letterFound = false;

                    for (int i = 0; i < word.length; i++) {
                        if (character.getState()==STATE_UNCHECKED) {
                            if (character.getCharacter()==word[i]) {
                                characters[i].setText(java.lang.Character.toString(character.getCharacter()));
                                characters[i].setBackground(Color.BLUE);
                                charactersFound++;
                                character.setBackground(Color.BLUE);
                                letterFound = true;
                            }
                        } else if (character.getState()==STATE_CHECKED){
                            letterFound = true;
                        }
                    }
                    character.setState(STATE_CHECKED);

                    if (!letterFound) {
                        character.setBackground(Color.RED);
                        intentsFailed++;
                    }

                    checkGame();
                }
            });
            abecedarianContainer.add(character);
        }

        mainContainer.add(wordContainer, BorderLayout.CENTER);
        mainContainer.add(abecedarianContainer, BorderLayout.SOUTH);
        add(mainContainer);
    }

    private char [] selectWord() {
        String word = "quieresserminovia";
        word = word.toUpperCase();
        return word.toCharArray();
    }

    private void checkGame() {
        if (charactersFound==word.length) {
            JOptionPane.showMessageDialog(null,"Usted es Ganador");
        }

        if (intentsFailed == 5) {
            JOptionPane.showMessageDialog(null,"Usted es perdedor");
            abecedarianContainer.setVisible(false);
            for (int i = 0; i < characters.length; i++) {
                characters[i].setText(java.lang.Character.toString(characters[i].getCharacter()));
            }
        }
    }

    private class Character extends JLabel {
        private int state;
        private char character;

        public Character(int state, char character) {
            setState(state);
            setCharacter(character);
            setPreferredSize(new Dimension(50,50));
            setHorizontalAlignment(CENTER);
            setOpaque(true);
            setBackground(Color.WHITE);
            setText(java.lang.Character.toString(character));
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public char getCharacter() {
            return character;
        }

        public void setCharacter(char character) {
            this.character = character;
        }
    }

    public static void main (String [] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ElAhorcado().setVisible(true);
            }
        });
    }

    /**

El Ahorcado. (Public class ElAhorcado)
Seleccionar Palabra (Llamar a la base de datos y seleccionar palabra aleatoria).
Separar en caracteres (char [] word)
Presentar las letras a seleccionar
Al presionar una letra verificar si la palabra (Array de arriba)
Si la letra existe desbloquearla
Sino, subir variable de intentos fallidos
Si completa la palabra presentar un JOptionPane()

     */
}
