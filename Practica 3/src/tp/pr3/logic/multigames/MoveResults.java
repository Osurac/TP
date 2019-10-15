package tp.pr3.logic.multigames;

public class MoveResults {
	//Clase que permite representar los resultados de la ejecución de un movimiento
	private boolean moved; // Para identificar si ha habido movimiento o fusión de baldosas
	private int points; //para almacenar el número de puntos obtenidos en el movimiento
	
	public MoveResults ( boolean move, int points){
		//Clase que crea los resultados de un movimiento a traves de unos parametros
		this.moved=move;
		this.points=points;
	}
	
	public boolean isMoved() {
		//Devuelve si se ha hecho un movimiento
		return moved;
	}

	public int getPoints() {
		//Devuelve los puntos de la partida
		return points;
	}
}
