package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.control.commands.*;
import tp.pr3.control.exceptions.CommandNoMatch;
import tp.pr3.logic.multigames.Game;

public class Controller {
	//clase para controlar la ejecución del juego, preguntando al usuario qué
	//quiere hacer y actualizando la partida de acuerdo a lo que éste indique
	private Game game;
	private Scanner in;
	private boolean printGameState;

	public Controller(Game game, Scanner in){	
		this.game = game;
		this.in = in;
		this.printGameState=true;
	}
	
	public void run() throws Exception{
		//Realiza la simulación del juego
		if(isPrintGameState()){
			System.out.println(game.toString());
		}
		
		if(!(game.getFinish())){
			String line;
			String[] words;
			System.out.print("Command > ");
			line= in.nextLine();
			words= line.split(" ");
			Command command = null;
			try{
			 command=CommandParser.parseCommand(words);
			
			}
			catch(CommandNoMatch excepcion){
	
				System.out.print("Command > ");
				line= in.nextLine();
				words= line.split(" ");
				command=CommandParser.parseCommand(words);
			}
			finally{
				this.printGameState = command.execute(game);
				this.setGame(game);
				this.run();
			}
		}
	}

	public boolean isPrintGameState() {
		return printGameState;
	}
	
	
	public void setGame(Game gameNew){
		game=gameNew;
	}
}
