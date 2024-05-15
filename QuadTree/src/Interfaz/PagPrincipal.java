package Interfaz;


import java.awt.*;


import javax.swing.border.*;

import Arbol.AbstractNode;
import Solucion.ArbolQT;
import Solucion.Imagen;

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
     
     JTree tree = new JTree();
     tree.setBounds(440, 10, 453, 256);
     panel.add(tree);
     
     
 
}
}
