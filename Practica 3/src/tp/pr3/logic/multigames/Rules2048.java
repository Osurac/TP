package tp.pr3.logic.multigames;

import java.util.Random;

public class Rules2048 implements GameRules{
	//Clase que representa las reglas del juego 2048 original
	public static final int STOP_VALUE = 2048;
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		//Añade un valor a una posicion del tablero
		int arrayValues[]={2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
		int x=rand.nextInt(10);
		x=arrayValues[x];
		board.setCell(pos, x);
	}

	@Override
	public int merge(Cell self, Cell other) {
		//Comprueba si se puede hacer merge y devuelve los puntos obtenidos
		int points=0;
		if(self.getValor()==other.getValor()){
			other.setValor(self.getValor()+other.getValor());
			self.setValor(0);
			points=other.getValor();
		}
		return points;
	}

	@Override
	public int getWinValue(Board board) {
		//Devuelve el mejor valor del tablero
		int max;
		max=0;
		for(int i=0;i<board.getBoardSize();i++){
			for(int j=0;j<board.getBoardSize();j++){
				Position pos=new Position(i,j);
				if(board.getCell(pos)>max){
					max=board.getCell(pos);
				}
			}
		}
		return max;
	}

	@Override
	public boolean win(Board board) {
		//Devuelve si se ha ganado la partida
		return getWinValue(board)==STOP_VALUE;
	}

}
