package pyi_programa_ana_maritza.modelos;

import java.awt.*;
import java.io.Serializable;
import javax.swing.UIManager.*;

/**
 * Modelo de datos para las Aristas
 * @author Dell
 */
public class Arista implements Serializable{
    public Nodo noEn;
    public Nodo noSal;

    
    /**
     * Constructor de la clase
     * @param in
     * @param out 
     */
    public Arista(Nodo in,Nodo out){
        noEn=in;
        noSal=out;
    }

    /**
     * Dibujar sobre la vista para pinta las aristas
     * recibiendo el dato g de tipo Graphics
     * @param g 
     */
    public void paint(Graphics g){
        
        /**
         * Establecer tamaño de la arista
         */
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(3);
        ((Graphics2D)g).setStroke(stroke);
        
        g.setColor(Color.yellow);//Se coloca el color de la arista
        g.drawLine(noEn.x+4, noEn.y, noSal.x+4, noSal.y);//se mapean las coordenadas en la vista para dibujar la arista
        g.drawString(distancia(),(noEn.x+noSal.x)/2,(noEn.y+noSal.y)/2);//se dibuja el nombre de la arista
        
        /**
         * Se dibujan
         */
        noEn.paint(g);
        noSal.paint(g);
    }

    
    /**
     * Dibujar sobre la vista con una linea roja
     * la arista que fue eliminada
     * @param g 
     */
    public void marcarEliminada(Graphics g){
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(4);
        ((Graphics2D)g).setStroke(stroke);
        g.setColor(Color.red);//se coloca el color 
        g.drawLine(noEn.x+4, noEn.y, noSal.x+4, noSal.y);//se establecen las coordenadas
         g.setColor(Color.white);//se coloca el color 
        g.drawString("Se elimino esta arista",(noEn.x+noSal.x)/2,(noEn.y+noSal.y)/2);//se muestra el nombre

        
    }
    
    
    /**
     * Dibujar sobre la vista para pintar las aristas 
     * recibiendo el dato g de tipo Graphics
     * para dibujar la ruta más corta
     * @param g 
     */
    public void marcar(Graphics g){
         ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(4);
        ((Graphics2D)g).setStroke(stroke);
        g.setColor(Color.green);//se coloca el color 
        g.drawLine(noEn.x+4, noEn.y, noSal.x+4, noSal.y);//se establecen las coordenadas
        g.drawString(distancia(),(noEn.x+noSal.x)/2,(noEn.y+noSal.y)/2);//se muestra el nombre
        
        /**
         * Se dibuja en la vista
         */
        noEn.paint(g,Color.green);
        noSal.paint(g,Color.green);
    }
    
    
    /**
     * Se calcula la distancia dependiendo las coordenadas donde se dio el click
     * para obtener X, Y de un punto de inicio y final
     * 
     * @return 
     */
    private String distancia(){
        int x =(int) Math.sqrt(Math.pow(noEn.x-noSal.x, 2)+Math.pow(noEn.y-noSal.y, 2));
        return String.valueOf(x);
    }
    
    
    /**
     * Obtener el peso de la arista
     * @return 
     */
    public int  getPeso(){
        return Integer.parseInt(distancia());
    }
    
    public Nodo getNoEn() {
        return noEn;
    }

    public void setNoEn(Nodo noEn) {
        this.noEn = noEn;
    }

    public Nodo getNoSal() {
        return noSal;
    }

    public void setNoSal(Nodo noSal) {
        this.noSal = noSal;
    }
    
    
    
}
