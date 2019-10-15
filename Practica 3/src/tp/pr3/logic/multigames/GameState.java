package tp.pr3.logic.multigames;

public class GameState {
	//Clase que representa el estado de la partida
	private int score;
	private int[][] boardState;
	
	public GameState(int score, int[][] boardState) {
		//Crea un nuevo estado de la partia a traves de unos parametros
		this.score = score;
		this.boardState = boardState;
	}
	
	public int getScore() {
		//Devuelve los puntos de la partida
		return score;
	}
	
	public int[][] getBoardState() {
		//Devuelve el estado de la partida
		return boardState;
	}
}
