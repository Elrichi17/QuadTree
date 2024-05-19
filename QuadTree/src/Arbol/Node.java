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


	public void setHijoNO(AbstractNode hijo) {
		this.hijos[0] = hijo;
	}
	public void setHijoNE(AbstractNode hijo) {
		this.hijos[1] = hijo;
	}
	public void setHijoSE(AbstractNode hijo) {
		this.hijos[2] = hijo;
	}
	public void setHijoSO(AbstractNode hijo) {
		this.hijos[3] = hijo;
	}
	

}
	