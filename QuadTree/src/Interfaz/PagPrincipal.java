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
	
	//importante (todo lo relacionado a arbol y imagen)
	Imagen imagenATratar= new Imagen(imagen);
	
	// se incializa el arbol
	this.arbol= new ArbolQT(imagenATratar.getHeight(), imagenATratar.getHeight());
	
	// codigo imagen mostrada
	JLabel lblNewLabel = new JLabel("");
     lblNewLabel.setBounds(10, 10, 296, 209);
     ImageIcon imageIcon = new ImageIcon(imagen);
     Image image = imageIcon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
     imageIcon = new ImageIcon(image);
     lblNewLabel.setIcon(imageIcon);
     panel.add(lblNewLabel);
     
     
     //creacion del arbol
     boolean[][] MatrizImagen= imagenATratar.getBoolArray();
     arbol.construirQuadtree(MatrizImagen);
    arbol.getMatrizImagen();
     JTree tree = CrearArbol();
     JScrollPane scrollPane = new JScrollPane(tree);
     scrollPane.setBounds(440, 10, 453, 256); 
     panel.add(scrollPane);
     
     
 
   //nada importante, solo probando
     System.out.println(MatrizImagen.length);
     System.out.println(MatrizImagen[0].length);
     System.out.println(arbol.getContadorHojas());
 
     //creacion de la imagen a partir de la matriz
     AbstractNode raiz= arbol.getQuadtree().getRaiz();
     arbol.construirImagen(raiz, MatrizImagen[0].length, MatrizImagen.length, 0, 0);
     JLabel lblNewLabel_1 = new JLabel("");
     lblNewLabel_1.setBounds(365, 410, 296, 209);
     ImageIcon imageIcon2 = new ImageIcon(imagenATratar.convertBooleanArrayToImage(arbol.getMatrizImagen()));
     Image image2 = imageIcon2.getImage().getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
     imageIcon2 = new ImageIcon(image2);
     lblNewLabel_1.setIcon(imageIcon2);
     panel.add(lblNewLabel_1);
     
     
     
     
     
     
     
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
