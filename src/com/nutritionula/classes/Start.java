package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Angel C on 04/04/2016.
 */
public class Start extends JFrame {

    JLabel titleLabel;
    JPanel startContainer;

    public Start() {
        setUndecorated(true);
        initComponents();
    }

    public void initComponents() {
        registerFont();
        getContentPane().setLayout(new BorderLayout());
        titleLabel = new JLabel("AQUI VA EL TITULO");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(300,300));
        titleLabel.setBackground(Color.BLUE);
        titleLabel.setOpaque(true);
        getContentPane().add(titleLabel,BorderLayout.NORTH);

        startContainer = new JPanel(new GridLayout(4,1,10,10));
        JLabel jLabel1 = new JLabel("Memoria Nutritiva");
        JLabel jLabel2 = new JLabel("Arma Tu Plato");
        JLabel jLabel3 = new JLabel("Conoce Los Alimentos");
        JLabel jLabel4 = new JLabel("Salir");

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel1.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel2.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel3.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel4.setFont(new Font("Mickey",Font.PLAIN,30));

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iniciarMemoria();
            }
        });

        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        startContainer.add(jLabel1);
        startContainer.add(jLabel2);
        startContainer.add(jLabel3);
        startContainer.add(jLabel4);

        getContentPane().add(startContainer,BorderLayout.CENTER);

        setExtendedState(MAXIMIZED_BOTH);
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

    public void iniciarMemoria() {
        new Memory().setVisible(true);
        this.dispose();
    }

    public static void main (String [] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start().setVisible(true);
            }
        });
    }
}
