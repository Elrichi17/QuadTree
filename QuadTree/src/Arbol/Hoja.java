package Arbol;

public class Hoja extends AbstractNode {

	private boolean color;

	public Hoja(Comparable llave, boolean color) {
		super(llave);
		this.color = color;
	}

	public boolean isColor() {
		return color;
	}	
}
