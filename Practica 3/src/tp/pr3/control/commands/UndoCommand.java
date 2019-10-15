package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class UndoCommand extends NoParamsCommand{
	//Clase que representa el comando undo.
	private final static String commandName="undo";
	private final static String helpCommand="deshace el ultimo comando.";
	
	public UndoCommand() {
		super(commandName,helpCommand);
	}
	
	@Override
	public boolean execute(Game game) {
		//Ejecuta un undo.
		boolean undo;
		undo=game.undo();
		return undo;
		/*controller.setPrintGameState(undo);
		controller.run();*/
	}

}
