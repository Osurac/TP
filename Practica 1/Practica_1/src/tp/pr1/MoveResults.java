package tp.pr1;

//Clase que permite representar los resultados de la ejecución de un movimiento

public class MoveResults {
	private boolean moved; // Para identificar si ha habido movimiento o fusión de baldosas
	private int points; //para almacenar el número de puntos obtenidos en el movimiento
	private int maxToken; //Para llevar el valor mas alto tras el movimiento
	
	public MoveResults (Cell[][] _board, int size){
		this.moved=false;
		this.points=0;
		this.maxToken=this.setMaxToken(_board, size);
	}
	
	public boolean setMove(){
		return this.moved=true;
	}
	
	public boolean isMoved() {
		return moved;
	}

	public int getPoints() {
		return points;
	}
	
	public void setPoints(int value){
		this.points=value;
	}
	
	public int getMaxToken(){
		return this.maxToken;
	}
	
	public int setMaxToken(Cell[][] _board, int size) {
    maxToken = getMaxToken();
		//Cell[][] board = _board.getBoard();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if( _board[i][j].getValor() > this.getMaxToken()) {
					this.maxToken = _board[i][j].getValor();
				}
			}
		}
		return maxToken;
	}
}
