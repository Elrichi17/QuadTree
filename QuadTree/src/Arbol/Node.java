package Arbol;

public class Node<E> extends AbstractNode{

	private AbstractNode[] hijos = new AbstractNode[4];
	
	public Node(Comparable llave,AbstractNode[] hijos) {
		super(llave);
		this.hijos = hijos;
	}

	public AbstractNode[] getHijos() {
		return hijos;
	}

	public void setHijos(AbstractNode[] hijos) {
		this.hijos = hijos;
	}
	
	
}
