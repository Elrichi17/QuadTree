package Solucion;



import Arbol.AbstractNode;
import Arbol.Arbol;
import Arbol.Hoja;
import Arbol.Node;

public class ArbolQT {
	private Arbol Quadtree;
	private int alto;
	private int ancho;
	


	public ArbolQT(int alto, int ancho) {
		this.Quadtree= new Arbol(null);
		this.alto = alto;
		this.ancho = ancho;
	}
	public Arbol getQuadtree() {
		return Quadtree;
	}


	public boolean tieneColorDiferente(boolean[][] MatrizImagen, int ancho, int alto, int x, int y) {
		boolean tieneTrue = false;
		boolean tieneFalse = false;
		boolean flag=false;
		for (int i = y; i < y + alto&& !(flag); i++) {
			for (int j = x; j < x + ancho; j++) {
				if (MatrizImagen[i][j] == false) {
					tieneFalse = true;
				} else if (MatrizImagen[i][j] == true) {
					tieneTrue = true;
				}
				if (tieneTrue && tieneFalse) {
					flag=true;
				}
			}
		}
		
		return flag;
	}

	public AbstractNode construirQuadtree(boolean[][] MatrizImagen, int ancho, int alto, int x, int y) {
		if (ancho == 0 || alto == 0) {
			return null;
		}
		if (!tieneColorDiferente(MatrizImagen, ancho, alto, x, y)) {
			Hoja hoja = new Hoja(MatrizImagen[y][x]);
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
	
	public void asignarACadaPixel(boolean[][] matriz, int ancho, int alto, int x, int y, boolean valor) {
	    for (int i = y; i < y + alto && i < matriz.length; i++) {
	        for (int j = x; j < x + ancho && j < matriz[0].length; j++) {
	        	matriz[i][j] = valor;
	        }
	    }
	}
	public boolean[][] construirMatriz() {
		boolean[][] matriz = new boolean[this.alto][this.ancho];
		construirMatriz(matriz, this.getQuadtree().getRaiz(), this.ancho,  this.alto,  0, 0);
		return matriz;
		
	}

	private void construirMatriz(boolean[][] matriz, AbstractNode raiz, int ancho, int alto, int x, int y){
		if(raiz==null|| ancho == 0 || alto == 0) {
			return;
		}
		if(raiz instanceof Hoja) {
			Hoja hoja = (Hoja) raiz;
			asignarACadaPixel(matriz, ancho, alto, x, y, hoja.isColor());
		}else {
			int mitadAncho = ancho / 2;
			int mitadAlto = alto / 2;

			construirMatriz(matriz, ((Node) raiz).getHijos()[0], mitadAncho, mitadAlto, x, y);
			construirMatriz(matriz, ((Node) raiz).getHijos()[1], ancho - mitadAncho, mitadAlto, x + mitadAncho, y);
			construirMatriz(matriz, ((Node) raiz).getHijos()[2], ancho - mitadAncho, alto - mitadAlto, x + mitadAncho, y + mitadAlto);
			construirMatriz(matriz, ((Node) raiz).getHijos()[3], mitadAncho, alto - mitadAlto, x, y + mitadAlto);

		}
	}
	

}
