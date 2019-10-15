package tp.pr3.logic.multigames;
import java.util.Random;

public interface GameRules {
	//Interface que representa las reglas del juego
	void addNewCellAt(Board board, Position pos, Random rand);
		//Incorpora una nueva celda con valor aleatorio en la posicion pos del tablero board
	int merge(Cell self, Cell other);
		//Fusiona dos celdas y devuelve el numero de puntos obtenidos
	int getWinValue(Board board);
		//Devuelve el mejor valor de tablero segun las reglas del juego, comprobando si es un valor ganador y si se ha ganado el juego
	boolean win(Board board);
		//Devuelve si el juego se ha ganado o no
	//boolean lose(Board board);
		//Devuelve si el juego se ha perdido o no
	
	default boolean lose(Board board){
		//Devuelve si el juego se ha perdido o no
		return board.isFull()&&!(board.canDoMerge());
	}
	default Board createBoard(int size){
		//Crea y devuelve un tablero size*size
		return new Board(size);
	}
	default void addNewCell(Board board,Random rand){
		//Elige una posicion libre de board e invoca el metodo addNewCellAt() para añadir una celda en esa posicion
		Position posCell=new Position(0,0);
		int x = rand.nextInt(board.getBoardSize());
		posCell.setFila(x);
		x = rand.nextInt(board.getBoardSize());
		posCell.setColumna(x);
		
		while(board.getCell(posCell)!=0){
			x = rand.nextInt(board.getBoardSize());
			posCell.setFila(x);
			x = rand.nextInt(board.getBoardSize());
			posCell.setColumna(x);
		}
		
		this.addNewCellAt(board,posCell,rand);
	}
	default void initBoard(Board board, int numCells,Random rand){
		//Inicializa board eligiendo numCells posiciones libres, e invoca el metodo addNewCellAt() para añadir nuevas celdas en esas posiciones
		for(int i=0;i<numCells;i++){
			addNewCell(board,rand);
		}
	}
}
