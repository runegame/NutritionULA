package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.nutritionula.classes.Constants.*;

/**
 * Created by Angel C on 03/04/2016.
 */
public class Memory extends JFrame {
    // Opciones de la barra de menú

    private Dimension DIMENSION_EASY = new Dimension(700,700);
    private Dimension DIMENSION_MID = new Dimension(875,700);
    private Dimension DIMENSION_HIGH = new Dimension(1225,700);

    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuItemPlay;
    private JMenuItem jMenuItemLevelUp;
    private JMenuItem jMenuItemLevelDown;
    private JMenuItem jMenuItemExit;

    // Variables del tablero

    private int level;

    private Tablero tablero;

    private JPanel jPanelContenedor;

    public Memory (){
        initComponents();
        createBoard(LEVEL_EASY);
        level = LEVEL_EASY;
    }

    public void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelContenedor = new JPanel();

        jMenu = new JMenu();
        jMenuBar = new JMenuBar();
        jMenuItemPlay = new JMenuItem();
        jMenuItemLevelUp = new JMenuItem();
        jMenuItemLevelDown = new JMenuItem();
        jMenuItemExit = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        getContentPane().setBackground(new Color(5,88,44));

        getContentPane().setLayout(new GridBagLayout());

        jPanelContenedor.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        jPanelContenedor.setPreferredSize(new Dimension(700,700));

        GroupLayout containerGroupLayout = new GroupLayout(jPanelContenedor);
        jPanelContenedor.setLayout(containerGroupLayout);

        containerGroupLayout.setHorizontalGroup(
                containerGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,669,Short.MAX_VALUE)
        );

        containerGroupLayout.setVerticalGroup(
                containerGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,669,Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(jPanelContenedor,gridBagConstraints);


        jMenu.setText("Archivo");

        jMenuItemPlay.setText("Jugar");
        jMenuItemPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playActionPerformed(e);
            }
        });

        jMenuItemLevelUp.setText("Subir Dificultad");
        jMenuItemLevelUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level==LEVEL_EASY) {
                    level = LEVEL_MID;
                    levelUpActionPerformed(e);
                } else if (level == LEVEL_MID) {
                    level = LEVEL_HIGH;
                    levelUpActionPerformed(e);
                } else if (level == LEVEL_HIGH) {
                    return;
                }
            }
        });

        jMenuItemLevelDown.setText("Bajar Dificultad");
        jMenuItemLevelDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level==LEVEL_HIGH) {
                    level = LEVEL_MID;
                    levelDownActionPerformed(e);
                } else if (level == LEVEL_MID) {
                    level = LEVEL_EASY;
                    levelDownActionPerformed(e);
                } else if (level == LEVEL_EASY) {
                    return;
                }
            }
        });

        jMenuItemExit.setText("Salir");
        jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitActionPerformed(e);
            }
        });

        jMenu.add(jMenuItemPlay);
        jMenu.add(jMenuItemLevelUp);
        jMenu.add(jMenuItemLevelDown);
        jMenu.add(jMenuItemExit);
        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        pack();

        setExtendedState(MAXIMIZED_BOTH);
    }

    private void playActionPerformed(ActionEvent event) {
        tablero.comenzarJuego();
    }

    private void levelUpActionPerformed(ActionEvent event) {
        jPanelContenedor.remove(tablero);
        jPanelContenedor.repaint();
        createBoard(level);
        if (level==LEVEL_MID) {
            jPanelContenedor.setPreferredSize(DIMENSION_MID);
        } else if (level == LEVEL_HIGH) {
            jPanelContenedor.setPreferredSize(DIMENSION_HIGH);
        }
        pack();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void levelDownActionPerformed(ActionEvent event) {
        jPanelContenedor.remove(tablero);
        jPanelContenedor.repaint();
        createBoard(level);

        if (level==LEVEL_MID) {
            jPanelContenedor.setPreferredSize(DIMENSION_MID);
        } else if (level == LEVEL_EASY) {
            jPanelContenedor.setPreferredSize(DIMENSION_EASY);
        }

        pack();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void exitActionPerformed(ActionEvent event) {
        System.exit(0);
    }

    public static void main (String [] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Memory().setVisible(true);
            }
        });
    }

    public void createBoard(int level) {

        tablero = new Tablero(level);

        jPanelContenedor.add(tablero);
        jPanelContenedor.repaint();
    }
}
