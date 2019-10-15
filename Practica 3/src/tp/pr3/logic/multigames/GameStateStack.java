package tp.pr3.logic.multigames;

public class GameStateStack {
	//Clase que representa la pila del undo y redo
	private static final int CAPACITY=20;
	private GameState[] buffer;
	private int numeroElementos;
	
	public GameStateStack() {
		//Crea una pila
		this.buffer = new GameState[CAPACITY+1];
		this.numeroElementos = 0;
	}

	public GameState pop(){
		//Devuelve el ultimo estado almacenado
		numeroElementos--;
		return this.buffer[numeroElementos];
	}
	
	public void push(GameState state){
		//Almacena un nuevo estado
		if(numeroElementos==CAPACITY){
			for(int i=1;i<CAPACITY;i++){
				buffer[i-1]=buffer[i];
			}
			buffer[numeroElementos-1]=state;
		}
		else{
			buffer[numeroElementos]=state;
			numeroElementos++;
		}
	}
	
	public boolean isEmpty(){
		//Devuelve true si la secuencia esta vacia.
		return numeroElementos==0;
	}
}
