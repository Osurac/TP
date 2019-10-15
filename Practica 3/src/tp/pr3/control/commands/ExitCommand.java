package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class ExitCommand extends NoParamsCommand{
	//Clase que representa el comando exit
	private final static String commandName="exit";
	private final static String helpCommand="terminate the program.";
	
	public ExitCommand(){
		super(commandName, helpCommand);
	}

	@Override
	public boolean execute(Game game) {
		//Genera un mensaje de partida perdida y cambia el estado de printGameState
		System.out.println("Game over......");
		return false;
	}

}
