package tp.pr2;
import java.util.Random;

public class Game {
    //Representar el estado de la partida
	public static final int N = 2048;
	private Board _board;
	private int _size;
	private int _initCells;
	private Random _myRandom;
	private boolean finalizada;	
	private int Score;
	private int Max;

	public Game(int dimension, int baldosasIniciales, Random random ){
		this._size = dimension;
		this._initCells = baldosasIniciales;
		this._myRandom = random;
		this.finalizada=false;
		this._board = new Board(_size);
		this.Score=0;
		this.Max=0;
		for(int i=0;i<_initCells; i++){
			Position posCell=new Position(0,0);
			int x = this._myRandom.nextInt(_size);
			posCell.setFila(x);
			x = this._myRandom.nextInt(_size);
			posCell.setColumna(x);
			while(_board.getCell(posCell)!=0){
				x = this._myRandom.nextInt(_size);
				posCell.setFila(x);
				x = this._myRandom.nextInt(_size);
				posCell.setColumna(x);
			}
			int arrayPos[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
			x = random.nextInt(10);
			x = arrayPos[x];
			_board.setCell(posCell, x);
			if(x>this.Max){
				this.Max=x;
			}
		}
	}
	
	@Override
	public String toString(){
		String game;
		game=this._board.toString();
		game=game+"highest: "+this.Max+"                     Score: "+this.Score+"\n";
		return game;
	}
	
	public void move(Direction dir){
		//método que ejecuta un movimiento en la dirección
		//dir sobre el tablero, actualizando el marcador de puntos Score y el valor
		//más alto de las baldosas del tablero Highest.
		MoveResults result;
		result=this._board.executeMove(dir);
		if(result.isMoved()){
			Score=Score+result.getPoints();
			Max=result.getMaxToken();
			Position posCell=new Position(0,0);
			int x = _myRandom.nextInt(_size);
			posCell.setFila(x);
			x = _myRandom.nextInt(_size);
			posCell.setColumna(x);
			while(_board.getCell(posCell)!=0){
				x = this._myRandom.nextInt(_size);
				posCell.setFila(x);
				x = this._myRandom.nextInt(_size);
				posCell.setColumna(x);
			}
			int arrayPos[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
			x = this._myRandom.nextInt(10);
			x = arrayPos[x];
			_board.setCell(posCell, x);
		}
		finalizada=((this._board.isFull()&&!this._board.canDoMerge())||Max==N);
	}
	public void reset(){
		//iniciliza la partida actual
		this._board = new Board(_size);
		this.Score=0;
		this.Max=0;
		for(int i=0;i<_initCells; i++){
			Position posCell=new Position(0,0);
			int x = this._myRandom.nextInt(_size);
			posCell.setFila(x);
			x = this._myRandom.nextInt(_size);
			posCell.setColumna(x);
			final int arrayPos[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
			x = this._myRandom.nextInt(10);
			x = arrayPos[x];
			_board.setCell(posCell, x);
			if(x>this.Max){
				this.Max=x;
			}
		}
	}
	public boolean getFinish(){
		return this.finalizada;
	}
	public int getMax(){
		return this.Max;
	}
}
