package pyi_programa_ana_maritza.algoritmos;


import pyi_programa_ana_maritza.modelos.Grafo;
import pyi_programa_ana_maritza.modelos.Arista;
import pyi_programa_ana_maritza.modelos.Cola;
import javax.swing.JOptionPane;



/**
 * Implementacion del algoritmo Dijkstra
 * @author Dell
 */
public class Dijkstra{
    //Declaracion de variables globales
    public boolean PE []=new boolean[100];
    public int C [][] = new int[100][100];
    public int PA [] = new int [100];
    public int D [] = new int [100];
    public final static int inf=999999;
    public int s,f;
    public String salida;
    public Grafo grafo;
    public Cola cola;
    public int vC[],vc=0;
    

    /**
     * Constructor para inicializar datos de la clase Dijkstra
     * @param grafo
     * @param s
     * @param f 
     */
    public Dijkstra(Grafo grafo,int s,int f){
        this.grafo=grafo;
        this.C=grafo.getMatAdy();
        this.s=s;
        this.f=f;
        vC=new int[100];
    }

    /**
     * Empezar a buscar la ruta más corta
     * @return 
     */
    public String empezar(){
        cola=new Cola();//inicializar la variable de tipo cola
        for (int i=1; i<=grafo.noNodos; i++){
            D[i] = C[s][i];
            PA[i] = s;
            PE[i] = false;
        }
        D[s]=0;
        PA[s]=s;
        cola.encolar(s,s,C[s][s]);
        
        /**
         * Recorrer la cola siempre y cuando sea distinta a vacia
         */
        while(!cola.esVacia()){
            int ww=cola.desencolar().en;
            if(!PE[ww]){
                int v = menor();
                PE[v]=true;
                for(int i=1;i<=grafo.noNodos;i++){
                    if(C[v][i]!=inf){
                        int w=i;
                        if(PE[w]==false){
                            if(D[v]+C[v][w]<=D[w]){
                                D[w]=D[v]+C[v][w];
                                PA[w]=C[0][v];
                                cola.encolar(w,w,D[w]);
                            }
                        }
                    }
                }
            }
        }
        return this.imprimeCamino();
    }

    /**
     * Buscar el valor menor
     * @return 
     */
    public int menor(){
        int menor=inf;
        int men=inf;
        for(int y=1;y<=grafo.noNodos;y++)
            if(D[y]<menor && PE[y]==false){
                menor=D[y];
                men=y;

            }
        return men;
    }

    /**
     * Imprime el camino 
     * @return 
     */
    public String imprimeCamino(){
        salida="El costo minimo de "+s+" a "+f+" es: ";
        if(D[f]!=inf)
            salida+=D[f]+"\n";
        else
            salida+="infinito";
        salida+="\n\n////Posible Camino\\\\\\\\\n";
        if(D[f]!=inf){             
            if(C[0][f]!=s)
            imprimeCamino(f);
            salida+=C[0][f]+"\n";
            vC[vc++]=C[0][f];
        }
        else{salida+="Camino para llegar de "+s+" a "+C[0][f]+"= No Existe\n";}
        genAris();
        return salida;
    }

    /**
     * Imprime el camino
     * @param v 
     */
    public void imprimeCamino(int v){
        if(PA[v]!=s){
            imprimeCamino(PA[v]);
        }
        salida+=PA[v]+"--";
        vC[vc++]=PA[v];
    }

    
    /**
     * Se obtiene las aristas
     */
    public  void genAris(){
        for(int i=1;i<vc;i++){
                    for(Arista arist : grafo.aristasA){
                        if((arist.noEn.id==vC[i]&&arist.noSal.id==vC[i-1])||(arist.noSal.id==vC[i]&&arist.noEn.id==vC[i-1]))
                            grafo.aristasB.add(arist);
                    }
            }
    }
}

