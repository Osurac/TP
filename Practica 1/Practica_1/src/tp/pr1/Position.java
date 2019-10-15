package tp.pr1;

public class Position {
	
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

	@Override
	public String toString() {
		return "Posicion [fila=" + fila + ", columna=" + columna + "]";
	}
	
    public Position getPosNeighbour(Direction dir,int boardSize){
    	Position pos = new Position(this.getFila(),this.getColumna());
    	if (dir == Direction.up){
    		if(this.fila>0){
    			pos.setFila(this.fila-1);
    		}
    	}
    	else if (dir == Direction.down){
    		if(this.fila< boardSize-1){
    			pos.setFila(this.fila+1);
    		}
    		
    	}else if (dir == Direction.right){
    		if(this.columna < boardSize-1){
    			pos.setColumna(this.columna+1);
    		}
    		
    	}else if (dir == Direction.left){
    		if(this.columna>0){
    			pos.setColumna(this.columna-1);
    		}
    	}
    	return pos;
    }
}
