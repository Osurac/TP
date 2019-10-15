package tp.pr3.logic.multigames;

import java.util.Random;

public class Game {
    //Representar el estado de la partida
	private Board currentBoard;
	private long currentSeed;
	private int currentSize;
	private int currentInitCells;
	private Random currentRandom;
	private int points;
	private GameStateStack undoStack;
	private GameStateStack redoStack;
	private GameRules currentRules;
	private boolean finish;

	public Game(GameRules rules ,long seed, int dim,int initCells ){
		//Crea una numeva partida a partir de unos parametros
		currentRules = rules;
		currentSeed =seed;
		currentSize= dim;
		currentInitCells = initCells;
		this.currentRandom = new Random(currentSeed);
		points=0;
		this.reset();
		undoStack = new GameStateStack();
		redoStack = new GameStateStack();
		finish=false;
		undoStack.push(this.getState());
	}
	
	@Override
	public String toString(){
		//Imprime el estado de la partida
		String game;
		
		game=this.currentBoard.toString();
		game=game+"best value: "+this.currentRules.getWinValue(currentBoard)+"                     Score: "+this.points+"\n";
		
		if(currentRules.win(currentBoard)){
			game=game+"Well done!\n";
			finish=true;
		}
		else if(currentRules.lose(currentBoard)){
			game=game+"Game over.\n";
			finish=true;
		}
		
		return game;
	}
	
	public void move(Direction dir){
		//Método que ejecuta un movimiento en la dirección dir sobre el tablero, actualizando el marcador de puntos Score y el valor más alto de las baldosas del tablero Highest.
		MoveResults result;
		
		result=this.currentBoard.executeMove(dir, currentRules);
		
		if(result.isMoved()){
			currentRules.addNewCell(currentBoard, currentRandom);
			points=points+result.getPoints();
			redoStack = new GameStateStack();
			undoStack.push(this.getState());
		}
	}
	
	public final void reset(){
		//Resetea la partida actual
		this.undoStack = new GameStateStack();
		this.redoStack = new GameStateStack();
		
		this.currentBoard = currentRules.createBoard(currentSize);
		this.currentRules.initBoard(currentBoard, currentInitCells, currentRandom);
	}
	
	public boolean undo(){
		//Pone el estado anterior del tablero
		if(!(undoStack.isEmpty())){
			System.out.println("Undoing one move...");
			redoStack.push(undoStack.pop());
			this.setState(undoStack.pop());
			return true;
		}
		else{
			System.out.println("Undo is not available");
			return false;
		}
	}
	
	public boolean redo(){
		//Pone el estado posterior del tablero
		if(!(redoStack.isEmpty())){
			System.out.println("Redoing one move...");
			undoStack.push(getState());
			this.setState(redoStack.pop());
			return true;
		}
		else{
			System.out.println("Nothing to redo");
			return false;
		}
	}
	
	public GameState getState(){
		//Devuelve el estado actual del juego
		GameState State=new GameState(this.points, this.currentBoard.getState());
		
		return State;
	}
	
	public void setState(GameState aState){
		//Restablece el juego al que determina el estado pasado como argumento
		points=aState.getScore();
		currentBoard.setState(aState.getBoardState());
	}
	public boolean getFinish(){
		//Devuelve el valor del atributo finish
		return finish;
	}
}
