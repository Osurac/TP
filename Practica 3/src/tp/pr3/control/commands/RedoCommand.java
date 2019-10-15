package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class RedoCommand extends NoParamsCommand{
	//Clase que representa el comando redo
	private final static String commandName="redo";
	private final static String helpCommand="repite el ultimo comando.";
	public RedoCommand() {
		super(commandName, helpCommand);
	}
	
	@Override
	public boolean execute(Game game) {
		//Ejecuta un redo
		boolean redo;
		redo=game.redo();
		return redo;
		//controller.setPrintGameState(redo);
		//controller.run();
	}

}
