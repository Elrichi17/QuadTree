package Arbol;

public class Node extends AbstractNode{

	private AbstractNode[] hijos ;
	
	public Node() {
		this.hijos = new AbstractNode[4]; ;
	}

	public AbstractNode[] getHijos() {
		return hijos;
	}

	public void setHijos(AbstractNode[] hijos) {
		this.hijos = hijos;
	}
	

}
