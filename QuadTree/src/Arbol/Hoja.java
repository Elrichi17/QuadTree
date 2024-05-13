package Arbol;

public class Hoja extends AbstractNode {

	private boolean color;

	public Hoja(boolean color) {
		this.color = color;
	}

	public boolean isColor() {
		return color;
	}	
}
