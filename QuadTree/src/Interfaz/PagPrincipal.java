package Interfaz;


import java.awt.*;


import javax.swing.border.*;
import javax.swing.*;

public class PagPrincipal extends JFrame {


	private Image imagen;
public PagPrincipal(Image imagen){
	this.imagen=imagen;
	initialize();
}
public  void initialize() {
	this.setSize(817, 553);
	JPanel panel = new JPanel();
	panel.setBackground(Color.GRAY);
	panel.setForeground(Color.WHITE);
	panel.setBounds(-10, 0, 446, 263);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("");
	  // Option 1: Use the provided Image object (if available)
	 Image scaledImage = imagen.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
     lblNewLabel.setIcon(new ImageIcon(scaledImage));
     lblNewLabel.setBounds(10, 10, 296, 209);
     panel.add(lblNewLabel);
}
}
