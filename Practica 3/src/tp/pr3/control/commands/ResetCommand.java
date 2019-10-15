package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class ResetCommand extends NoParamsCommand{
	//Clase que representa el comando reset.
	private final static String commandName="reset";
	private final static String helpCommand="start a new game.";
	
	public ResetCommand(){
		super(commandName, helpCommand);
	}
	
	@Override
	public boolean execute(Game game) {
		//Ejecuta un reseteo de la partida con los mismos parametros.
		game.reset();
		return true;
		/*controller.setGame(game);
		controller.setPrintGameState(true);
		controller.run();*/
	}
}
