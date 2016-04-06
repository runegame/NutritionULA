package com.nutritionula.classes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Angel C on 05/04/2016.
 */
public class WordSearch extends JFrame {

    JPanel jPanelContenedor, jPanelSopaDeLetras, jPanelPalabras;

    public WordSearch() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        initComponents();
    }

    public void initComponents() {
        jPanelContenedor = new JPanel(new BorderLayout());
        jPanelSopaDeLetras = new JPanel();
        jPanelPalabras = new JPanel();

        jPanelSopaDeLetras.setPreferredSize(new Dimension(768,768));
        jPanelSopaDeLetras.setLayout(new GridLayout(20,20,0,0));

        for (int i = 0; i < 400; i++) {
            jPanelSopaDeLetras.add(new Word("A"));
        }

        jPanelPalabras.setPreferredSize(new Dimension(598,768));
        jPanelPalabras.setBackground(Color.black);

        jPanelContenedor.add(jPanelSopaDeLetras, BorderLayout.CENTER);
        jPanelContenedor.add(jPanelPalabras, BorderLayout.WEST);

        add(jPanelContenedor);
    }

    public static void main (String [] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordSearch();
            }
        });
    }
}
