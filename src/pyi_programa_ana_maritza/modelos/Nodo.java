package pyi_programa_ana_maritza.modelos;

import pyi_programa_ana_maritza.utils.Dib;
import java.awt.*;
import java.io.Serializable;


/**
 * Clase NodoG
 * @author Dell
 */
public class Nodo implements Dib,Serializable {
    
    /**
     * Declaracion de variables
     */
    public Integer id;
    public int x;
    public int y;
    public int m;
    
    /**
     * Constructor de la clase
     * @param ca
     * @param xx
     * @param yy 
     */
    public Nodo(int ca,int xx, int yy){
        id=ca;
        x=xx;
        y=yy;
        sit();
    }

    
    /**
     * Pintar en la vista el nodo
     * @param g
     * @param color 
     */
    public void paint(Graphics g,Color color){
        g.setColor(color);
        g.fillOval(x-m, y-17, 25, 25);
        g.setColor(Color.black);
        g.drawString(id.toString(), x, y);
        g.setColor(Color.black);
        g.drawOval(x-m, y-17, 25, 25);
        
    }

    /**
     * Pintar en la vista el nodo
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x-m, y-17, 25, 25);
        g.setColor(Color.black);
        g.drawString(id.toString(), x, y);
        g.setColor(Color.black);
        g.drawOval(x-m, y-17, 25, 25);
    }

    public void sit() {
        if(id.toString().length()==1)
            m=9;
        else m=4;
    }

    public boolean estaDentro(int x, int y) {
        if (
            Math.sqrt(
                    (((this.x-m )- x) * ((this.x-m ) - x)) +
                    (((this.y-17) - y) * ((this.y-17) - y))) < 25)
        {
            return true;
        }

        return false;
    }

    public void setPosicion(int x, int y) {
        this.x=x;
        this.y=y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
