package tp.pr2;


public class Board {
	private Cell[][] _board;
	private int _boardSize;
	
	public Board(int x){
		this._boardSize = x;
		this._board = new Cell[_boardSize][_boardSize];
		//Crear la celda para dar el valor
		for (int i = 0; i < _boardSize; i++){
			for (int j = 0; j < _boardSize; j++){
				this._board[i][j] = new Cell(0);
			}
		}
	}
	
	public Cell[][] getBoard(){
		return _board;
	}
	
	public void setCell(Position pos, int value){
		//Modifica el valor de la badosa de la posicion pos con el valor
		_board[pos.getFila()][pos.getColumna()].setValor(value);	
	}
	
	public int getCell(Position pos){
		return _board[pos.getFila()][pos.getColumna()].getValor();
	}
	
	public MoveResults executeMove(Direction dir){
		//método que ejecuta las dos primeras acciones de un movimiento (desplazar y fusionar) en la dirección dir
		// del tablero. Devuelve un objeto con los resultados del movimiento
		MoveResults result = new MoveResults(_board,_boardSize);
		String dir2 = dir.toString();
		int f = 0;
		int c = 0;
		
		
		while(  f < _boardSize ){
			
			Cell cell=new Cell(0);
			Position posCell=new Position(f,c);
			cell=_board[posCell.getFila()][posCell.getColumna()];
			
			if(!cell.isEmpty()){
				Cell neighbour=new Cell(0);
				Position posCellNeighbour=new Position(f,c);
				posCellNeighbour=posCellNeighbour.getPosNeighbour(dir,_boardSize);
				neighbour=_board[posCellNeighbour.getFila()][posCellNeighbour.getColumna()];
				if(posCell.getFila()!= posCellNeighbour.getFila() || posCell.getColumna()!= posCellNeighbour.getColumna()){
					if(!neighbour.isEmpty()){
						if(cell.doMerge(neighbour)){
							setCell(posCellNeighbour,cell.getValor()+neighbour.getValor());
							setCell(posCell, 0);
							result.setMove();
							result.setPoints(cell.getValor()+neighbour.getValor());
							result.setMaxToken(this._board, this._boardSize);
							c=posCell.getColumna()+1;
							if(c==_boardSize){
								c=0;
								f++;
							}
						}
						else
						{	Cell neighbour2=new Cell(0);
							Position posCellNeighbour2=new Position(posCellNeighbour.getFila(),posCellNeighbour.getColumna());
							posCellNeighbour2=posCellNeighbour2.getPosNeighbour(dir,_boardSize);
							neighbour2=_board[posCellNeighbour2.getFila()][posCellNeighbour2.getColumna()];
							if(posCellNeighbour.getFila()!= posCellNeighbour2.getFila() || posCellNeighbour.getColumna()!= posCellNeighbour2.getColumna()){
								if(neighbour2.isEmpty()){
									setCell(posCellNeighbour2, neighbour.getValor());
									setCell(posCellNeighbour,cell.getValor());
									setCell(posCell,0);
									result.setMove();
								}
							}
							c = posCell.getColumna()+1;
							if(c==_boardSize){
							f++;
							c=0;
							}
						}
					}
					else{
						setCell(posCellNeighbour, cell.getValor());
						setCell(posCell,0);
						result.setMove();
						
						
						if(dir2.equalsIgnoreCase("down")){
							
							f=posCell.getFila();
							c=posCell.getColumna();
							
						}else if(dir2.equalsIgnoreCase("left")||dir2.equalsIgnoreCase("right")|| dir2.equalsIgnoreCase("up")){
							
							f=posCellNeighbour.getFila();
							c=posCellNeighbour.getColumna();
						}
					}
				}
				else{
					c++;
					if(c==_boardSize){
						f++;
						c=0;
					}
				}
			}
			else{
				
				
				c++;
				if(c ==_boardSize){
					f++;
					c=0;
				}
			}
		}
		return result;
	}
	public boolean isFull(){
		boolean result=true;
		for(int i=0;i<this._boardSize;i++){
			for(int j=0;j<this._boardSize;j++){
				if(this._board[i][j].getValor()==0){
					result=false;
				}
			}
		}
		return result;
	}
	public boolean canDoMerge(){
		boolean result=false;
		int value,value1, value2, value3, value4;
		for(int i=1;i<this._boardSize-1;i++){
			for(int j=1;j<this._boardSize-1;j++){
				value=this._board[i][j].getValor();
				if(value!=0){
					value1=this._board[i-1][j].getValor();
					value2=this._board[i][j+1].getValor();
					value3=this._board[i+1][j].getValor();
					value4=this._board[i][j-1].getValor();
					if(value==value1||value==value2||value==value3||value==value4){
						result=true;
					}
				}
			}
		}
		return result;
	}
	@Override
	public String toString(){
		String board="";
		int cellSize = 7;
		String space ="   ";
		String vDelimiter = "|";
		String hDelimiter = "-";
		for(int j=0;j<((cellSize+1)*_boardSize)-2;j++){
			if(j == 0){
			   board = board + " -";
			}
				board = board + hDelimiter;
			
		}
		board=board+"\n"; //temporalmente
		for(int i=0;i<_boardSize;i++){
		 board=board+vDelimiter;
			for(int j=0;j<_boardSize;j++){
				if(_board[i][j].getValor() != 0){
				board=board + space +_board[i][j].getValor()+ space +vDelimiter;
				}else{
					board=board + space +" "+ space +vDelimiter;
				}
			}
			board=board+"\n";
			for(int j=0;j<((cellSize+1)*_boardSize)-2;j++){
				if(j == 0){
					   board = board + " -";
					}
						board = board + hDelimiter;
			}
			board=board+"\n";
		}
		return board;
	}
}
