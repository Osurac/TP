package tp.pr3.logic.multigames;

import java.util.Random;
import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Game;

public class Game2048 {
	public static void main(String[] args) throws Exception {
		//Entrada de datos por parámetros. Recibe primero el tamaño de el tablero, el numero de fichas colocadas y en caso de que haya la semilla para la aleatoriedad	
		int dim = 4;
		int initCells = 2;
		long seed = 0;
		if(args.length > 0){
			 dim = Integer.parseInt(args[0]);
				if(args.length > 1){
					 initCells = Integer.parseInt(args[1]); 
					if(args.length == 2 ){
						 seed = new Random().nextInt(1000);	
					}else if (args.length > 2){	
						 seed = Long.parseLong(args[2]);
					}
			  }
		}else if (args.length == 0){
			 seed = new Random().nextInt(1000);
		}
		if(dim<initCells){
			System.out.println("Error: It can not generate a game with this arguments.");
		}
		else{
			Controller controller = new Controller(new Game(new Rules2048(),seed, dim, initCells),new Scanner(System.in));
			controller.run();
		}
	}
}