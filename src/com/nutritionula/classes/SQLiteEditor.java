package com.nutritionula.classes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Angel C on 08/04/2016.
 */
public class SQLiteEditor extends JFrame {

    public static final int COLUMN_ID = 0;
    public static final int COLUMN_NAME = 1;
    public static final int COLUMN_DESCRIPTION = 2;
    public static final int COLUMN_CARBOHYDRATES = 3;
    public static final int COLUMN_PROTEINS = 4;
    public static final int COLUMN_LIPIDS = 5;
    public static final int COLUMN_POINTS = 6;

    public final String [] groupStrings = {"Grupo","Amarillo","Verde","Azul","Anaranjado","Gris"};

    ArrayList<Food> foods;
    Object[] data;
    SQLiteConsultor sQLiteConsultor;

    // Objetos de la interfaz grafica
    JButton buttonAdd;
    JButton buttonEdit;
    JButton buttonDelete;
    JButton buttonReturn;
    JButton buttonExit;
    JButton buttonCancel;
    JButton buttonComplete;
    JButton buttonFind;

    JTextField textId;
    JTextField textName;
    JTextField textCalories;
    JTextField textCarbohydrates;
    JTextField textProteins;
    JTextField textLipids;
    JTextField textPoints;
    JTextField textImage;

    JComboBox <String> comboGroups;

    JTextPane textDescription;

    JList<Object> list;

    boolean editState = false;

    public SQLiteEditor() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    public void initComponents() {
        sQLiteConsultor = new SQLiteConsultor();

        setBounds(0,0,500,620);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getWidth())/2,(screenSize.height-getHeight())/2);
        setVisible(true);

        JPanel mainContainer = new JPanel(null);

        list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        refreshList();

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(5,5,350,300);

        mainContainer.add(listScroller);

        buttonAdd = new JButton("Agregar");
        buttonAdd.setBounds(360,5,120,30);
        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(360,40,120,30);
        buttonDelete = new JButton("Eliminar");
        buttonDelete.setBounds(360,75,120,30);
        buttonReturn = new JButton("Regresar al Juego");
        buttonReturn.setBounds(360,240,120,30);
        buttonExit = new JButton("Salir");
        buttonExit.setBounds(360,275,120,30);
        buttonCancel = new JButton("Cancelar");
        buttonCancel.setBounds(235,460,120,30);
        buttonCancel.setEnabled(false);
        buttonComplete = new JButton("Completado");
        buttonComplete.setBounds(360,460,120,30);
        buttonComplete.setEnabled(false);
        buttonFind = new JButton("Buscar");
        buttonFind.setBounds(410,547,70,30);
        buttonFind.setEnabled(false);

        mainContainer.add(buttonAdd);
        mainContainer.add(buttonEdit);
        mainContainer.add(buttonDelete);
        mainContainer.add(buttonReturn);
        mainContainer.add(buttonExit);
        mainContainer.add(buttonCancel);
        mainContainer.add(buttonComplete);
        mainContainer.add(buttonFind);

        if (list.getSelectedIndex()==-1) {
            buttonEdit.setEnabled(false);
        }

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel labelId = new JLabel("ID:");
        JLabel labelName = new JLabel("Nombre:");
        JLabel labelDescription = new JLabel("Descripción");
        JLabel labelCalories = new JLabel("Calorias");
        JLabel labelCarbohydrates = new JLabel("Carbohidratos");
        JLabel labelProteins = new JLabel("Proteinas");
        JLabel labelLipids = new JLabel("Lipidos");
        JLabel labelPoints = new JLabel("Puntos");
        JLabel labelGroup = new JLabel("Grupo");
        JLabel labelImage = new JLabel("Imagen");

        labelId.setBounds(5,310,100,25);
        labelName.setBounds(5,340,100,25);
        labelDescription.setBounds(215,310,100,25);
        labelCalories.setBounds(5,490,100,25);
        labelCarbohydrates.setBounds(5,370,100,25);
        labelProteins.setBounds(5,400,100,25);
        labelLipids.setBounds(5,430,100,25);
        labelPoints.setBounds(5,460,100,25);
        labelGroup.setBounds(5,520,100,25);
        labelImage.setBounds(5,550,100,25);

        textId = new JTextField("ID");
        textId.setBounds(110,310,100,25);
        textId.setEnabled(false);
        mainContainer.add(textId);


        textName = new JTextField("Nombre");
        textName.setBounds(110,340,100,25);
        textName.setEnabled(false);
        mainContainer.add(textName);

        textCalories = new JTextField("Calorias");
        textCalories.setBounds(110,490,100,25);
        textCalories.setEnabled(false);
        mainContainer.add(textCalories);

        textCarbohydrates = new JTextField("Carbohidratos");
        textCarbohydrates.setBounds(110,370,100,25);
        textCarbohydrates.setEnabled(false);
        mainContainer.add(textCarbohydrates);

        textProteins = new JTextField("Proteinas");
        textProteins.setBounds(110,400,100,25);
        textProteins.setEnabled(false);
        mainContainer.add(textProteins);


        textLipids = new JTextField("Lípidos");
        textLipids.setBounds(110,430,100,25);
        textLipids.setEnabled(false);
        mainContainer.add(textLipids);

        textPoints = new JTextField("Puntos");
        textPoints.setBounds(110,460,100,25);
        textPoints.setEnabled(false);
        mainContainer.add(textPoints);

        JPanel panelDescription = new JPanel(new BorderLayout());
        panelDescription.setBorder(BorderFactory.createTitledBorder("Descripción"));
        panelDescription.setBounds(215,310,265,145);

        textImage = new JTextField("Direccion de Imagen");
        textImage.setBounds(110,550,290,25);
        textImage.setEnabled(false);
        mainContainer.add(textImage);

        textDescription = new JTextPane();
        textDescription.setEnabled(false);
        JScrollPane scrollDescription = new JScrollPane(textDescription);
        panelDescription.add(scrollDescription);

        comboGroups = new JComboBox<>(groupStrings);
        comboGroups.setBounds(110,520,100,25);
        comboGroups.setEnabled(false);

        mainContainer.add(comboGroups);

        mainContainer.add(panelDescription);

        mainContainer.add(labelId);
        mainContainer.add(labelName);
        mainContainer.add(labelDescription);
        mainContainer.add(labelCalories);
        mainContainer.add(labelCarbohydrates);
        mainContainer.add(labelProteins);
        mainContainer.add(labelLipids);
        mainContainer.add(labelPoints);
        mainContainer.add(labelGroup);
        mainContainer.add(labelImage);

        add(mainContainer,BorderLayout.CENTER);


        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (list.getSelectedIndex()==-1) {
                    buttonEdit.setEnabled(false);
                } else {
                    buttonEdit.setEnabled(true);
                    int index = list.getSelectedIndex();
                    Food foodSelected = foods.get(index);
                    textId.setText(String.format("%s",foodSelected.get_ID()));
                    textName.setText(foodSelected.getName());
                    textDescription.setText(foodSelected.getDescription());
                    textCalories.setText(String.format("%s",foodSelected.getCalories()));
                    textCarbohydrates.setText(String.format("%s",foodSelected.getCarbohydrates()));
                    textProteins.setText(String.format("%s",foodSelected.getProteins()));
                    textLipids.setText(String.format("%s",foodSelected.getLipids()));
                    textPoints.setText(String.format("%s",foodSelected.getPoints()));
                    comboGroups.setSelectedIndex(foodSelected.getGroup());
                    textImage.setText(foodSelected.getUrl());
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateButtons(true);
                textId.setText("");
                textName.setText("");
                textDescription.setText("");
                textCalories.setText("");
                textCarbohydrates.setText("");
                textProteins.setText("");
                textLipids.setText("");
                textPoints.setText("");
                editState = false;
                comboGroups.setSelectedIndex(0);
                textImage.setText("");
            }
        });

        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateButtons(true);
                editState = true;
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,"¿Desea eliminar este registro?");

                if (result == JOptionPane.YES_OPTION && list.getSelectedIndex()!= -1) {
                    sQLiteConsultor.delete(foods.get(list.getSelectedIndex()).get_ID());
                    refreshList();
                    list.setSelectedIndex(0);
                }
            }
        });

        buttonComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "insert into alimentos ( name, description, groups, calories, carbohydrates, proteins, lipids, points, image)" +
                        " VALUES ('" +
                        textName.getText()+"','" +
                        textDescription.getText()+"'," +
                        comboGroups.getSelectedIndex()+"," +
                        textCalories.getText()+"," +
                        textCarbohydrates.getText()+"," +
                        textProteins.getText()+"," +
                        textLipids.getText()+"," +
                        textPoints.getText()+", '" +
                        textImage.getText()+"');";

                if (editState) {
                    sQLiteConsultor.update("UPDATE alimentos SET "+
                            "name = '"+textName.getText()+"',"+
                            "description = '"+textDescription.getText()+"',"+
                            "groups = " + comboGroups.getSelectedIndex()+ ","+
                            "calories = " + textCalories.getText()+ ","+
                            "carbohydrates = "+textCarbohydrates.getText()+","+
                            "proteins = "+textProteins.getText()+","+
                            "lipids = "+textLipids.getText()+","+
                            "points = "+textPoints.getText()+","+
                            "image = '"+textImage.getText()+"'"+
                            " WHERE _id = '"+textId.getText()+"';");

                    stateButtons(false);
                    refreshList();
                } else {
                    sQLiteConsultor.insert("insert into alimentos ( name, description, groups, calories, carbohydrates, proteins, lipids, points, image)" +
                            " VALUES ('" +
                            textName.getText()+"','" +
                            textDescription.getText()+"'," +
                            comboGroups.getSelectedIndex()+"," +
                            textCalories.getText()+"," +
                            textCarbohydrates.getText()+"," +
                            textProteins.getText()+"," +
                            textLipids.getText()+"," +
                            textPoints.getText()+", '" +
                            textImage.getText()+"');");

                    stateButtons(false);
                    refreshList();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateButtons(false);
                list.setSelectedIndex(0);
            }
        });

        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                textImage.setText(file.getAbsolutePath());
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Start().setVisible(true);
                dispose();
            }
        });
    }

    public void refreshList() {
        foods = new ArrayList<Food>();
        try {
            ResultSet resultSet = sQLiteConsultor.query("SELECT * FROM alimentos");
            while (resultSet.next()) {
                foods.add(new Food(
                        resultSet.getInt("_ID"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("groups"),
                        resultSet.getInt("calories"),
                        resultSet.getDouble("carbohydrates"),
                        resultSet.getDouble("proteins"),
                        resultSet.getDouble("lipids"),
                        resultSet.getInt("points"),
                        resultSet.getString("image")
                ));
            }

            resultSet.close();
            sQLiteConsultor.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        data = new Object[foods.size()];

        for (int i = 0; i < data.length; i++) {
            data[i] = foods.get(i).getName();
        }

        list.setListData(data);

        list.updateUI();
    }

    public void stateButtons(boolean stateEditOrAdd) {

        textName.setEnabled(stateEditOrAdd);
        textDescription.setEnabled(stateEditOrAdd);
        textCalories.setEnabled(stateEditOrAdd);
        textCarbohydrates.setEnabled(stateEditOrAdd);
        textProteins.setEnabled(stateEditOrAdd);
        textLipids.setEnabled(stateEditOrAdd);
        textPoints.setEnabled(stateEditOrAdd);
        comboGroups.setEnabled(stateEditOrAdd);
        textImage.setEnabled(stateEditOrAdd);
        list.setEnabled(!stateEditOrAdd);

        buttonAdd.setEnabled(!stateEditOrAdd);
        buttonEdit.setEnabled(!stateEditOrAdd);
        buttonDelete.setEnabled(!stateEditOrAdd);
        buttonReturn.setEnabled(!stateEditOrAdd);
        buttonExit.setEnabled(!stateEditOrAdd);
        buttonCancel.setEnabled(stateEditOrAdd);
        buttonComplete.setEnabled(stateEditOrAdd);
        buttonFind.setEnabled(stateEditOrAdd);
    }

    public static void main(String [] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new SQLiteEditor().setVisible(true);
    }
}
