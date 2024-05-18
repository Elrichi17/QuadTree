package Interfaz;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Inicio extends JFrame  {
	private JLabel lblImagen;

	public Inicio() {
		initialize();



	}



	public void initialize() {
		this.setSize(500, 300);
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setForeground(Color.WHITE);
		panel.setBounds(-10, 0, 446, 263);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Seleccione una imagen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
				int selection = fileChooser.showOpenDialog(null); 

				if (selection == JFileChooser.APPROVE_OPTION) {
					File chosenFile = fileChooser.getSelectedFile();

					String fileName = chosenFile.getName();
					String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				
					if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                        try {
                            JOptionPane.showMessageDialog(null, "Imagen seleccionada exitosamente!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            PagPrincipal pagprincipal = new PagPrincipal(chosenFile.getPath());
                            pagprincipal.setVisible(true);
                            setVisible(false);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al abrir la imagen seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El archivo seleccionado no es una imagen válida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
				}
			}

		});
		btnNewButton.setBounds(146, 105, 173, 21);
		panel.add(btnNewButton);

	}


	public static void main(String[] args) {
		Inicio interfaz= new Inicio();
		interfaz.setVisible(true);
	}
}
