package pyi_programa_ana_maritza;

import pyi_programa_ana_maritza.interfaces.Dib;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.event.ChangeEvent;
import pyi_programa_ana_maritza.algoritmos.Dijkstra;
import pyi_programa_ana_maritza.algoritmos.Floyd;
import pyi_programa_ana_maritza.modelos.Grafo;
import pyi_programa_ana_maritza.utils.TareaSleep;
import pyi_programa_ana_maritza.utils.TareaSleep2;
import pyi_programa_ana_maritza.algoritmos.Warshall;

/**
 * Vista princial, extiende las propiedades de JFrame e implementa eventos de
 * escucha en los clicks
 *
 * @author Dell
 */
public class VistaPrincipal extends JFrame implements ActionListener {

    //Declaracion de variables globales
    public PanelPersonalizado lienzo;
    public JScrollPane scroll;
    public JMenuBar menuBar;
    public JMenu archivo, algoritmos;
    public JMenuItem nuevo, abrir, guardar, salir, dijkstra, floyd, warshall;
    public Grafo grafo;
    public Font fuente;
    public JButton inAr;
    public JTextField txtEn, txtSal;
    public Dib dib;
    public Integer perm, permutacion[], menor, no, permutacionV[];
    public boolean activateDelNod = false;
    public static final String[] options = {"Si", "No"};

    /**
     * Constructor de la clase
     */
    public VistaPrincipal() {
        super("Dijkstra - Floyd - Warshall");//Colocar titulo a la ventana
        init();//inicializar componentes
    }

    /**
     * Inicializar los componentes en la vista
     */
    public void init() {
        dib = null;
        permutacion = new Integer[1000];
        permutacionV = new Integer[1000];
        grafo = new Grafo();
        fuente = new Font("Purisa", 1, 12);
        lienzo = new PanelPersonalizado(grafo);
        menuBar = new JMenuBar();
        menuBar.setFont(new Font("Purisa", 1, 12));
        inAr = new JButton("Conectar nodos");
        inAr.addActionListener(this);
 

        txtEn = new JTextField();

        /**
         * Contenedor del menu
         */
        
        
        
        
        
        /**
         * Estableces placeholder origen
         */
        txtEn = new JTextField("Escribe el nodo origen");
        txtEn.setForeground(Color.GRAY);
        txtEn.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtEn.getText().equals("Escribe el nodo origen")) {
                    txtEn.setText("");
                    txtEn.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtEn.getText().isEmpty()) {
                    txtEn.setForeground(Color.GRAY);
                    txtEn.setText("Escribe el nodo origen");
                }
            }
        });
        /**
         * Establecer placeholder destino
         */

        txtSal = new JTextField();
        txtSal = new JTextField("Escribe el nodo destino");
        txtSal.setForeground(Color.GRAY);
        txtSal.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSal.getText().equals("Escribe el nodo destino")) {
                    txtSal.setText("");
                    txtSal.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSal.getText().isEmpty()) {
                    txtSal.setForeground(Color.GRAY);
                    txtSal.setText("Escribe el nodo destino");
                }
            }
        });

        txtEn.setColumns(10);
        txtSal.setColumns(10);
        txtEn.setSize(10, txtEn.getHeight());
        txtSal.setSize(10, txtEn.getHeight());
        txtEn.addActionListener(this);
        txtSal.addActionListener(this);

        /**
         * Opciones del menu
         */
        archivo = new JMenu("Archivo");
        algoritmos = new JMenu("Algoritmos");

        archivo.setFont(new Font("Purisa", 1, 12));
        algoritmos.setFont(new Font("Purisa", 1, 12));

        nuevo = new JMenuItem("Nuevo");
        nuevo.setFont(fuente);
        abrir = new JMenuItem("Abrir");
        abrir.setFont(fuente);
        guardar = new JMenuItem("Guardar");
        guardar.setFont(fuente);
        salir = new JMenuItem("Salir");
        salir.setFont(fuente);
        dijkstra = new JMenuItem("Dijkstra");
        dijkstra.setFont(fuente);
        floyd = new JMenuItem("Floyd");
        floyd.setFont(fuente);
        warshall = new JMenuItem("Warshall");
        warshall.setFont(fuente);

        /**
         * Agregar eventos de escucha a los componentes
         */
        archivo.add(nuevo).addActionListener(this);
        archivo.add(abrir).addActionListener(this);
        archivo.add(guardar).addActionListener(this);
        archivo.add(salir).addActionListener(this);

        algoritmos.add(dijkstra).addActionListener(this);
        algoritmos.add(floyd).addActionListener(this);
        algoritmos.add(warshall).addActionListener(this);

        /**
         * Agregar opciones al menu
         */
        menuBar.add(archivo);
        menuBar.add(algoritmos);

        /**
         * Mensaje para indicar que se ingresen los nombres de los nodos para
         * crear las aristas
         */
        JLabel aris = new JLabel("Nodo inicio: ");
        JLabel aris2 = new JLabel("Nodo destino: ");
        aris.setFont(fuente);
        aris.setForeground(Color.black);
        aris2.setFont(fuente);
        aris2.setForeground(Color.black);
        menuBar.add(aris);

        //setLayout(new BorderLayout());
        /**
         * Agregar opciones al menu
         */
        menuBar.add(txtEn);
        menuBar.add(aris2);
        menuBar.add(txtSal);
        menuBar.add(inAr);
        this.setJMenuBar(menuBar);
        //agregar panel a la vista
        add(lienzo);

        this.setMinimumSize(new Dimension(700, 700));//definir el tamaño minimo de la ventana
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        lienzo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent me) {

                if (me.getButton() == MouseEvent.BUTTON1) {
                    lienzoMousePressed(me);
                }
                if (me.getButton() == MouseEvent.BUTTON2) {
                }
                if (me.getButton() == MouseEvent.BUTTON3) {
                    String opciones = (String) JOptionPane.showInputDialog(VistaPrincipal.this,
                        "Opciones",
                        "Modificar Nodos - Aristas",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                    
                    
                    switch(opciones){
                        case "Si":
                            clickMouseRightSi(me);
                            break;
                        case "No":
                            
                            break;
                    }
                }
                

                

            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent me) {
                
            }
        });

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
        }
        pack();
    }
    
    
    public void clickMouseRightSi(MouseEvent evt){
        
             
             String nod = JOptionPane.showInputDialog("Ingrese el nombre del nodo a eliminar");
             grafo.EliminarNodo(Integer.parseInt(nod));
            lienzo.paint(lienzo.getGraphics());
            //lienzo.repaint();
         
    }


    /**
     * click en la vista
     *
     * @param evt
     */
    public void lienzoMousePressed(MouseEvent evt) {
        if ((dib = lienzo.dameFigura(evt)) == null) {
            grafo.InsertaNodo(++grafo.noNodos, evt.getX(), evt.getY());
            lienzo.paint(lienzo.getGraphics());
        } else {
            dib = null;
        }
    }

    /**
     * Manejo de clicks en la vista
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nuevo) {
            grafo = new Grafo();
            lienzo.arbol = grafo;
            lienzo.nodos = grafo.nodosA;
            lienzo.repaint();
        }

        if (e.getSource() == inAr) {
            inAris();
            return;
        }
        if (e.getSource() == txtEn) {
            inAris();
            return;
        }
        if (e.getSource() == txtSal) {
            inAris();
            return;
        }

        if (e.getSource() == guardar) {
            try {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                grafo.serializar(fc.getSelectedFile());

                /**
                 * Indicar que el grafo se guardo correctamente
                 */
                JOptionPane.showMessageDialog(lienzo, "Se guardo correctamente el grafo, en la ruta: " + fc.getSelectedFile().getPath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar guardar el archivo!");
            }
            return;
        }

        /**
         * Cuando se da click en la opcion de abrir archivo
         */
        if (e.getSource() == abrir) {
            try {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                FileInputStream file = new FileInputStream(fc.getSelectedFile());
                ObjectInputStream output = new ObjectInputStream(file);
                grafo = (Grafo) output.readObject();
                lienzo.repaint();
                lienzo.nodos = grafo.nodosA;
                lienzo.arbol = grafo;
                txtEn.setText(null);
                txtSal.setText(null);
                output.close();
                JOptionPane.showMessageDialog(null, "Archivo cargado exitosamente!");
                lienzo.paint(lienzo.getGraphics());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error de archivo!");
            }
            return;
        }

        /**
         * Cuando se da click en la opcion del menu salir
         */
        if (e.getSource() == salir) {
            System.exit(0);

        }



       

        /**
         * Cuando se selecciona el algoritmo Dijkstra
         */
        if (e.getSource() == dijkstra) {
            lienzo.paint(lienzo.getGraphics());
            if (grafo.noNodos == 0) {
                return;
            }
            String[] c = new String[2];
            c[0] = "Algortimo de Dijkstra";
            String de = JOptionPane.showInputDialog("Nombre del nodo origen");
            if (de.equals("")) {
                return;
            }
            String a = JOptionPane.showInputDialog("Nombre del nodo destino");
            if (a.equals("")) {
                return;
            }
            try {
                if (grafo.exNodo(parser(de)) && grafo.exNodo(parser(a))) {
                    Dijkstra dij = new Dijkstra(grafo, parser(de), parser(a));
                    c[1] = dij.empezar();
                    TareaSleep hilo = new TareaSleep(grafo, lienzo, c);
                    hilo.start();
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de un nodo no es valido");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El nombre de un nodo no es valido");
            }
        }

        /**
         * Cuando se selecciona el algoritmo Floyd
         */
        if (e.getSource() == floyd) {
            lienzo.paint(lienzo.getGraphics());
            if (grafo.noNodos == 0) {
                return;
            }
            String[] c = new String[2];
            c[0] = "Algortimo de Floyd";
            String de = JOptionPane.showInputDialog("Nombre del nodo origen");
            if (de.equals("")) {
                return;
            }
            String a = JOptionPane.showInputDialog("Nombre del nodo destino");
            if (a.equals("")) {
                return;
            }
            try {
                if (grafo.exNodo(parser(de)) && grafo.exNodo(parser(a))) {
                    Floyd floy = new Floyd(grafo, parser(de), parser(a));
                    c[1] = floy.empezar();
                    TareaSleep hilo = new TareaSleep(grafo, lienzo, c);
                    hilo.start();
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de un nodo no es valido");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El nombre de un nodo no es valido");
            }
        }

        /**
         * Cuando se selecciona el algoritmo Warshall
         */
        if (e.getSource() == warshall) {
            lienzo.paint(lienzo.getGraphics());
            if (grafo.noNodos == 0) {
                return;
            }
            String[] c = new String[2];
            c[0] = "Algortimo de Warshall";
            String de = JOptionPane.showInputDialog("Nombre del nodo origen");
            if (de.equals("")) {
                return;
            }
            String a = JOptionPane.showInputDialog("Nombre del nodo destino");
            if (a.equals("")) {
                return;
            }
            try {
                if (grafo.exNodo(parser(de)) && grafo.exNodo(parser(a))) {
                    Warshall war = new Warshall(grafo, parser(de), parser(a));
                    c[1] = war.empezar();
                    TareaSleep hilo = new TareaSleep(grafo, lienzo, c);
                    hilo.start();
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de un nodo no es valido");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El nombre de un nodo no es valido");
            }
        }

    }

    /**
     * Generar dialogo personalizado
     *
     * @param gra
     * @param title
     */
    private void genCuadro(Grafo gra, String title) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle(title);
        PanelPersonalizado l = new PanelPersonalizado(gra);
        dialogo.add(l);
        dialogo.setSize(new Dimension(400, 300));
        dialogo.setVisible(true);
        l.paint(lienzo.getGraphics());
    }

    /**
     * Iniciar crear aristas
     */
    private void inAris() {
        if (txtEn.getText().equals("") || txtSal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione los nodos a unir");
            return;
        }
        try {
            if (parser(txtEn.getText()) == parser(txtSal.getText())) {
                JOptionPane.showMessageDialog(null, "Inserte nombres de nodos diferentes");
                return;
            }
            if (!grafo.exNodo(parser(txtEn.getText())) || !grafo.exNodo(parser(txtSal.getText()))) {
                JOptionPane.showMessageDialog(null, "Inserte nombre de nodos validos");
                return;
            }
            if (grafo.exArista(parser(txtEn.getText()), parser(txtSal.getText()))) {
                JOptionPane.showMessageDialog(null, "Estos nodos ya estan unidos");
                return;
            }
            grafo.InsertaArista(parser(txtEn.getText()), parser(txtSal.getText()));
            lienzo.paint(lienzo.getGraphics());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserte nombres de nodos validos");
        }
    }

    /**
     * Convertir tipo de dato
     *
     * @param p
     * @return
     */
    private Integer parser(String p) {
        return Integer.parseInt(p);
    }

    /**
     * Dialogo personalizado
     *
     * @param title
     * @param cad
     */
    public void dialog(String title, String cad) {
        JScrollPane scr = new JScrollPane();
        scr.setSize(300, 500);
        JDialog dialogo = new JDialog();
        dialogo.setTitle(title);
        JTextArea area = new JTextArea();
        dialogo.setSize(300, 500);
        area.setSize(300, 500);
        scroll.setViewportView(area);
        dialogo.add(scroll);
        area.setText(cad);
    }

    /**
     * Generar permuta
     *
     * @param n
     * @param j
     * @param x
     */
    public void genPermu(Integer n, Integer j, Integer[] x) {
        Integer i;

        if (j < n) {
            for (i = j; i < n; i++) {
                int t = x[i];
                x[i] = x[j];
                x[j] = t;
                genPermu(n, j + 1, x);
                t = x[i];
                x[i] = x[j];
                x[j] = t;
            }
        }

    }

    /**
     * Generar factorial
     *
     * @param n
     * @return
     */
    private long factorial(long n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * Metodo principal (Main)
     *
     * @param args
     */
   /*
     public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaPrincipal x = new VistaPrincipal();
                x.setExtendedState(VistaPrincipal.MAXIMIZED_BOTH);
                x.setVisible(true);
            }
        });
    }
    */

}
