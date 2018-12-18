package pyi_programa_ana_maritza.modelos;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;


/**
 * Modelo para la clase Grafo
 * @author Dell
 */
public class Grafo implements Serializable{
        //Declaracion de variables 
        public final static Integer inf=999999;
        public Integer noNodos;
        public int [][] mA;
        public boolean marcas[];
        public String salida;
        public Integer vel;
        public LinkedList <NodoG> nodosA;
        public LinkedList<Arista> aristasA,aristasB;

        public int aux = 0;
        /**
         * Contructor de la clase
         */
        public Grafo(){
            initVariables();
            init();
        }
        
        /**
         * Inicializar 
         */
        public void init(){
            for(int i = 0;i<100;i++)
                for(int j = 0;j<100;j++){
                    if(i==j)mA[i][j]=0;
                    else mA[i][j]=inf;
                }
        }

        
        /**
         * Colocar valores de la matriz
         */
        public void setMA(){
            init();
            for(NodoG no: nodosA){
                mA[0][no.id] = mA[no.id][0] =no.id;
            }
            for(Arista aris:aristasA){
                mA[0][aris.noEn.id] = mA[aris.noEn.id][0] =aris.noEn.id;
                mA[0][aris.noSal.id] = mA[aris.noSal.id][0] =aris.noSal.id;
                mA[aris.noEn.id][aris.noSal.id] = mA[aris.noSal.id][aris.noEn.id] = aris.getPeso();
            }
        }
        
        
        /**
         * Inicializar variables
         */
        public void initVariables(){
            noNodos=0;
            vel=500;
            mA = new int[100][100];
            marcas = new boolean[100];
            nodosA=new LinkedList();
            aristasA=new LinkedList();
            aristasB=new LinkedList();
        }

        
        /**
         * Insertar nodo
         * @param nodo
         * @param x
         * @param y 
         */
        public void InsertaNodo(Integer nodo,int x,int y){
            NodoG no= new NodoG(nodo,x,y);
            mA[0][nodo]=mA[nodo][0]=nodo;
            nodosA.add(no);

        }

        
        public void EliminarNodo(int nodo){
           int aux = -1;
            for (int i = 0; i < nodosA.size(); i++) {
                if(nodosA.get(i).id == nodo){
                    aux = i;
                }
                
            }
            
            if (aux != -1) {
             nodosA.remove(aux);
             mA[0][aux]=-1;
            }else{
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "El nombre del nodo no existe", "Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
            
            for (int i = 0; i < nodosA.size(); i++) {
                nodosA.get(i).id = i+1;
                
            }
            
            
        }
        
        /**
         * Insertar arista
         * @param p
         * @param q 
         */
        public void InsertaArista(int p,int q){
            Arista arista = new Arista(nodosA.get(p-1),nodosA.get(q-1));
            mA[p][q] = mA[q][p]=arista.getPeso();
            aristasA.add(arista);
            
        }
        
      

        
        /**
         * Borrar marcas
         */
        public void borraMarcas(){
        for(int i=1;i<=noNodos;i++){
            marcas[i]=false;
        }
    }

        
        /**
         * Busqyeda de la profundidad del grafo
         * @return 
         */
    public String busquedaProfundidad(){
        borraMarcas();
        salida="Recorrido de Profundidad";
        for(int k=1;k<=noNodos;k++){
            if(marcas[k]==false){
                bbp(k);
            }
        }
        return salida;
    }

    public void bbp(int k) {
        salida+="\nNodo: "+k;
        if(getGradoNodo(k)==0)aristasB.add(buscaAris(k,k));
        marcas[k]=true;
        for(int i=1;i<=noNodos;i++){
            if(mA[k][i]!=inf){
                int x=i;
                if (marcas[x]==false){
                    aristasB.add(buscaAris(k,x));
                    bbp(x);
                }
            }
        }
    }

    /**
     * Busqueda de la anchura del grafo
     * @return 
     */
    public String busquedaAnchura(){
        borraMarcas();
        salida="Recorrido de Anchura";
        for(int i=1;i<=noNodos;i++){
            if(marcas[i]==false)
                bpa(i);
        }
        return salida;
    }

    public void bpa(int i) {
        Cola cola = new Cola();
        marcas[i]=true;
                cola.encolar(i,i,0);
                while(!cola.esVacia()){
                    int x = cola.desencolar().en;
                    if(getGradoNodo(x)==0)aristasB.add(buscaAris(x,x));
                    salida+="\nNodo: "+x;
                    for(int j=1;j<=noNodos;j++){
                        if(mA[x][j]!=inf){
                            int y=j;
                            if(marcas[y]==false){
                                aristasB.add(buscaAris(x,y));
                                marcas[y]=true;
                                cola.encolar(y,y,0);
                            }
                        }
                    }
                }
    }

    
    /**
     * Buscar arista
     * @param en
     * @param sa
     * @return 
     */
        public Arista buscaAris(int en,int sa){
            for(Arista arist:aristasA){
                if((arist.noEn.id==en && arist.noSal.id==sa)||(arist.noEn.id==sa && arist.noSal.id==en)){
                    return arist;
                }
            }
            return new Arista(nodosA.get(en-1),nodosA.get(en-1));
        }

        public boolean exNodo(int k){
            
          
            
            if(mA[0][k]==inf || k>100 ||k<1)return false;
            return true;
        }

        public boolean exArista(int p,int q){
            if(mA[p][q]==inf)return false;
            return true;
        }


        /**
         * Matriz adyacencia
         * @return 
         */
        public int [][] getMatAdy(){
            int  C [][] = new int [100][100];
            for(int i=0;i<=noNodos;i++){
              System.arraycopy(mA[i], 0, C[i], 0,noNodos+1);
            }
            return C;
        }

        public void pintarze(Graphics g){
            for(NodoG n:nodosA)
                n.paint(g);

            for(Arista a:aristasA)
                a.paint(g);
        }
        
        public void setVel(int v){
            vel=v;
        }

    public int  getVel() {
        return vel;
    }

    
    /**
     * Guardar el archivo obteniendo la ruta
     * y serializar el contenido del grafo
     * @param archivo
     * @throws IOException 
     */
    public void serializar(File archivo) throws IOException{
         ObjectOutputStream sali = new ObjectOutputStream(new FileOutputStream(archivo));
         sali.writeObject(this);
    }

    
    /**
     * Obtener el grado del nodo
     * @param x
     * @return 
     */
    public int getGradoNodo(int x){
        int g=0;
        for(int i=1;i<=noNodos;i++)
            if(mA[x][i]!=inf)g++;
        return g-1;
    }

    
    /**
     * Mostrar matriz
     * @return 
     */
    public String showMA(){
        String sa="";
        for(int i=0;i<=noNodos;i++){
            for(int j=0;j<=noNodos;j++){
                if(mA[i][j]==inf)sa+="i  ";
                else
                sa+=mA[i][j]+"  ";
            }
            sa+="\n";
        }
         return sa;
    }

    
    /**
     * Obtener vector
     * @return 
     */
    public Integer [] getVectorNo(){
        Integer [] x=new Integer[noNodos+1];
        int p=0;
        for(NodoG nodo:nodosA){
            x[p++]=nodo.id;
        }
        return x;
    }

}


