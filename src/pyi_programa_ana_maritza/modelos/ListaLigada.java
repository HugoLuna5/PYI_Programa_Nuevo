package pyi_programa_ana_maritza.modelos;


/**
 * Claser ListaLigada
 * @author Dell
 */
public class ListaLigada {
    
    /**
     * Declaracion de variables
     */
    public ListaNodos primer;
    public ListaNodos ultimo;
    
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
        ListaNodos aux,act,ant;
        ant = act = primer;
        while(act!=null && act.prio<=peso){
            ant=act;
            act=act.sigNodo;
        }
        aux=new ListaNodos(in,out,peso);
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
    public ListaNodos deletePrimerNodo(){
    	if (esVacia()){
    		return null;
    	}

    	ListaNodos objetoEliminado=primer;

    	if(primer==ultimo){
    		primer = ultimo = null;
    	}

    	else
    		primer=primer.sigNodo;

    	return objetoEliminado;
    }
    
}
