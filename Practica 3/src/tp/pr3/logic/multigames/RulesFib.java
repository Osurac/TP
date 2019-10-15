package tp.pr3.logic.multigames;

import java.util.Random;
import tp.pr3.util.MyMathsUtil;

public class RulesFib implements GameRules{
	//Clase que representa las regal del juego 2048 fibonaci
	public static final int STOP_VALUE = 144;
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		//Añade un valor a una posicion del tablero
		int arrayValues[]={1, 1, 1, 1, 1, 1, 1, 1, 1, 2};
		int x=rand.nextInt(10);
		x=arrayValues[x];
		board.setCell(pos, x);
	}

	@Override
	public int merge(Cell self, Cell other) {
		//Comprueba si se puede hacer merge y devuelve los puntos obtenidos
		int points=0;
		if(MyMathsUtil.nextFib(self.getValor())==other.getValor()){
			other.setValor(MyMathsUtil.nextFib(other.getValor()));
		}
		self.setValor(0);
		points=other.getValor();
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