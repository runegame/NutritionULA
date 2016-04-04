package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Angel C on 03/04/2016.
 */
public class Memory extends JFrame {
    // Opciones de la barra de menú

    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuItemPlay;
    private JMenuItem jMenuItemExit;

    // Variables del tablero

    private Tablero tablero = new Tablero();

    private JPanel jPanelContenedor;

    public Memory (){
        initComponents();

        jPanelContenedor.add(tablero);
        jPanelContenedor.repaint();
    }

    public void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelContenedor = new JPanel();

        jMenu = new JMenu();
        jMenuBar = new JMenuBar();
        jMenuItemPlay = new JMenuItem();
        jMenuItemExit = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memoria Nutritiva");
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

        jMenuItemExit.setText("Salir");
        jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitActionPerformed(e);
            }
        });

        jMenu.add(jMenuItemPlay);
        jMenu.add(jMenuItemExit);
        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        pack();

        setExtendedState(MAXIMIZED_BOTH);
    }

    private void playActionPerformed(ActionEvent event) {
        tablero.comenzarJuego();
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
}
