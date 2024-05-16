package Solucion;



import Arbol.AbstractNode;
import Arbol.Arbol;
import Arbol.Hoja;
import Arbol.Node;

public class ArbolQT {
	private Arbol Quadtree;
	private boolean [][] matrizImagen;
	private int contadorHojas;


	//private imgen

	public ArbolQT(int alto, int ancho) {
		this.Quadtree= new Arbol(null);
		this.matrizImagen= new boolean[alto][ancho];
		this.contadorHojas=0;

	}
	public Arbol getQuadtree() {
		return Quadtree;
	}
	public boolean tieneColorDiferente(boolean[][] MatrizImagen, int ancho, int alto, int x, int y) {
		boolean tieneTrue = false;
		boolean tieneFalse = false;

		for (int i = y; i < y + alto; i++) {
			for (int j = x; j < x + ancho; j++) {
				if (MatrizImagen[i][j] == false) {
					tieneFalse = true;
				} else if (MatrizImagen[i][j] == true) {
					tieneTrue = true;
				}
				if (tieneTrue && tieneFalse) {
					return true;
				}
			}
		}

		return false;
	}

	public AbstractNode construirQuadtree(boolean[][] MatrizImagen, int ancho, int alto, int x, int y) {
		if (ancho == 0 || alto == 0) {
			return null;
		}
		if (!tieneColorDiferente(MatrizImagen, ancho, alto, x, y)) {
			Hoja hoja = new Hoja(MatrizImagen[y][x]);
			contadorHojas++;
			return hoja;
		}
		int mitadAncho = ancho / 2;
		int mitadAlto = alto / 2;

		Node nodo = new Node();
		nodo.setHijoNO(construirQuadtree(MatrizImagen, mitadAncho, mitadAlto, x, y));
		nodo.setHijoNE(construirQuadtree(MatrizImagen, ancho - mitadAncho, mitadAlto, x + mitadAncho, y));
		nodo.setHijoSE(construirQuadtree(MatrizImagen, ancho - mitadAncho, alto - mitadAlto, x + mitadAncho, y + mitadAlto));
		nodo.setHijoSO(construirQuadtree(MatrizImagen, mitadAncho, alto - mitadAlto, x, y + mitadAlto));

		return nodo;
	}

	public void construirQuadtree(boolean[][] MatrizImagen) {
		Quadtree.setRaiz(construirQuadtree(MatrizImagen, MatrizImagen[0].length, MatrizImagen.length, 0, 0));
	}
	public void asignarACadaPixel(int ancho, int alto, int x, int y, boolean valor) {
	    for (int i = y; i < y + alto && i < matrizImagen.length; i++) {
	        for (int j = x; j < x + ancho && j < matrizImagen[0].length; j++) {
	            matrizImagen[i][j] = valor;
	        }
	    }
	}

	public void construirImagen(AbstractNode raiz, int ancho, int alto, int x, int y){
		if(raiz==null|| ancho == 0 || alto == 0) {
			return;
		}
		if(raiz instanceof Hoja) {
			Hoja hoja = (Hoja) raiz;
			asignarACadaPixel(ancho, alto, x, y, hoja.isColor());
		}else {
			int mitadAncho = ancho / 2;
			int mitadAlto = alto / 2;

			construirImagen(((Node) raiz).getHijos()[0], mitadAncho, mitadAlto, x, y);
			construirImagen(((Node) raiz).getHijos()[1], ancho - mitadAncho, mitadAlto, x + mitadAncho, y);
			construirImagen(((Node) raiz).getHijos()[2], ancho - mitadAncho, alto - mitadAlto, x + mitadAncho, y + mitadAlto);
			construirImagen(((Node) raiz).getHijos()[3], mitadAncho, alto - mitadAlto, x, y + mitadAlto);

		}
	}



	public int getContadorHojas() {
		return contadorHojas;
	}

	public static void main(String[] args) {

		//		Imagen converter = new Imagen("jin.jpg");
		//		boolean[][] boolArray = converter.getBoolArray();
//		boolean[][] matriz = {
//				{true, true, true, true, true, true, false, false},
//				{true, true, true, true, true, true, false, false},
//				{true, true, true, true, false, false, true, true},
//				{true, true, true, true, false, false, true, true},
//				{true, true, false, false, true, true, true, true},
//				{true, true, false, false, true, false, false, false},
//				{false, false, true, true, true, false, true, true},
//				{false, false, true, true, true, false, true, false}
//		};
		//	boolean[][] matriz = {
		//			{false, false, true, true, },
		//			{false, false, true, true, },
		//			{true, true, false, false, },
		//		    {true, true, false, false, },
		//		    
		//		};

		//		boolean[][] matriz = {
		//				{false, false, true, true, },
		//				{false, false, true, true, },
		//				{true, true, false, false, },
		//				{true, true, false, false, },
		//				{false, false, true, true, },
		//				{false, false, true, true, },
		//
		//		};
				boolean[][] matriz = {
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
						{false, false, true },
				};
		ArbolQT arbol= new ArbolQT(matriz.length,matriz[0].length);


		arbol.construirQuadtree(matriz);
		arbol.construirImagen(arbol.getQuadtree().getRaiz(),  matriz[0].length, matriz.length, 0, 0);
		arbol.getMatrizImagen();
	}
	public boolean[][] getMatrizImagen() {
		return matrizImagen;
	}



}
