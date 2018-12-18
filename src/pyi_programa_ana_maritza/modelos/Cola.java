package pyi_programa_ana_maritza.modelos;



/**
 * Modelo de datos para la Cola
 * @author Dell
 */
public class Cola {
    
    /**
     * Atributos de la clase (Modelo)
     */
    public ListaLigada lista;
    
    /**
     * Constructor de la clase
     */
    public Cola(){
        lista=new ListaLigada();
    }
    
    
    /**
     * Verificar s ela lista esta vacia
     * @return 
     */
    public boolean esVacia(){
        return lista.esVacia();
    }
    
    
    /**
     * Insertar nodos
     * @param e
     * @param s
     * @param pr 
     */
    public void encolar(int e,int s,int pr){
        lista.insertaNodos(e, s, pr);
    }
    
    
    /**
     * Eliminar nodos
     * @return 
     */
    public ListaNodos desencolar(){
        return lista.deletePrimerNodo();
    }
}
