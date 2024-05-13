package Arbol;

public class Padre extends Nodo{
	
	private Nodo[] hijos = new Nodo[4];
	
	public void print() {
		for (Nodo n: hijos) {
			n.print();
		}
	}

}
