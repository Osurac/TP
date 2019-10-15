package tp.pr1;

public class Cell {
	
	private int valor;
	
	public Cell (int valor){
		this.valor = valor;
	}
	
	public boolean isEmpty(){
		return this.getValor()==0;
	}
	
	public boolean doMerge(Cell neighbour){
		boolean merge = false;
		if(this.getValor() == neighbour.getValor()){
			merge = true;
		}
		return merge;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public void setValor(int valor){
		 this.valor = valor;
	}

}

