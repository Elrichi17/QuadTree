package Arbol;

public class Node extends AbstractNode{

//attributes
	private AbstractNode[] hijos ;
	
//constructors
	public Node() {
		this.hijos = new AbstractNode[4]; ;
	}

//accessors
	public AbstractNode[] getHijos() {
		return hijos;
	}

	public void setHijos(AbstractNode[] hijos) {
		this.hijos = hijos;
	}
	

}
