package tp.pr1;

import java.util.Random;
import java.util.Scanner;

public class Game2048 {
	public static void main(String[] args) {
	//Entrada de datos por parámetros. Recibe primero el tamaño de el tablero,
	//el numero de fichas colocadas y en caso de que haya la semilla para la aleatoriedad	
	int size = 4;
	int numInicial = 2;
	long seed = 0;
	Scanner scan = new Scanner(System.in);
	if(args.length > 0){
		 size = Integer.parseInt(args[0]);
			if(args.length > 1){
				 numInicial = Integer.parseInt(args[1]); 
				if(args.length == 2 ){
					 seed = new Random().nextInt(1000);	
				}else if (args.length > 2){	
					 seed = Long.parseLong(args[2]);
				}
		  }
	}else if (args.length == 0){
		 seed = new Random().nextInt(1000);
	}
	if(size<numInicial){
		System.out.println("Error: It can not generate a game with this arguments.");
	}
	else{
		Random rand = new Random(seed);
		Game game = new Game(size, numInicial, rand);
		Controller controller = new Controller(game, scan);
		controller.run();
	}
	}
}