package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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

        String path = "/com/nutritionula/images/banner.jpg";
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(url);

//        JLabel label = new JLabel("some text");
//        label.setIcon(icon);

        getContentPane().setLayout(new BorderLayout());
        titleLabel = new JLabel();
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(1366,400));
        titleLabel.setIcon(icon);
//        titleLabel.setBackground(Color.BLUE);
//        titleLabel.setOpaque(true);
        getContentPane().add(titleLabel,BorderLayout.NORTH);

        startContainer = new JPanel(new GridLayout(5,1,10,10));
        startContainer.setOpaque(true);
        startContainer.setBackground(Color.WHITE);
        JLabel jLabel1 = new JLabel("Memoria Nutritiva");
        JLabel jLabel2 = new JLabel("Arma Tu Plato");
        JLabel jLabel3 = new JLabel("Conoce Los Alimentos");
        JLabel jLabel4 = new JLabel("Sopa de Letras");
        JLabel jLabel5 = new JLabel("El Ahorcado");
        JLabel jLabel6 = new JLabel("Salir");

        jLabel1.setOpaque(true);
        jLabel2.setOpaque(true);
        jLabel3.setOpaque(true);
        jLabel4.setOpaque(true);
        jLabel5.setOpaque(true);
        jLabel6.setOpaque(true);

        jLabel1.setBackground(Color.WHITE);
        jLabel2.setBackground(Color.WHITE);
        jLabel3.setBackground(Color.WHITE);
        jLabel4.setBackground(Color.WHITE);
        jLabel5.setBackground(Color.WHITE);
        jLabel6.setBackground(Color.WHITE);

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel1.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel2.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel3.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel4.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel5.setFont(new Font("Mickey",Font.PLAIN,30));
        jLabel6.setFont(new Font("Mickey",Font.PLAIN,30));

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        jLabel1.setCursor(cursor);
        jLabel4.setCursor(cursor);
        jLabel5.setCursor(cursor);
        jLabel6.setCursor(cursor);

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initMemory();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel1.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel1.setBackground(Color.WHITE);
            }
        });

        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel2.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel2.setBackground(Color.WHITE);
            }
        });

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setBackground(Color.WHITE);
            }
        });

        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initWordSearch();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel4.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel4.setBackground(Color.WHITE);
            }
        });

        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initElAhorcado();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setBackground(Color.WHITE);
            }
        });

        jLabel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel6.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel6.setBackground(Color.WHITE);
            }
        });

        startContainer.add(jLabel1);
        startContainer.add(jLabel2);
        startContainer.add(jLabel3);
        startContainer.add(jLabel4);
        startContainer.add(jLabel5);
        startContainer.add(jLabel6);

        getContentPane().add(startContainer,BorderLayout.CENTER);

        setExtendedState(MAXIMIZED_BOTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode()==123) {
                    initSQLiteEditor();
                }
            }
        });
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

    public void initMemory() {
        new Memory().setVisible(true);
        this.dispose();
    }

    public void initWordSearch() {
        new WordSearch();
        this.dispose();
    }

    public void initElAhorcado() {
        new ElAhorcado().setVisible(true);
        this.dispose();
    }

    public void initSQLiteEditor() {
        new SQLiteEditor();
        this.dispose();
    }

    public static void main (String [] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start().setVisible(true);
            }
        });
    }
}
