package tp.pr1;

import java.util.Scanner;

public class Controller {
	//clase para controlar la ejecución del juego, preguntando al usuario qué
	//quiere hacer y actualizando la partida de acuerdo a lo que éste indique
	private Game game;
	private Scanner in;

	public Controller(Game game, Scanner in){	
		this.game = game;
		this.in = in;
	}
	
	public void run(){
		//Realiza la simulación del juego
		System.out.println(game.toString());
		String line;
		String[] words;
		System.out.print("Command > ");
		line= in.nextLine();
		words= line.split(" ");
		while(!words[0].equalsIgnoreCase("exit")&& !game.getFinish()){
			if(words[0].equalsIgnoreCase("reset")){
				game.reset();
				System.out.println(game.toString());
				System.out.println( "\n" + "Started new game." + "\n");
			}
			else if(words[0].equalsIgnoreCase("help")){
				System.out.println("Move <direction>: execute a move in one of the four directions, up, down, left, right.");
				System.out.println("Reset: start a new game");
				System.out.println("Help: this help message");
				System.out.println("Exit: terminate the program");
			}
			else if(words[0].equalsIgnoreCase("move")){
				if(words.length==1){
					System.out.println("Move must be followed by a direction: up, down, left or right");
				}
				else if(words[1].equalsIgnoreCase("up") || words[1].equalsIgnoreCase("down") || words[1].equalsIgnoreCase("left") || words[1].equalsIgnoreCase("right")){
					switch (words[1].toLowerCase()){
					case "up": game.move(Direction.up);break;
					case "down" : game.move(Direction.down);break;
					case "right" : game.move(Direction.right);break;
					case "left" : game.move(Direction.left);break;
					}
					System.out.println(game.toString());
				}
				else{
					System.out.println("Unknown direction for move command");
				}
			}
			else{
				System.out.println("Unknown command");
			}
			if(!game.getFinish()){
				System.out.print("Command > ");
				line= in.nextLine();
				words= line.split(" ");
			}
			else{
				if(game.getMax()==2048){
					System.out.println("Well done!");
				}
				else{
					System.out.println("Game Over");
				}
			}
		}
		if(!game.getFinish()){
			System.out.println("Game Over");
		}
	}

}
