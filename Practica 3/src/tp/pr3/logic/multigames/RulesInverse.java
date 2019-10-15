package tp.pr3.logic.multigames;

import java.util.Random;

public class RulesInverse implements GameRules{
	//Clase que representa las reglas del juego 2048 inverso
	public static final int STOP_VALUE = 1;
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		//Añade un valor a una posicion del tablero
		int arrayValues[]={2048, 2048, 2048, 2048, 2048, 2048, 2048, 2048, 2048, 1024};
		int x=rand.nextInt(10);
		x=arrayValues[x];
		board.setCell(pos, x);
	}

	@Override
	public int merge(Cell self, Cell other) {
		//Comprueba si se puede hacer merge y devuelve los puntos obtenidos
		int points;
		points=0;
		if(self.getValor()==other.getValor()){
			other.setValor(self.getValor()/2);
			self.setValor(0);
			points=2048/other.getValor();
		}
		return points;
	}

	@Override
	public int getWinValue(Board board) {
		//Devuelve el mejor valor del tablero
		int min;
		min=2048;
		for(int i=0;i<board.getBoardSize();i++){
			for(int j=0;j<board.getBoardSize();j++){
				Position pos=new Position(i,j);
				if(board.getCell(pos)<min && board.getCell(pos)!=0){
					min=board.getCell(pos);
				}
			}
		}
		return min;
	}

	@Override
	public boolean win(Board board) {
		//Devuelve si se ha ganado la partida
		return getWinValue(board)==STOP_VALUE;
	}

}
