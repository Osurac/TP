package tp.pr3.logic.multigames;

public class Position {
	//Clase que representa las posiciones del tablero
	private int fila;
	private int columna;
	
	public Position(int fila, int columna) {
	  this.fila = fila;
	  this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
	
    public Position getPosNeighbour(Direction dir,int boardSize){
    	Position pos = new Position(this.getFila(),this.getColumna());
    	if (dir == Direction.UP){
    		if(this.fila>0){
    			pos.setFila(this.fila-1);
    		}
    	}
    	else if (dir == Direction.DOWN){
    		if(this.fila< boardSize-1){
    			pos.setFila(this.fila+1);
    		}
    		
    	}else if (dir == Direction.RIGHT){
    		if(this.columna < boardSize-1){
    			pos.setColumna(this.columna+1);
    		}
    		
    	}else if (dir == Direction.LEFT){
    		if(this.columna>0){
    			pos.setColumna(this.columna-1);
    		}
    	}
    	return pos;
    }
}
