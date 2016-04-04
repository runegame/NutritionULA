package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Angel C on 03/04/2016.
 */
public class Casilla extends JLabel {

    private int ancho=140;
    private int alto=140;
    private ImageIcon hide = new ImageIcon(getClass().getResource("/com/nutritionula/images/hide.jpg"));
    private ImageIcon bandera;
    private String sBandera="";
    private boolean congelado=false;

    /**
     * constructor de clase
     * @param name String El nomnbre de instancia
     */
    public Casilla( String name ){
        super();
        Dimension d = new Dimension(ancho,alto);
        setName(name);
        setSize( d );
        setPreferredSize( d );
        setText("");
        setIcon( hide );
        setVisible(true);
        setOpaque(true);
        setCursor(new Cursor( Cursor.HAND_CURSOR ));
    }

    /**
     * Muestra la imagen de la bandera asignada a esta casilla
     * @return no tiene
     */
    public void showBandera(){
        setIcon( bandera );
    }

    /**
     * Oculta la bandera
     * @return no tiene
     */
    public void ocultarBandera(){
        if( !congelado ){
            setIcon( hide );
        }
    }

    /**
     * Cuando una imagen es congelada, no se puede volver a ocultar hasta comenzar un nuevo juego
     * @param value boolean
     */
    public void congelarImagen(boolean value){
        this.congelado=value;
    }

    /**
     * Metodo que retorna el valor boolean de una casilla si este esta o no congelado
     * @return boolean
     */
    public boolean isCongelado(){
        return this.congelado;
    }

    /**
     * Asigna la bandera que contendra la casilla
     * @param name nombre de la bandera
     */
    public void setBandera( String name ){
        this.sBandera = name;
        if( !name.equals("") ){
            bandera = new ImageIcon(getClass().getResource("/com/nutritionula/images/"+name+".jpg"));
        }
    }

    /**
     * Retorna el nombre de la bandera que tenga asignada la casilla, si no tiene ninguna
     * retorna una cadena vacia
     * @return String
     */
    public String getNameBandera(){
        return sBandera;
    }

}
