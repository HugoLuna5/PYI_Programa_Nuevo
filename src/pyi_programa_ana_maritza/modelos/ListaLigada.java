package pyi_programa_ana_maritza.modelos;


/**
 * Claser ListaLigada
 * @author Dell
 */
public class ListaLigada {
    
    /**
     * Declaracion de variables
     */
    public NodoLista primer;
    public NodoLista ultimo;
    
    /**
     * Contructor de la clase
     */
    public ListaLigada(){
        primer=ultimo=null;
    }
    
    
    /**
     * Insertae nodos en la lista
     * @param in
     * @param out
     * @param peso 
     */
    public void insertaNodos(int in,int out,int peso){
        NodoLista aux,act,ant;
        ant = act = primer;
        while(act!=null && act.prio<=peso){
            ant=act;
            act=act.sigNodo;
        }
        aux=new NodoLista(in,out,peso);
        if (ant == null || ant == act)
        {
            aux.sigNodo = ant;
            primer = aux;
        }
        else
        {
            aux.sigNodo = act;
            ant.sigNodo = aux;
        }
    }
    
    /**
     * Verificar datos de la lista
     * (si es vacia)
     * @return 
     */
    public boolean esVacia() {
    	return primer==null;
    }
    
    /**
     * Eliminar primer nodo
     * @return 
     */
    public NodoLista deletePrimerNodo(){
    	if (esVacia()){
    		return null;
    	}

    	NodoLista objetoEliminado=primer;

    	if(primer==ultimo){
    		primer = ultimo = null;
    	}

    	else
    		primer=primer.sigNodo;

    	return objetoEliminado;
    }
    
}
