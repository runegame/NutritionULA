package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import static com.nutritionula.classes.Word.*;
import static javax.swing.JOptionPane.YES_OPTION;

/**
 * Created by Angel C on 05/04/2016.
 */
public class WordSearch extends JFrame {

    JPanel jPanelContenedor, jPanelSopaDeLetras, jPanelPalabras;

    WordSearchGenerator generator;

    private String[] wordsArray = {"alimentacion","calabozo","facebook","merida"};

    private int wordsFounds = 0;

    private int [] lettersFounds = new int[4];

    private JLabel [] labelsWords = new JLabel[4];

    public WordSearch() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        initComponents();
    }

    public void initComponents() {
        generator = new WordSearchGenerator();

        sortWordsByLength();

        Letter[][] puzzle = generator.generate(wordsArray);

        jPanelContenedor = new JPanel(new BorderLayout());
        jPanelSopaDeLetras = new JPanel();
        jPanelPalabras = new JPanel(new BorderLayout());

        jPanelSopaDeLetras.setOpaque(true);
        jPanelSopaDeLetras.setBackground(Color.BLACK);
        jPanelSopaDeLetras.setPreferredSize(new Dimension(768,768));
        jPanelSopaDeLetras.setBorder(BorderFactory.createLineBorder(Color.WHITE,20));
        jPanelSopaDeLetras.setLayout(new GridLayout(20,20,0,0));

        for (Letter[] line : puzzle) {
            for (Letter c : line) {
                Word word = new Word(c.getCharacther(),c.getNumberOfWord(),c.getFindable());
                jPanelSopaDeLetras.add(word);

                word.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (word.getWordSearchState()==WORD_LOST) {
                            word.refreshWord(word.getLetterCheckedState());
                            if (word.getLetterFindableState()==LETTER_FINDABLE) {
                                lettersFounds[word.getNumberOfWord()]++;
                                if (lettersFounds[word.getNumberOfWord()]==wordsArray[word.getNumberOfWord()].length()) {
                                    labelsWords[word.getNumberOfWord()].setBackground(Color.BLUE);
                                    wordsFounds++;
                                    if (wordsFounds==labelsWords.length) {
                                        JOptionPane.showMessageDialog(null,"Usted es un cazapalabras");
                                    }
                                }
                                word.setWordSearchState(WORD_FOUND);
                            } else if (word.getLetterFindableState()==LETTER_INFINDABLE) {
                                // JOptionPane.showMessageDialog(null,word.getNumberOfWord());
                            }
                        }
                    }
                });
            }
        }

        jPanelPalabras.setPreferredSize(new Dimension(598,768));
        jPanelPalabras.setBackground(Color.WHITE);

        JLabel jLabelTitle = new JLabel("Sopa de Letras");
        registerFont();
        jLabelTitle.setFont(new Font("Mickey",Font.PLAIN,50));
        jLabelTitle.setBorder(BorderFactory.createEmptyBorder(200,0,0,0));
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel jPanel = new JPanel(new GridLayout(4,1));
        jPanel.setOpaque(true);
        jPanel.setBackground(Color.WHITE);

        for (int i = 0;i < wordsArray.length; i++) {
            labelsWords[i] = new JLabel(wordsArray[i]);
            labelsWords[i] .setOpaque(true);
            labelsWords[i] .setBackground(Color.WHITE);
            labelsWords[i] .setHorizontalAlignment(SwingConstants.CENTER);
            labelsWords[i] .setFont(new Font("Mickey",Font.PLAIN, 15));
            jPanel.add(labelsWords[i]);
        }

        jPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        JLabel jLabelExit = new JLabel("Salir");
        jLabelExit.setForeground(Color.RED);
        jLabelExit.setFont(new Font("Mickey",Font.PLAIN,50));
        jLabelExit.setBorder(BorderFactory.createEmptyBorder(0,0,200,0));
        jLabelExit.setHorizontalAlignment(SwingConstants.CENTER);

        jLabelExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                JOptionPane.showConfirmDialog(null,"¿Quieres salir de la sopa de letras?","Salir",dialogButton);

                if (dialogButton == YES_OPTION) {
                    exit();
                }
            }
        });


        jPanelPalabras.add(jLabelTitle,BorderLayout.NORTH);
        jPanelPalabras.add(jPanel,BorderLayout.CENTER);
        jPanelPalabras.add(jLabelExit,BorderLayout.SOUTH);

        jPanelContenedor.add(jPanelSopaDeLetras, BorderLayout.CENTER);
        jPanelContenedor.add(jPanelPalabras, BorderLayout.WEST);

        add(jPanelContenedor);
    }

    public void registerFont() {
        try {
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/com/nutritionula/fonts/Mickey.ttf")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
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

    public static void main (String [] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordSearch();
            }
        });
    }

    public void exit() {
        new Start().setVisible(true);
        this.dispose();
    }
}
