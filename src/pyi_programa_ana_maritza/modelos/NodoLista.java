package pyi_programa_ana_maritza.modelos;


/**
 * Clase NodoLista
 * @author Dell
 */
public class NodoLista {
    /**
     * Declaracion de las variables
     */
    public int en;
    public int sal;
    public int prio;
    public NodoLista sigNodo;
    
    /**
     * Constructor de la clase para inicializar las 
     * variables
     * @param e
     * @param s
     * @param prio 
     */
    public NodoLista(int e,int s,int prio){
        en=e;
        sal=s;
        this.prio=prio;
    }
    
}
