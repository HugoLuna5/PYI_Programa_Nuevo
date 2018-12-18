package pyi_programa_ana_maritza.utils;


import pyi_programa_ana_maritza.modelos.Grafo;
import pyi_programa_ana_maritza.modelos.Arista;
import javax.swing.*;
import pyi_programa_ana_maritza.PanelPersonalizado;

/**
 * Hilos para tareas con sleep (retrazar tareas por un tiempo definido)
 * @author Dell
 */

public class Sleep extends Thread{
    
    /**
     * Declaracion de variables
     */
    public Grafo arbol;
    public PanelPersonalizado lienzo;
    public String c[];
    
    /**
     * Constructor de la clase
     * @param arbol
     * @param lienzo
     * @param cad 
     */
    public Sleep(Grafo arbol, PanelPersonalizado lienzo,String[] cad){
        this.arbol = arbol;
        this.lienzo = lienzo;
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
            sleep(100);
        } catch (InterruptedException ex) {
        }
        arbol.aristasB.clear();
        dialog(c[0],c[1]);
    }

    
    /**
     * Mostrar cuadro de dialogo personalizado
     * @param title
     * @param cad 
     */
    public void dialog(String title,String cad){
        JScrollPane scr = new JScrollPane();
        scr.setSize(300,500);
        JDialog dialogo = new JDialog();
            dialogo.setTitle(title);
            JTextArea area = new JTextArea();
            dialogo.setSize(300,500);
            area.setSize(300,500);
            scr.setViewportView(area);
            dialogo.add(scr);
            area.setText(cad);
            dialogo.setVisible(true);
    }
}
