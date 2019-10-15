package tp.pr3.logic.multigames;

public class Board {
	//Clase que representa el tablero de la partida
	private Cell[][] _board;
	private int _boardSize;
	
	public Board(int x){
		//Crea un tablecon con la dimension de _boardsize y inicializa las celdas
		this._boardSize = x;
		this._board = new Cell[_boardSize][_boardSize];

		for (int i = 0; i < _boardSize; i++){
			for (int j = 0; j < _boardSize; j++){
				this._board[i][j] = new Cell(0);
			}
		}
	}
	
	public void setCell(Position pos, int value){
		//Modifica el valor de la badosa de la posicion pos con el valor
		_board[pos.getFila()][pos.getColumna()].setValor(value);	
	}
	
	public int getCell(Position pos){
		//Devuelve el valor de la badosa de la posicion pos
		return _board[pos.getFila()][pos.getColumna()].getValor();
	}
	
	public MoveResults executeMove( Direction dir,GameRules rules/*Direction dir*/){
		//metodo que ejecuta las dos primeras acciones de un movimiento (desplazar y fusionar) en la direcciÃ³n dir
		// del tablero. Devuelve un objeto con los resultados del movimiento
		boolean move=false;
		int points=0;
		
	    Direction auxDirDerecha = Direction.RIGHT;
		Direction auxDirIzquierda = Direction.LEFT;
		Direction auxDirArriba = Direction.UP;
		Direction auxDirAbajo = Direction.DOWN;

		int f = 0 ;
		int c = 0;

		if(dir.equals(auxDirIzquierda) || dir.equals(auxDirArriba)){
			f = 0;
			c = 0;
		}else if(dir.equals(auxDirDerecha) || dir.equals(auxDirAbajo)){
			f = this._boardSize - 1;
			c = this._boardSize - 1;
		}
			//Hace los movimientos de up y left
			if(dir.equals(auxDirIzquierda) || dir.equals(auxDirArriba)){
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
							int result=cell.doMerge(neighbour,rules);
							if(result>0){
								move=true;
								points=points+result;
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
										move=true;
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
							move=true;
							f=posCellNeighbour.getFila();
							c=posCellNeighbour.getColumna();
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
		}else if (dir.equals(auxDirDerecha) || dir.equals(auxDirAbajo)){
		//Hace los movimientos de down y right
		while(  f > -1 ){
				Cell cell=new Cell(0);
				Position posCell=new Position(f,c);
				cell=_board[posCell.getFila()][posCell.getColumna()];
				
				if(!cell.isEmpty()){
					Cell neighbour=new Cell(0);
					//Creo un vecino para ver si esta vacio
					Position posCellNeighbour=new Position(f,c); // Cojo la poscion actual 
					posCellNeighbour=posCellNeighbour.getPosNeighbour(dir,_boardSize); //Busco en la dirección dada 
					neighbour=_board[posCellNeighbour.getFila()][posCellNeighbour.getColumna()];
					if(posCell.getFila()!= posCellNeighbour.getFila() || posCell.getColumna()!= posCellNeighbour.getColumna()){
						if(!neighbour.isEmpty()){
							int result=cell.doMerge(neighbour,rules);
							if(result>0){
								move=true;
								points=points+result;
								c=posCell.getColumna()-1;
								if(c<0){
									c=this._boardSize-1;
									f--;
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
										move=true;
									}
								}
								c = posCell.getColumna()-1;
								if(c<0){
								f--;
								c=this._boardSize-1;
								}
							}
						}
						else{
							setCell(posCellNeighbour, cell.getValor());
							setCell(posCell,0);
							move=true;
							f=posCellNeighbour.getFila();
							c=posCellNeighbour.getColumna();
						}
					}
					else{
						c--;
						if(c<0){
							f--;
							c=_boardSize - 1;
						}
					}
				}
				else{
					c--;
					if(c <0){
						f--;
						c=_boardSize - 1;
					}
				}
			  }
			}
		MoveResults result=new MoveResults(move,points);
		return result;
	}
	
	public boolean isFull(){
		//Comprueba si el tablero esta lleno
		boolean result=true;
		int i=0,j=0;
		
		while(i<this._boardSize && result){
			while(j<this._boardSize&& result){
				if(this._board[i][j].getValor()==0){
					result=false;
				}
				j++;
			}
			i++;
		}
		
		return result;
	}
	
	public boolean canDoMerge(){
		//Comprueba si se puede hacer un Merge
		boolean result=false;
		int value,value1, value2, value3, value4,i=0,j=0;
		
		while(i<this._boardSize-1&&!result){
			while(j<this._boardSize-1&&!result){
				value=this._board[i][j].getValor();
				if(value!=0){
					if(i==0){
						value1=0;
					}
					else{
						value1=this._board[i-1][j].getValor();
					}
					if(j==this._boardSize-1){
						value2=0;
					}
					else{
						value2=this._board[i][j+1].getValor();
					}
					if(i==this._boardSize-1){
						value3=0;
					}
					else{
						value3=this._board[i+1][j].getValor();
					}
					if(j==0){
						value4=0;
					}
					else{
						value4=this._board[i][j-1].getValor();
					}
					if(value==value1||value==value2||value==value3||value==value4){
						result=true;
					}
				}
				j++;
			}
			i++;
		}
		
		return result;
	}
	
	public int[][] getState(){
		//Produce la representacion compacta a partir del estado del tablero actual
		int[][] board;
		board = new int[_boardSize][_boardSize];
		
		for(int i=0;i<_boardSize;i++){
			for(int j=0;j<_boardSize;j++){
				board[i][j]=this._board[i][j].getValor();
			}
		}
		
		return board;
	}
	
	public void setState(int[][] aState){
		//Establece el estado del tablero actual a partir de la representacion compacta pasada como argumento
		for(int i=0;i<_boardSize;i++){
			for(int j=0;j<_boardSize;j++){
				this._board[i][j].setValor(aState[i][j]);
			}
		}
	}
	
	public int getBoardSize(){
		//Devuelve el tamaño del tablero
		return _boardSize;
	}
	
	@Override
	public String toString(){
		//Imprime el tablero
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