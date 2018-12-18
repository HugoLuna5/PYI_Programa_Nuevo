package pyi_programa_ana_maritza.modelos;


/**
 * Clase NodoLista
 * @author Dell
 */
public class ListaNodos {
    /**
     * Declaracion de las variables
     */
    public int en;
    public int sal;
    public int prio;
    public ListaNodos sigNodo;
    
    /**
     * Constructor de la clase para inicializar las 
     * variables
     * @param e
     * @param s
     * @param prio 
     */
    public ListaNodos(int e,int s,int prio){
        en=e;
        sal=s;
        this.prio=prio;
    }
    
}
