package Interfaz;


import java.awt.*;


import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;

import Arbol.*;

import Solucion.*;


import javax.swing.*;

public class PagPrincipal extends JFrame {


	private String imagen;
	private ArbolQT arbol;
public PagPrincipal(String imagen){
	this.imagen=imagen;
	this.arbol=new ArbolQT();
	initialize();
}
public  void initialize() {
	this.setSize(929, 692);
	JPanel panel = new JPanel();
	panel.setBackground(Color.RED);
	panel.setForeground(Color.WHITE);
	panel.setBounds(-10, 0, 446, 263);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	Imagen imagenATratar= new Imagen(imagen);
	
	JLabel lblNewLabel = new JLabel("");
	 
	
     lblNewLabel.setBounds(10, 10, 296, 209);
     ImageIcon imageIcon = new ImageIcon(imagen);
     Image image = imageIcon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
     imageIcon = new ImageIcon(image);
     
     lblNewLabel.setIcon(imageIcon);
     panel.add(lblNewLabel);
     
     
     //crear el arbol
     boolean[][] MatrizImagen= imagenATratar.getBoolArray();
 
     arbol.construirQuadtree(MatrizImagen);
     AbstractNode raiz= arbol.getQuadtree().getRaiz();
     
     JTree tree = CrearArbol();

     // Create a JScrollPane and add the JTree
     JScrollPane scrollPane = new JScrollPane(tree);
     scrollPane.setBounds(440, 10, 453, 256); // Adjust scroll pane position and size

     panel.add(scrollPane);
     
 
}
public JTree CrearArbol() {
    AbstractNode raiz = arbol.getQuadtree().getRaiz();
    if (raiz != null) {
        DefaultMutableTreeNode jRaiz = new DefaultMutableTreeNode(raiz);
        return new JTree(crearSubArbol(raiz, jRaiz));
    } else {
        
        return new JTree();
    }
}

private DefaultMutableTreeNode crearSubArbol(AbstractNode raiz, DefaultMutableTreeNode jRaiz) {
    if (raiz instanceof Hoja) {
        jRaiz.add(new DefaultMutableTreeNode("Hoja: " + ((((Hoja) raiz).isColor())?'B':'N')));
    } else {
        Node node = (Node) raiz;
        jRaiz.add(new DefaultMutableTreeNode("Padre"));

       
        for (int i = 0; i < 4; i++) {
            if (node.getHijos()[i] != null) {
                jRaiz.add(crearSubArbol(node.getHijos()[i], new DefaultMutableTreeNode("Node " + (i + 1))));
            } else {
                jRaiz.add(new DefaultMutableTreeNode("Null"));
            }
        }
    }
    return jRaiz;
}
}
