package Interfaz;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;

import Arbol.*;
import Solucion.*;

public class PagPrincipal extends JFrame {

	private String imagen;
	private ArbolQT arbol;
	private BufferedImage bufferedImage;

	public PagPrincipal(String imagen) {
		this.imagen = imagen;
		try{
			initialize();
		}    catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al procesar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void initialize() throws IOException {
		this.setTitle("Quadtree Image Processor");
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		// Importante (todo lo relacionado a arbol y imagen)
		Imagen imagenATratar = new Imagen(imagen);

		// Se inicializa el arbol
		this.arbol = new ArbolQT(imagenATratar.getHeight(), imagenATratar.getWidth());

		// Código imagen mostrada
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new GridLayout(2, 1, 10, 10));
		imagePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblOriginalImage = new JLabel("Imagen Original", JLabel.CENTER);
		lblOriginalImage.setVerticalTextPosition(JLabel.BOTTOM);
		lblOriginalImage.setHorizontalTextPosition(JLabel.CENTER);
		ImageIcon imageIcon = new ImageIcon(imagen);
		Image image = imageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		lblOriginalImage.setIcon(imageIcon);
		imagePanel.add(lblOriginalImage);

		// Creación del arbol
		boolean[][] MatrizImagen = imagenATratar.getBoolArray();
		arbol.construirQuadtree(MatrizImagen);

		// Creación de la imagen a partir de la matriz
		AbstractNode raiz = arbol.getQuadtree().getRaiz();
		arbol.construirMatriz(raiz, MatrizImagen[0].length, MatrizImagen.length, 0, 0);

		JLabel lblProcessedImage = new JLabel("Imagen Procesada", JLabel.CENTER);
		lblProcessedImage.setVerticalTextPosition(JLabel.BOTTOM);
		lblProcessedImage.setHorizontalTextPosition(JLabel.CENTER);
		bufferedImage = imagenATratar.convertBooleanArrayToImage(arbol.getMatrizImagen());
		ImageIcon imageIcon2 = new ImageIcon(bufferedImage);
		Image image2 = imageIcon2.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		imageIcon2 = new ImageIcon(image2);
		lblProcessedImage.setIcon(imageIcon2);
		imagePanel.add(lblProcessedImage);

		panel.add(imagePanel, BorderLayout.CENTER);

		// Creación del árbol
		JTree tree = CrearArbol();
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setPreferredSize(new Dimension(200, 800));
		panel.add(scrollPane, BorderLayout.WEST);

		// Botón de descarga
		JButton botonDescargar = new JButton("Descargar Imagen");
	
		botonDescargar.addActionListener(e -> guardarImagen());
		panel.add(botonDescargar, BorderLayout.SOUTH);
	}
	public void guardarImagen() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Imagen");
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPEG Image", "jpg"));

		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File archivoDestino = fileChooser.getSelectedFile();
			if (!archivoDestino.getAbsolutePath().endsWith(".jpg")) {
				archivoDestino = new File(archivoDestino + ".jpg");
			}

			try {
				ImageIO.write(bufferedImage, "jpg", archivoDestino);
				JOptionPane.showMessageDialog(this, "Imagen guardada correctamente como " + archivoDestino.getName(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
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
			jRaiz.add(new DefaultMutableTreeNode("Hoja: " + ((((Hoja) raiz).isColor()) ? 'B' : 'N')));
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