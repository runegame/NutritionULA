package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 * Created by Angel C on 08/04/2016.
 */
public class SQLiteConsultor {

    String ruta;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public SQLiteConsultor() {
        ruta = "C:/users/Angel C/Desktop/alimentos.s3db";
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ruta);
            statement = connection.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean insert(String sql) {
        boolean result = true;
        connect();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            result = false;
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        return result;
    }

    public boolean delete(int row) {
        boolean result = true;
        connect();
        try {
            statement.executeUpdate("DELETE FROM alimentos where _id = " + row +";");
        } catch (SQLException e) {
            result = false;
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        return result;
    }

    public boolean update(String sql) {
        boolean result = true;
        connect();

        try {
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        return result;
    }

    public ResultSet query(String sql) {
        connect();
        resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return resultSet;
    }

    public void closeConnection() {
        try {
            if (connection!= null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) {
        SQLiteConsultor sqLiteConsultor = new SQLiteConsultor();
    }
}
