package Arbol;

public class Hoja extends AbstractNode {

//attributes
	private boolean color;

//constructors
	public Hoja(boolean color) {
		this.color = color;
	}

//accessors
	public boolean isColor() {
		return color;
	}	
}
