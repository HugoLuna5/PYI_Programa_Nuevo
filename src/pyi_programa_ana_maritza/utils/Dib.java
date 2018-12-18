package pyi_programa_ana_maritza.utils;


import java.awt.Graphics;

/**
 * Interface Dibujable
 * @author Dell
 */
public interface Dib {

    public boolean estaDentro(int x,int y );

    public void setPosicion(int x, int y);

    public int getX();

    public int getY();

    public void paint(Graphics g);
}
