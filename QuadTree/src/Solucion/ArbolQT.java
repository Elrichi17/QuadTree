package Solucion;

import Arbol.AbstractNode;
import Arbol.Arbol;
import Arbol.Hoja;
import Arbol.Node;

public class ArbolQT {
	private Arbol Quadtree;


	public ArbolQT() {
		this.Quadtree= new Arbol(null);
		

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
	public static void main(String[] args) {
		ArbolQT arbol= new ArbolQT();
		Imagen converter = new Imagen("jin.jpg");
        boolean[][] boolArray = converter.getBoolArray();
//			boolean[][] matriz = {
//				    {true, true, true, true, true, true, false, false},
//				    {true, true, true, true, true, true, false, false},
//				    {true, true, true, true, false, false, true, true},
//				    {true, true, true, true, false, false, true, true},
//				    {true, true, false, false, true, true, true, true},
//				    {true, true, false, false, true, false, false, false},
//				    {false, false, true, true, true, false, true, true},
//				    {false, false, true, true, true, false, true, false}
//				};
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
//		boolean[][] matriz = {
//				{false, false, true },
//				{false, false, true },
//		};
		
		arbol.construirQuadtree(boolArray);
	}


}
