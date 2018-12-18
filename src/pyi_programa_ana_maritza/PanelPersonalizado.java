package pyi_programa_ana_maritza;

import pyi_programa_ana_maritza.modelos.NodoG;
import pyi_programa_ana_maritza.modelos.Grafo;
import pyi_programa_ana_maritza.interfaces.Dib;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pyi_programa_ana_maritza.modelos.Grafo;
import pyi_programa_ana_maritza.modelos.NodoG;

/**
 * Clase Lienzo que extiende propiedades de un Panel y eventos de Click
 * con el mouse
 * @author Dell
 */
public class PanelPersonalizado extends Panel implements MouseMotionListener{
    
    /**
     * Declaracion de variables
     */
     private URL url= getClass().getResource("imagen.jpg");
    Image image = new ImageIcon(url).getImage();
    public int c =0;
    public int xA,yA;
    public Grafo arbol;
    public LinkedList<NodoG>nodos;
    public Dib dibujandose=null;
    /**
     * Constructor de la clase
     * @param arbol 
     */
    public PanelPersonalizado(Grafo arbol){
        setBackground(Color.black);//establecer un color de fondo para el panel
        addMouseMotionListener(this);//agregar eventos de escucha para el click del mouse
        this.setPreferredSize(new Dimension(500,500));//establecer el tamaño de la ventana (Panel)
        
        nodos=arbol.nodosA;
        this.arbol = arbol;
    }

    
    /**
     * Dibujar arbol(nodos y aristas)
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0,0,940,680, this);
        arbol.pintarze(g);

    }
    
    
   
    
    
    
    /**
     * Eventos del mouse cuando el nodo sea arrastrado
     * @param e 
     */
    public void mouseDragged(MouseEvent e)
   {
      if (dibujandose == null)
        {
            // Se guardan las posiciones del ratón
            xA= e.getX();
            yA = e.getY();
            dibujandose = dameFigura(e);
            
            
        }
        else
        {
            // Si ya había empezado el arrastre, se calculan las nuevas
            // coordenadas del rectángulo
            dibujandose.setPosicion(
                dibujandose.getX() + (e.getX() - xA),
                dibujandose.getY() + (e.getY() - yA));

            // Se guarda la posición del ratón para el siguiente cálculo
            xA = e.getX();
            yA = e.getY();
            repaint();
            arbol.setMA();
            
           
        }
   }

    
    /**
     * Cuando el mouse tiene movimiento
     * @param e 
     */
    public void mouseMoved(MouseEvent e) {
        dibujandose=null;
    }

    
    /**
     * Dar figura
     * @param e
     * @return 
     */
    public  Dib dameFigura(MouseEvent e)
    {
        for (Dib figura : nodos)
        {
            if (figura.estaDentro(e.getX(), e.getY()))
            {
                return figura;
            }
        }

        return null;
    }


}
