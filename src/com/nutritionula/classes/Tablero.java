package com.nutritionula.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Angel C on 03/04/2016.
 */
public class Tablero extends JPanel {
    //array con los nombres de las banderas 8 en total para 16 pares
    private String[] band = {"cambur","durazno","fresa","mango","manzana","naranja","pinia","uvas"};

    private int fila =4;
    private int col = 4;
    private int ancho_casilla=175;

    public boolean play = false;

    int c=0;
    Casilla c1;
    Casilla c2;
    int aciertos=0;

    /** Constructor de clase */
    public Tablero(){
        super();
        //propiedades
        setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout( new java.awt.GridLayout(fila, col) );
        Dimension d= new Dimension( (ancho_casilla*col),(ancho_casilla*fila)  );
        setBackground(new Color(5,88,44));
        setSize(d);
        setPreferredSize(d);
        //crea instancias de casillas para crear el tablero
        int count=0;
        for(int i=1;i<=(fila*col);i++){
            Casilla p = new Casilla( String.valueOf(i) );
            p.setBandera( band[count] );
            count++;
            count = (count>=band.length)? 0:count++;
            p.showBandera();
            p.addMouseListener( new juegoMouseListener() );
            this.add( p );
        }
        setVisible(true);
    }

    /**
     * Inicia juegos
     * - llena las casillas con pares de banderas
     * @return no tiene
     */
    public void comenzarJuego(){
        aciertos=0;
        play=true;
        Component[] componentes = this.getComponents();
        //limpia banderas
        for( int i=0; i< componentes.length ;i++){
            ((Casilla)componentes[i]).congelarImagen(false);
            ((Casilla)componentes[i]).ocultarBandera();
            ((Casilla)componentes[i]).setBandera( "" );
        }
        //coloca nuevo orden aleatorio de banderas
        for( int i=0; i< componentes.length ;i++){
            int n = (int) (Math.random()*(band.length));
            if( !existe(band[n]) ){//comprueba que bandera no este asignada mas de 2 veces
                ((Casilla)componentes[i]).setBandera( band[n] );
            }else{
                i--;
            }
        }

    }


    /**
     * Metodo que comprueba que una casilla existe
     * int num nombre del objeto
     * @return Casilla si existe
     *         NULL si no existe
     */
    private boolean existe( String bandera ){
        int count=0;
        Component[] componentes = this.getComponents();
        for( int i=0; i<componentes.length;i++ ) {
            if( componentes[i] instanceof Casilla ) {
                if( ((Casilla)componentes[i]).getNameBandera().equals( bandera ) ) {
                    count++;
                }
            }
        }
        return (count==2)? true:false;
    }

    /**
     * Clase que implemenenta un MouseListener para la captura de eventos del mouse
     */
    class juegoMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if( play ){
                c++;//lleva la cuenta de los click realizados en las casillas
                if( c==1 ){ //primer click
                    c1=((Casilla) e.getSource()); //obtiene objeto
                    if( !c1.isCongelado() ){
                        c1.showBandera();
                        System.out.println("Primera Bandera: " + c1.getNameBandera() );
                    }else{//no toma en cuenta
                        c=0;
                    }
                }else if( c==2 && !c1.getName().equals( ((Casilla) e.getSource()).getName() ) ){//segundo click
                    c2=((Casilla) e.getSource());
                    if( !c2.isCongelado() ){
                        c2.showBandera();
                        System.out.println("Segunda Bandera: " + c2.getNameBandera() );
                        //compara imagenes
                        Animacion ani = new Animacion( c1, c2 );
                        ani.execute();
                    }
                    c=0;//contador de click a 0
                }else{ //mas de 2 clic consecutivos no toma en cuenta
                    c=0;
                }
            }else{
                System.out.println("Para jugar: FILE -> JUGAR");
            }


        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e){}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

    }

    /**
     *
     */
    class Animacion extends SwingWorker<Void, Void>{
        private Casilla casilla1;
        private Casilla casilla2;

        public Animacion(Casilla value1, Casilla value2){
            this.casilla1= value1;
            this.casilla2= value2;
        }

        @Override
        protected Void doInBackground() throws Exception {
            System.out.println("doInBackground: procesando imagenes...");
            //espera 1 segundo
            Thread.sleep( 1000 );
            if( casilla1.getNameBandera().equals( casilla2.getNameBandera() ) ){//son iguales
                casilla1.congelarImagen(true);
                casilla2.congelarImagen(true);
                System.out.println("doInBackground: imagenes son iguales");
                aciertos++;
                if( aciertos == 8 ){//win
                    System.out.println("doInBackground: Usted es un ganador!");
                    JOptionPane.showMessageDialog(null,"Usted es un ganador!");
                }
            }
            else{//no son iguales
                casilla1.ocultarBandera();
                casilla2.ocultarBandera();
                System.out.println("doInBackground: imagenes no son iguales");
            }
            return null;
        }
    }
}