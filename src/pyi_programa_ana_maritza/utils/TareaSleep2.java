package pyi_programa_ana_maritza.utils;

import pyi_programa_ana_maritza.modelos.Grafo;
import pyi_programa_ana_maritza.modelos.Arista;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import pyi_programa_ana_maritza.PanelPersonalizado;

/**
 * Hilos para tareas con sleep (retrazar tareas por un tiempo definido)
 * @author Dell
 */
public class TareaSleep2 extends Thread{
    
    /**
     * Declaracion de variables
     */
    public Grafo arbol,arbol2;
    public PanelPersonalizado lienzo;
    public String c[];
    
    /**
     * Constructor de la clase
     * @param arbol
     * @param graf
     * @param lienzo
     * @param cad 
     */
    public TareaSleep2(Grafo arbol,Grafo graf, PanelPersonalizado lienzo,String[] cad){
        this.arbol = arbol;
        this.lienzo = lienzo;
        arbol2=graf;
        c=cad;
    }

    /**
     * Ejecutar tareas depues de un tipo
     */
    @SuppressWarnings("SleepWhileHoldingLock")
    @Override
    public void run(){
        for(Arista actual: arbol.aristasB){
            try {
                actual.marcar(lienzo.getGraphics());
                sleep(arbol.getVel());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
        }
        arbol.aristasB.clear();
        genCuadro(arbol2,c[1]);
    }
    
    
    /**
     * Generar un cuadro de dialogo personalizado
     * @param gra
     * @param title 
     */
    public void genCuadro(Grafo gra,String title){
        JDialog dialogo= new JDialog();
        dialogo.setTitle(title);
        PanelPersonalizado l = new PanelPersonalizado(gra);
        dialogo.add(l);
        dialogo.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        dialogo.setVisible(true);
        l.paint(lienzo.getGraphics());
    }
}
