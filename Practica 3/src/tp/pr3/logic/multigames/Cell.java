package tp.pr3.logic.multigames;

public class Cell {
	//Clase que representa la celda del tablero
	private int valor;
	
	public Cell (int valor){
		//Inicializa la celda con un valor
		this.valor = valor;
	}
	
	public boolean isEmpty(){
		//Comprueba si la celda esta vacia
		return this.getValor()==0;
	}
	
	public int doMerge(Cell neighbourCell, GameRules rules){
		//Comprueba si se puede hacer un merge y devuelve los puntos del merge
		int points;
		
		points=rules.merge(this, neighbourCell);
		
		return points;
	}
	
	public int getValor(){
		//Devuelve el valor de una celda
		return this.valor;
	}
	
	public void setValor(int valor){
		//Modifica el valor de una celda
		 this.valor = valor;
	}
}